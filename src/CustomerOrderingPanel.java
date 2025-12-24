// Placeholder for Customer panel - Implement based on "Place an order" scenario
import javax.swing.*;

public class CustomerOrderingPanel extends JFrame {
    public CustomerOrderingPanel() {
        setTitle("iRestaurant - Customer Ordering");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("Customer Ordering Interface Under Development");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label);
        
        // TODO: Add menu browsing, item adding, submit to MealOrderMgmt
    }
}
