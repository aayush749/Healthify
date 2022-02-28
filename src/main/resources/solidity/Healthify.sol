pragma solidity ^0.8.0;

import "./MedicalRecord.sol";

contract Healthify {
    MedicalRecord[] private medicalDB;
    string[] prognosisChart = [
        "null",
        "(vertigo) paroymsal  positional vertigo",
        "acne",
        "aids",
        "alcoholic hepatitis",
        "allergy",
        "arthiritis",
        "bronchial asthma",
        "cervical spondylosis",
        "chicken pox",
        "chronic cholestasis",
        "common cold",
        "dengue",
        "diabetes",
        "diamorhpic hemmorhoids(piles)",
        "drug reaction",
        "fungal infection",
        "gastrenteretis",
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
        "osteoarthiristis",
        "paralysis (brain hemorrhage)",
        "peptic ulcer disea",
        "pnuemonia",
        "psoriasis",
        "tuberculosis",
        "typhoid",
        "urinary tract infection",
        "varicose veins"
    ];

    constructor() public {
        MedicalRecord dummyRecord = new MedicalRecord();
        dummyRecord.setPID(0);
        medicalDB.push(dummyRecord);
    }

    function addNewRecord(Symptoms[] memory symptoms, uint prognosis) public {
        MedicalRecord record = new MedicalRecord();

        // set all the symptoms field
        for(uint256 i = 0; i < symptoms.length; i++) {
            record.setSymptomField(symptoms[i]);
        }

        // finally set the prognosis, once the symptoms have been set
        record.setPrognosis(prognosis);


        // now push the record
        medicalDB.push(record);
    }

    function viewMedicalRecordNumber(uint recordNumber) public view returns(MedicalRecord){
        MedicalRecord reqRecord;
        if (recordNumber < medicalDB.length)
            reqRecord = medicalDB[recordNumber];
        
        return reqRecord;
    }
}