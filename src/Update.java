import java.util.Scanner;

public class Update implements FindCriteria {
    private int price = -1;
    private int size = -1;
    private String type = "";
    private OrderBookStorage orderBookStorage = OrderBookStorage.getInstance();

    @Override
    public void findCriteria(String line) {
        Scanner s1 = new Scanner(line);
        type = s1.findInLine("(bid|ask|spread)");
        s1.close();
        Scanner s2 = new Scanner(line);
        s2.useDelimiter(",");
        while (s2.hasNext()) {
            if (s2.hasNextInt()) {
                price = s2.nextInt();
                size = s2.nextInt();
            } else {
                s2.next();
            }
        }
        s2.close();
        update();
    }
    private void update() {
        if(type.equals("bid")){
            saveToOrderBookBid();
            orderBookStorage.add(price,size);
        }
        if(type.equals("ask")){
            saveToOrderBookAsk();
            saveToOrderBookStorage();
        }
        if(type.equals("spread")){
            saveToOrderBookStorage();
        }
    }

    private void saveToOrderBookStorage() {
        orderBookStorage.add(price,size);
    }

    private void saveToOrderBookAsk() {
        (OrderBookAsk.getInstance()).setPriceAndSize(price, size);
    }

    private void saveToOrderBookBid() {
        (OrderBookBid.getInstance()).setPriceAndSize(price, size);
    }
}
