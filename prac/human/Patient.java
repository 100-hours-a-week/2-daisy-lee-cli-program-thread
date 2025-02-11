package prac.human;

import prac.injury.Injuries;

public class Patient extends Human {
    public static Patient patient = new Patient();
    int woundType;
    Injuries injuryType;
    Healer healerType;
    int payment = 0;

    public Patient() {

    }

    public void pay(int payment) {
        int cumulativeSum = getPayment();
        cumulativeSum += payment;
        setPayment(cumulativeSum);
    }

    public int getWoundType() {
        return woundType;
    }
    public void setWoundType(int woundType) {
        this.woundType = woundType;
    }

    public Injuries getInjuryType() {
        return injuryType;
    }
    public void setInjuryType(Injuries injuryType) {
        this.injuryType = injuryType;
    }

    public Healer getHealerType() {
        return healerType;
    }
    public void setHealerType(Healer healerType) {
        this.healerType = healerType;
    }

    public int getPayment() {
        return payment;
    }
    public void setPayment(int payment) {
        this.payment = payment;
    }
}
