package FinalProject;
import java.util.ArrayList;
import java.util.TreeMap;

public class ParkingManager {

    private TreeMap<String, Car> parkedCars = new TreeMap<>();

    public void parkCar(Car car) {
        parkedCars.put(car.getSlot(), car);
    }

    public void unparkCar(String slot) {
        parkedCars.remove(slot);
    }

    // Check if slot is occupied
    public boolean isSlotOccupied(String slot) {
        return parkedCars.containsKey(slot);
    }

    // Get car by slot
    public Car getCarBySlot(String slot) {
        return parkedCars.get(slot);
    }

    // Get all parked cars
    public ArrayList<Car> getAllParkedCars() {
        return new ArrayList<>(parkedCars.values());
    }

    // Get all vacant slots
    public ArrayList<String> getVacantSlots(ArrayList<String> allSlots) {
        ArrayList<String> vacant = new ArrayList<>();
        for (String slot : allSlots) {
            if (!parkedCars.containsKey(slot)) {
                vacant.add(slot);
            }
        }
        return vacant;
    }

    
    public Car searchByPlate(String plate) {
        for (Car car : parkedCars.values()) { 
            if (car.getPlate().equalsIgnoreCase(plate)) {
                return car;
            }
        }
        return null;
    }

    // Get occupied slots
    public ArrayList<String> getOccupiedSlots(ArrayList<String> allSlots) {
        ArrayList<String> occupied = new ArrayList<>();
        for (String slot : allSlots) {
            if (parkedCars.containsKey(slot)) {
                occupied.add(slot);
            }
        }
        return occupied;
    }
}