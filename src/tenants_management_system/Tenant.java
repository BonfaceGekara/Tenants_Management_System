package tenants_management_system;

public class Tenant {
    private String tenant_no;
    private int landlord_id;
    private String full_name;
    private String phone_number;
    private String email;
    private String room_no;
    private String rent_due_date;

    public Tenant(String tenant_no, int landlord_id, String full_name, String phone_number, String email) {
        this.tenant_no = tenant_no;
        this.landlord_id = landlord_id;
        this.full_name = full_name;
        this.phone_number = phone_number;
        this.email = email;
    }

    public String getTenant_no() {
        return tenant_no;
    }

    public void setTenant_no(String tenant_no) {
        this.tenant_no = tenant_no;
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

    public String getRoom_no() {
        return room_no;
    }

    public void setRoom_no(String room_no) {
        this.room_no = room_no;
    }

    public String getRent_due_date() {
        return rent_due_date;
    }

    public void setRent_due_date(String rent_due_date) {
        this.rent_due_date = rent_due_date;
    }
    
    
}
