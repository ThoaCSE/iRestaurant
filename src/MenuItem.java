public class MenuItem {
	private String name;
	private String description;
	private String imagePath;
	private double price;

	public MenuItem(String name, String description, double price, String imagePath) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.imagePath = imagePath;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "MenuItem{" +
			   "name='" + name + '\'' +
			   ", price=" + price +
			   '}';
	}
}
