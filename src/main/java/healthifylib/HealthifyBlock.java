package healthifylib;

import java.util.List;

import healthify.server.BlockChainReader;

public class HealthifyBlock implements Block {
	private static String m_HeaderRow;
	private String m_CsvString;
	
	HealthifyBlock(List symptomsList, String prognosis) {
		if (m_HeaderRow.isBlank()) {
			m_HeaderRow = BlockChainReader.createHeaderRow();			
		}
		if (m_CsvString.isBlank()) {
			m_CsvString = BlockChainReader.createCSVString(symptomsList, prognosis);
		}
	}
	
	@Override
	public String getCSVString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getBlockHeaderCSV() {
		return m_HeaderRow;
	}

}
