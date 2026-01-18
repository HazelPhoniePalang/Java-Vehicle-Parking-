package FinalProject;

public class Car {
    private String name;
    private String plate;
    private String slot;

    public Car(String name, String plate, String slot) {
        this.name = name;
        this.plate = plate;
        this.slot = slot;
    }

    public String getName() {
        return name;
    }

    public String getPlate() {
        return plate;
    }

    public String getSlot() {
        return slot;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Plate: " + plate + ", Slot: " + slot;
    }
}