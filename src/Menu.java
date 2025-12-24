import java.util.Vector;

public class Menu {
    private final Vector<RestaurantMenuItem> items = new Vector<>();

    public void addMenuItem(RestaurantMenuItem mi) {
		if (mi != null) {
			items.add(mi);
		}
	}

public Vector<RestaurantMenuItem> getItems() {
		return items;
	}

	public int countItems() {
		return items.size();
	}
}
