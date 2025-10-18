package tenants_management_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Vector;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.Date;
import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;

public class Dashboard {
    
    private JFrame frame;
    private MysqlDatabaseConn dbconn = new MysqlDatabaseConn();
    private Landlord landlord;
    private JPanel  apartmentsPanel, tenantsPanel, roomsPanel, settingsPanel;
    private JTable apartmentsTable, tenantsTable, roomsTable;
    private JTextField apartmentsNotxt, apartmentsNametxt, address_apartmentstxt, no_of_rooms_apartmentstxt;
    private String[] apartments;
    
    
    
    public Dashboard(Landlord landlord) {
        this.landlord = landlord;
        frame = new JFrame();

        JLabel hellomsg = new JLabel("Hello, " + landlord.getFull_name());
        hellomsg.setBounds(600,20,200,30);
        JButton apartmentsbtn = new JButton("Apartments");
        apartmentsbtn.setBounds(50,70,150,50);
        JButton tenantsbtn = new JButton("Tenants");
        tenantsbtn.setBounds(50,150,150,50);
        JButton roomsbtn = new JButton("Rooms");
        roomsbtn.setBounds(50,230,150,50);
        JButton settingsbtn = new JButton("Settings");
        settingsbtn.setBounds(50,310,150,50);
        JButton logoutbtn = new JButton("Logout");
        logoutbtn.setBounds(50,390,150,50);

        apartmentsPanel = apartmentsPanel();
        tenantsPanel = tenantsPanel();
        roomsPanel = roomsPanel();
        settingsPanel = settingsPanel();
        
        frame.add(hellomsg);
        frame.add(apartmentsbtn);
        frame.add(tenantsbtn);
        frame.add(roomsbtn);
        frame.add(settingsbtn);
        frame.add(logoutbtn);
        frame.add(apartmentsPanel);
        frame.add(tenantsPanel);
        frame.add(roomsPanel);
        frame.add(settingsPanel);
        
        apartmentsbtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                switchToApartmentsPanel();
            }
        });
        
       tenantsbtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                switchToTenantsPanel();
            }
        });
        
        roomsbtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                switchToRoomsPanel();
            }
        });
        
        settingsbtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                switchToSettingsPanel();
            }
        });
        
        logoutbtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                logout();
            }
        });

        frame.setLayout(null);
        frame.setSize(1366,768);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
        
        
        
        //Appartments start
    private JPanel apartmentsPanel() {
        JPanel panel = new JPanel();
        
        JLabel titlemsg = new JLabel("APARTMENTS");
        titlemsg.setBounds(400,10,480,30);
        
        JLabel apartmentsNo = new JLabel("Apartments No.:");
        apartmentsNo.setBounds(870, 50, 200, 30);
        JLabel apartmentsName = new JLabel("Apartments Name:");
        apartmentsName.setBounds(870, 150, 200, 30);
        JLabel address_apartments = new JLabel("Address:");
        address_apartments.setBounds(870, 250, 200, 30);
        JLabel no_of_rooms_apartments = new JLabel("No of rooms:");
        no_of_rooms_apartments.setBounds(870, 350, 200, 30);
        
        apartmentsNotxt = new JTextField("");
        apartmentsNotxt.setBounds(870, 85, 200, 30);
        apartmentsNotxt.setEditable(false);
        apartmentsNametxt = new JTextField("");
        apartmentsNametxt.setBounds(870, 185, 200, 30);
        address_apartmentstxt = new JTextField("");
        address_apartmentstxt.setBounds(870, 285, 200, 30);
        no_of_rooms_apartmentstxt = new JTextField("");
        no_of_rooms_apartmentstxt.setBounds(870, 385, 200, 30);
       
        JButton delete_apartmentsbtn = new JButton("DELETE");
        delete_apartmentsbtn.setBounds(980,450,90,30);
        
        String data[][] = {};
        String columns[] = {"Apartment No.","Apartment Name","Address","No. of rooms"};
        DefaultTableModel tableModel = new DefaultTableModel(data,columns);
        apartmentsTable = new JTable();
        apartmentsTable.setModel(tableModel);
        JScrollPane sp = new JScrollPane(apartmentsTable);
        sp.setBounds(50,50,800,500);
        
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(null);
        
        panel.add(titlemsg);
        panel.add(sp);
        panel.add(apartmentsNo);
        panel.add(apartmentsName);
        panel.add(address_apartments);
        panel.add(no_of_rooms_apartments);
        panel.add(apartmentsNotxt);
        panel.add(apartmentsNametxt);
        panel.add(address_apartmentstxt);
        panel.add(no_of_rooms_apartmentstxt);
        panel.add(delete_apartmentsbtn);
        
        delete_apartmentsbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String apartment_no = apartmentsNotxt.getText();
                int result = dbconn.deleteApartment(apartment_no);
                if(result != 0) {
                    JOptionPane.showMessageDialog(frame,"Apartment deleted successfully.");
                    updateApartmentsTable();
                } else {
                    JOptionPane.showMessageDialog(frame,"Apartment not deleted!");
                }
            }
        });
        
        
        panel.setBounds(220,70,1100,600);
        
        updateApartmentsTable();
        
        return panel;
    }
        //Apartments end
        
    
    private void update_apartments() {
        apartments = dbconn.getApartments(landlord.getLandlord_id());
    }
        //Tenants start
    private JPanel tenantsPanel() {
        
        JPanel panel = new JPanel();
        
        JLabel titlemsg = new JLabel("TENANTS");
        titlemsg.setBounds(400,10,480,30);
        
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(null);
        panel.add(titlemsg);
        
        panel.setBounds(220,70,1100,600);
        panel.setVisible(false);
        
        String tenants_data[][] = {};
        String tenants_columns[] = {"Tenant No.","Full Name","Phone No.","Email","ID Number","Rent Due Date","Room No."};
        DefaultTableModel tenantsTableModel = new DefaultTableModel(tenants_data,tenants_columns);
        tenantsTable = new JTable();
        tenantsTable.setModel(tenantsTableModel);
        JScrollPane tenants_sp = new JScrollPane(tenantsTable);
        tenants_sp.setBounds(50,50,800,500);
        
        panel.setLayout(null);
        
        //assign tenants a room
        JLabel assign_rooms = new JLabel("Assign tenants a room.");
        assign_rooms.setBounds(900, 50, 150, 30);
        JLabel after_assign_rooms = new JLabel("-----------------------------------");
        after_assign_rooms.setBounds(900, 90, 150, 10);
        JLabel tenant_no = new JLabel("Tenant No.");
        tenant_no.setBounds(900, 100, 150, 30);
        JTextField tenant_notxt = new JTextField("");
        tenant_notxt.setBounds(900, 140, 150, 30);
        JLabel after_tenant_no = new JLabel("-----------------------------------");
        after_tenant_no.setBounds(900, 180, 150, 10);
        JLabel apartment_no = new JLabel("Apartment No.");
        apartment_no.setBounds(900, 200, 150, 30);
        
        update_apartments();
        
        JComboBox apartment_notxt = new JComboBox(apartments);
        apartment_notxt.setBounds(900, 240, 150, 30);
        JLabel after_apartment_no = new JLabel("-----------------------------------");
        after_apartment_no.setBounds(900, 280, 150, 10);
        JLabel room_no = new JLabel("Room No.");
        room_no.setBounds(900, 300, 150, 30);
        JComboBox room_notxt = new JComboBox();
        room_notxt.setBounds(900, 340, 150, 30);
        JLabel after_room_no = new JLabel("-----------------------------------");
        after_room_no.setBounds(900, 380, 150, 10);
        JLabel rent_due_date = new JLabel("Rent Due Date:");
        rent_due_date.setBounds(900, 400, 150, 30);
        JDateChooser rent_due_datetxt = new JDateChooser();
        rent_due_datetxt.setDateFormatString("yyyy-MM-dd");
        rent_due_datetxt.setBounds(900, 440, 150, 30);
        JLabel after_date = new JLabel("-----------------------------------");
        after_date.setBounds(900, 480, 150, 10);
        JButton assign_roomsbtn = new JButton("Assign room");
        assign_roomsbtn.setBounds(900, 500, 150, 30);
        
        apartment_notxt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String selectedApartment = (String) apartment_notxt.getSelectedItem();

                String[] rooms = dbconn.getRoomsForApartment(selectedApartment);
            
                room_notxt.removeAllItems();
            
                for (String room : rooms) {
                    room_notxt.addItem(room);
                }
            }
        });
        
        panel.add(tenants_sp);
        panel.add(assign_rooms);
        panel.add(after_assign_rooms);
        panel.add(tenant_no);
        panel.add(tenant_notxt);
        panel.add(after_tenant_no);
        panel.add(apartment_no);
        panel.add(apartment_notxt);
        panel.add(after_apartment_no);
        panel.add(room_no);
        panel.add(room_notxt);
        panel.add(after_room_no);
        panel.add(rent_due_date);
        panel.add(rent_due_datetxt);
        panel.add(after_date);
        panel.add(assign_roomsbtn);
        
        tenantsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Get the selected row index
                int selectedRow = tenantsTable.getSelectedRow();

                if (selectedRow != -1) {  // Check if a row is actually selected
                    // Get the values of the selected row and display them in the text fields
                    tenant_notxt.setText((String) tenantsTable.getValueAt(selectedRow, 0));
                }
            }
        });
        
        assign_roomsbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String tenant_no = tenant_notxt.getText();
                String room_no = (String) room_notxt.getSelectedItem();
                String apartment_no = (String) apartment_notxt.getSelectedItem();
                Date due_date = rent_due_datetxt.getDate();
                
                int result = dbconn.assignTenantsRoom(tenant_no, room_no, apartment_no, due_date.toString());
                if(result != 0) {
                    JOptionPane.showMessageDialog(frame,"Room assigned successfully.");
                    updateTenantsTable();
                    updateRoomsTable();
                } else {
                    JOptionPane.showMessageDialog(frame,"Room not assigned!");
                }

            }
        });
        
        updateTenantsTable();
        
        return panel;
    }
        //Tenants end
    private JPanel roomsPanel() {
        
        //Rooms start
        JPanel panel = new JPanel();
        
        JLabel titlemsg = new JLabel("ROOMS");
        titlemsg.setBounds(400,10,480,30);
        
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(null);
        panel.add(titlemsg);
        
        panel.setBounds(220,70,1100,600);
        panel.setVisible(false);
        
        String rooms_data[][] = {};
        String rooms_columns[] = {"Room No.","Apartment No.","Room Type","Room Status","Room Price","Paid"};
        DefaultTableModel roomsTableModel = new DefaultTableModel(rooms_data,rooms_columns);
        roomsTable = new JTable();
        roomsTable.setModel(roomsTableModel);
        JScrollPane rooms_sp = new JScrollPane(roomsTable);
        rooms_sp.setBounds(50,50,800,500);
        
        panel.setLayout(null);
        
        panel.add(rooms_sp);
        
        panel.setBounds(220,70,1100,600);
        
        updateRoomsTable();
        return panel;
    }
        //rooms end
        
        //settings start
    private JPanel settingsPanel() {
        JPanel panel = new JPanel();
        JButton editprofilebtn = new JButton("Edit Profile");
        editprofilebtn.setBounds(50,250,200,50);
        JButton addtenantbtn = new JButton("Add Tenant");
        addtenantbtn.setBounds(50,400,200,50);
        JButton addapartmentbtn = new JButton("Add Apartment");
        addapartmentbtn.setBounds(50,100,200,50);
        
        //add apartments start
        JPanel addapartmentPanel = new JPanel();
        
        JLabel apartment_no = new JLabel("Appartment No.");
        apartment_no.setBounds(50,20,150,30);
        JLabel apartment_name = new JLabel("Apartment Name");
        apartment_name.setBounds(50,80,150,30);
        JLabel address = new JLabel("Address");
        address.setBounds(50,140,150,30);
        JLabel room_type = new JLabel("Rooms Type");
        room_type.setBounds(50,200,150,30);
        JLabel no_of_rooms = new JLabel("No. of rooms");
        no_of_rooms.setBounds(50,260,150,30);
        JLabel each_room_price = new JLabel("Price of each room");
        each_room_price.setBounds(50,320,150,30);
        JTextField txtapartment_no = new JTextField("");
        txtapartment_no.setBounds(250,20,300,30);
        JTextField txtapartment_name = new JTextField("");
        txtapartment_name.setBounds(250,80,300,30);
        JTextField txtaddress = new JTextField("");
        txtaddress.setBounds(250,140,300,30);
        String room_types[] = new String[] {"Single","Double","Bedsitter","One Bedroom","Two Bedroom","3 Bedroom"};
        JComboBox rooms_type_cb = new JComboBox(room_types);
        rooms_type_cb.setBounds(250,200,300,30);
        JTextField txtno_of_rooms = new JTextField("");
        txtno_of_rooms.setBounds(250,260,300,30);
        JTextField txteach_room_price = new JTextField("");
        txteach_room_price.setBounds(250,320,300,30);
        JButton addbtn = new JButton("Add");
        addbtn.setBounds(250,380,150,30);
        
        addapartmentPanel.setLayout(null);
        addapartmentPanel.add(apartment_no);
        addapartmentPanel.add(apartment_name);
        addapartmentPanel.add(address);
        addapartmentPanel.add(room_type);
        addapartmentPanel.add(no_of_rooms);
        addapartmentPanel.add(each_room_price);
        addapartmentPanel.add(txtapartment_no);
        addapartmentPanel.add(txtapartment_name);
        addapartmentPanel.add(txtaddress);
        addapartmentPanel.add(rooms_type_cb);
        addapartmentPanel.add(txtno_of_rooms);
        addapartmentPanel.add(txteach_room_price);
        addapartmentPanel.add(addbtn);
        
        addapartmentPanel.setBackground(Color.WHITE);
        addapartmentPanel.setBounds(300,100,600,450);
        addapartmentPanel.setVisible(false);
        
        txtapartment_no.setText(apartment_auto_id());
        txtapartment_no.setEnabled(false);
        
        addbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                if(txtapartment_name.getText().equals("") || txtaddress.getText().equals("") || txtno_of_rooms.getText().equals("")|| txteach_room_price.getText().equals("") ){
                    JOptionPane.showMessageDialog(frame, "All fields are required!", "", JOptionPane.ERROR_MESSAGE);
                } else {
                    String apartment_no = txtapartment_no.getText();
                    String apartment_name = txtapartment_name.getText();
                    String address = txtaddress.getText();
                    String rooms = txtno_of_rooms.getText();
                    int no_of_rooms = Integer.parseInt(rooms);
                    String each_room_price = txteach_room_price.getText();
                    String room_type = (String) rooms_type_cb.getItemAt(rooms_type_cb.getSelectedIndex());

                    int addApartmentResult = dbconn.addApartment(apartment_no,apartment_name,landlord.getLandlord_id(),address,room_type,no_of_rooms,each_room_price);
                    if(addApartmentResult == 1) {
                        JOptionPane.showMessageDialog(frame,"Apartment added successfully.");
                        
                        updateApartmentsTable();
                        updateRoomsTable();
                        update_apartments();
                        
                        txtapartment_no.setText(apartment_auto_id());
                        txtapartment_name.setText("");
                        txtaddress.setText("");
                        txtno_of_rooms.setText("");
                        txteach_room_price.setText("");
                        
                    } else {
                        JOptionPane.showMessageDialog(frame,"Apartment not added!.", "",JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
        
        //add apartments end
        
        //add tenants start
        JPanel addTenantsPanel = new JPanel();
        
        JLabel tenant_no = new JLabel("Tenant No.");
        tenant_no.setBounds(50,20,150,30);
        JLabel full_name = new JLabel("Full Name");
        full_name.setBounds(50,80,150,30);
        JLabel phone_no = new JLabel("Phone Number");
        phone_no.setBounds(50,140,150,30);
        JLabel email = new JLabel("Email");
        email.setBounds(50,200,150,30);
        JLabel id_card_number = new JLabel("ID Number");
        id_card_number.setBounds(50,260,150,30);
        JTextField txttenant_no = new JTextField("");
        txttenant_no.setBounds(250,20,300,30);
        JTextField txtfull_name = new JTextField("");
        txtfull_name.setBounds(250,80,300,30);
        JTextField txtphone_no = new JTextField("");
        txtphone_no.setBounds(250,140,300,30);
        JTextField txtemail = new JTextField("");
        txtemail.setBounds(250,200,300,30);
        JTextField txtid_card_number = new JTextField("");
        txtid_card_number.setBounds(250,260,300,30);
        JButton tenantaddbtn = new JButton("Add");
        tenantaddbtn.setBounds(250,320,150,30);
        
        addTenantsPanel.setLayout(null);
        addTenantsPanel.add(tenant_no);
        addTenantsPanel.add(full_name);
        addTenantsPanel.add(phone_no);
        addTenantsPanel.add(email);
        addTenantsPanel.add(id_card_number);
        addTenantsPanel.add(txttenant_no);
        addTenantsPanel.add(txtfull_name);
        addTenantsPanel.add(txtphone_no);
        addTenantsPanel.add(txtemail);
        addTenantsPanel.add(txtid_card_number);
        addTenantsPanel.add(tenantaddbtn);
        
        addTenantsPanel.setBackground(Color.WHITE);
        addTenantsPanel.setBounds(300,100,600,450);
        addTenantsPanel.setVisible(false);
        
        
        txttenant_no.setText(auto_tenant_number());
        txttenant_no.setEnabled(false);
        
        tenantaddbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                if(txtfull_name.getText().equals("") || txtphone_no.getText().equals("") || txtemail.getText().equals("") || txtid_card_number.getText().equals("")) {
                    JOptionPane.showMessageDialog(frame, "All fields are required!", "", JOptionPane.ERROR_MESSAGE);
                } else {
                    String tenant_no = txttenant_no.getText();
                    String full_name = txtfull_name.getText();
                    String phone_no = txtphone_no.getText();
                    String email = txtemail.getText();
                    String id_card_number = txtid_card_number.getText();

                    int addTenantResult = dbconn.addTenant(tenant_no,landlord.getLandlord_id(),full_name,phone_no,email,id_card_number);
                    if(addTenantResult == 1) {
                        JOptionPane.showMessageDialog(frame,"Tenant added successfully.");
                        
                        updateTenantsTable();
                        
                        txttenant_no.setText(auto_tenant_number());
                        txtfull_name.setText("");
                        txtphone_no.setText("");
                        txtemail.setText("");
                        txtid_card_number.setText("");
                        
                    } else {
                        JOptionPane.showMessageDialog(frame,"Tenant not added!.", "",JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
        //add tenants end
        
        //edit profile start
        JPanel editProfilePanel = new JPanel();
        
        JLabel edit_landlord_id = new JLabel("Landlord Id.");
        edit_landlord_id.setBounds(50,20,150,30);
        JLabel edit_full_name = new JLabel("Name");
        edit_full_name.setBounds(50,80,150,30);
        JLabel edit_phone_no = new JLabel("Phone Number");
        edit_phone_no.setBounds(50,140,150,30);
        JLabel edit_email = new JLabel("Email");
        edit_email.setBounds(50,200,150,30);
        JTextField txtedit_landlord_id = new JTextField("");
        txtedit_landlord_id.setBounds(250,20,300,30);
        txtedit_landlord_id.setText(String.valueOf(landlord.getLandlord_id()));
        txtedit_landlord_id.setEnabled(false);
        JTextField txtedit_full_name = new JTextField("");
        txtedit_full_name.setBounds(250,80,300,30);
        txtedit_full_name.setText(landlord.getFull_name());
        JTextField txtedit_phone_no = new JTextField("");
        txtedit_phone_no.setBounds(250,140,300,30);
        txtedit_phone_no.setText(landlord.getPhone_number());
        JTextField txtedit_email = new JTextField("");
        txtedit_email.setBounds(250,200,300,30);
        txtedit_email.setText(landlord.getEmail());
        JButton editbtn = new JButton("Edit");
        editbtn.setBounds(250,260,150,30);
        
        
        editProfilePanel.setLayout(null);
        editProfilePanel.add(edit_landlord_id);
        editProfilePanel.add(edit_full_name);
        editProfilePanel.add(edit_phone_no);
        editProfilePanel.add(edit_email);
        editProfilePanel.add(txtedit_landlord_id);
        editProfilePanel.add(txtedit_full_name);
        editProfilePanel.add(txtedit_phone_no);
        editProfilePanel.add(txtedit_email);
        editProfilePanel.add(editbtn);
        
        editProfilePanel.setBackground(Color.WHITE);
        editProfilePanel.setBounds(300,100,600,450);
        editProfilePanel.setVisible(false);
        
        editbtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                
                if(txtedit_full_name.getText().equals("") || txtedit_phone_no.getText().equals("") || txtedit_email.getText().equals("")) {
                    JOptionPane.showMessageDialog(frame, "Can not edit to null!", "", JOptionPane.ERROR_MESSAGE);
                } else {
                    String full_name = txtedit_full_name.getText();
                    String phone_number = txtedit_phone_no.getText();
                    String email = txtedit_email.getText();

                    int edit_profile = dbconn.edit_profile(full_name,phone_number,email,landlord.getLandlord_id());
                    if(edit_profile == 1) {
                        JOptionPane.showMessageDialog(frame, "Your profile is editted successfully","",JOptionPane.PLAIN_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Error! Profile not editted!!!","",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        //edit profile end
        
        addtenantbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                addapartmentPanel.setVisible(false);
                editProfilePanel.setVisible(false);
                addTenantsPanel.setVisible(true);
            }
        });
        addapartmentbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                editProfilePanel.setVisible(false);
                addTenantsPanel.setVisible(false);
                addapartmentPanel.setVisible(true);
            }
        });
        editprofilebtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                addTenantsPanel.setVisible(false);
                addapartmentPanel.setVisible(false);
                editProfilePanel.setVisible(true);
            }
        });
        
        
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(null);
        panel.add(addapartmentPanel);
        panel.add(addTenantsPanel);
        panel.add(editProfilePanel);
        panel.add(editprofilebtn);
        panel.add(addtenantbtn);
        panel.add(addapartmentbtn);
        
        panel.setBounds(220,70,1000,600);
        panel.setVisible(false);
        
        return panel;
    }
        //settings end
        
        
        
    private void switchToApartmentsPanel() {
        tenantsPanel.setVisible(false);
        settingsPanel.setVisible(false);
        roomsPanel.setVisible(false);
        apartmentsPanel.setVisible(true);
    }
       
    private void switchToTenantsPanel() {
        settingsPanel.setVisible(false);
        roomsPanel.setVisible(false);
        apartmentsPanel.setVisible(false);
        tenantsPanel.setVisible(true);
    }
    
    private void switchToRoomsPanel() {
        tenantsPanel.setVisible(false);
        settingsPanel.setVisible(false);
        apartmentsPanel.setVisible(false);
        roomsPanel.setVisible(true);
    }
    
    private void switchToSettingsPanel() {
        tenantsPanel.setVisible(false);
        roomsPanel.setVisible(false);
        apartmentsPanel.setVisible(false);
        settingsPanel.setVisible(true);
    }
        
    private void logout() {
        int logout_result = JOptionPane.showConfirmDialog(frame,"Are you sure you want to log out?", "", JOptionPane.YES_NO_OPTION);
        if(logout_result == JOptionPane.YES_OPTION){
            frame.dispose();
            Tenants_Management_System tms = new Tenants_Management_System();
        }
    }
    
    private void updateApartmentsTable() {
        ResultSet rs = dbconn.getApartmentsMetaData(landlord.getLandlord_id());
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            int c = rsmd.getColumnCount();
            DefaultTableModel model = (DefaultTableModel) apartmentsTable.getModel();
            model.setRowCount(0);
            while(rs.next()) {
                Vector vector = new Vector();
                for(int i = 1 ; i <= c ; i++){
                    vector.add(rs.getString(1));
                    vector.add(rs.getString(2));
                    vector.add(rs.getString(4));
                    vector.add(rs.getString(5));
                }
                model.addRow(vector);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        apartmentsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Get the selected row index
                int selectedRow = apartmentsTable.getSelectedRow();

                if (selectedRow != -1) {  // Check if a row is actually selected
                    // Get the values of the selected row and display them in the text fields
                    apartmentsNotxt.setText((String) apartmentsTable.getValueAt(selectedRow, 0));
                    apartmentsNametxt.setText((String) apartmentsTable.getValueAt(selectedRow, 1));
                    address_apartmentstxt.setText((String) apartmentsTable.getValueAt(selectedRow, 2));
                    no_of_rooms_apartmentstxt.setText((String) apartmentsTable.getValueAt(selectedRow, 3));
                }
            }
        });
    }
    
    private void updateTenantsTable() {
        ResultSet tenants_rs = dbconn.getTenantsMetaData(landlord.getLandlord_id());
        try {
            ResultSetMetaData rsmd = tenants_rs.getMetaData();
            int c = rsmd.getColumnCount();
            DefaultTableModel model = (DefaultTableModel) tenantsTable.getModel();
            model.setRowCount(0);
            while(tenants_rs.next()) {
                Vector vector = new Vector();
                for(int i = 1 ; i <= c ; i++){
                    vector.add(tenants_rs.getString(1));
                    vector.add(tenants_rs.getString(3));
                    vector.add(tenants_rs.getString(4));
                    vector.add(tenants_rs.getString(5));
                    vector.add(tenants_rs.getString(6));
                    vector.add(tenants_rs.getString(7));
                    vector.add(tenants_rs.getString(8));
                }
                model.addRow(vector);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void updateRoomsTable() {
        ResultSet rooms_rs = dbconn.getRoomsMetaData(landlord.getLandlord_id());
        try {
            ResultSetMetaData rsmd = rooms_rs.getMetaData();
            int c = rsmd.getColumnCount();
            DefaultTableModel model = (DefaultTableModel) roomsTable.getModel();
            model.setRowCount(0);
            while(rooms_rs.next()) {
                Vector vector = new Vector();
                for(int i = 1 ; i <= c ; i++){
                    vector.add(rooms_rs.getString(2));
                    vector.add(rooms_rs.getString(3));
                    vector.add(rooms_rs.getString(5));
                    vector.add(rooms_rs.getString(6));
                    vector.add(rooms_rs.getString(7));
                    vector.add(rooms_rs.getString(8));
                }
                model.addRow(vector);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private String apartment_auto_id() {
        Apartments apartments = dbconn.getLastApartment(landlord.getLandlord_id());
        
        String auto_no = apartments.getApartment_no();
        String parts[] = auto_no.split("/");
        int number = Integer.parseInt(parts[2]);
        number++;
        parts[2] = Integer.toString(number);
        String auto_apart_id = String.join("/", parts);
        
        return auto_apart_id;
    }
    
    private String auto_tenant_number() {
        Tenant lastTenant = dbconn.getLastTenant(landlord.getLandlord_id());
        String auto_tenant_no = lastTenant.getTenant_no();
        String tenant_parts[] = auto_tenant_no.split("/");
        int tenant_id = Integer.parseInt(tenant_parts[2]);
        tenant_id++;
        tenant_parts[2] = Integer.toString(tenant_id);
        String auto_tenant_number = String.join("/", tenant_parts);
        return auto_tenant_number;
    }
}