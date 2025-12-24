public class OrderItem {
	private final RestaurantMenuItem item;
	private final int quantity;

	public OrderItem(RestaurantMenuItem item, int quantity) {
		this.item = item;
		this.quantity = quantity;
	}

public RestaurantMenuItem getItem() {
		return item;
	}

	public int getQuantity() {
		return quantity;
	}

	public double calculatePrice() {
		return (item != null ? item.getPrice() : 0.0) * quantity;
	}
}
