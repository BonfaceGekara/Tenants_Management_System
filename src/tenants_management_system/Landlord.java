package tenants_management_system;

public class Landlord {
    private int landlord_id;
    private String full_name;
    private String phone_number;
    private String email;
    
    public Landlord(int landlord_id,String full_name,String phone_number,String email){
        this.landlord_id = landlord_id;
        this.full_name = full_name;
        this.phone_number = phone_number;
        this.email = email;
    }

    public int getLandlord_id() {
        return landlord_id;
    }

    public void setLandlord_id(int landlord_id) {
        this.landlord_id = landlord_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
