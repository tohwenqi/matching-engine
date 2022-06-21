import java.util.ArrayList;
import java.util.List;

public class OrderHistory {
    private List<Activity> activities = new ArrayList<>();

    public void add(Activity activity) {
        activities.add(activity);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Activity activity : activities) {
            s.append(activity);
            s.append("\n");
        }
        return s.toString();
    }
}
