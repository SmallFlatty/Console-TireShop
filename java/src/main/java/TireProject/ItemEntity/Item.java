package TireProject.ItemEntity;

import java.io.Serial;
import java.io.Serializable;

public class Item implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    /// Properties
    private final int id;
    private final String name;
    private final String description;
    private double price;
    private int quantity;

    private static int inexOfId = 1;
    /// Constructors
    Item(String name, String description, double price, int quantity) {
        this.id = inexOfId++;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    Item(String name, String description) {
        this.id = inexOfId++;
        this.name = name;
        this.description = description;
        this.price = 0.0;
        this.quantity = 0;
    }
    /// Getters
    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    /// Setters
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public static void setInexOfId(int inexOfId) {
        Item.inexOfId = inexOfId;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
