import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Shop {
    private final LinkedList<Car> cars = new LinkedList<>();
    private final int SALES_LIMIT = 10;
    private int salesCounter = 0;
    private final ReentrantLock lock = new ReentrantLock(true);
    private final Condition condition = lock.newCondition();

    void addCar(Car car) {
        try {
            lock.lock();
            cars.add(car);
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    boolean hasCar() {
        return !cars.isEmpty();
    }

    boolean salesLimitReached() {
        return salesCounter == SALES_LIMIT;
    }

    Car sellCar() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " зашёл в автосалон");
            while (!hasCar()) {
                System.out.println("Нет машин!");
                condition.await();
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " Завершает работу...");
        } finally {
            lock.unlock();
        }

        System.out.println(Thread.currentThread().getName() + " уехал на новеньком автомобиле!");
        salesCounter++;
        System.out.println("Продано машин: " + salesCounter);
        return cars.removeFirst();
    }
}
