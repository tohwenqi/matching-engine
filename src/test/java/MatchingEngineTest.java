import order.Order;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class MatchingEngineTest {

    private MatchingEngine matchingEngine;

    @Before
    public void setUp() {
        this.matchingEngine = new MatchingEngine();
    }

    @Test
    public void testProcessBuys() {
        Order ask1 = new Order(false, 10.0, 10, "A");
        Order ask2 = new Order(false, 14.0, 10, "D");
        Order ask3 = new Order(false, 13.0, 10, "C");
        Order ask4 = new Order(false, 12.0, 10, "B");
        Order bid1 = new Order(true, 11.0, 5, "1");
        Order bid2 = new Order(true, 12.0, 5, "2");
        Order bid3 = new Order(true, 13.0, 30, "3");
        matchingEngine.add(ask1);
        matchingEngine.add(ask2);
        matchingEngine.add(ask3);
        matchingEngine.add(ask4);
        matchingEngine.process(Arrays.asList(bid1, bid2, bid3));
        System.out.println("--- ORDERS MATCHED ---");
        matchingEngine.printOrderHistory();
        System.out.println("--- BIDS ---");
        matchingEngine.printBids();
        System.out.println("--- ASKS ---");
        matchingEngine.printAsks();
    }

    @Test
    public void testProcessSells() {
        Order bid1 = new Order(true, 11.0, 5, "1");
        Order bid2 = new Order(true, 12.0, 5, "2");
        Order bid3 = new Order(true, 13.0, 12, "3");
        Order ask1 = new Order(false, 10.0, 10, "A");
        Order ask2 = new Order(false, 14.0, 10, "D");
        Order ask3 = new Order(false, 13.0, 10, "C");
        Order ask4 = new Order(false, 12.0, 10, "B");
        matchingEngine.add(bid1);
        matchingEngine.add(bid2);
        matchingEngine.add(bid3);
        matchingEngine.process(Arrays.asList(ask1, ask2, ask3, ask4));
        System.out.println("--- ORDERS MATCHED ---");
        matchingEngine.printOrderHistory();
        System.out.println("--- BIDS ---");
        matchingEngine.printBids();
        System.out.println("--- ASKS ---");
        matchingEngine.printAsks();
    }

}