package healthify.server;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	private static final int SYMPTOM_COUNT = 131;
	private static Healthify contract;
	private static Web3j web3;
	
	static {
		BlockChain.init();
		contract = BlockChain.getDeployedContract();
		web3 = BlockChain.getWeb3Instance();
	}
	
	@SuppressWarnings("finally")
	public static String GetBlocks(int blocksCount) {
		System.out.println("Requested " + blocksCount + " blocks.");
		String csvData = createHeaderRow();
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
			
			System.out.println("There are " + medicalRecordList.size() + " records currently retrieved from the blockchain.");
			
			for (MedicalRecord record : medicalRecordList) {
				List symptomsList = record.listAllSymptoms().send();
				BigInteger prognosis = record.getPrognosis().send();

				String tempCsvDataString = createCSVString(symptomsList, Prognosis.getNameById(prognosis));
				csvData += tempCsvDataString;
			}
		}
		catch(Exception e) {
			System.out.println("Exception thrown while getting records: " + e.getMessage());
		} finally {
			return csvData;
		}
	}
	
	
	
	private static String createHeaderRow() {
		String data = "";
		for (int symptomID = 1; symptomID <= SYMPTOM_COUNT; symptomID++) {
			data += Symptom.getNameById(symptomID);
			if (symptomID != SYMPTOM_COUNT) {
				data += ",";
			} else {
				data += ",Prognosis\n";
			}
		}
		return data;
	}



	private static String createCSVString(List symptomsList, String prognosis) {
		String data = "";
		Set<BigInteger> symptomSet = new HashSet<BigInteger>(symptomsList);
		for(int symptomID = 1; symptomID <= SYMPTOM_COUNT; symptomID++) {
			if(symptomSet.contains(BigInteger.valueOf(symptomID))) {
				data += '1';
			} else {
				data += '0';
			}
			
			if (symptomID != SYMPTOM_COUNT) {
				data += ",";
			} else {
				data += String.format(",%s\n", prognosis);
			}
		}
//		System.out.println(data);
		return data;
	}
	
}