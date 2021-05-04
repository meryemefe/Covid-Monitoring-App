package com.bilkent.covidmonitoringservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Symptom implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    private LocalDate date;

    private boolean fever;
    private boolean dryCough;
    private boolean tiredness;
    private boolean lossOfTaste;
    private boolean lossOfSmell;
    private boolean soreThroat;
    private boolean headache;

    public Symptom() {
    }

    public Symptom(Long id, Long userId, LocalDate date, boolean fever, boolean dryCough, boolean tiredness, boolean lossOfTaste, boolean lossOfSmell, boolean soreThroat, boolean headache) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.fever = fever;
        this.dryCough = dryCough;
        this.tiredness = tiredness;
        this.lossOfTaste = lossOfTaste;
        this.lossOfSmell = lossOfSmell;
        this.soreThroat = soreThroat;
        this.headache = headache;
    }

    public Symptom(Long userId, LocalDate date, boolean fever, boolean dryCough, boolean tiredness, boolean lossOfTaste, boolean lossOfSmell, boolean soreThroat, boolean headache) {
        this.userId = userId;
        this.date = date;
        this.fever = fever;
        this.dryCough = dryCough;
        this.tiredness = tiredness;
        this.lossOfTaste = lossOfTaste;
        this.lossOfSmell = lossOfSmell;
        this.soreThroat = soreThroat;
        this.headache = headache;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
