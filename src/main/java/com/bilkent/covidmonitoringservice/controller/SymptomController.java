package com.bilkent.covidmonitoringservice.controller;

import com.bilkent.covidmonitoringservice.entity.Symptom;
import com.bilkent.covidmonitoringservice.request.SymptomRequest;
import com.bilkent.covidmonitoringservice.request.SymptomUpdateRequest;
import com.bilkent.covidmonitoringservice.service.SymptomService;
import com.bilkent.covidmonitoringservice.util.AppResponse;
import com.bilkent.covidmonitoringservice.util.AppResponses;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/symptoms")
public class SymptomController {

    @Autowired
    private SymptomService symptomService;

    @ApiOperation("Add a new daily symptom record")
    @PostMapping
    public AppResponse<Symptom> addSymptom(@RequestBody SymptomRequest request) {

        Symptom symptomToAdd = request.toSymptom();
        Symptom symptom = symptomService.addDailySymptom(symptomToAdd);
        if (symptom == null){
            return AppResponses.failure("There is already a symptom record for this day.");
        }
        return AppResponses.from(symptom);
    }

    @ApiOperation("Update symptom record by given id")
    @PutMapping
    public AppResponse<Symptom> updateSymptom(@RequestBody SymptomUpdateRequest request) {

        Symptom symptomToUpdate = request.toSymptom();
        Symptom symptom = symptomService.updateDailySymptom(symptomToUpdate);
        if (symptom == null){
            return AppResponses.failure("There is no such a symptom record.");
        }
        return AppResponses.from(symptom);
    }

    @ApiOperation("Delete symptom by given id")
    @DeleteMapping("/{id}")
    public AppResponse<String> deleteSymptom(@PathVariable Long id) {

        Symptom symptom = symptomService.getSymptomById(id);
        if (symptom == null){
            return AppResponses.failure("There is no such a symptom record.");
        }
        symptomService.deleteDailySymptom(symptom);
        return AppResponses.from("Symptom is deleted.");
    }

    @ApiOperation("Get symptom by given id")
    @GetMapping("/{id}")
    public AppResponse<Symptom> getSymptom(@PathVariable Long id){

        Symptom symptom = symptomService.getSymptomById(id);
        if (symptom == null){
            return AppResponses.failure("There is no such a symptom record.");
        }
        return AppResponses.from(symptom);
    }

    @ApiOperation("Get all symptoms of the user")
    @GetMapping("/all/{userId}")
    public AppResponse<List<Symptom>> getAllSymptoms(@PathVariable Long userId){

        List<Symptom> symptoms = symptomService.getSymptomsByUserId(userId);
        return AppResponses.from(symptoms);
    }

    @ApiOperation("Get current emergency status of the user")
    @GetMapping("/status/{userId}")
    public AppResponse<String> getEmergencyStatus(@PathVariable Long userId){
        return AppResponses.from("Not implemented yet.");
    }


}
