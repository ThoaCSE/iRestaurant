public class Main {
	public static void main(String[] args) {
		MealOrderMgmt mgmt = new MealOrderMgmt();

		MealOrder o1 = new MealOrder(1001, "iT001");
		o1.setStatus(OrderStatus.ORDERED);
		o1.addItem(new OrderItem(new MenuItem("Hamburger", "Beef burger", 8.99, null), 1));

		MealOrder o2 = new MealOrder(1002, "iT002");
		o2.setStatus(OrderStatus.IN_KITCHEN);
		o2.addItem(new OrderItem(new MenuItem("Cheese Pizza", "12\" pie", 12.50, null), 1));

		MealOrder o3 = new MealOrder(1003, "iT003");
		// stays INACTIVE initially

		mgmt.addMealOrder(o1);
		mgmt.addMealOrder(o2);
		mgmt.addMealOrder(o3);

		System.out.println("Total orders: " + mgmt.getOrderCount());
		System.out.println("INACTIVE: " + mgmt.countNumberOf(OrderStatus.INACTIVE));
		System.out.println("ORDERED: " + mgmt.countNumberOf(OrderStatus.ORDERED));
		System.out.println("IN_KITCHEN: " + mgmt.countNumberOf(OrderStatus.IN_KITCHEN));

		// Update an order status
		mgmt.updateMealOrder(1001, OrderStatus.DELIVERED);
		System.out.println("After delivery of 1001 -> DELIVERED: " + mgmt.countNumberOf(OrderStatus.DELIVERED));
	}
}
