package com.example.datlichkham.dto;

public class StatisticalDTO {
    private int numberOrder;
    private float sumPrice;

    public StatisticalDTO(int numberOrder, float sumPrice) {
        this.numberOrder = numberOrder;
        this.sumPrice = sumPrice;
    }

    public StatisticalDTO() {
    }

    public int getNumberOrder() {
        return numberOrder;
    }

    public void setNumberOrder(int numberOrder) {
        this.numberOrder = numberOrder;
    }

    public float getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(float sumPrice) {
        this.sumPrice = sumPrice;
    }
}
