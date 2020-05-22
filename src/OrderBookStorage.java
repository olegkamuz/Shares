import java.util.HashMap;
import java.util.Map;

public class OrderBookStorage {

    private static OrderBookStorage instance = new OrderBookStorage();
    private HashMap<Integer,Integer> orders = new HashMap<Integer, Integer>();

    private OrderBookStorage() {
    }

    public static OrderBookStorage getInstance() {
        return instance;
    }

    public void add(int price, int size) {
        this.orders.put(price, size);
    }

    public Integer getSizeAtPrice(Integer price) {
        return orders.get(price);
    }
}
