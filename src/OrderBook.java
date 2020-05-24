public interface OrderBook {
    void setPriceAndSize(int price, int size);

    boolean bestExists();

    int getPrice();

    void setSize(int size);

    void setNewBest(int price, int size);

    int getSize();

    void resetBest();
}
