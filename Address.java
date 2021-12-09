public class Address {
    
    private String city;
    private String street;
    private int street_number;
    private int floor;
    private int apartment_number;
    
    /* City */
    public String getCity () {
        return this.city;
    }
   
    public void setCity (String c) {
        this.city = c;
    }

    /* Street */
    public String getStreet () {
        return this.street;
    }
   
    public void setStreet (String s) {
        this.street = s;
    }

    /* Street Number */
    public int getStreetNumber () {
        return this.street_number;
    }
   
    public void setStreetNumber (int sn) {
        this.street_number = sn;
    }

    /* Floor */
    public int getFloor () {
        return this.floor;
    }
   
    public void setFloor (int f) {
        this.floor = f;
    }

    /* Apartment Number */
    public int getApartmentNumber () {
        return this.apartment_number;
    }
   
    public void setApartmentNumber (int an) {
        this.apartment_number = an;
    }
    
}