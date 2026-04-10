package service;

import core.IMeasurable;
import core.Quantity;
import repository.IQuantityMeasurementRepository;

public class QuantityMeasurementServiceImpl<U extends IMeasurable>
        implements QuantityMeasurementService<U> {

    private final IQuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean compare(Quantity<U> q1, Quantity<U> q2) {
        return q1.equals(q2);
    }

    @Override
    public Quantity<U> convert(Quantity<U> q, U targetUnit) {
        return q.convertTo(targetUnit);
    }

    @Override
    public Quantity<U> add(Quantity<U> q1, Quantity<U> q2) {
        return q1.add(q2);
    }

    @Override
    public Quantity<U> subtract(Quantity<U> q1, Quantity<U> q2) {
        return q1.subtract(q2);
    }

    @Override
    public double divide(Quantity<U> q1, Quantity<U> q2) {
        return q1.divide(q2);
    }
}