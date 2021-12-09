

public class User {
    private String email;
    private String password;
    private String first_name;
    private String last_name;
    private String phone_number;
    private String id;
    private const int PASSWORDLENGTH = 6;

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
    public String getEmail () {
        return this.email;
    }
   
    public void setEmail (String e) {
        this.email = e;
    }
   
    /* Password */
    public String getPassword() {
        return this.password;
    }
   
    public void setPassword(String p) {
        if (p.length >= PASSWORDLENGTH)
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
    public String getPhoneNumber() {
        return this.phone_number;
    }
   
    public void setPhoneNumber(String pn) {
        this.phone_number = pn;
    }

    /* ID */
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
        String em = hash.get("email");
        String p = hash.get("password");
        String fn = hash.get("first_name");
        String ln = hash.get("last_name");
        String pn = hash.get("phone_number");
        String id = hash.get("id");
        User user = new User(em, p, fn, ln, pn, id);
        return user;
    } 
}