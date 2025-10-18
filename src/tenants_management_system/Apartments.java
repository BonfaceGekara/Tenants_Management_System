package tenants_management_system;

public class Apartments {
    private String apartment_no;
    private String apartment_name;
    private int landlord_id;
    private String address;
    private int no_of_rooms;

    public Apartments(String apartment_no, String apartment_name, int landlord_id, String address, int no_of_rooms) {
        this.apartment_no = apartment_no;
        this.apartment_name = apartment_name;
        this.landlord_id = landlord_id;
        this.address = address;
        this.no_of_rooms = no_of_rooms;
    }

    public String getApartment_no() {
        return apartment_no;
    }

    public void setApartment_no(String apartment_no) {
        this.apartment_no = apartment_no;
    }

    public String getApartment_name() {
        return apartment_name;
    }

    public void setApartment_name(String apartment_name) {
        this.apartment_name = apartment_name;
    }

    public int getLandlord_id() {
        return landlord_id;
    }

    public void setLandlord_id(int landlord_id) {
        this.landlord_id = landlord_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNo_of_rooms() {
        return no_of_rooms;
    }

    public void setNo_of_rooms(int no_of_rooms) {
        this.no_of_rooms = no_of_rooms;
    }
    
    

}