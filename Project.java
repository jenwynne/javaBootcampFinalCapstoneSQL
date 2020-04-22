public class Project {
    //Attributes
    private String number;
    private String name;
    private String type;
    private String address;
    private String erf;
    private double totalCost;
    private double totalPaid;
    private String deadlineDate;
    private String completionDate;
    private Person customer;
    private Person contractor;
    private Person architect;

    //Exhaustive Constructor
    public Project(String number, String name, String type, String address, String erf, double totalCost, double totalPaid,
                   String deadlineDate, String completionDate, Person customer, Person contractor, Person architect){
        this.number = number;
        this.name = name;
        this.type = type;
        this.address = address;
        this.erf = erf;
        this.totalCost = totalCost;
        this.totalPaid = totalPaid;
        this.deadlineDate = deadlineDate;
        this.completionDate = completionDate;
        this.customer = customer;
        this.contractor = contractor;
        this.architect = architect;
    }

    //Constructor string to object:
    public Project(String allInfo){
        allInfo = number + ";" + name + ";" + type + ";" + address + ";" + erf + ";" + totalCost + ";" + totalPaid
                + ";" + deadlineDate + ";" + completionDate + ";" + customer + ";" + contractor + ";" + architect;
        this.number = allInfo.split(";")[0];
        this.name = allInfo.split(";")[1];
        this.type = allInfo.split(";")[2];
        this.address = allInfo.split(";")[3];
        this.erf = allInfo.split(";")[4];
        this.totalCost = Double.parseDouble(allInfo.split(";")[5]);
        this.totalPaid = Double.parseDouble(allInfo.split(";")[6]);
        this.deadlineDate = allInfo.split(";")[7];
        this.completionDate = allInfo.split(";")[8];
        this.customer = Person.parsePerson(allInfo.split(";")[9]);
        this.contractor = Person.parsePerson(allInfo.split(";")[10]);
        this.architect = Person.parsePerson(allInfo.split(";")[11]);
    }

    //Default Constructor
    public Project(){
    }

    //To String Method:
    public String toString(){
        String output = name + ";" + number + ";" + type + ";" + address + ";" + erf + ";" + totalCost + ";" + totalPaid
                + ";" + deadlineDate + ";" + completionDate + ";" + customer + ";" + contractor + ";" + architect + "\n";
        return output;
    }

    //Get and Set methods:
    public void setNumber(String number){
        this.number = number;
    }
    public String getNumber() {
        return number;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getType() {
        return type;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public String getAddress() {
        return address;
    }
    public void setErf(String erf){ this.erf = erf; }
    public String getErf() {
        return erf;
    }
    public void setTotalCost(double totalCost){
        this.totalCost = totalCost;
    }
    public double getTotalCost() {
        return totalCost;
    }
    public void setDeadlineDate(String deadlineDate){
        this.deadlineDate = deadlineDate;
    }
    public String getDeadlineDate() {
        return deadlineDate;
    }
    public void setCompletionDate(String completionDate){
        this.completionDate = completionDate;
    }
    public String getCompletionDate() {
        return completionDate;
    }
    public void setTotalPaid(double totalPaid){
        this.totalPaid = totalPaid;
    }
    public double getTotalPaid() {
        return totalPaid;
    }
    public void setCustomer(Person customer){
        this.customer = customer;
    }
    public Person getCustomer() {
        return customer;
    }
    public void setContractor(Person contractor){
        this.contractor = contractor;
    }
    public Person getContractor() {
        return contractor;
    }
    public void setArchitect(Person architect){
        this.architect = architect;
    }
    public Person getArchitect() {
        return architect;
    }

    //Convert String to Project
    public static Project parseProject(String projectString) {
        return new Project(projectString.split(";")[0], projectString.split(";")[1], projectString.split(";")[2],
                projectString.split(";")[3], projectString.split(";")[4], Double.parseDouble(projectString.split(";")[5]),
                Double.parseDouble(projectString.split(";")[6]), projectString.split(";")[7], projectString.split(";")[8],
                Person.parsePerson(projectString.split(";")[9]), Person.parsePerson(projectString.split(";")[10]),
                Person.parsePerson(projectString.split(";")[11]));
    }
}
