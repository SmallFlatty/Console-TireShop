package TireProject.ItemEntity;

import java.io.Serial;
import java.io.Serializable;

public class Wheel extends Item implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    /// Properties
    private final String diameter;
    private final String width;
    private final String boltPattern;

    private final int typeItemId = 0;

    /// Constructors
    public Wheel(String name, String description, double price, int quantity,String diameter, String width, String boltPattern) {
        super(name, description, price, quantity);
        this.diameter = diameter;
        this.width = width;
        this.boltPattern = boltPattern;
    }

    public Wheel(String name, String description,String diameter, String width, String boltPattern) {
        super(name, description);
        this.diameter = diameter;
        this.width = width;
        this.boltPattern = boltPattern;
    }

    public int getTypeItemId() {
        return typeItemId;
    }

    @Override
    public String toString() {
        return super.toString() +
                "diameter='" + diameter + '\'' +
                ", width='" + width + '\'' +
                ", boltPattern='" + boltPattern + '\'' +
                "}";
    }
}
