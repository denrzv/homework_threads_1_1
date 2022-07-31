
public class Buyer {
    private final Shop shop;
    private final long SLEEP_TIMER = 1000;

    public Buyer(Shop shop) {
        this.shop = shop;
    }


    public void buyCar() {
        try {
            while (!shop.salesLimitReached()) {
                shop.sellCar();
                Thread.sleep(SLEEP_TIMER);
            }
        } catch (InterruptedException e) {
            System.out.println("Завершаем работу...");
        }
    }
}
