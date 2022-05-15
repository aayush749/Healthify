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
import healthifylib.BlockCache;
import healthifylib.BlockChain;
import healthifylib.HealthifyBlock;
import healthifylib.Prognosis;
import healthifylib.SimpleBlockCache;
import healthifylib.Symptom;

public class BlockChainReader extends Thread {
	private static final int SYMPTOM_COUNT = 131;
	private static Healthify contract;
	private static Web3j web3;
	private static BlockCache cache;
	
	static {
		BlockChain.init();
		contract = BlockChain.getDeployedContract();
		web3 = BlockChain.getWeb3Instance();
		cache = new SimpleBlockCache();
	}
	
	public static String GetBlocks(int blocksCount) {
		return GetBlocks(blocksCount, 2);
	}
	
	@SuppressWarnings("finally")
	public static String GetBlocks(int blocksCount, int start_block) {
		start_block--;
		
		System.out.println("Requested " + blocksCount + " blocks.");
		if (start_block + blocksCount - 1 <= cache.curCachedBlocksNum()) {
			System.out.println("Blocks found in cache, returning from cache");
			return cache.getNBlocks(blocksCount, start_block);
		}
		else {	// all or some blocks not found in the cache
			int numBlocksToFetch = blocksCount - (cache.curCachedBlocksNum() - start_block + 1);
			int numBlocksInCache = blocksCount - numBlocksToFetch;
			System.out.println(String.format(
					"Blocks not found in cache, fetching %d block(s) from blockchain", 
					numBlocksToFetch));
			
			String csvData = cache.getNBlocks(numBlocksInCache, start_block);
			
			// move the start_block count by one more than the number of blocks already in the cache
			start_block = start_block + numBlocksInCache + 1;
			try {
				int cur_rec_idx = start_block == 0 ? 0 : start_block-1;	//The index of current record
				Credentials credentials = BlockChain.getCredentialsFromPrivateKey();
				ContractGasProvider gasProvider = new DefaultGasProvider();
				// Create an alias for the contract object
				var healthify = contract;
				while(true) {
					String address = healthify.viewMedicalRecordNumber(BigInteger.valueOf(cur_rec_idx)).send();
					if(!address.contains("0x00") && (cur_rec_idx - 1) < blocksCount) {
						MedicalRecord record = MedicalRecord.load(address, web3, credentials, gasProvider);
						List symptomsList = record.listAllSymptoms().send();
						BigInteger prognosis = record.getPrognosis().send();
						
						// add to csvData
						csvData += createCSVString(symptomsList, Prognosis.getNameById(prognosis));
						
						// cache this block
						cache.cacheBlock(new HealthifyBlock(symptomsList, 
															Prognosis.getNameById(prognosis)
														    )
										);
						
						// increment the current record number counter index
						cur_rec_idx++;
					}
					else {
						break;
					}
				}
			}catch(Exception e) {
				System.out.println("Exception thrown while getting records: " + e.getMessage());
			} finally {
				return csvData;
			}
		}
	}		
	
	public static String createHeaderRow() {
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



	public static String createCSVString(List symptomsList, String prognosis) {
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