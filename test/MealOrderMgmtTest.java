import org.junit.Test;
import static org.junit.Assert.*;

public class MealOrderMgmtTest {

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
