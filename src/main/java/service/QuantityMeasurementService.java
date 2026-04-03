package service;

import core.IMeasurable;
import core.Quantity;

public interface QuantityMeasurementService<U extends IMeasurable> {

    boolean compare(Quantity<U> q1, Quantity<U> q2);

    Quantity<U> convert(Quantity<U> q, U targetUnit);

    Quantity<U> add(Quantity<U> q1, Quantity<U> q2);

    Quantity<U> subtract(Quantity<U> q1, Quantity<U> q2);

    double divide(Quantity<U> q1, Quantity<U> q2);
}