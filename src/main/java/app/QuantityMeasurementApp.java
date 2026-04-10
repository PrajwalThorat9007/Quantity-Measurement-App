package app;

import controller.QuantityMeasurementController;
import core.LengthUnit;
import core.Quantity;
import core.WeightUnit;
import repository.IQuantityMeasurementRepository;
import repository.QuantityMeasurementDatabaseRepository;
import service.QuantityMeasurementService;
import service.QuantityMeasurementServiceImpl;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        // ✅ Create ONE repository
        IQuantityMeasurementRepository repository =
                new QuantityMeasurementDatabaseRepository();

        // ✅ Inject repository into services
        QuantityMeasurementService<LengthUnit> lengthService =
                new QuantityMeasurementServiceImpl<>(repository);

        QuantityMeasurementController<LengthUnit> lengthController =
                new QuantityMeasurementController<>(lengthService);

        Quantity<LengthUnit> l1 =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> l2 =
                new Quantity<>(120.0, LengthUnit.INCHES);

        System.out.println("=== LENGTH OPERATIONS ===");

        lengthController.compare(l1, l2);
        lengthController.convert(l1, LengthUnit.INCHES);
        lengthController.add(l1, l2);
        lengthController.subtract(l1, l2);
        lengthController.divide(l1, l2);


        // ✅ Same repository used for weight
        QuantityMeasurementService<WeightUnit> weightService =
                new QuantityMeasurementServiceImpl<>(repository);

        QuantityMeasurementController<WeightUnit> weightController =
                new QuantityMeasurementController<>(weightService);

        Quantity<WeightUnit> w1 =
                new Quantity<>(5.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> w2 =
                new Quantity<>(5000.0, WeightUnit.GRAM);

        System.out.println("\n=== WEIGHT OPERATIONS ===");

        weightController.compare(w1, w2);
        weightController.convert(w1, WeightUnit.GRAM);
        weightController.add(w1, w2);
        weightController.subtract(w1, w2);
        weightController.divide(w1, w2);


        // ✅ Print stored DB data
        System.out.println("\n=== DATABASE DATA ===");
        repository.getAllMeasurements().forEach(System.out::println);
    }
}