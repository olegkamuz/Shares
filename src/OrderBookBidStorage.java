import java.util.*;

public class OrderBookBidStorage {

    private static OrderBookBidStorage instance = new OrderBookBidStorage();
    private TreeMap<Integer, Integer> orders = new TreeMap<Integer, Integer>();

    private OrderBookBidStorage() {
    }

    public static OrderBookBidStorage getInstance() {
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

    public void findNewBestBid() {
        for (int key : orders.descendingKeySet()) {
            boolean noBest = true;
            if (orders.get(key) > 0) {
                (OrderBookBid.getInstance()).setNewBest(key, orders.get(key));
                noBest = false;
                break;
            }
            if (noBest) {
                (OrderBookBid.getInstance()).resetBest();
            }
        }
    }
}
