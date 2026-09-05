package com.cp.demo.strategy;

public class SeasonalSaleStrategy implements DiscountStrategy {
    @Override
    public double calculateDiscount(double price) {
        return price * 0.8;
    }
}