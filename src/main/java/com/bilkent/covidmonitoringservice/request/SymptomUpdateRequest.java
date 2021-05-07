package com.bilkent.covidmonitoringservice.request;

import com.bilkent.covidmonitoringservice.entity.Symptom;

public class SymptomUpdateRequest {

    private Long id;

    private boolean fever;
    private boolean dryCough;
    private boolean tiredness;
    private boolean lossOfTaste;
    private boolean lossOfSmell;
    private boolean soreThroat;
    private boolean headache;

    public Symptom toSymptom() {
        Symptom symptom = new Symptom();
        symptom.setId(this.id);
        symptom.setFever(this.fever);
        symptom.setDryCough(this.dryCough);
        symptom.setTiredness(this.tiredness);
        symptom.setLossOfTaste(this.lossOfTaste);
        symptom.setLossOfSmell(this.lossOfSmell);
        symptom.setSoreThroat(this.soreThroat);
        symptom.setHeadache(this.headache);
        return symptom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
