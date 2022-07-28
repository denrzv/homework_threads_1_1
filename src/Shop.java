import java.util.ArrayList;
import java.util.List;

public class Shop {
    private final Manufacturer manufacturer = new Manufacturer(this, "Toyota");
    private final int CAR_LIMIT = 1;
    private final List<Car> cars = new ArrayList<>(CAR_LIMIT);
    public void acceptCar() {
        manufacturer.produceCar();
    }

    List<Car> getCars() {
        return cars;
    }

    public Car sellCar() {
        return manufacturer.sellCar();
    }
}
