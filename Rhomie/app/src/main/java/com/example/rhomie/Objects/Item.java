package com.example.rhomie.Objects;
import java.util.Date;
import java.util.HashMap;

public class Item {

    private int item_id;
    private Address address;
    private Flags flags;
    private Date check_in;
    private Date check_out;
    private int guest_number;

    /* Default Constructor */
    public Item () {
        item_id = 0;
        address = null;
        flags = null;
        check_in = null;
        check_out = null;
        guest_number = 0;
    }

    /* Full Constructor */
    public Item (int i,Address a,Flags f,Date ci, Date co, int gn) {
        this.item_id = i;
        this.address = a;
        this.flags = f;
        this.check_in = ci;
        this.check_out = co;
        this.guest_number = gn;
    }

    /* Item ID */
    public int getItem () {
        return this.item_id;
    }

    /* Address */
    public Address getAddress () {
        return this.address;
    }

    public void setAddress (String c,String s,int sn,int f, int an) {
        this.address = new Address(c ,s ,sn ,f ,an);
    }

    /* Flags */
    public Flags getFlags () {
        return this.flags;
    }

    public void setFlags (boolean k, boolean an, boolean ac, boolean p, boolean s, boolean wi) {
        this.flags = new Flags(k, an, ac, p, s, wi);
    }

    /* Check In */
    public Date getCheckIn () {
        return this.check_in;
    }

    public void setCheckIn (Date in) {
        this.check_in = in;
    }

    /* Check Out */
    public Date getCheckOut () {
        return this.check_out;
    }

    public void setCheckOut (Date out) {
        this.check_out = out;
    }

    /* Guest Number */
    public int getGuestNumber () {
        return this.guest_number;
    }

    public void setGuestNumber (int gn) {
        this.guest_number = gn;
    }

    public HashMap<String,Object> itemToMap () {
        HashMap<String, Object> item = new HashMap<>();
        item.put("item_id", this.item_id);
        item.put("address", this.address);
        item.put("flags", this.flags);
        item.put("check_in", this.check_in);
        item.put("check_out", this.check_out);
        item.put("guest_number", this.guest_number);
        return item;
    }

}
