public interface OrderBook {
    void setPriceAndSize(int price, int size);
    boolean bestExists();
    int getPrice();
    int getSize();
}
