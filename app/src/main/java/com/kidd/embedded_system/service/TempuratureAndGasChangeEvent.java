package com.kidd.embedded_system.service;

public class TempuratureAndGasChangeEvent {
    double gas;
    double tempurature;

    public double getGas() {
        return gas;
    }

    public void setGas(double gas) {
        this.gas = gas;
    }

    public double getTempurature() {
        return tempurature;
    }

    public void setTempurature(double tempurature) {
        this.tempurature = tempurature;
    }

    public TempuratureAndGasChangeEvent(double gas, double tempurature) {

        this.gas = gas;
        this.tempurature = tempurature;
    }
}
