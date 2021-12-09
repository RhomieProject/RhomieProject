package com.example.rhomie.Objects;


import java.util.HashMap;

public class User implements IUser{
    private String email;
    private String password;
    private String first_name;
    private String last_name;
    private String phone_number;
    private String id;
    private static final int PASSWORDLENGTH = 6;

    /* Default Contructor */
    public User () {
        this.email = "";
        this.password= "";
        first_name = "";
        last_name = "";
        phone_number = "";
        id = "";

    }
    /* Constructor */
    public User (String em,String pass) {
        this.email = em;
        this.password = pass;
        first_name = "";
        last_name = "";
        phone_number = "";
        id = "";

    }

    /* Full Constructor*/
    public User (String em,String pass, String fn, String ln, String pn, String id) {
        this.email = em;
        this.password = pass;
        this.first_name = fn;
        this.last_name = ln;
        this.phone_number = pn;
        this.id = id;
    }


    /* Email */
    @Override
    public String getEmail() {
        return email;
    }


    public void setEmail (String e) {
        this.email = e;
    }
    /* Password */

    @Override
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String p) {
        if (p.length() >= PASSWORDLENGTH)
            this.password = p;
    }

    /* Name */
    public String getFullName() {
        String full_name = first_name +' '+last_name;
        return full_name;
    }

    public void setFirstName(String fn) {
        this.first_name = fn;
    }

    public void setLastName(String ln) {
        this.last_name = ln;
    }
    /* Phone Number */

    @Override
    public String getPhoneNumber() {
        return this.phone_number;
    }

    public void setPhoneNumber(String pn) {
        this.phone_number = pn;
    }

    /* ID */
    @Override
    public String getFirstName() {
        return first_name;
    }

    @Override
    public String getLastName() {
        return last_name;
    }

    @Override
    public String getID() {
        return this.id;
    }
    public void setID(String i) {
        this.id = i;
    }

    public HashMap<String,Object> toMap () {
        HashMap<String, Object> user = new HashMap<>();
        user.put("email", this.email);
        user.put("password", this.password);
        user.put("first_name", this.first_name);
        user.put("last_name", this.last_name);
        user.put("phone_number", this.phone_number);
        user.put("id", this.id);
        return user;
    }

    static public User toObject (HashMap<String,Object> hash){
        String em = hash.get("email").toString();
        String p = hash.get("password").toString();
        String fn = hash.get("first_name").toString();
        String ln = hash.get("last_name").toString();
        String pn = hash.get("phone_number").toString();
        String id = hash.get("id").toString();
        User user = new User(em, p, fn, ln, pn, id);
        return user;
    }

    @Override
    public int isValid() {
        if(this==null)
            return 0;
        //TODO check if all the attribute are correct
        return -1;
    }
}