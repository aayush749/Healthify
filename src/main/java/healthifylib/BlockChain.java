package healthifylib;

import java.math.BigInteger;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;

import contracts.Healthify;

/*
 * This class manages/store the state of the BlockChain. Use this to get information regarding the blockchain.
 */
public class BlockChain {
	private final static String PRIVATE_KEY = "424a140f0dacffeec3d2ddfbeaf5ad920350b654a0614bc6dd6af06a12a6d129";
	private final static BigInteger GAS_LIMIT = BigInteger.valueOf(6721975L);
	private final static BigInteger GAS_PRICE = BigInteger.valueOf(20000000000L);
	
	private static Web3j web3;
	private static Healthify healthify;
	
	private static boolean isSetUp = false;
	
	public static void init() {
		if (!isSetUp) {
			web3 = Web3j.build(new HttpService("HTTP://127.0.0.1:7545"));
			
			if (ConfigManager.exists()) {
				// check the file
				// if contract is deployed then load it else 
				// deploy the contract and set the info in the configuration file
				System.out.println("Contract already deployed!");
				System.out.println("Loading pre deployed contract...");
				healthify = Healthify.load(getExistingContractAddress(), web3, 
						getCredentialsFromPrivateKey(), new DefaultGasProvider());
				if(healthify != null) {
					System.out.println("Contract loaded successfully!");				
				} else {
					System.out.println("Error loading pre-deployed contract!");
				}
			} else {
				// deploy the contract and set the info in the configuration file
				System.out.println("Deploying contract..");
				healthify = deployContract();
				
				var newContractAddress = healthify.getContractAddress();
				ConfigManager.writeContractAddress(newContractAddress);
				
				System.out.println("Deployed Contract!");
			}
			isSetUp = true;
		} else {
			System.out.println("BlockChain set up already performed!");
		}
	}
	
	public static Web3j getWeb3Instance() {
		if(!isSetUp) {
			init();
		}
		return web3;
	}
	
	public static Healthify getDeployedContract() {
		if(!isSetUp) {
			init();
		}
		return healthify;
	}
	
	public static String getExistingContractAddress() {
		String address = ConfigManager.getContractAddressFromConfigFile();
		
		return address;
	}
	
	public static Credentials getCredentialsFromPrivateKey() {
		return Credentials.create(PRIVATE_KEY);
	}
	
	public static Healthify deployContract() {
		if(!isSetUp) {
			init();
		}
		try {
			healthify = Healthify.deploy(web3, getCredentialsFromPrivateKey(), new DefaultGasProvider()).send();
			return healthify;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
