package FinalProject;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.ArrayList;

public class ParkingGUI {
    private JFrame frame;
    private JPanel mapPanel;
    private ParkingManager manager = new ParkingManager();
    private ParkingController controller;
    private ArrayList<JButton> slotButtons = new ArrayList<>();
    private ArrayList<String> allSlots = new ArrayList<>();

    public ParkingGUI() {
        initializeSlots();
        controller = new ParkingController(manager, allSlots);

        // FRAME SETTINGS
        frame = new JFrame("Free Parking System");
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout(15, 15));
        frame.getContentPane().setBackground(new Color(240, 242, 245));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // HEADER PANEL
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(100, 128, 146));
        headerPanel.setPreferredSize(new Dimension(frame.getWidth(), 60));
        JLabel title = new JLabel("Free Parking System");
        title.setFont(new Font("Tahoma", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
        headerPanel.add(title);

        // CONTROL PANEL
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.setBackground(new Color(209, 216, 220));
        controlPanel.setPreferredSize(new Dimension(260, 0)); 
        controlPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        new LineBorder(new Color(100, 128, 146), 2, true),
                        "Actions",
                        TitledBorder.CENTER,
                        TitledBorder.TOP,
                        new Font("Tahoma", Font.BOLD, 14),
                        new Color(60, 60, 60)
                ),
                BorderFactory.createEmptyBorder(20, 25, 20, 25) 
        ));

        // BUTTONS
        JButton parkBtn = createStyledButton("Park Vehicle");
        JButton unparkBtn = createStyledButton("Unpark Vehicle");
        JButton viewBtn = createStyledButton("View All Parked Cars");
        JButton searchBtn = createStyledButton("Search Vehicle by Plate");

        parkBtn.addActionListener(e -> controller.parkVehicle(frame, this::refreshButtonColors));
        unparkBtn.addActionListener(e -> controller.unparkVehicle(frame, this::refreshButtonColors));
        viewBtn.addActionListener(e -> controller.showAllParkedCars(frame));
        searchBtn.addActionListener(e -> controller.searchVehicleByPlate(frame));

        // space between buttons
        controlPanel.add(Box.createVerticalGlue());
        controlPanel.add(parkBtn);
        controlPanel.add(Box.createVerticalStrut(25)); 
        controlPanel.add(unparkBtn);
        controlPanel.add(Box.createVerticalStrut(25));
        controlPanel.add(viewBtn);
        controlPanel.add(Box.createVerticalStrut(25));
        controlPanel.add(searchBtn);
        controlPanel.add(Box.createVerticalGlue());

        // MAP PANEL
        mapPanel = new JPanel(new GridBagLayout());
        mapPanel.setBackground(new Color(250,250,250));
        mapPanel.setBorder(BorderFactory.createTitledBorder(
                new LineBorder(new Color(100, 128, 146), 2, true),
                "Parking Map",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("Tahoma", Font.BOLD, 14),
                new Color(60, 60, 60)
        ));
        populateMap();

        // ADD TO FRAME
        frame.add(headerPanel, BorderLayout.NORTH);
        frame.add(controlPanel, BorderLayout.WEST);
        frame.add(mapPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    
    
    private void initializeSlots() {
        for (int i = 1; i <= 6; i++) allSlots.add("M" + i);
        for (int i = 1; i <= 6; i++) allSlots.add("C" + i);
    }

    private void populateMap() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 15, 8, 15);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        JLabel motoLabel = new JLabel("Motorcycle Slots");
        motoLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        mapPanel.add(motoLabel, gbc);

        gbc.gridwidth = 1;
        for (int i = 0; i < 6; i++) {
            JButton btn = createSlotButton("M" + (i + 1));
            gbc.gridx = i % 2;
            gbc.gridy = (i / 2) + 1;
            mapPanel.add(btn, gbc);
            slotButtons.add(btn);
        }

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        JLabel carLabel = new JLabel("Car Slots");
        carLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        mapPanel.add(carLabel, gbc);

        gbc.gridwidth = 1;
        for (int i = 0; i < 6; i++) {
            JButton btn = createSlotButton("C" + (i + 1));
            gbc.gridx = i % 2;
            gbc.gridy = (i / 2) + 5;
            mapPanel.add(btn, gbc);
            slotButtons.add(btn);
        }
    }

    private JButton createStyledButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Tahoma", Font.BOLD, 15));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(100, 128, 146));
        btn.setFocusPainted(false);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(220, 65)); 
        btn.setMaximumSize(new Dimension(220, 65));
        btn.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        btn.setOpaque(true);
        btn.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(100, 128, 146), 2, true),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));

        // Hover effect
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(190, 215, 235));
                btn.setForeground(Color.DARK_GRAY);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(100, 128, 146));
                btn.setForeground(Color.WHITE);

            }
        });
        
        return btn;
    }

    private JButton createSlotButton(String label) {
        JButton btn = new JButton(label);
        btn.setFont(new Font("Tahoma", Font.BOLD, 12));
        btn.setPreferredSize(new Dimension(100, 40));
        updateButtonColor(btn, label);
        btn.addActionListener(e -> controller.showSlotInfo(frame, label));
        return btn;
    }

    private void updateButtonColor(JButton btn, String slot) {
        if (manager.isSlotOccupied(slot)) {
            btn.setBackground(new Color(255, 153, 153)); // red
        } else {
            btn.setBackground(new Color(153, 255, 153)); // green
        }
    }

    private void refreshButtonColors() {
        for (JButton btn : slotButtons) {
            updateButtonColor(btn, btn.getText());
        }
    }

    public static void main(String[] args) {
        new ParkingGUI();
    }
}