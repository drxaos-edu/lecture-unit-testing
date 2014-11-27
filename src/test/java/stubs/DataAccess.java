package stubs;

public interface DataAccess {

    void save(Integer orderId, Order order);

    Order load(Integer orderId);

    int getItemPrice(String item);

}
