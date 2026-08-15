package TireProject.ItemEntity;

import java.io.Serial;
import java.io.Serializable;

public class Tire extends Item implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    /// Properties
    private String tireSize;
    private char speedRating;
    private String seasonType;

    /// Constructors
    Tire(String name, String description, double price, int quantity,String tireSize, char speedRating, String seasonType) {
        super(name, description, price, quantity);
        this.tireSize = tireSize;
        this.speedRating = speedRating;
        this.seasonType = seasonType;
    }

    Tire(String name, String description,String tireSize,String seasonType) {
        super(name, description);
        this.tireSize = tireSize;
        this.seasonType = seasonType;
    }
    /// Getters
    public String getTireSize() {
        return tireSize;
    }

    public String getSeasonType() {
        return seasonType;
    }

    public char getSpeedRating() {
        return speedRating;
    }
    /// Setters
    public void setTireSize(String tireSize) {
        this.tireSize = tireSize;
    }

    public void setSpeedRating(char speedRating) {
        this.speedRating = speedRating;
    }

    public void setSeasonType(String seasonType) {
        this.seasonType = seasonType;
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
