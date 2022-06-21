package book;

import order.Order;

public interface Book {
    public void add(Order order);

    public void remove(Order order);

    public Order getBest();
}
