package healthifylib;

import java.util.List;

import healthify.server.BlockChainReader;

public class HealthifyBlock implements Block {
	private static String m_HeaderRow;
	private String m_CsvString;
	
	private List m_SymptomsList;
	private String m_Prognosis;
	
	HealthifyBlock(List symptomsList, String prognosis) {
		m_SymptomsList = symptomsList;
		m_Prognosis = prognosis;
	}
	
	@Override
	public String getCSVString() {
		if (m_CsvString.isBlank()) {
			m_CsvString = BlockChainReader.createCSVString(
															m_SymptomsList,
															m_Prognosis
															);
		}
		return m_CsvString;
	}

	@Override
	public String getBlockHeaderCSV() {
		if (m_HeaderRow.isBlank()) {
			m_HeaderRow = BlockChainReader.createHeaderRow();			
		}
		return m_CsvString;
	}

}
