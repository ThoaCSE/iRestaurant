import java.util.Iterator;
import java.util.Vector;

public class MealOrderMgmt {
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
