package TireProject.ItemEntity;

import java.io.Serial;
import java.io.Serializable;

public class Tire extends Item implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    /// Properties
    private final String tireSize;
    private final char speedRating;
    private final String seasonType;

    /// Constructors
    public Tire(String name, String description, double price, int quantity,String tireSize, char speedRating, String seasonType) {
        super(name, description, price, quantity);
        this.tireSize = tireSize;
        this.speedRating = speedRating;
        this.seasonType = seasonType;
    }

    public Tire(String name, String description,String tireSize,String seasonType, char speedRating) {
        super(name, description);
        this.tireSize = tireSize;
        this.seasonType = seasonType;
        this.speedRating = speedRating;
    }

    @Override
    public String toString() {
        return super.toString() + "\nTire{" +
                "tireSize='" + tireSize + '\'' +
                ", speedRating=" + speedRating +
                ", seasonType='" + seasonType + '\'' +
                '}';
    }
}
