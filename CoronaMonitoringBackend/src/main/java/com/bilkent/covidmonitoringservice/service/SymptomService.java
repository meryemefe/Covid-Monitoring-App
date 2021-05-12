package com.bilkent.covidmonitoringservice.service;

import com.bilkent.covidmonitoringservice.entity.Symptom;
import com.bilkent.covidmonitoringservice.repository.SymptomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import java.util.List;
import java.util.Optional;

@Service
public class SymptomService {

    @Autowired
    private SymptomRepository symptomRepository;

    public Symptom addDailySymptom(Symptom symptomToAdd){

        Optional<Symptom> optionalSymptom = symptomRepository.findByUserIdAndDate(symptomToAdd.getUserId(), symptomToAdd.getDate());
        if (optionalSymptom.isPresent())
            return null;

        return symptomRepository.save(symptomToAdd);
    }

    public Symptom updateDailySymptom(Symptom symptomToUpdate){

        Optional<Symptom> optionalSymptom = symptomRepository.findById(symptomToUpdate.getId());
        if (optionalSymptom.isEmpty())
            return null;

        symptomToUpdate.setUserId(optionalSymptom.get().getUserId());
        symptomToUpdate.setDate(optionalSymptom.get().getDate());

        return symptomRepository.save(symptomToUpdate);
    }

    public void deleteDailySymptom(Symptom symptom){
        symptomRepository.delete(symptom);
    }

    public Symptom getSymptomById(Long id){
        Optional<Symptom> symptom = symptomRepository.findById(id);
        return symptom.orElse(null);
    }

    public List<Symptom> getSymptomsByUserId(Long userId){
        return symptomRepository.findAllByUserIdOrderByDateDesc(userId);
    }

    public String getEmergencyStatus(Long userId){

        List<Symptom> symptoms = getSymptomsByUserId(userId);

        if (symptoms.size() == 0){
            return "You don't have any symptom record!";
        }

        Symptom latestSymptom = symptoms.get(0);
        LocalDate today = LocalDate.now();
        long daysBetween = ChronoUnit.DAYS.between(latestSymptom.getDate(), today);
        if ( daysBetween != 0) {
            return "You did not enter any symptom for today. Please, add your daily symptom.";
        }

        int symptomCount = 0;
        if (latestSymptom.isFever())
            symptomCount++;
        if (latestSymptom.isDryCough())
            symptomCount++;
        if (latestSymptom.isTiredness())
            symptomCount++;
        if (latestSymptom.isLossOfTaste())
            symptomCount++;
        if (latestSymptom.isLossOfSmell())
            symptomCount++;
        if (latestSymptom.isSoreThroat())
            symptomCount++;
        if (latestSymptom.isHeadache())
            symptomCount++;

        if (symptomCount >= 3) {
            return "You should see a doctor!";
        }
        return "You don't need to see a doctor for now. Take care of yourself!";
    }

}
