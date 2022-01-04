pragma solidity ^0.6.0;

import "./MedicalRecord.sol"

contract Healthify {
    MedicalRecord[] private medicalDB = [MedicalRecord().setPID(0)]

    function addNewRecord(MedicalRecord.Symptoms[] symptoms, bool prognosis) {
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


}

