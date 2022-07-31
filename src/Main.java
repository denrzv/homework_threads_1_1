public class Main {
    public static void main(String[] args) throws InterruptedException {
        final long SLEEP_TIMER = 1000;
        final Shop shop = new Shop();
        final Manufacturer manufacturer = new Manufacturer(shop);
        final int BUYERS = 3;
        ThreadGroup group = new ThreadGroup("Потоки");

        for (int i = 0; i < BUYERS; i++) {
            new Thread(group, new Buyer(shop)::buyCar, "Покупатель " + (i + 1)).start();
        }
        new Thread(group, manufacturer::produceCar, "Производитель Toyota").start();

        while(!shop.salesLimitReached()) {
            Thread.sleep(SLEEP_TIMER);
        }
        group.interrupt();
        System.exit(0);
    }
}