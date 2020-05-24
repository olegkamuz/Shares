public class OrderBookAsk implements OrderBook {
    private static OrderBookAsk instance = new OrderBookAsk();
    private int price = -1;
    private int size = -1;

    private OrderBookAsk() {
    }

    public static OrderBookAsk getInstance() {
        return instance;
    }

    @Override
    public boolean bestExists() {
        if (price > 0 && size >= 0) {
            return true;
        }
        return false;
    }

    @Override
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
        if (size == 0) {
            (OrderBookAskStorage.getInstance()).findNewBestAsk();
        }
    }

    @Override
    public void setPriceAndSize(int price, int size) {
        if (this.price == -1) {
            this.price = price;
            this.size = size;
        }
        if (price < this.price && size != 0) {
            this.price = price;
            this.size = size;
        }
    }

    @Override
    public void setNewBest(int price, int size) {
        this.price = price;
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void resetBest() {
        this.price = -1;
        this.size = -1;
    }
}
