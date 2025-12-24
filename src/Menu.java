import java.util.Vector;

public class Menu {
	private final Vector<MenuItem> items = new Vector<>();

	public void addMenuItem(MenuItem mi) {
		if (mi != null) {
			items.add(mi);
		}
	}

	public Vector<MenuItem> getItems() {
		return items;
	}

	public int countItems() {
		return items.size();
	}
}
