public class Order {
    private static final Order instance = new Order();
    private final OrderBookBid orderBookBid = OrderBookBid.getInstance();
    private final OrderBookAsk orderBookAsk = OrderBookAsk.getInstance();
    private final OrderBookBidStorage orderBookBidStorage = OrderBookBidStorage.getInstance();
    private final OrderBookAskStorage orderBookAskStorage = OrderBookAskStorage.getInstance();

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
                    saveToBid(newSize);
                }
                if (newSize > 0) {
                    saveToBid(newSize);
                }
            }
        }
        if (orderAction.equals("buy")) {
            if (orderBookAsk.bestExists()) {
                int newSize = orderBookAsk.getSize() - size;
                if (newSize <= 0) {
                    newSize = 0;
                    saveToAsk(newSize);
                }
                if (newSize > 0) {
                    saveToAsk(newSize);
                }
            }
        }
    }

    private void saveToBid(int newSize) {
        orderBookBidStorage.updateSizeWherePrice(orderBookBid.getPrice(), newSize);
        orderBookBid.setSize(newSize);
    }

    private void saveToAsk(int newSize) {
        orderBookAskStorage.updateSizeWherePrice(orderBookAsk.getPrice(), newSize);
        orderBookAsk.setSize(newSize);
    }

}
