import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

//This program allows the user to create customers and projects, edit information within these and save projects to an
// output file.
public class Poised {

    //Main method provides the user with the main menu and calls a method depending on the users selection:
    public static void main(String[] args) throws IOException, ParseException {

        //variables used throughout main method are declared:
        Person customer = null;
        Person contractor = new Person ("Amanda Groves", "082 456 3232", "amanda@contractor.com",
                "456 Buitengracht Street, Cape Town");
        Person architect = new Person("James Pearce", "021 666 7890", "james@architect.com",
                "22 Bree Street, Cape Town");
        Project project = null;
        String userEntry;
        Scanner scan = new Scanner(System.in);
        ArrayList<Project>allProjects = null;

        //As long as user does not enter 8 (exit), program gives main menu options:
        System.out.println("Welcome to the Poised project management system");
        do {
            System.out.println("Please select an option");
            System.out.println("1\tCreate a person");
            System.out.println("2\tCreate a project");
            System.out.println("3\tEdit a person");
            System.out.println("4\tView all projects");
            System.out.println("5\tEdit a project");
            System.out.println("6\tView all overdue projects");
            System.out.println("7\tView all incomplete projects");
            System.out.println("8\tExit");

            userEntry = scan.nextLine();

            //depending on userEntry, a different method is called:
            //CREATING A PERSON - entering option 1 will prompt the user to choose a person type and will call the
            // suitable createPerson method:
            if (userEntry.equals("1")) {
                System.out.println("\nWhich person are you creating, please select the correct number");
                System.out.println("1\tCustomer");
                System.out.println("2\tContractor");
                System.out.println("3\tArchitect");
                String personType = scan.nextLine();
                if (personType.equals("1")) {
                    customer = createCustomer(scan);
                }
                if (personType.equals("2")) {
                    contractor = createContractor(scan);
                }
                if (personType.equals("3")) {
                    architect = createArchitect(scan);
                }
            }

            //CREATING A PROJECT
            //entering option 2 will check if a customer has been created, if so the createProject method will be called:
            if (userEntry.equals("2")) {
                if (customer == null) {
                    System.out.println("Please ensure that you enter your customer information before creating a project.\n");
                } else if (!(customer == null)) {
                    //if a customer has already been created, the createProject method if called:
                    project = createProject(scan, customer, contractor, architect);
                }
            }
            //EDITING A PERSON
            //entering option 3 will prompt the user to choose a person type:
            if (userEntry.equals("3")) {
                System.out.println("Which person would you like to edit? Please enter 1, 2 or 3.");
                System.out.println("1\tCustomer");
                System.out.println("2\tContractor");
                System.out.println("3\tArchitect");

                String personChoice = scan.nextLine();
                //if the person selected has not yet been created, a null pointer exception will be thrown
                //depending on the person choice, the editPerson method will be called with the appropriate argument:
                if (personChoice.equals("1")) {
                    try {
                        editPerson(scan, customer);
                    } catch (NullPointerException e) {
                        System.out.println(e.getMessage());
                    }
                }
                if (personChoice.equals("2")) {
                    try {
                        editPerson(scan, contractor);
                    } catch (NullPointerException e) {
                        System.out.println(e.getMessage());
                    }
                }
                if (personChoice.equals("3")) {
                    try {
                        editPerson(scan, architect);
                    } catch (NullPointerException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
            //VIEW ALL PROJECTS
            //entering option 4 will call the viewProjects method
            //if a project has not yet been created, a null pointer exception will be thrown
            if (userEntry.equals("4")) {
                try {
                    viewProjects(scan);
                }
                catch (NullPointerException f) {
                    System.out.println(f.getMessage());
                }
            }
            //EDIT A PROJECT
            //entering option 5 will call the editProject method
            //if a project has not yet been created, a null pointer exception will be thrown
            if (userEntry.equals("5")) {
                try {
                    editProject(scan);
                }
                catch (NullPointerException f) {
                    System.out.println(f.getMessage());
                }
            }

            //VIEW OVERDUE PROJECTS
            //entering option 6 will call the overdueProjects method:
            if (userEntry.equals("6")) {
                overdueProjects();
            }

            //VIEW COMPLETED PROJECTS
            //entering option 7 will call the completedProjects method:
            if (userEntry.equals("7")) {
                notCompletedProjects();
            }
        }
        //EXIT
        //entering option 8 will exit the program:
        while (!userEntry.equals("8"));
        System.out.println("Thank you, good bye");
    }

    //METHODS:

    //CREATE CUSTOMER - requests person information and uses set methods to assign info to customer:
    public static Person createCustomer(Scanner scan){
        Person customer = new Person();
        System.out.println("Please enter the following information required to create a new customer");
        System.out.println("Full Name:");
        customer.setName(scan.nextLine());

        System.out.println("Telephone Number:");
        customer.setTel(scan.nextLine());

        System.out.println("Email:");
        customer.setEmail(scan.nextLine());

        System.out.println("Address:");
        customer.setAddress(scan.nextLine());

        System.out.println(customer + "\n");
        return customer;
    }

    //CREATE CONTRACTOR - requests person information and uses set methods to assign info to contractor:
    public static Person createContractor(Scanner scan) {
        Person contractor = new Person();
        System.out.println("Please enter the following information required to create a new contractor");
        System.out.println("Full Name:");
        contractor.setName(scan.nextLine());

        System.out.println("Telephone Number:");
        contractor.setTel(scan.nextLine());

        System.out.println("Email:");
        contractor.setEmail(scan.nextLine());

        System.out.println("Address:");
        contractor.setAddress(scan.nextLine());

        System.out.println(contractor + "\n");
        return contractor;
    }

    //CREATE ARCHITECT - requests person information and uses set methods to assign info to architect:
    public static Person createArchitect(Scanner scan) {
        Person architect = new Person();
        System.out.println("Please enter the following information required to create a new architect");
        System.out.println("Full Name:");
        architect.setName(scan.nextLine());

        System.out.println("Telephone Number:");
        architect.setTel(scan.nextLine());

        System.out.println("Email:");
        architect.setEmail(scan.nextLine());

        System.out.println("Address:");
        architect.setAddress(scan.nextLine());

        System.out.println(architect + "\n");
        return architect;
    }

    //CREATE PROJECT - requests project information and uses set methods to assign info to project.
    //Person objects(1 of each) are passed in as arguments so that all information regarding customer, contractor and
    // architect is stored within the project.
    public static Project createProject(Scanner scan, Person customer, Person contractor, Person architect)
            throws IOException {
        Project project = new Project();

        //each answer is assigned to a variable:
        System.out.println("Please enter the following information required to create a new project");
        System.out.println("Name:");
        String nameResponse = scan.nextLine();

        System.out.println("Number:");
        String number = scan.nextLine();

        System.out.println("Building Type:");
        String type = scan.nextLine();

        System.out.println("Site Address:");
        String address = scan.nextLine();

        System.out.println("Site ERF:");
        String erf = scan.nextLine();

        System.out.println("Total quoted amount for project inc VAT, no spaces:");
        double totalCost = scan.nextDouble();
        scan.nextLine();

        System.out.println("Total amount paid to date inc VAT, no spaces:");
        double totalPaid = scan.nextDouble();
        scan.nextLine();

        System.out.println("Deadline Date: (dd/mm/yyyy)");
        String deadlineDate = scan.nextLine();

        //if the project name has not been given, the default name will be the build type followed by the customer surname:
        String projectName;
        if (nameResponse.equals("")) {
            projectName = type + " " + (customer.getName().split(" ")[1]);
        } else {
            projectName = nameResponse;
        }
        //each variable is passed in to set methods:
        project.setName(projectName);
        project.setNumber(number);
        project.setType(type);
        project.setAddress(address);
        project.setErf(erf);
        project.setTotalCost(totalCost);
        project.setTotalPaid(totalPaid);
        project.setDeadlineDate(deadlineDate);
        project.setCompletionDate(null);

        //automatically sets to customer, contractor and architect passed in as arguments:
        project.setCustomer(customer);
        project.setContractor(contractor);
        project.setArchitect(architect);

        //Project information is written to projects.txt and printed to screen
        writeProjectToFile(project);
        printProject(project);
        return project;
    }

    //EDIT PERSON - user can select which information they need to change and then provide the updated information:
    public static void editPerson(Scanner scan, Person person) throws NullPointerException {
        //checks that the person has already been created:
        if (person != null) {
            System.out.println("Which information would you like to edit? Please enter 1, 2, 3 or 4");
            System.out.println("1\tName");
            System.out.println("2\tTel");
            System.out.println("3\tEmail");
            System.out.println("4\tAddress");
            String editChoice = scan.nextLine();

            if (editChoice.equals("1")) {
                System.out.println("Please enter the new name");
                person.setName(scan.nextLine());
            }
            if (editChoice.equals("2")) {
                System.out.println("Please enter the new telephone number");
                person.setTel(scan.nextLine());
            }
            if (editChoice.equals("3")) {
                System.out.println("Please enter new email address");
                person.setEmail(scan.nextLine());
            }
            if (editChoice.equals("4")) {
                System.out.println("Please enter new address");
                person.setAddress(scan.nextLine());
            }
            //Prints out updated Person information:
            System.out.println("Updated information:");
            System.out.println(person + "\n");

            //if this person has not been created, a null pointer exception will be thrown
        } else if (person == null)
            throw new NullPointerException("This person has not yet been created\n");
    }

    //WRITE PROJECT TO FILE - writes projects to file as a string:
    public static void writeProjectToFile(Project project) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("projects.txt", true));
        writer.write(String.valueOf(project));
        writer.close();
    }

    //VIEW PROJECTS - opens the projects text file and adds all projects in the file to an ArrayList:
    public static void viewProjects(Scanner scan) throws NullPointerException, FileNotFoundException {
        File file = new File("projects.txt");
        ArrayList<Project> allProjects = new ArrayList<Project>();
        Scanner sc = new Scanner(file);

        if (sc.hasNext()) {
            while (sc.hasNext()) {
                allProjects.add(Project.parseProject(sc.nextLine()));
            }
            //Iterates over project array list and prints each project to screen:
            for (int i = 0; i < allProjects.size(); i++) {
                printProjectFromList(allProjects, i);
            }
        }
        //if no projects have been saved to the text file, the NullPointerException message will print
        else {
            throw new NullPointerException("There are no projects for you to view.\n");
        }
    }

    //EDIT PROJECT - displays all projects, asks the user if they would like to edit a project,
    // if they select yes, it displays the options to edit. The user can then provide updated information.
    public static void editProject(Scanner scan) throws IOException, NullPointerException {
        //displaying projects:
        File file = new File("projects.txt");
        Scanner sc = new Scanner(file);
        ArrayList<Project> allProjects = new ArrayList<Project>();

        //checks if there is text inside projects.txt:
        if (sc.hasNext()) {
            while (sc.hasNext()) {
                allProjects.add(Project.parseProject(sc.nextLine()));
            }
            for (int i = 0; i < allProjects.size(); i++) {
                printProjectFromList(allProjects, i);
            }

            //Prompts user for option to edit:
            System.out.println("Would you like to edit a project? \nPlease select 1 or 2");
            System.out.println("1:\tYes");
            System.out.println("2:\tNo");
            String editResponse = scan.nextLine();

            //Prompts user for project to edit choice and displays their selection :
            if (editResponse.equals("1")) {
                System.out.println("Please enter the number of the project you would like to edit");
                int projectChoice = scan.nextInt() - 1;
                scan.nextLine();
                printProjectFromList(allProjects, projectChoice);

                //Prompts user for specific information to edit:
                System.out.println("Which information in this project would you like to edit?");
                System.out.println("1:\tTotal Cost");
                System.out.println("2:\tTotal Paid");
                System.out.println("3:\tDeadline Date");
                System.out.println("4:\tCompletion Date\n");
                String editChoice = scan.nextLine();

                //Changing the TotalCost of the project:
                if (editChoice.equals("1")) {
                    System.out.println("Please enter the new total cost of this project");
                    allProjects.get(projectChoice).setTotalCost(scan.nextDouble());
                    scan.nextLine();
                    System.out.println("Your updated project:");
                    printProjectFromList(allProjects, projectChoice);
                }

                //Changing the TotalPaid of the project:
                if (editChoice.equals("2")) {
                    System.out.println("Please enter total amount paid for this project");
                    allProjects.get(projectChoice).setTotalPaid(scan.nextDouble());
                    scan.nextLine();
                    System.out.println("Your updated project:");
                    printProjectFromList(allProjects, projectChoice);
                }
                //Changing the DeadlineDate of the project:
                if (editChoice.equals("3")) {
                    System.out.println("Please enter the new deadline date for this project (dd/mm/yyyy)");
                    allProjects.get(projectChoice).setDeadlineDate(scan.nextLine());
                    System.out.println("Your updated project:");
                    printProjectFromList(allProjects, projectChoice);
                }
                //Finalising the project:
                if (editChoice.equals("4")) {
                    System.out.println("Please enter completion date (dd/mm/yyyy)");
                    allProjects.get(projectChoice).setCompletionDate(scan.nextLine());

                    //checks if further money is owed on this project:
                    //if further money is owed, invoice information is displayed on the screen and saved to an output file:
                    if (allProjects.get(projectChoice).getTotalCost() > allProjects.get(projectChoice).getTotalPaid()) {

                        String invoice = "INVOICE\n\n";
                        invoice += allProjects.get(projectChoice).getName() + "\n";
                        invoice += allProjects.get(projectChoice).getCustomer().getName() + "\n";
                        invoice += allProjects.get(projectChoice).getCustomer().getTel() + "\n";
                        invoice += allProjects.get(projectChoice).getCustomer().getEmail() + "\n";
                        invoice += allProjects.get(projectChoice).getCustomer().getAddress() + "\n\n";
                        invoice += "Total Cost:\t" + allProjects.get(projectChoice).getTotalCost() + "\n";
                        invoice += "Total Paid:\t" + allProjects.get(projectChoice).getTotalPaid() + "\n";
                        invoice += "Total Due:\t" + (allProjects.get(projectChoice).getTotalCost() -
                                allProjects.get(projectChoice).getTotalPaid()) + "\n\n";
                        invoice += "Thank you very much for your business\n";
                        invoice += "It has been a pleasure working with you\n";
                        System.out.println(invoice);

                        BufferedWriter writer = new BufferedWriter(new FileWriter("taxInvoice.txt", true));
                        writer.write(invoice);
                        writer.close();
                    }
                    //If not further money is owed:
                    else {
                        System.out.println("This project has been paid for in full, no invoicing is required");
                        System.out.println("Thank you very much for your business");
                        System.out.println("It has been a pleasure working with you");
                    }
                }
            }
            //Updated project list is written to projects file:
            BufferedWriter writer = new BufferedWriter(new FileWriter("projects.txt"));
            for (int i = 0; i < allProjects.size(); i++) {
                writer.write(String.valueOf(allProjects.get(i)));
            }
            writer.close();
        }
        //if projects.txt is empty:
        else {
            throw new NullPointerException("There are no projects for you to edit.\n");
        }
    }

    //PRINT PROJECT - print layout when printing project
    public static void printProject(Project project) {
        String projectInfo = "Name:\t\t\t" + project.getName();
        projectInfo += "\nNumber:\t\t\t" + project.getNumber();
        projectInfo += "\nType:\t\t\t" + project.getType();
        projectInfo += "\nAddress:\t\t" + project.getAddress();
        projectInfo += "\nERF:\t\t\t" + project.getErf();
        projectInfo += "\nTotal Cost:\t\t" + project.getTotalCost();
        projectInfo += "\nTotal Paid:\t\t" + project.getTotalPaid();
        projectInfo += "\nDeadline Date:\t" + project.getDeadlineDate();
        projectInfo += "\nDate completed:\t" + project.getCompletionDate();
        projectInfo += "\nCustomer:\t\t" + project.getCustomer();
        projectInfo += "\nContractor:\t\t" + project.getContractor();
        projectInfo += "\nArchitect:\t\t" + project.getArchitect() + "\n\n";
        System.out.println(projectInfo);
    }

    //PRINT PROJECT FROM LIST - print layout when printing project from project arrayList:
    public static void printProjectFromList(ArrayList<Project>allProjects, int i){
        String thisProject = String.valueOf(allProjects.get(i));
        String projectFromList = "Project " + (i + 1);
        projectFromList += "\nProject Name:\t\t" + thisProject.split(";")[0];
        projectFromList += "\nProject Number:\t\t" + thisProject.split(";")[1];
        projectFromList += "\nProject Type:\t\t" + thisProject.split(";")[2];
        projectFromList += "\nProject Address:\t" + thisProject.split(";")[3];
        projectFromList += "\nProject ERF:\t\t" + thisProject.split(";")[4];
        projectFromList += "\nTotal Cost:\t\t\t" + thisProject.split(";")[5];
        projectFromList += "\nTotal Paid:\t\t\t" + thisProject.split(";")[6];
        projectFromList += "\nDeadline Date:\t\t" + thisProject.split(";")[7];
        projectFromList += "\nProject Completed:\t" + thisProject.split(";")[8];
        projectFromList += "\nCustomer:\t\t\t" + thisProject.split(";")[9];
        projectFromList += "\nContractor:\t\t\t" + thisProject.split(";")[10];
        projectFromList += "\nArchitect:\t\t\t" + thisProject.split(";")[11] + "\n";

        System.out.println(projectFromList);
    }

    //OVERDUE PROJECTS - compares deadlineDate with today's date
    // if the project is overdue it is added to the overdue project array list
    public static void overdueProjects() throws FileNotFoundException, ParseException {

        //creates today's date and converts to date format:
        String currentDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        Date todaysDate = new SimpleDateFormat("dd/MM/yyyy").parse(currentDate);

        //Reads information from project file:
        File file = new File("projects.txt");
        Scanner sc = new Scanner(file);
        ArrayList<Project> allProjects = new ArrayList<Project>();
        ArrayList<Project> overdueProjects = new ArrayList<Project>();

        //adds all projects in file to allProjects arrayList:
        while (sc.hasNext()) {
            allProjects.add(Project.parseProject(sc.nextLine()));
        }
        //Iterates over allProjects arrayList:
        for (int i = 0; i < allProjects.size(); i++) {
            String thisProject = String.valueOf(allProjects.get(i));

            //only looks at projects that are not yet completed:
            if (allProjects.get(i).getCompletionDate().equals("null")) {

                //take deadline Date in each project and convert to date format
                Date deadlineDate = new SimpleDateFormat("dd/MM/yyyy").parse(allProjects.get(i).getDeadlineDate());

                //compare today's date to deadline date:
                int overdue = deadlineDate.compareTo(todaysDate);

                //if the deadline date is before today's date, the project is overdue and is added to the overdue
                // project arrayList:
                if (overdue < 0) {
                    overdueProjects.add(allProjects.get(i));
                }
            }
        }
        //If the overdue project arrayList is empty, this message will be displayed:
        if (overdueProjects.size() == 0){
            System.out.println("There are no overdue projects at this time.\n");
        }
        //If the arrayList is not empty, iterate over the overdue arrayList and print each overdue project:
        else if (overdueProjects.size() > 0) {
            System.out.println("Overdue Projects\n");
            for (int i = 0; i < overdueProjects.size(); i++) {
                printProjectFromList(overdueProjects, i);
            }
        }
    }

    //NOT COMPLETED PROJECTS - if a project does not have a completion date, it is added to the notCompletedProjects arrayList
    public static void notCompletedProjects() throws FileNotFoundException {

        //Reads information from project file:
        File file = new File("projects.txt");
        Scanner sc = new Scanner(file);
        ArrayList<Project> allProjects = new ArrayList<Project>();
        ArrayList<Project> notCompletedProjects = new ArrayList<Project>();

        //adds all projects in file to allProjects arrayList:
        while (sc.hasNext()) {
            allProjects.add(Project.parseProject(sc.nextLine()));
        }
        //Iterates over allProjects arrayList
        for (int i = 0; i < allProjects.size(); i++) {
            //if a project completion date equals null, it is added to the notCompletedProjects arrayList
            if (allProjects.get(i).getCompletionDate().equals("null")) {
                notCompletedProjects.add(allProjects.get(i));
            }
        }
        //If the notCompletedProjects arrayList is empty, this message will be displayed:
        if (notCompletedProjects.size() == 0) {
            System.out.println("There are no incomplete projects at this time.\n");
        }
        //If the notCompletedProjects arrayList is not empty, all projects on the list will be displayed:
        else if (notCompletedProjects.size() > 0) {
            System.out.println("Incomplete Projects");
            for (int i = 0; i < notCompletedProjects.size(); i++) {
                printProjectFromList(notCompletedProjects, i);
            }
        }
    }
}