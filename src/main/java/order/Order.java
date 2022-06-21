package order;

import java.util.UUID;

public class Order {
    private Boolean isBuy;
    private String id;
    private double price;

    private int quantity;


    private ExecutionOption executionOption;

    public Order(Boolean isBuy, double price, int quantity, String id) {
        this.isBuy = isBuy;
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.executionOption = ExecutionOption.LIMIT;
    }

    public Order(Boolean isBuy, double price, int quantity) {
        this.isBuy = isBuy;
        this.id = UUID.randomUUID().toString();
        this.price = price;
        this.quantity = quantity;
        this.executionOption = ExecutionOption.LIMIT;
    }

    public Order(Boolean isBuy, int quantity) {
        this.isBuy = isBuy;
        this.id = UUID.randomUUID().toString();
        this.quantity = quantity;
        this.executionOption = ExecutionOption.MARKET;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Boolean isBuy() {
        return isBuy;
    }

    public String getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public ExecutionOption getExecutionOption() {
        return executionOption;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(isBuy ? "BUY" : "SELL");
        s.append("-");
        s.append(this.getQuantity());
        s.append("-");
        s.append(this.getPrice());
        s.append("-");
        s.append(this.getExecutionOption());
        s.append("-");
        s.append(this.getId());
        return s.toString();
    }
}