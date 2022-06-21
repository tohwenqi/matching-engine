package book;

import order.Order;

import java.util.Comparator;
import java.util.TreeSet;

public class Asks implements Book {
    // asks, sorted in order of increasing price
    private TreeSet<Order> asks;

    public Asks() {
        Comparator<Order> sellComparator = (o1, o2) -> {
            if (o1.getPrice() < o2.getPrice()) {
                return -1;
            } else if (o2.getPrice() < o1.getPrice()) {
                return 1;
            }
            return 0;
        };
        asks = new TreeSet<>(sellComparator);
    }

    @Override
    public void add(Order ask) {
        asks.add(ask);
    }

    @Override
    public void remove(Order order) {
        asks.remove(order);
    }

    @Override
    public Order getBest() {
        return asks.first();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Order ask : asks) {
            s.append(ask);
            s.append("\n");
        }
        return s.toString();
    }
}
