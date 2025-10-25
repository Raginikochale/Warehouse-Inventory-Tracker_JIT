public class MainApp {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();

        // Add alert service observer
        warehouse.addObserver(new AlertService());

        // Add products
        Product laptop = new Product("P001", "Laptop", 0, 5);
        Product phone = new Product("P002", "Phone", 0, 3);

        warehouse.addProduct(laptop);
        warehouse.addProduct(phone);

        // Receive shipments
        warehouse.receiveShipment("P001", 10);
        warehouse.receiveShipment("P002", 5);

        warehouse.displayInventory();

        // Fulfill orders
        warehouse.fulfillOrder("P001", 6); // Triggers low stock alert
        warehouse.fulfillOrder("P002", 3); // Triggers low stock alert

        warehouse.displayInventory();

        // Invalid operations
        warehouse.fulfillOrder("P003", 1); // Invalid ID
        warehouse.fulfillOrder("P002", 10); // Insufficient stock
    }
}
