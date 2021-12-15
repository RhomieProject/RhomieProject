package com.example.rhomie.Objects;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Date;
import java.util.HashMap;

public class Item {

    private int item_id;
    private Address address;
    private Flags flags;
    private String check_in;
    private String check_out;
    private String guest_number;
    private String dateFormat;

    /* Default Constructor */
    public Item () {
        item_id = 0;
        address = null;
        flags = null;
        check_in = null;
        check_out = null;
        guest_number = "";
    }

    /* Full Constructor */
    public Item (int i,Address a,Flags f,String ci, String co, String gn) {
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

    public void setAddress (String c,String s,String sn,String f, String an) {
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
    public String getCheckIn () {
        return this.check_in;
    }

    public void setCheckIn (String in) {
        this.check_in = in;
    }

    /* Check Out */
    public String getCheckOut () {
        return this.check_out;
    }

    public void setCheckOut (String out) {
        this.check_out = out;
    }

    /* Guest Number */
    public String getGuestNumber () {
        return this.guest_number;
    }

    public void setGuestNumber (String gn) {
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

    public int isValid() {
        if(this == null)
            return 0;
        //TODO check if all the attribute are correct

/*
        if (!isDate(getCheckIn()))
            return 1;
        if(!isDate(getCheckOut()))
            return 2;
*/
        if(getGuestNumber().length() == 0 || getGuestNumber().length() > 2 || !onlyDigit(getGuestNumber()))
            return 3;
        if(getAddress().getCity().length() < 2 || !onlyAlphabetic(getAddress().getCity()))
            return 4;
        if(getAddress().getStreet().length() < 2 || !onlyAlphabetic(getAddress().getStreet()))
            return 5;
        if(getAddress().getStreetNumber().length() == 0 || !onlyDigit(getAddress().getStreetNumber()))
            return 6;
        //TODO how to check if its start in 05

        return -1;
    }

    private boolean onlyAlphabetic(String s) {
        char[] chars = s.toCharArray();
        for(char c : chars){
            if(Character.isDigit(c))
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Item{" +
                "item_id=" + item_id +
                ", address=" + address +
                ", flags=" + flags +
                ", check_in='" + check_in + '\'' +
                ", check_out='" + check_out + '\'' +
                ", guest_number='" + guest_number + '\'' +
                ", dateFormat='" + dateFormat + '\'' +
                '}';
    }

    private boolean onlyDigit(String s) {
        char[] chars = s.toCharArray();
        for(char c : chars){
            if(!Character.isDigit(c))
                return false;
        }
        return true;
    }

    public boolean isDate(String dateStr) {
        DateFormat sdf = new SimpleDateFormat(this.dateFormat);
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
