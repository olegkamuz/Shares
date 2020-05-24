import java.util.HashMap;

public class OrderBookSpreadStorage {

    private static OrderBookSpreadStorage instance = new OrderBookSpreadStorage();
    private HashMap<Integer, Integer> orders = new HashMap<Integer, Integer>();

    private OrderBookSpreadStorage() {
    }

    public static OrderBookSpreadStorage getInstance() {
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
