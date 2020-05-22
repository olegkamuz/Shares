import java.util.Scanner;

public class Update implements FindCriteria {
    private static String ACTION = "u";
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
        printUpdate();
    }

    private void printUpdate() {
        System.out.println("action: " + Update.ACTION + " price: " + price + " size: " + size + " type: " + type);
    }
    private void update() {
        if(type.equals("bid")){
            OrderBook ob = OrderBookBid.getInstance();
            ob.setPriceAndSize(price, size);
            orderBookStorage.add(price,size);
        }
        if(type.equals("ask")){
            OrderBook ob = OrderBookAsk.getInstance();
            ob.setPriceAndSize(price, size);
            orderBookStorage.add(price,size);
        }
        if(type.equals("spread")){
            orderBookStorage.add(price,size);
        }
    }
}
