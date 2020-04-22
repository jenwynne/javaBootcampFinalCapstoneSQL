import java.util.Date;

public class Project {
    //Attributes
    private String number;
    private String name;
    private String type;
    private String address;
    private String erf;
    private float totalCost;
    private float totalPaid;
    private String deadlineDate;
    private String completionDate;
    private String customer;
    private String structuralEngineer;
    private String architect;
    private String projectManager;

    //Exhaustive Constructor
    public Project(String number, String name, String type, String address, String erf, float totalCost, float totalPaid,
                   String deadlineDate, String completionDate, String customer, String structuralEngineer, String architect,
                   String projectManager){
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
        this.structuralEngineer = structuralEngineer;
        this.architect = architect;
    }

    //Default Constructor
    public Project(){
    }

    //To String Method:
    public String toString(){
        String output = name + ";" + number + ";" + type + ";" + address + ";" + erf + ";" + totalCost + ";" + totalPaid
                + ";" + deadlineDate + ";" + completionDate + ";" + customer + ";" + structuralEngineer + ";"
                + architect + ";" + projectManager + "\n";
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
    public void setTotalCost(float totalCost){
        this.totalCost = totalCost;
    }
    public float getTotalCost() {
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
    public void setTotalPaid(float totalPaid){
        this.totalPaid = totalPaid;
    }
    public float getTotalPaid() {
        return totalPaid;
    }
    public void setCustomer(String customer){
        this.customer = customer;
    }
    public String getCustomer() {
        return customer;
    }
    public void setStructuralEngineer(String contractor){
        this.structuralEngineer = structuralEngineer;
    }
    public String getStructuralEngineer() {
        return structuralEngineer;
    }
    public void setArchitect(String architect){
        this.architect = architect;
    }
    public String getArchitect() { return architect; }
    public void setProjectManager(String projectManager){ this.projectManager = projectManager; }
    public String getProjectManager() {
        return projectManager;
    }
}
