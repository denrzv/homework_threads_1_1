
public class Manufacturer {
    private final Shop shop;
    private final long PRODUCE_SLEEP_TIMER = 1500;

    public Manufacturer(Shop shop) {
        this.shop = shop;
    }

    public void produceCar() {
        try {
            while (!shop.salesLimitReached()) {
                Thread.sleep(PRODUCE_SLEEP_TIMER);
                shop.addCar(new Car());
                System.out.println(Thread.currentThread().getName() + " выпустил 1 автомобиль");
            }
        } catch (InterruptedException e) {
            System.out.println("Завершаем работу...");
        }
    }
}
