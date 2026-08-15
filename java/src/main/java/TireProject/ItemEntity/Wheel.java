package TireProject.ItemEntity;

import java.io.Serial;
import java.io.Serializable;

public class Wheel extends Item implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    /// Properties
    private String diameter;
    private String width;
    private String boltPattern;
    /// Constructors
    Wheel(String name, String description, double price, int quantity,String diameter, String width, String boltPattern) {
        super(name, description, price, quantity);
        this.diameter = diameter;
        this.width = width;
        this.boltPattern = boltPattern;
    }

    Wheel(String name, String description) {
        super(name, description);
    }
    /// Getters
    public String getDiameter() {
        return diameter;
    }

    public String getWidth() {
        return width;
    }

    public String getBoltPattern() {
        return boltPattern;
    }

    @Override
    public String toString() {
        return super.toString() + "\n Wheel{" +
                "diameter='" + diameter + '\'' +
                ", width='" + width + '\'' +
                ", boltPattern='" + boltPattern + '\'' +
                '}';
    }
}
