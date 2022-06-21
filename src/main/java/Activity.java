import java.util.Date;

public class Activity {
    private double price;
    private int quantity;
    private Date entryDate;
    private String buyId;
    private String sellId;

    public Activity(double price, int quantity, Date entryDate, String buyId, String sellId) {
        this.price = price;
        this.quantity = quantity;
        this.entryDate = entryDate;
        this.buyId = buyId;
        this.sellId = sellId;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("price: ");
        s.append(price);
        s.append(", quantity: ");
        s.append(quantity);
        s.append(", entryDate: ");
        s.append(entryDate);
        s.append(", buyId: ");
        s.append(buyId);
        s.append(", sellId: ");
        s.append(sellId);
        return s.toString();
    }
}
