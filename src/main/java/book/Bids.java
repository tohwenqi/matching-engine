package book;

import order.Order;

import java.util.*;

public class Bids implements Book {
    // bids, sorted in order of decreasing price
    private TreeSet<Order> bids;

    public Bids() {
        Comparator<Order> buyComparator = (o1, o2) -> {
            if (o1.getPrice() < o2.getPrice()) {
                return 1;
            } else if (o2.getPrice() < o1.getPrice()) {
                return -1;
            }
            return 0;
        };
        bids = new TreeSet<>(buyComparator);
    }

    @Override
    public void add(Order order) {
        bids.add(order);
    }

    @Override
    public void remove(Order order) {
        bids.remove(order);
    }

    @Override
    public Order getBest() {
        return bids.first();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Order bid : bids) {
            s.append(bid);
            s.append("\n");
        }
        return s.toString();
    }
}
