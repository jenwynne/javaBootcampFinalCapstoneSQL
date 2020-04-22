public class Person {
    //Attributes
    private String name;
    private String tel;
    private String email;
    private String address;

    //Exhaustive Constructor
    public Person(String name, String tel, String email, String address){
        this.name = name;
        this.tel = tel;
        this.email = email;
        this.address = address;
    }

    //Default Constructor
    public Person(){
    }

    //To String Method:
    public String toString(){
        String output = name + "-" + tel + "-" + email + "-" + address;
        //output += "\nTel:\t\t" + tel;
        //output += "\nEmail:\t\t" + email;
        //output += "\nAddress:\t" + address;
        return output;
    }

    //Get and Set Methods
    public String getName() { return name; }
    public void setName(String name) {
        this.name = name;
    }
    public String getTel() { return tel; }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public static Person parsePerson(String personString) {
        return new Person(personString.split("-")[0], personString.split("-")[1], personString.split("-")[2],
                personString.split("-")[3]);
    }

}
