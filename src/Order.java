public class Order {
    private static Order instance = new Order();
    private static int size = -1;

    private OrderBookBid orderBookBid = OrderBookBid.getInstance();
    private OrderBookAsk orderBookAsk = OrderBookAsk.getInstance();

    private OrderBookBidStorage orderBookBidStorage = OrderBookBidStorage.getInstance();
    private OrderBookAskStorage orderBookAskStorage = OrderBookAskStorage.getInstance();

    private Order() {
    }

    public static Order getInstance() {
        return instance;
    }

    public void findCriteria(String orderAction, int size) {
        if (orderAction.equals("sell")) {
            if (orderBookBid.bestExists()) {
                int newSize = orderBookBid.getSize() - size;
                if (newSize <= 0) {
                    newSize = 0;
                    orderBookBidStorage.updateSizeWherePrice(orderBookBid.getPrice(), newSize);
                    orderBookBid.setSize(newSize);
                }
                if (newSize > 0) {
                    orderBookBid.setSize(newSize);
                    orderBookBidStorage.updateSizeWherePrice(orderBookBid.getPrice(), newSize);
                }
            }
        }
        if (orderAction.equals("buy")) {
            if (orderBookAsk.bestExists()) {
                int newSize = orderBookAsk.getSize() - size;
                if (newSize <= 0) {
                    newSize = 0;
                    orderBookAskStorage.updateSizeWherePrice(orderBookAsk.getPrice(), newSize);
                    orderBookAsk.setSize(newSize);
                }
                if (newSize > 0) {
                    orderBookAsk.setSize(newSize);
                    orderBookBidStorage.updateSizeWherePrice(orderBookAsk.getPrice(), newSize);
                }
            }
        }
    }
}
