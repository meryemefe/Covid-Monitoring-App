package com.bilkent.covidmonitoringservice.request;

import com.bilkent.covidmonitoringservice.entity.Symptom;

import java.time.LocalDate;

public class SymptomRequest {

    private Long userId;
    private LocalDate date;

    private boolean fever;
    private boolean dryCough;
    private boolean tiredness;
    private boolean lossOfTaste;
    private boolean lossOfSmell;
    private boolean soreThroat;
    private boolean headache;

    public Symptom toSymptom() {
        Symptom symptom = new Symptom();
        symptom.setUserId(this.userId);
        symptom.setDate(this.date);
        symptom.setFever(this.fever);
        symptom.setDryCough(this.dryCough);
        symptom.setTiredness(this.tiredness);
        symptom.setLossOfTaste(this.lossOfTaste);
        symptom.setLossOfSmell(this.lossOfSmell);
        symptom.setSoreThroat(this.soreThroat);
        symptom.setHeadache(this.headache);
        return symptom;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isFever() {
        return fever;
    }

    public void setFever(boolean fever) {
        this.fever = fever;
    }

    public boolean isDryCough() {
        return dryCough;
    }

    public void setDryCough(boolean dryCough) {
        this.dryCough = dryCough;
    }

    public boolean isTiredness() {
        return tiredness;
    }

    public void setTiredness(boolean tiredness) {
        this.tiredness = tiredness;
    }

    public boolean isLossOfTaste() {
        return lossOfTaste;
    }

    public void setLossOfTaste(boolean lossOfTaste) {
        this.lossOfTaste = lossOfTaste;
    }

    public boolean isLossOfSmell() {
        return lossOfSmell;
    }

    public void setLossOfSmell(boolean lossOfSmell) {
        this.lossOfSmell = lossOfSmell;
    }

    public boolean isSoreThroat() {
        return soreThroat;
    }

    public void setSoreThroat(boolean soreThroat) {
        this.soreThroat = soreThroat;
    }

    public boolean isHeadache() {
        return headache;
    }

    public void setHeadache(boolean headache) {
        this.headache = headache;
    }
}
