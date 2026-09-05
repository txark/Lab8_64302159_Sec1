package com.cp.demo.strategy;

public class NoDiscountStrategy implements DiscountStrategy {
    @Override
    public double calculateDiscount(double price) {
        return price;
    }
}