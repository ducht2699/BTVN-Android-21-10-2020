package com.example.currency;

public class Currency {
    private String sign;
    private double coefficient;

    public Currency(String sign, double coefficient) {
        this.sign = sign;
        this.coefficient = coefficient;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public String getSign() {
        return sign;
    }


}
