import java.util.Vector;

public class MealOrder {
	private final int orderID;
	private final String tableID;
	private final Vector<OrderItem> items = new Vector<>();
	private OrderStatus status;

	public MealOrder(int orderID, String tableID) {
		this.orderID = orderID;
		this.tableID = tableID;
		this.status = OrderStatus.INACTIVE;
	}

	public int getOrderID() {
		return orderID;
	}

	public String getTableID() {
		return tableID;
	}

	public Vector<OrderItem> getItems() {
		return items;
	}

	public void addItem(OrderItem oi) {
		if (oi != null) {
			items.add(oi);
		}
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus newStatus) {
		if (newStatus != null) {
			this.status = newStatus;
		}
	}
}
