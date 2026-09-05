package com.cp.demo.strategy;

public class MemberDiscountStrategy implements DiscountStrategy {
    @Override
    public double calculateDiscount(double price) {
        return price * 0.9;
    }
}