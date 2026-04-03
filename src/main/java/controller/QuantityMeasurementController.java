package controller;

import core.IMeasurable;
import core.Quantity;
import service.QuantityMeasurementService;

public class QuantityMeasurementController<U extends IMeasurable> {

    private final QuantityMeasurementService<U> service;

    public QuantityMeasurementController(QuantityMeasurementService<U> service) {
        this.service = service;
    }

    public void compare(Quantity<U> q1, Quantity<U> q2) {
        System.out.println("Comparison: " + service.compare(q1, q2));
    }

    public void convert(Quantity<U> q, U targetUnit) {
        System.out.println("Conversion: " + service.convert(q, targetUnit));
    }

    public void add(Quantity<U> q1, Quantity<U> q2) {
        System.out.println("Addition: " + service.add(q1, q2));
    }

    public void subtract(Quantity<U> q1, Quantity<U> q2) {
        System.out.println("Subtraction: " + service.subtract(q1, q2));
    }

    public void divide(Quantity<U> q1, Quantity<U> q2) {
        System.out.println("Division: " + service.divide(q1, q2));
    }
}