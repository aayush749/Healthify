package healthifylib;

import java.math.BigInteger;

public class Prognosis {
	private static String[] prognosis = {
			"null",
	        "(vertigo) paroymsal  positional vertigo",
	        "acne",
	        "AIDS",
	        "alcoholic hepatitis",
	        "allergy",
	        "arthritis",
	        "bronchial asthma",
	        "cervical spondylosis",
	        "chicken pox",
	        "chronic cholestasis",
	        "common cold",
	        "dengue",
	        "diabetes",
	        "dimorphic hemmorhoids(piles)",
	        "drug reaction",
	        "fungal infection",
	        "gastroenteritis",
	        "gerd",
	        "heart attack",
	        "hepatitis a",
	        "hepatitis b",
	        "hepatitis c",
	        "hepatitis d",
	        "hepatitis e",
	        "hypertension",
	        "hyperthyroidism",
	        "hypoglycemia",
	        "hypothyroidism",
	        "impetigo",
	        "jaundice",
	        "malaria",
	        "migraine",
	        "osteoarthristis",
	        "paralysis (brain hemorrhage)",
	        "peptic ulcer diseae",
	        "pneumonia",
	        "psoriasis",
	        "tuberculosis",
	        "typhoid",
	        "urinary tract infection",
	        "varicose veins"	
	};

	public static String getNameById(int id) {
		String prognosisName = prognosis[id].replace("_", " ");
		String[] words = prognosisName.split(" ");
		String consolidatedPrognosisName = "";
		for(var word : words) {
			
			consolidatedPrognosisName += word.substring(0, 1).toUpperCase() + word.substring(1) + " ";
		}
		
		return consolidatedPrognosisName.strip();
	}
	
	public static String getNameById(BigInteger id) {
		return getNameById(id.intValue());
	}

	public static BigInteger getIdByName(String prognosis_query) throws PrognosisNotFoundException{
		if (prognosis_query.equals("AIDS")) {
			return BigInteger.valueOf(3);
		} else {
			var lowerProgElem = prognosis_query.toLowerCase();
			for(int i = 1; i < prognosis.length; i++) {
				if(lowerProgElem.equals(prognosis[i].trim())) {
					return BigInteger.valueOf(i);
				}
			}
		}
		throw new PrognosisNotFoundException(
				String.format("The prognosis [%s] was not found!",
						prognosis_query));
	}
}
