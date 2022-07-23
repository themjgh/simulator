package com.shaparak.simulator.dto;

public class CivilRegistryRequest extends Request{
    private String idNumber;

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }
}
