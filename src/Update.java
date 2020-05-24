public class Update {
    private static final Update instance = new Update();
    private final OrderBookBidStorage orderBookBidStorage = OrderBookBidStorage.getInstance();
    private final OrderBookAskStorage orderBookAskStorage = OrderBookAskStorage.getInstance();
    private final OrderBookSpreadStorage orderBookSpreadStorage = OrderBookSpreadStorage.getInstance();
    private final OrderBookAsk orderBookAsk = OrderBookAsk.getInstance();
    private final OrderBookBid orderBookBid = OrderBookBid.getInstance();

    private Update() {
    }

    public static Update getInstance() {
        return instance;
    }

    public void findCriteria(int price, int size, String type) {
        if (type.equals("bid")) {
            updateBid(price, size);
        }
        if (type.equals("ask")) {
            updateAsk(price, size);
        }
        if (type.equals("spread")) {
            updateSpread(price, size);
        }

    }

    private void updateBid(int price, int size) {
        orderBookBid.setPriceAndSize(price, size);
        orderBookBidStorage.add(price, size);
    }

    private void updateAsk(int price, int size) {
        orderBookAsk.setPriceAndSize(price, size);
        orderBookAskStorage.add(price, size);
    }

    private void updateSpread(int price, int size) {
        orderBookSpreadStorage.add(price, size);
    }
}
