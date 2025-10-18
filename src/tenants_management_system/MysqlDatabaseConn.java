package tenants_management_system;

import java.sql.*;
import java.util.ArrayList;

public class MysqlDatabaseConn {
    
    private static final String URL = "jdbc:mysql://localhost:3306/tenants_management_system";
    private static final String USER = "root";
    private static final String PASS = "";
    
    public int addUsers(String full_name,String phone_number,String email,String password) {
        int result = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement ps = conn.prepareStatement("INSERT INTO landlords (full_name,phone_number,email,password) VALUES (?,?,?,?)");
            
            ps.setString(1,full_name);
            ps.setString(2,phone_number);
            ps.setString(3,email);
            ps.setString(4,password);
            
            int rowsAffected = ps.executeUpdate();
            if(rowsAffected > 0) {
                result = 1;
            }
            
            conn.close();
            
        } catch (ClassNotFoundException | SQLException ce){
            result = 0;
        }
        return result;
    }
    
    public Landlord loginUser(String email, String password) {
        
        Landlord landlord = null;
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM landlords WHERE email = ? AND password = ?");
            ps.setString(1, email);
            ps.setString(2, password);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                landlord = new Landlord(rs.getInt("landlord_id"),rs.getString("full_name"),rs.getString("phone_number"),rs.getString("email"));
            }
            
            conn.close();
            
        } catch (ClassNotFoundException | SQLException ce){
            landlord = null;
        }
        
        return landlord;
    }
    
    public Apartments getLastApartment(int landlord_id){
        Apartments apartment = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM apartments WHERE landlord_id = ? ORDER BY apartment_no DESC LIMIT 1");
            ps.setInt(1, landlord_id);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()) {
                apartment = new Apartments(rs.getString("apartment_no"),rs.getString("apartment_name"),rs.getInt("landlord_id"),rs.getString("address"),rs.getInt("number_of_rooms"));
            } else {
                apartment = new Apartments("A/"+landlord_id+"/0","",landlord_id,"103 Juja",0);
            }
            
            conn.close();
            
        } catch (ClassNotFoundException | SQLException ce){
            apartment = null;
        }
        
        return apartment;
    }
    
    public Tenant getLastTenant(int landlord_id){
        Tenant tenant = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM tenants WHERE landlord_id = ? ORDER BY tenant_no DESC LIMIT 1");
            ps.setInt(1, landlord_id);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()) {
                tenant = new Tenant(rs.getString("tenant_no"),rs.getInt("landlord_id"),rs.getString("full_name"),rs.getString("phone_number"),rs.getString("email"));
            } else {
                tenant = new Tenant("T/"+landlord_id+"/0",landlord_id,"","","");
            }
            
            conn.close();
            
        } catch (ClassNotFoundException | SQLException ce){
            tenant = null;
        }
        
        return tenant;
    }
    
    public int addTenant(String tenant_no,int landlord_id,String full_name,String phone_no,String email,String id_card_number) {
        int result = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement ps = conn.prepareStatement("INSERT INTO tenants (tenant_no,landlord_id,full_name,phone_number,email,id_card_number) VALUES (?,?,?,?,?,?)");
            
            ps.setString(1,tenant_no);
            ps.setInt(2,landlord_id);
            ps.setString(3,full_name);
            ps.setString(4,phone_no);
            ps.setString(5,email);
            ps.setString(6, id_card_number);
            
            int rowsAffected = ps.executeUpdate();
            if(rowsAffected > 0) {
                result = 1;
            }
            
            conn.close();
            
        } catch (ClassNotFoundException | SQLException ce){
            result = 0;
        }
        return result;
    }
    
    
    public int addApartment(String apartment_no, String apartment_name, int landlord_id, String address, String room_type, int no_of_rooms, String each_room_price){
        int addApartmentResult = 0;
        String apartmentQuery = "INSERT INTO apartments (apartment_no,apartment_name,landlord_id,address,number_of_rooms) VALUES (?,?,?,?,?)";
        String roomsQuery = "INSERT INTO rooms (room_no,apartment_no,landlord_id,room_type,room_status,room_price) VALUES (?,?,?,?,?,?)";
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement ps = conn.prepareStatement(apartmentQuery);
            PreparedStatement ps_rooms = conn.prepareStatement(roomsQuery);
            
            ps.setString(1,apartment_no);
            ps.setString(2,apartment_name);
            ps.setInt(3,landlord_id);
            ps.setString(4,address);
            ps.setInt(5, no_of_rooms);
            
            int rows = ps.executeUpdate();
            if(rows > 0) {
                for (int i = 1 ; i <= no_of_rooms ; i++){
                    String room_no = "R/"+landlord_id+"/"+i;
                    String room_status = "available";
                
                    ps_rooms.setString(1, room_no);
                    ps_rooms.setString(2, apartment_no);
                    ps_rooms.setInt(3, landlord_id);
                    ps_rooms.setString(4, room_type);
                    ps_rooms.setString(5, room_status);
                    ps_rooms.setString(6,each_room_price);
                    ps_rooms.addBatch();
                }
                
                ps_rooms.executeBatch();
            
                addApartmentResult = 1;
            }
            
            conn.close();
            
            } catch (ClassNotFoundException | SQLException ce){
                addApartmentResult = 0;
                System.out.println("Hello !!!");
            }
        
        return addApartmentResult;
    }
    
    public int edit_profile(String full_name,String phone_number,String email,int landlord_id){
        int editProfileResult = 0;
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement ps = conn.prepareStatement("UPDATE landlords SET full_name = ?, phone_number = ?, email = ? WHERE landlord_id= ?");
            ps.setString(1, full_name);
            ps.setString(2, phone_number);
            ps.setString(3, email);
            ps.setInt(4, landlord_id);
            
            int rowsAffected = ps.executeUpdate();
            if(rowsAffected > 0) {
                editProfileResult = 1;
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            editProfileResult = 0;
        }
        
        return editProfileResult;
    }
    
    public ResultSet getApartmentsMetaData(int landlord_id){
        
        ResultSet rs = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM apartments WHERE landlord_id = ?");
            ps.setInt(1, landlord_id);
            rs = ps.executeQuery();
        } catch (ClassNotFoundException | SQLException e) {
            rs = null;
        }
        return rs;
    }
    
    public ResultSet getRoomsMetaData(int landlord_id){
        
        ResultSet rs = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM rooms WHERE landlord_id = ?");
            ps.setInt(1, landlord_id);
            rs = ps.executeQuery();
        } catch (ClassNotFoundException | SQLException e) {
            rs = null;
        }
        return rs;
    }
    
    public ResultSet getTenantsMetaData(int landlord_id){
        
        ResultSet rs = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM tenants WHERE landlord_id = ?");
            ps.setInt(1, landlord_id);
            rs = ps.executeQuery();
        } catch (ClassNotFoundException | SQLException e) {
            rs = null;
        }
        return rs;
    }

    public int deleteApartment(String apartment_no) {
        delete_rooms(apartment_no);
        int result = 0;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement ps = conn.prepareStatement("DELETE FROM apartments WHERE apartment_no = ?");
            ps.setString(1, apartment_no);
            result = ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return result;
    }
    private void delete_rooms(String apartment_no) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement ps = conn.prepareStatement("DELETE FROM rooms WHERE apartment_no = ?");
            ps.setString(1, apartment_no);
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }
    
    public String[] getApartments(int landlord_id) {
        ResultSet rs = null;
        ArrayList<String> apartmentList = new ArrayList<>();
        String[] apartments = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM apartments WHERE landlord_id = ?");
            ps.setInt(1, landlord_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                String apartment = rs.getString(1);
                apartmentList.add(apartment);
            }
            apartments = apartmentList.toArray(new String[0]);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return apartments;
    }
    public String[] getRoomsForApartment(String selectedApartment) {
        ResultSet rs = null;
        ArrayList<String> roomList = new ArrayList<>();
        String[] rooms = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM rooms WHERE apartment_no = ?");
            ps.setString(1, selectedApartment);
            rs = ps.executeQuery();
            while (rs.next()) {
                String room = rs.getString(2);
                String room_status = rs.getString(6);
                if(room_status.equals("available")) {
                    roomList.add(room);
                }
            }
            rooms = roomList.toArray(new String[0]);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return rooms;
    }
    
    public int assignTenantsRoom(String tenant_no,String room_no,String apartment_no,String due_date) {
        int result = 0;
        int i = 0;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement ps = conn.prepareStatement("UPDATE tenants SET rent_due_date = ?,room_no= ? WHERE tenant_no = ?");
            PreparedStatement ps2 = conn.prepareStatement("UPDATE rooms SET room_status = 'occupied' , paid = 'Yes' WHERE apartment_no = ? AND room_no = ?");
            ps.setString(1, due_date);
            ps.setString(2, room_no);
            ps.setString(3, tenant_no);
            ps2.setString(1, apartment_no);
            ps2.setString(2, room_no);
            
            i = ps.executeUpdate();
            if(i != 0) {
                result = ps2.executeUpdate();
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return result;
    }
}