import java.util.HashMap;

public class OrderBookStorage {

    private static OrderBookStorage instance = new OrderBookStorage();
    private HashMap<Integer, Integer> orders = new HashMap<Integer, Integer>();

    private OrderBookStorage() {
    }

    public static OrderBookStorage getInstance() {
        return instance;
    }

    public void add(int price, int size) {
        this.orders.put(price, size);
    }

    public void updateSizeWherePrice(int price, int size) {
        orders.replace(price, size);
    }

    public Integer getSizeAtPrice(Integer price) {
        if (!orders.containsKey(price)) {
            return null;
        }
        return orders.get(price);
    }
}
