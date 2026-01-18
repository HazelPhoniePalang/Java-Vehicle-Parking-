package FinalProject;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ParkingController {
    private ParkingManager manager;
    private ArrayList<String> allSlots;

    public ParkingController(ParkingManager manager, ArrayList<String> allSlots) {
        this.manager = manager;
        this.allSlots = allSlots;
    }

    public void parkVehicle(Component parent, Runnable refreshUI) {
        ArrayList<String> vacant = manager.getVacantSlots(allSlots);
        if (vacant.isEmpty()) {
            JOptionPane.showMessageDialog(parent, "No vacant slots.");
            return;
        }

        String name = JOptionPane.showInputDialog(parent, "Enter name:");
        if (name == null || name.isBlank()) return;

        String plate = JOptionPane.showInputDialog(parent, "Enter plate number:");
        if (plate == null || plate.isBlank()) return;

        String slot = (String) JOptionPane.showInputDialog(
                parent,
                "Select a slot:",
                "Choose Slot",
                JOptionPane.QUESTION_MESSAGE,
                null,
                vacant.toArray(),
                vacant.get(0)
        );

        if (slot != null) {
            Car car = new Car(name, plate, slot);
            manager.parkCar(car);
            refreshUI.run(); // pang refresh ra sa buttns and colors 
        }
    }

    public void unparkVehicle(Component parent, Runnable refreshUI) {
        ArrayList<String> occupied = manager.getOccupiedSlots(allSlots);
        if (occupied.isEmpty()) {
            JOptionPane.showMessageDialog(parent, "No cars to unpark.");
            return;
        }

        String slot = (String) JOptionPane.showInputDialog(
                parent,
                "Select slot to unpark:",
                "Unpark Vehicle",
                JOptionPane.QUESTION_MESSAGE,
                null,
                occupied.toArray(),
                occupied.get(0)
        );

        if (slot != null) {
            manager.unparkCar(slot);
            refreshUI.run();
        }
    }

    public void showSlotInfo(Component parent, String slotId) {
        Car car = manager.getCarBySlot(slotId);
        String message;
        if (car != null) {
            message = "Slot " + slotId + " is OCCUPIED\n" +
                      "Name: " + car.getName() + "\n" +
                      "Plate: " + car.getPlate();
        } else {
            message = "Slot " + slotId + " is VACANT.";
        }
        JOptionPane.showMessageDialog(parent, message);
    }

    public void showAllParkedCars(Component parent) {
        ArrayList<Car> cars = manager.getAllParkedCars();
        if (cars.isEmpty()) {
            JOptionPane.showMessageDialog(parent, "No vehicles parked.");
            return;
        }

        StringBuilder info = new StringBuilder();
        for (Car car : cars) {
            info.append(car.toString()).append("\n");
        }

        JTextArea area = new JTextArea(info.toString());
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JOptionPane.showMessageDialog(parent, new JScrollPane(area), "Parked Vehicles", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void searchVehicleByPlate(Component parent) {
        String plate = JOptionPane.showInputDialog(parent, "Enter plate number to search:");
        if (plate == null || plate.isBlank()) return;

        Car car = manager.searchByPlate(plate);
        if (car != null) {
            JOptionPane.showMessageDialog(parent,
                "Vehicle Found!\n\nName: " + car.getName() +
                "\nPlate: " + car.getPlate() +
                "\nSlot: " + car.getSlot(),
                "Search Result",
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(parent,
                "No vehicle found with plate number: " + plate,
                "Search Result",
                JOptionPane.WARNING_MESSAGE);
        }
    }

}
