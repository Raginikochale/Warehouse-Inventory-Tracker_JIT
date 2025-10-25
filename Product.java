public class Product {
    private final String id;
    private final String name;
    private int quantity;
    private final int reorderThreshold;

    public Product(String id, String name, int quantity, int reorderThreshold) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.reorderThreshold = reorderThreshold;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getReorderThreshold() {
        return reorderThreshold;
    }

    public void increaseQuantity(int amount) {
        quantity += amount;
    }

    public void decreaseQuantity(int amount) throws IllegalArgumentException {
        if (amount > quantity) {
            throw new IllegalArgumentException("Insufficient stock for product: " + name);
        }
        quantity -= amount;
    }

    @Override
    public String toString() {
        return name + " (ID: " + id + ", Qty: " + quantity + ")";
    }
}
