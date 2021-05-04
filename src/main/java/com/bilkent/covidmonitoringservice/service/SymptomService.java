package com.bilkent.covidmonitoringservice.service;

import com.bilkent.covidmonitoringservice.entity.Symptom;
import com.bilkent.covidmonitoringservice.repository.SymptomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
