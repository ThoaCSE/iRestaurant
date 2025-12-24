import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Main Frame - Container for all panels
public class MainPanel extends JFrame {
    
    public MainPanel() {
        // Frame setup
        setTitle("iRestaurant - Role Selection");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(234, 242, 248));
        
        // Add custom panels
        add(new HeaderPanel(), BorderLayout.NORTH);
        add(new RoleSelectionPanel(this), BorderLayout.CENTER);
        add(new FooterPanel(), BorderLayout.SOUTH);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new MainPanel().setVisible(true);
        });
    }
}

// Header Panel - Top section with branding
class HeaderPanel extends JPanel {
    
    public HeaderPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(41, 128, 185));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Left side - Logo and welcome
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        leftPanel.setBackground(getBackground());
        
        JLabel logoLabel = new JLabel("iRestaurant");
        logoLabel.setFont(new Font("Arial", Font.BOLD, 28));
        logoLabel.setForeground(Color.WHITE);
        
        JLabel welcomeLabel = new JLabel("Welcome! Select your role to continue.");
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        welcomeLabel.setForeground(Color.WHITE);
        
        leftPanel.add(logoLabel);
        leftPanel.add(Box.createHorizontalStrut(30));
        leftPanel.add(welcomeLabel);
        
        // Right side - Status
        JLabel statusLabel = new JLabel("System Online - December 24, 2025");
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        statusLabel.setForeground(new Color(230, 240, 250));
        
        add(leftPanel, BorderLayout.WEST);
        add(statusLabel, BorderLayout.EAST);
    }
}

// Role Selection Panel - Main content with role buttons
class RoleSelectionPanel extends JPanel {
    private JFrame parentFrame;
    
    public RoleSelectionPanel(JFrame parent) {
        this.parentFrame = parent;
        setLayout(new GridBagLayout());
        setBackground(new Color(234, 242, 248));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 15, 10, 15);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        
        // Customer Role Button
        RoleButton customerBtn = new RoleButton(
            "Order as Customer",
            "🍔",
            "Browse menu and place orders",
            new Color(46, 204, 113)
        );
        customerBtn.addActionListener(e -> openCustomerPanel());
        add(customerBtn, gbc);
        
        // Waiter Role Button
        gbc.gridx = 1;
        RoleButton waiterBtn = new RoleButton(
            "Staff: Waiter",
            "🍽️",
            "Manage order deliveries",
            new Color(52, 152, 219)
        );
        waiterBtn.addActionListener(e -> openWaiterPanel());
        add(waiterBtn, gbc);
        
        // Admin Role Button
        gbc.gridx = 2;
        RoleButton adminBtn = new RoleButton(
            "Admin: Management",
            "⚙️",
            "Manage menu and system",
            new Color(230, 126, 34)
        );
        adminBtn.addActionListener(e -> openAdminPanel());
        add(adminBtn, gbc);
    }
    
    private void openCustomerPanel() {
        new CustomerOrderingPanel().setVisible(true);
        parentFrame.dispose();
    }
    
    private void openWaiterPanel() {
        new WaiterDeliveryPanel().setVisible(true);
        parentFrame.dispose();
    }
    
    private void openAdminPanel() {
        new AdminPanel().setVisible(true);
        parentFrame.dispose();
    }
}

// Role Button - Custom button component for each role
class RoleButton extends JPanel {
    private Color baseColor;
    private boolean isHovered = false;
    
    public RoleButton(String title, String icon, String description, Color color) {
        this.baseColor = color;
        setLayout(new BorderLayout());
        setBackground(color);
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(color.darker(), 3),
            BorderFactory.createEmptyBorder(20, 15, 20, 15)
        ));
        setPreferredSize(new Dimension(180, 200));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Icon
        JLabel iconLabel = new JLabel(icon, SwingConstants.CENTER);
        iconLabel.setFont(new Font("Arial", Font.PLAIN, 48));
        iconLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        // Title
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(Color.WHITE);
        
        // Description
        JLabel descLabel = new JLabel(description, SwingConstants.CENTER);
        descLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        descLabel.setForeground(new Color(255, 255, 255, 200));
        
        // Text panel
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setOpaque(false);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        descLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        textPanel.add(titleLabel);
        textPanel.add(Box.createVerticalStrut(5));
        textPanel.add(descLabel);
        
        add(iconLabel, BorderLayout.NORTH);
        add(textPanel, BorderLayout.CENTER);
        
        // Hover effect
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                setBackground(baseColor.brighter());
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                setBackground(baseColor);
            }
        });
    }
    
    public void addActionListener(ActionListener listener) {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
            }
        });
    }
}

// Footer Panel - Bottom section with help and exit
class FooterPanel extends JPanel {
    
    public FooterPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        setBackground(new Color(236, 240, 241));
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        // Help label
        JLabel helpLabel = new JLabel("📞 Contact support if you experience any issues");
        helpLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        helpLabel.setForeground(Color.GRAY);
        
        // Exit button
        JButton exitButton = new JButton("Exit Application");
        exitButton.setFont(new Font("Arial", Font.PLAIN, 12));
        exitButton.setFocusPainted(false);
        exitButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to exit?",
                "Exit Confirmation",
                JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        
        add(helpLabel);
        add(exitButton);
    }
}
