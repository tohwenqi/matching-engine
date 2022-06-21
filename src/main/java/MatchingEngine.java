import book.Asks;
import book.Bids;
import order.Order;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

public class MatchingEngine {
    private Bids bids = new Bids();
    private Asks asks = new Asks();

    private OrderHistory activities = new OrderHistory();

    public void process(List<Order> orders) {
        for (Order order : orders) {
            match(order);
        }
    }

    private void match(Order order) {
        if (order.isBuy()) {
            while (order.getQuantity() > 0) {
                try {
                    Order curAsk = asks.getBest();
                    // order can at least be partially fulfilled
                    if (order.getPrice() >= curAsk.getPrice()) {
                        int fulfilment = Math.min(order.getQuantity(), curAsk.getQuantity());
                        Boolean isCurAskCompletelyFulfilled = curAsk.getQuantity() <= order.getQuantity();
                        Boolean isOrderCompletelyFulfilled = order.getQuantity() <= curAsk.getQuantity();
                        // fulfill as much of the order as possible, using bid price
                        order.setQuantity(order.getQuantity() - fulfilment);
                        activities.add(new Activity(order.getPrice(), fulfilment, new Date(), order.getId(), curAsk.getId()));
                        asks.remove(curAsk);
                        // persist back the unfulfilled ask, preserving its id, if it is partially fulfilled
                        if (!isCurAskCompletelyFulfilled) {
                            add(new Order(false, curAsk.getPrice(), curAsk.getQuantity() - fulfilment, curAsk.getId()));
                        }
                        // don't look at the next ask as the order is fulfilled
                        if (isOrderCompletelyFulfilled) {
                            break;
                        }
                    } else {
                        break;
                    }
                } catch (NoSuchElementException e) {
                    // no asks available in the book
                    return;
                }
            }
            // add the order if it was not completely fulfilled
            if (order.getQuantity() > 0) {
                add(new Order(true, order.getPrice(), order.getQuantity(), order.getId()));
            }
        } else {
            while (order.getQuantity() > 0) {
                try {
                    Order curBid = bids.getBest();
                    // order can at least be partially fulfilled
                    if (order.getPrice() <= curBid.getPrice()) {
                        int fulfilment = Math.min(order.getQuantity(), curBid.getQuantity());
                        Boolean isCurBidCompletelyFulfilled = curBid.getQuantity() <= order.getQuantity();
                        Boolean isOrderCompletelyFulfilled = order.getQuantity() <= curBid.getQuantity();
                        // fulfill as much of the order as possible, following bid price
                        order.setQuantity(order.getQuantity() - fulfilment);
                        activities.add(new Activity(curBid.getPrice(), fulfilment, new Date(), curBid.getId(), order.getId()));
                        bids.remove(curBid);
                        // persist back the unfulfilled bid, preserving its id, if it is partially fulfilled
                        if (!isCurBidCompletelyFulfilled) {
                            add(new Order(true, curBid.getPrice(), curBid.getQuantity() - fulfilment, curBid.getId()));
                        }
                        // don't look at the next bid as the order is fulfilled
                        if (isOrderCompletelyFulfilled) {
                            break;
                        }
                    } else {
                        break;
                    }
                } catch (NoSuchElementException e) {
                    // no bids available in the book
                    return;
                }
            }
            // add the order if it was not completely fulfilled
            if (order.getQuantity() > 0) {
                add(new Order(false, order.getPrice(), order.getQuantity(), order.getId()));
            }
        }
    }

    protected void add(Order order) {
        if (order.getPrice() <= 0 || order.getQuantity() <= 0) {
            throw new IllegalArgumentException("Price and Quantity must be positive values!");
        }
        if (order.isBuy()) {
            bids.add(order);
        } else {
            asks.add(order);
        }
    }

    public void printOrderHistory() {
        System.out.println(activities);
    }

    public void printBids() {
        System.out.println(bids);
    }

    public void printAsks() {
        System.out.println(asks);
    }

}
