import java.util.Map;
import java.util.TreeMap;

public class OrderBookAskStorage {

    private static OrderBookAskStorage instance = new OrderBookAskStorage();
    private TreeMap<Integer, Integer> orders = new TreeMap<Integer, Integer>();

    private OrderBookAskStorage() {
    }

    public static OrderBookAskStorage getInstance() {
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
    public void findNewBestAsk() {
        for(Map.Entry<Integer,Integer> entry : orders.entrySet()) {
            if(entry.getValue() > 0){
                (OrderBookAsk.getInstance()).setNewBest(entry.getKey(), entry.getValue());
                break;
            }
        }
    }
}
