import java.util.LinkedList;

public class Shop {
    private final LinkedList<Car> cars = new LinkedList<>();
    private final int SALES_LIMIT = 10;
    private int salesCounter = 0;

    synchronized void addCar(Car car) {
        cars.add(car);
        notify();
    }

    boolean hasCar() {
        return !cars.isEmpty();
    }

    boolean salesLimitReached() {
        return salesCounter == SALES_LIMIT;
    }

    synchronized Car sellCar() {
        while (!hasCar()) {
            try {
                System.out.println(Thread.currentThread().getName() + " зашёл в автосалон");
                System.out.println("Нет машин!");
                wait();
            } catch (InterruptedException e) {
                System.out.println("Завершаем работу...");
            }
        }

        System.out.println(Thread.currentThread().getName() + " уехал на новеньком автомобиле!");
        salesCounter++;
        System.out.println("Продано машин: " + salesCounter);
        return cars.removeFirst();
    }
}
