public class Main {
    public static void main(String[] args) {
        final short SELLS_LIMIT = 10;
        final Shop shop = new Shop();

        for (int i = 0; i < SELLS_LIMIT; i++) {
            new Thread(shop::sellCar, "Покупатель " + (i + 1)).start();
        }

        new Thread(null, shop::acceptCar, "Производитель").start();
    }
}