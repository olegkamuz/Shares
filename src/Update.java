import java.util.Scanner;

public class Update implements FindCriteria {
    private static Update instance = new Update();
    private int price = -1;
    private int size = -1;
    private String type = "";
    private OrderBookBidStorage orderBookBidStorage = OrderBookBidStorage.getInstance();
    private OrderBookAskStorage orderBookAskStorage = OrderBookAskStorage.getInstance();
    private OrderBookSpreadStorage orderBookSpreadStorage = OrderBookSpreadStorage.getInstance();
    private OrderBookAsk orderBookAsk = OrderBookAsk.getInstance();
    private OrderBookBid orderBookBid = OrderBookBid.getInstance();
    private Update(){}
    public static Update getInstance() {
        return instance;
    }

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
            orderBookBid.setPriceAndSize(price, size);
            orderBookBidStorage.add(price,size);
        }
        if(type.equals("ask")){
            orderBookAsk.setPriceAndSize(price, size);
            orderBookAskStorage.add(price,size);
        }
        if(type.equals("spread")){
            orderBookSpreadStorage.add(price,size);
        }
    }
}
