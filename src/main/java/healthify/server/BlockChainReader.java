package healthify.server;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

import contracts.Healthify;
import contracts.MedicalRecord;
import healthifylib.BlockChain;
import healthifylib.Prognosis;
import healthifylib.Symptom;

public class BlockChainReader extends Thread {
	private static Healthify contract;
	private static Web3j web3;
	
	static {
		BlockChain.init();
		contract = BlockChain.getDeployedContract();
		web3 = BlockChain.getWeb3Instance();
	}
	
	public static void GetBlocks(int blocksCount) {
		System.out.println("Requested " + blocksCount + " blocks.");
		
		try {
			ArrayList<MedicalRecord> medicalRecordList = new ArrayList<MedicalRecord>();
			
			int cur_rec_num = 0;
			Credentials credentials = BlockChain.getCredentialsFromPrivateKey();
			ContractGasProvider gasProvider = new DefaultGasProvider();
			// Create an alias for the contract object
			var healthify = contract;
			while(true) {
				String address = healthify.viewMedicalRecordNumber(BigInteger.valueOf(cur_rec_num)).send();
				if(!address.contains("0x00") && cur_rec_num < blocksCount) {
					medicalRecordList.add(MedicalRecord.load(address, web3, credentials, gasProvider));
					cur_rec_num++;
				}
				else {
					break;
				}
			}
			
			System.out.println("There are " + medicalRecordList.size() + " records stored currently in the blockchain.");
			
			for (MedicalRecord record : medicalRecordList) {
				List symptomsList = record.listAllSymptoms().send();
				for (var symptomId : symptomsList) {
					System.out.print(Symptom.getNameById((BigInteger)symptomId) + ", ");
				}
				System.out.print("\nPrognosis: " + Prognosis.getNameById(record.getPrognosis().send()));
				System.out.println("\n====");
			}
			
			
		}
		catch(Exception e) {
			System.out.println("Exception thrown while getting records: " + e.getMessage());
		}
	}
	
}