public interface OrderBook {
    void setPriceAndSize(int price, int size);
    boolean bestExists();
    int getPrice();
    void setPrice(int price);
    void setSize(int size);
    int getSize();
}
