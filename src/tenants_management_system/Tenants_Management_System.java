package tenants_management_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Tenants_Management_System {
    
    private JFrame frame;
    private JPanel loginPanel, registerPanel;
    private JTextField loginEmailInput, fullNameInput, phoneNoInput, emailInput;
    private JPasswordField loginPasswordInput, passwordInput, confirmPasswordInput;
    private MysqlDatabaseConn dbconn;
    
    public Tenants_Management_System() {
        dbconn = new MysqlDatabaseConn();
        frame = new JFrame("Tenants Management System");
        frame.setSize(1366, 768);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        loginPanel = loginPanel();
        registerPanel = registerPanel();

        frame.add(loginPanel);
        frame.add(registerPanel);
        frame.setVisible(true);
    }
    
    private JPanel loginPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(400, 200, 500, 350);
        panel.setBackground(Color.LIGHT_GRAY);
        
        JLabel loginEmailLabel = new JLabel("Email:");
        loginEmailLabel.setBounds(50,50,100,30);
        loginEmailInput = new JTextField("");
        loginEmailInput.setBounds(200,50,200,30);
        JLabel loginPasswordLabel = new JLabel("Password:");
        loginPasswordLabel.setBounds(50,100,100,30);
        loginPasswordInput = new JPasswordField("");
        loginPasswordInput.setBounds(200,100,200,30);
        JButton loginbtn = new JButton("Login");
        loginbtn.setBounds(50,150,150,30);
        JButton forgotPasswordbtn = new JButton("Forgot Password?");
        forgotPasswordbtn.setBounds(250,150,150,30);
        JLabel registerLabel = new JLabel("Don't have an account?");
        registerLabel.setBounds(50,200,150,30);
        JButton loginRegisterbtn = new JButton("Register");
        loginRegisterbtn.setBounds(250,200,150,30);
        
        panel.add(loginEmailLabel);
        panel.add(loginEmailInput);
        panel.add(loginPasswordLabel);
        panel.add(loginPasswordInput);
        panel.add(loginbtn);
        panel.add(forgotPasswordbtn);
        panel.add(registerLabel);
        panel.add(loginRegisterbtn);

        loginRegisterbtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                switchToRegisterPanel();
            }
        });
        loginbtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });
        
        return panel;
    }
    
    private JPanel registerPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(400, 100, 500, 400);
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setVisible(false);
        
        JLabel fullNameLabel = new JLabel("Landlord's Name:");
        fullNameLabel.setBounds(50,50,100,30);
        fullNameInput = new JTextField("");
        fullNameInput.setBounds(200,50,200,30);
        JLabel phoneNoLabel = new JLabel("Phone Number:");
        phoneNoLabel.setBounds(50,100,100,30);
        phoneNoInput = new JTextField("");
        phoneNoInput.setBounds(200,100,200,30);
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50,150,100,30);
        emailInput = new JTextField("");
        emailInput.setBounds(200,150,200,30);
        JLabel passwordLabel = new JLabel("New Password:");
        passwordLabel.setBounds(50,200,100,30);
        passwordInput = new JPasswordField("");
        passwordInput.setBounds(200,200,200,30);
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setBounds(50,250,100,30);
        confirmPasswordInput = new JPasswordField("");
        confirmPasswordInput.setBounds(200,250,200,30);
        JButton registerbtn = new JButton("Register");
        registerbtn.setBounds(150,300,150,30);
        JLabel loginLabel = new JLabel("Already have an account?");
        loginLabel.setBounds(50,350,150,30);
        JButton registerLoginbtn = new JButton("Login");
        registerLoginbtn.setBounds(250,350,150,30);
        
        panel.add(fullNameLabel);
        panel.add(fullNameInput);
        panel.add(phoneNoLabel);
        panel.add(phoneNoInput);
        panel.add(emailLabel);
        panel.add(emailInput);
        panel.add(passwordLabel);
        panel.add(passwordInput);
        panel.add(confirmPasswordLabel);
        panel.add(confirmPasswordInput);
        panel.add(registerbtn);
        panel.add(loginLabel);
        panel.add(registerLoginbtn);
        
        registerLoginbtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                switchToLoginPanel();
            }
        });
        registerbtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                handleRegister();
            }
        });
                
        return panel;
    }

    private void switchToRegisterPanel() {
        loginPanel.setVisible(false);
        registerPanel.setVisible(true);
    }

    private void switchToLoginPanel() {
        registerPanel.setVisible(false);
        loginPanel.setVisible(true);
    }

    private void handleRegister() {
        if (fullNameInput.getText().isEmpty() || phoneNoInput.getText().isEmpty() ||
            emailInput.getText().isEmpty() || passwordInput.getText().isEmpty() || 
            confirmPasswordInput.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "All fields must be filled!", "", JOptionPane.WARNING_MESSAGE);
        } else if (!passwordInput.getText().equals(confirmPasswordInput.getText())) {
            JOptionPane.showMessageDialog(frame, "Passwords do not match!", "", JOptionPane.WARNING_MESSAGE);
        } else {
            String fullName = fullNameInput.getText();
            String phoneNumber = phoneNoInput.getText();
            String email = emailInput.getText();
            String password = passwordInput.getText();

            if((email.contains("@")) && (email.contains("."))) {
                int successMsg = dbconn.addUsers(fullName, phoneNumber, email, password);
                if (successMsg == 1) {
                    JOptionPane.showMessageDialog(frame, "Successfully registered!");
                    clearRegisterFields();
                } else {
                    JOptionPane.showMessageDialog(frame, "Registration failed!", "", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid Email!!!", "", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void handleLogin() {
        String email = loginEmailInput.getText();
        String password = String.valueOf(loginPasswordInput.getPassword());

        Landlord landlord = dbconn.loginUser(email, password);
        if (landlord != null) {
            frame.setVisible(false);
            Dashboard db = new Dashboard(landlord);
        } else {
            JOptionPane.showMessageDialog(frame, "Wrong password or email!", "", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void clearRegisterFields() {
        fullNameInput.setText("");
        phoneNoInput.setText("");
        emailInput.setText("");
        passwordInput.setText("");
        confirmPasswordInput.setText("");
    }

    public static void main(String[] args) {
        Tenants_Management_System tms = new Tenants_Management_System();
    }
}
