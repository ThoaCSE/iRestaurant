import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Iterator;
import java.util.Vector;

// Standalone test file - includes all necessary classes for independent execution

enum OrderStatus {ORDERED, IN_KITCHEN, READY, DELIVERING, DELIVERED, ARCHIVED, INACTIVE}

class MealOrderMgmt {
    private final Vector<MealOrder> orders = new Vector<>();

    public void addMealOrder(MealOrder mo) {
        if (mo != null) {
            orders.add(mo);
        }
    }

    public boolean updateMealOrder(int orderID, OrderStatus newStatus) {
        boolean updated = false;
        if (newStatus == null) return false;
        Iterator<MealOrder> it = orders.iterator();
        while (it.hasNext()) {
            MealOrder mo = it.next();
            if (mo.getOrderID() == orderID) {
                mo.setStatus(newStatus);
                updated = true;
            }
        }
        return updated;
    }

    public int countNumberOf(OrderStatus status) {
        int count = 0;
        if (status == null) return 0;
        Iterator<MealOrder> it = orders.iterator();
        while (it.hasNext()) {
            MealOrder mo = it.next();
            if (status.equals(mo.getStatus())) {
                count++;
            }
        }
        return count;
    }

    public int getOrderCount() {
        return orders.size();
    }
}

class MealOrder {
    private int orderID;
    private String tableID;
    private OrderStatus status;

    MealOrder(int orderID, String tableID) {
        this.orderID = orderID;
        this.tableID = tableID;
        this.status = OrderStatus.INACTIVE;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public OrderStatus getStatus() {
        return status;
    }
}

// Test class - can run independently
public class StandaloneMealOrderTest {

    public static void main(String[] args) {
        StandaloneMealOrderTest test = new StandaloneMealOrderTest();
        
        // Run test 1
        try {
            test.updateMealOrderTestFunction1();
            System.out.println("Test: updateMealOrderTestFunction1");
            System.out.println("Result: Success\n");
        } catch (AssertionError e) {
            System.out.println("Test: updateMealOrderTestFunction1");
            System.out.println("Result: Fail - " + e.getMessage() + "\n");
        }
        
        // Run test 2
        try {
            test.updateMealOrderTestFunction2();
            System.out.println("Test: updateMealOrderTestFunction2");
            System.out.println("Result: Success\n");
        } catch (AssertionError e) {
            System.out.println("Test: updateMealOrderTestFunction2");
            System.out.println("Result: Fail - " + e.getMessage() + "\n");
        }
        
        // Run test 3
        try {
            test.addMealOrderTestFunction1();
            System.out.println("Test: addMealOrderTestFunction1");
            System.out.println("Result: Success\n");
        } catch (AssertionError e) {
            System.out.println("Test: addMealOrderTestFunction1");
            System.out.println("Result: Fail - " + e.getMessage() + "\n");
        }
        
        // Run test 4
        try {
            test.addMealOrderTestFunction2();
            System.out.println("Test: addMealOrderTestFunction2");
            System.out.println("Result: Success\n");
        } catch (AssertionError e) {
            System.out.println("Test: addMealOrderTestFunction2");
            System.out.println("Result: Fail - " + e.getMessage() + "\n");
        }
    }

    @Test
    public void updateMealOrderTestFunction1() {
        MealOrderMgmt mgmt = new MealOrderMgmt();
        MealOrder o1 = new MealOrder(1, "iT001");
        o1.setStatus(OrderStatus.ORDERED);
        MealOrder o2 = new MealOrder(2, "iT002");
        o2.setStatus(OrderStatus.IN_KITCHEN);
        MealOrder o3 = new MealOrder(3, "iT003");
        o3.setStatus(OrderStatus.INACTIVE);
        mgmt.addMealOrder(o1);
        mgmt.addMealOrder(o2);
        mgmt.addMealOrder(o3);

        int beforeOrdered = mgmt.countNumberOf(OrderStatus.ORDERED);
        int beforeDelivered = mgmt.countNumberOf(OrderStatus.DELIVERED);
        boolean updated = mgmt.updateMealOrder(1, OrderStatus.DELIVERED);

        assertTrue(updated);
        assertEquals(beforeOrdered - 1, mgmt.countNumberOf(OrderStatus.ORDERED));
        assertEquals(beforeDelivered + 1, mgmt.countNumberOf(OrderStatus.DELIVERED));
        assertEquals(3, mgmt.getOrderCount());
    }

    @Test
    public void updateMealOrderTestFunction2() {
        MealOrderMgmt mgmt = new MealOrderMgmt();
        MealOrder o1 = new MealOrder(10, "iT010");
        o1.setStatus(OrderStatus.IN_KITCHEN);
        MealOrder o2 = new MealOrder(11, "iT011");
        o2.setStatus(OrderStatus.ORDERED);
        mgmt.addMealOrder(o1);
        mgmt.addMealOrder(o2);

        int beforeReady = mgmt.countNumberOf(OrderStatus.READY);
        int beforeInKitchen = mgmt.countNumberOf(OrderStatus.IN_KITCHEN);

        boolean updated = mgmt.updateMealOrder(10, OrderStatus.READY);

        assertTrue(updated);
        assertEquals(beforeReady + 1, mgmt.countNumberOf(OrderStatus.READY));
        assertEquals(beforeInKitchen - 1, mgmt.countNumberOf(OrderStatus.IN_KITCHEN));
        assertEquals(2, mgmt.getOrderCount());
    }

    @Test
    public void addMealOrderTestFunction1() {
        MealOrderMgmt mgmt = new MealOrderMgmt();
        mgmt.addMealOrder(new MealOrder(100, "iT100"));
        mgmt.addMealOrder(new MealOrder(101, "iT101"));

        int beforeTotal = mgmt.getOrderCount();
        int beforeInactive = mgmt.countNumberOf(OrderStatus.INACTIVE);

        MealOrder newOrder = new MealOrder(102, "iT102");
        mgmt.addMealOrder(newOrder);

        assertEquals(beforeTotal + 1, mgmt.getOrderCount());
        // New orders default to INACTIVE
        assertEquals(beforeInactive + 1, mgmt.countNumberOf(OrderStatus.INACTIVE));
    }

    @Test
    public void addMealOrderTestFunction2() {
        MealOrderMgmt mgmt = new MealOrderMgmt();
        mgmt.addMealOrder(new MealOrder(200, "iT200"));

        int beforeTotal = mgmt.getOrderCount();
        mgmt.addMealOrder(null); // should be ignored

        assertEquals(beforeTotal, mgmt.getOrderCount());
    }
}
