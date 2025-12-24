import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AdminPanel extends JFrame {
    private Menu menu;
    private JTable menuTable;
    private DefaultTableModel tableModel;
    private JTextField nameField, priceField, imagePathField;
    private JTextArea descriptionArea;
    private JLabel statusLabel;

    public AdminPanel() {
        menu = new Menu();
        initializeGUI();
        loadSampleData();
    }

    private void initializeGUI() {
        setTitle("iRestaurant - Admin Panel");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(41, 128, 185));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel titleLabel = new JLabel("iRestaurant Admin Panel - Menu Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        
        JLabel userLabel = new JLabel("Admin: Bill");
        userLabel.setForeground(Color.WHITE);
        
        headerPanel.add(titleLabel, BorderLayout.WEST);
        headerPanel.add(userLabel, BorderLayout.EAST);
        add(headerPanel, BorderLayout.NORTH);

        // Main content area with split pane
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        
        // Top: Menu table view
        JPanel viewPanel = createViewPanel();
        splitPane.setTopComponent(viewPanel);
        
        // Bottom: Add item form
        JPanel addPanel = createAddPanel();
        splitPane.setBottomComponent(addPanel);
        
        splitPane.setDividerLocation(300);
        add(splitPane, BorderLayout.CENTER);

        // Footer with status
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        statusLabel = new JLabel("Ready");
        footerPanel.add(statusLabel);
        add(footerPanel, BorderLayout.SOUTH);
    }

    private JPanel createViewPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Current Menu Items"));

        // Table setup
        String[] columnNames = {"Name", "Description", "Price", "Image Path"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        menuTable = new JTable(tableModel);
        menuTable.getColumnModel().getColumn(1).setPreferredWidth(250);
        
        JScrollPane scrollPane = new JScrollPane(menuTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Toolbar
        JPanel toolbarPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> refreshMenuTable());
        
        JButton deleteButton = new JButton("Delete Selected");
        deleteButton.addActionListener(e -> deleteSelectedItem());
        
        toolbarPanel.add(refreshButton);
        toolbarPanel.add(deleteButton);
        panel.add(toolbarPanel, BorderLayout.NORTH);

        return panel;
    }

    private JPanel createAddPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Add New Menu Item"));

        // Form panel with grid layout
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Name field
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        nameField = new JTextField(20);
        formPanel.add(nameField, gbc);

        // Price field
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        formPanel.add(new JLabel("Price:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        priceField = new JTextField(20);
        formPanel.add(priceField, gbc);

        // Description area
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0;
        formPanel.add(new JLabel("Description:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0; gbc.fill = GridBagConstraints.BOTH;
        descriptionArea = new JTextArea(3, 20);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        JScrollPane descScroll = new JScrollPane(descriptionArea);
        formPanel.add(descScroll, gbc);

        // Image path field
        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0; gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(new JLabel("Image Path:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        imagePathField = new JTextField(20);
        formPanel.add(imagePathField, gbc);

        panel.add(formPanel, BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton addButton = new JButton("Add Item");
        addButton.setBackground(new Color(46, 204, 113));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.addActionListener(e -> addMenuItem());
        
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> clearForm());
        
        buttonPanel.add(clearButton);
        buttonPanel.add(addButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void loadSampleData() {
        // Add some initial sample items
        menu.addMenuItem(new RestaurantMenuItem("Hamburger", "Classic beef burger with lettuce and tomato", 8.99, "/images/hamburger.jpg"));
        menu.addMenuItem(new RestaurantMenuItem("Cheese Pizza", "12 inch pizza with mozzarella", 12.50, "/images/pizza.jpg"));
        menu.addMenuItem(new RestaurantMenuItem("Chicken Salad", "Fresh greens with grilled chicken", 9.75, "/images/salad.jpg"));
        menu.addMenuItem(new RestaurantMenuItem("Coke", "Coca-Cola 12oz can", 2.50, "/images/coke.jpg"));
        refreshMenuTable();
        statusLabel.setText("Sample menu loaded - " + menu.countItems() + " items");
    }

    private void refreshMenuTable() {
        tableModel.setRowCount(0);
        for (RestaurantMenuItem item : menu.getItems()) {
            Object[] rowData = {
                item.getName(),
                item.getDescription(),
                String.format("$%.2f", item.getPrice()),
                item.getImagePath()
            };
            tableModel.addRow(rowData);
        }
        statusLabel.setText("Menu refreshed - " + menu.countItems() + " items");
    }

    private void addMenuItem() {
        String name = nameField.getText().trim();
        String priceText = priceField.getText().trim();
        String description = descriptionArea.getText().trim();
        String imagePath = imagePathField.getText().trim();

        // Validation
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name is required", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double price;
        try {
            price = Double.parseDouble(priceText);
            if (price <= 0) {
                JOptionPane.showMessageDialog(this, "Price must be greater than 0", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid price format", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (description.isEmpty()) {
            description = "No description available";
        }

        if (imagePath.isEmpty()) {
            imagePath = "/images/default.jpg";
        }

        // Create and add menu item
        RestaurantMenuItem newItem = new RestaurantMenuItem(name, description, price, imagePath);
        menu.addMenuItem(newItem);
        refreshMenuTable();
        clearForm();
        
        statusLabel.setText("Item added successfully! Total items: " + menu.countItems());
        JOptionPane.showMessageDialog(this, "Menu item '" + name + "' added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void deleteSelectedItem() {
        int selectedRow = menuTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an item to delete", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String itemName = (String) tableModel.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete '" + itemName + "'?", 
            "Confirm Delete", 
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            // Find and remove the item from menu
            RestaurantMenuItem toRemove = null;
            for (RestaurantMenuItem item : menu.getItems()) {
                if (item.getName().equals(itemName)) {
                    toRemove = item;
                    break;
                }
            }
            if (toRemove != null) {
                menu.getItems().remove(toRemove);
                refreshMenuTable();
                statusLabel.setText("Item deleted. Total items: " + menu.countItems());
            }
        }
    }

    private void clearForm() {
        nameField.setText("");
        priceField.setText("");
        descriptionArea.setText("");
        imagePathField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            AdminPanel panel = new AdminPanel();
            panel.setVisible(true);
        });
    }
}
