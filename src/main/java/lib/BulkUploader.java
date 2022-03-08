package lib;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.web3j.protocol.core.methods.response.TransactionReceipt;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import contracts.Healthify;
import debugtest.Prognosis;
import debugtest.PrognosisNotFoundException;
import debugtest.Symptom;

public class BulkUploader {
	private CSVReader reader;
	private String filepath;
	private Healthify contract;
	
	public BulkUploader(String filepath, Healthify healthifyContract) {
		FileReader fileReader;
		try {
			this.filepath = filepath;
			this.contract = healthifyContract;
			fileReader = new FileReader(this.filepath);
			reader = new CSVReaderBuilder(fileReader).
							withSkipLines(1).build();
		} catch (FileNotFoundException e) {
			System.out.println("Could not locate file to read at path: " + filepath);
		}
	}
	
	public List<CompletableFuture<TransactionReceipt>> bulkUploadToBlockchainAsync() {
		List<CompletableFuture<TransactionReceipt>> futures = new 
				ArrayList<CompletableFuture<TransactionReceipt>>();
		
		String[] nextRecord;
		List<BigInteger> symptoms = null;
		String prognosis = "";
		try {
			while ((nextRecord = reader.readNext()) != null) {
			    symptoms = new ArrayList<BigInteger>();
				int col_num = 1;
				for (String cell : nextRecord) {
					var symptomID = col_num;
					
			        
			        try {
			        	if(Integer.parseInt(cell) == 1) {
			        		// Add this symptom to the list
			        		symptoms.add(BigInteger.valueOf(symptomID));
			        	}			        	
			        } catch(NumberFormatException e) {
			        	prognosis = cell;
			        }

			        col_num++;
				}
				
				if(symptoms != null) {
					BigInteger prognosisInt;
					try {
						prognosisInt = Prognosis.getIdByName(prognosis);
						var future = contract.addNewRecord(symptoms, prognosisInt).sendAsync();
						futures.add(future);
					} catch (PrognosisNotFoundException e) {
						System.out.println(e);
					}
				}
			}
			
		} catch (CsvValidationException e) {
			System.out.println("User defined validation failed for : " + filepath);
		} catch (IOException e) {
			System.out.println("Error reading the csv file at : " + filepath);
		}
		
		return futures;
	}
	
	public void bulkUploadToBlockchain() {	
		String[] nextRecord;
		List<BigInteger> symptoms = null;
		String prognosis = "";
		try {
			while ((nextRecord = reader.readNext()) != null) {
			    symptoms = new ArrayList<BigInteger>();
				int col_num = 1;
				for (String cell : nextRecord) {
					var symptomID = col_num;
					
			        
			        try {
			        	if(Integer.parseInt(cell) == 1) {
			        		// Add this symptom to the list
			        		symptoms.add(BigInteger.valueOf(symptomID));
			        	}			        	
			        } catch(NumberFormatException e) {
			        	prognosis = cell;
			        }

			        col_num++;
				}
				
				if(symptoms != null) {
					BigInteger prognosisInt;
					try {
						prognosisInt = Prognosis.getIdByName(prognosis);
						contract.addNewRecord(symptoms, prognosisInt).send();
					} catch (PrognosisNotFoundException e) {
						System.out.println("Error in finding prognosis: " + e);
					} catch (Exception e) {
						System.out.println(e);
					}
				}
			}
			
		} catch (CsvValidationException e) {
			System.out.println("User defined validation failed for : " + filepath);
		} catch (IOException e) {
			System.out.println("Error reading the csv file at : " + filepath);
		}
	}
	
	public void readLineByLine() {
		String[] nextRecord;
		try {
			while ((nextRecord = reader.readNext()) != null) {
			    for (String cell : nextRecord) {
			        System.out.print(cell + "\t");
			    }
			    System.out.println();
			}
		} catch (CsvValidationException e) {
			System.out.println("User defined validation failed for : " + filepath);
		} catch (IOException e) {
			System.out.println("Error reading the csv file at : " + filepath);
		}	
	}
	
	// Cleanup
	public void finalize() {
		try {
			reader.close();
		} catch (IOException e) {
			System.out.println("Closing csv reader for the file at [" + this.filepath + "] failed!");
			e.printStackTrace();
		}
	}
	
}
