public class Manufacturer {
    private final Shop shop;
    private final String name;
    private final long SLEEP_TIMER = 1000;
    private final long PRODUCE_SLEEP_TIMER = 3000;

    public Manufacturer(Shop shop, String name) {
        this.shop = shop;
        this.name = name;
    }

    public synchronized void produceCar() {
        try {
                Thread.sleep(PRODUCE_SLEEP_TIMER);
                shop.getCars().add(new Car());
                System.out.println(this + " выпустил автомобиль");
                notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public synchronized Car sellCar() {
        try {
            System.out.println(Thread.currentThread().getName() + " зашёл в автосалон");
            while (shop.getCars().size() == 0) {
                System.err.println("Машин нет!");
                wait();
            }
            Thread.sleep(SLEEP_TIMER);
            System.out.println(Thread.currentThread().getName() + " уехал на новом автомобиле");
            produceCar();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return shop.getCars().remove(0);
    }

    @Override
    public String toString() {
        return "Производитель " + name;
    }
}
