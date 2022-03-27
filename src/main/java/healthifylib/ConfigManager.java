package healthifylib;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class ConfigManager {
	private static String m_ConfigDirPath = "src/main/java/config/";
	private static boolean m_Exists = false;
	static {
		Path configPath = Paths.get(m_ConfigDirPath + "deployement_status.txt");
		m_Exists = Files.exists(configPath); 
	}
	
	public static boolean exists() {
		return m_Exists;
	}
	
	public static void writeContractAddress(String address) {
		try {
			FileWriter writer = new FileWriter(m_ConfigDirPath + "deployement_status.txt");
			writer.write(address);
			writer.close();
		} catch (IOException e) {
			System.out.println("Error opening config file! Address not written");
		}
	}
	
	public static String getContactAddressFromConfigFile() {
		String address = null;
		try {
			FileReader reader = new FileReader(m_ConfigDirPath + "deployement_status.txt");
			
			int sampleAddressLength = "0x6B12f54cb0630c22f05a151796966BFC631736ef".length();
			
			char[] buffer = new char[sampleAddressLength];
			reader.read(buffer, 0, sampleAddressLength);
			reader.close();

			address = new String(buffer);
		} catch (FileNotFoundException e) {
			System.out.println("Error opening config file!");
		} catch (IOException e) {
			System.out.println("Error reading from config file!");
		}
	
		return address;
	}
}
