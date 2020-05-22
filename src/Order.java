import java.util.Scanner;

public class Order implements FindCriteria {
    private static int size = -1;
    private static String orderAction;

    private OrderBookBid orderBookBid = OrderBookBid.getInstance();
    private OrderBookAsk orderBookAsk = OrderBookAsk.getInstance();

    private OrderBookStorage orderBookStorage = OrderBookStorage.getInstance();

    @Override
    public void findCriteria(String line) {
        Scanner s1 = new Scanner(line);
        orderAction = s1.findInLine("(sell|buy)");
        s1.close();
        Scanner s2 = new Scanner(line);
        s2.useDelimiter(",");
        while (s2.hasNext()) {
            if (s2.hasNextInt()) {
                size = s2.nextInt();
            } else {
                s2.next();
            }
        }
        s2.close();
        if (orderAction.equals("sell")) {
            if (orderBookBid.bestExists()) {
                int newSize = orderBookBid.getSize() - size;
                orderBookBid.setSize(newSize);
                orderBookStorage.updateSizeWherePrice(orderBookBid.getPrice(), newSize);
            }
        }
        if (orderAction.equals("buy")) {
            if (orderBookAsk.bestExists()) {
                int newSize = orderBookAsk.getSize() - size;
                orderBookAsk.setSize(newSize);
                orderBookStorage.updateSizeWherePrice(orderBookAsk.getPrice(), newSize);
            }
        }
    }
}
