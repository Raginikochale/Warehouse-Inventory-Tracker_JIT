import java.util.*;

public class Warehouse {
    private final Map<String, Product> products = new HashMap<>();
    private final List<StockObserver> observers = new ArrayList<>();

    public void addObserver(StockObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(Product product) {
        if (product.getQuantity() < product.getReorderThreshold()) {
            for (StockObserver observer : observers) {
                observer.onLowStock(product);
            }
        }
    }

    public void addProduct(Product product) {
        if (products.containsKey(product.getId())) {
            System.out.println("Product with ID " + product.getId() + " already exists.");
            return;
        }
        products.put(product.getId(), product);
        System.out.println("Added product: " + product);
    }

    public void receiveShipment(String productId, int quantity) {
        Product product = products.get(productId);
        if (product == null) {
            System.out.println("Invalid product ID: " + productId);
            return;
        }
        product.increaseQuantity(quantity);
        System.out.println("Shipment received. Updated product: " + product);
    }

    public void fulfillOrder(String productId, int quantity) {
        Product product = products.get(productId);
        if (product == null) {
            System.out.println("Invalid product ID: " + productId);
            return;
        }
        try {
            product.decreaseQuantity(quantity);
            System.out.println("Order fulfilled. Updated product: " + product);
            notifyObservers(product);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void displayInventory() {
        System.out.println("\nCurrent Inventory:");
        for (Product p : products.values()) {
            System.out.println(p);
        }
        System.out.println();
    }
}
