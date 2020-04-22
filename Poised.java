import java.sql.*;
import java.util.Scanner;
import java.util.Date;

//This program allows the user to create people and projects, edit information within these and save them to a database
public class Poised {

    //Main method provides the user with the main menu and calls a method depending on the users selection:
    public static void main(String[] args) {

        String userEntry;
        Scanner scan = new Scanner(System.in);

        //As long as user does not enter 9 (exit), program gives main menu options:
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
            System.out.println("8\tPrint project tax invoice");
            System.out.println("9\tExit");

            userEntry = scan.nextLine();

            //depending on userEntry, a different method is called:

            //CREATING A PERSON - entering option 1 will prompt the user to choose a person type and will call the
            //createPerson() method passing in the suitable table to be added to:
            String personToCreate = null;
            if (userEntry.equals("1")) {
                System.out.println("\nWhich person are you creating, please select the correct number");
                System.out.println("1\tCustomer");
                System.out.println("2\tStructural Engineer");
                System.out.println("3\tArchitect");
                System.out.println("4\tProject Manager");
                String personType = scan.nextLine();
                if (personType.equals("1")) {
                    personToCreate = "customers";
                    createPerson(scan, personToCreate);
                }
                if (personType.equals("2")) {
                    personToCreate = "structuralEngineers";
                    createPerson(scan, personToCreate);
                }
                if (personType.equals("3")) {
                    personToCreate = "architects";
                    createPerson(scan, personToCreate);
                }
                if (personType.equals("4")) {
                    personToCreate = "projectManagers";
                    createPerson(scan, personToCreate);
                }
            }

            //CREATING A PROJECT
            //entering option 2 will call the createProject method:
            if (userEntry.equals("2")) {
                createProject(scan);
            }

            //EDITING A PERSON
            //entering option 3 will call the editPerson method:
            if (userEntry.equals("3")) {
                editPerson(scan);
            }

            //VIEW ALL PROJECTS
            //entering option 4 will call the viewProjects method
            if (userEntry.equals("4")) {
                viewProjects(scan);
            }

            //EDIT A PROJECT
            //entering option 5 will call the editProject method
            if (userEntry.equals("5")) {
                editProject(scan);
            }

            //VIEW OVERDUE PROJECTS
            //entering option 6 will call the overdueProjects method:
            if (userEntry.equals("6")) {
                overdueProjects();
            }

            //VIEW COMPLETED PROJECTS
            //entering option 7 will call the incompleteProjects method:
            if (userEntry.equals("7")) {
                incompleteProjects();
            }

            //PRINT TAX INVOICE
            //entering option 8 will call the printTaxInvoice method:
            if (userEntry.equals("8")) {
                printTaxInvoice(scan);
            }
        }
        //EXIT
        //entering option 8 will exit the program:
        while (!userEntry.equals("9"));
        System.out.println("Thank you, good bye");
    }

    //METHODS:

    //CREATE PERSON - requests person information and uses set methods to assign info to person:
    public static void createPerson(Scanner scan, String personToCreate) {
        Person person = new Person();
        try {
            System.out.println("Please enter the following information required to create a new person");
            System.out.println("Full Name:");
            String name = "'" + scan.nextLine() + "'";

            System.out.println("Telephone Number:");
            String tel = "'" + scan.nextLine() + "'";

            System.out.println("Email:");
            String email = "'" + scan.nextLine() + "'";

            System.out.println("Address:");
            String address = "'" + scan.nextLine() + "'";

            person.setName(name.replace("'", ""));
            person.setTel(tel.replace("'", ""));
            person.setEmail(email.replace("'", ""));
            person.setAddress(address.replace("'", ""));

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PoisePMS?useSSL=false&allowPublicKeyRetrieval=true", "jen", "bananas");
            Statement stmt = conn.createStatement();

            String newPerson = "INSERT into " + personToCreate + " VALUES (" + name + ", " + tel + ", " + email + ", " + address + ")";
            System.out.println("The SQL query is: " + newPerson);
            int countInserted = stmt.executeUpdate(newPerson);
            System.out.println(countInserted + " new people added.\n");

            conn.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //CREATE PROJECT - requests project information and uses set methods to assign info to project.
    public static void createProject(Scanner scan) {
        Project project = new Project();

        System.out.println("\nPlease review the customer table below to ensure that your customer is already listed.\n");

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PoisePMS?useSSL=false&allowPublicKeyRetrieval=true", "jen", "bananas");
            Statement stmt = conn.createStatement();

            String customerSearch = "SELECT * FROM customers";
            System.out.println("The SQL query is: " + customerSearch);

            ResultSet rset = stmt.executeQuery(customerSearch);

            System.out.println("The customers on the system are:");
            int customerCount = 0;

            while (rset.next()) {
                String name = rset.getString("name");
                String tel = rset.getString("tel");
                String email = rset.getString("email");
                String address = rset.getString("address");
                String customerOutput = "\nName:\t\t" + name;
                customerOutput += "\nTel:\t\t" + tel;
                customerOutput += "\nEmail:\t\t" + email;
                customerOutput += "\nAddress:\t" + address;
                System.out.println(customerOutput);
                ++customerCount;
            }

            System.out.println("\nIs your customer already listed in the customer table? Enter 'Y' or 'N'");

            String customerCheck = scan.nextLine();
            if (customerCheck.equalsIgnoreCase("Y")) {

                //each answer is assigned to a variable:
                System.out.println("Please enter the following information required to create a new project");

                System.out.println("Customer Name: ");
                String custName = "'" + scan.nextLine() + "'";

                System.out.println("Project Name:");
                String nameResponse = scan.nextLine();

                System.out.println("Number:");
                String number = "'" + scan.nextLine() + "'";

                System.out.println("Building Type:");
                String type = "'" + scan.nextLine() + "'";

                System.out.println("Site Address:");
                String address = "'" + scan.nextLine() + "'";

                System.out.println("Site ERF:");
                String erf = scan.nextLine();

                System.out.println("Total quoted amount for project inc VAT: (0000,00)");
                float totalCost = scan.nextFloat();
                scan.nextLine();

                System.out.println("Total amount paid to date inc VAT: (0000,00)");
                float totalPaid = scan.nextFloat();
                scan.nextLine();

                System.out.println("Deadline Date: (YYYY-MM-DD)");
                String deadlineDate = "'" + scan.nextLine() + "'";

                System.out.println("Structural Engineer Name: ");
                String strucEngName = "'" + scan.nextLine() + "'";

                System.out.println("Architect Name: ");
                String archName = "'" + scan.nextLine() + "'";

                System.out.println("Project Manager Name: ");
                String projMngrName = "'" + scan.nextLine() + "'";

                //if the project name has not been given, the default name will be the build type followed by the customer surname:
                String projectName;
                if (nameResponse.equals("")) {
                    projectName = "'" + type.replace("'", "") + " " + (custName.replace("'", "").split(" ")[1]) + "'";
                } else {
                    projectName = "'" + nameResponse + "'";
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
                project.setCustomer(custName);
                project.setStructuralEngineer(strucEngName);
                project.setArchitect(archName);
                project.setProjectManager(projMngrName);

                String newProject = "INSERT into projects " + " VALUES (" + number + ", " + projectName + ", "
                        + type + ", " + address + ", " + erf + ", " + totalCost + ", " + totalPaid + ", "
                        + deadlineDate + ", " + null + ", " + custName + ", " + strucEngName + ", " + archName + ", "
                        + projMngrName + ")";
                System.out.println("The SQL query is: " + newProject);
                int countInserted = stmt.executeUpdate(newProject);
                System.out.println(countInserted + " new projects added.\n");
            }

            if (customerCheck.equalsIgnoreCase("N")) {
                System.out.println("Please make sure you add your customer to the customer table before creating your new project");
            }

            conn.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //EDIT PERSON - user can select the type of person, the specific person and the type of information they need to
    //change and then provide the updated information:
    public static void editPerson(Scanner scan) {
        try {
            String tableToEdit = null;
            String infoToEdit = null;
            String newInfo = null;

            System.out.println("Which kind of person would you like to edit? Please enter 1, 2 or 3.");
            System.out.println("1\tCustomer");
            System.out.println("2\tStructural Engineer");
            System.out.println("3\tArchitect");
            System.out.println("4\tProject Manager");

            String personChoice = scan.nextLine();
            if (personChoice.equals("1")) {
                tableToEdit = "customers";
            }
            if (personChoice.equals("2")) {
                tableToEdit = "structuralEngineers";
            }
            if (personChoice.equals("3")) {
                tableToEdit = "architects";
            }
            if (personChoice.equals("4")) {
                tableToEdit = "projectManagers";
            }

            System.out.println("What is the name of the person you would like to edit?");
            String personName = "'" + scan.nextLine() + "'";

            System.out.println("Which information would you like to edit? Please enter 1, 2, 3 or 4");
            System.out.println("1\tName");
            System.out.println("2\tTel");
            System.out.println("3\tEmail");
            System.out.println("4\tAddress");
            String editChoice = scan.nextLine();

            if (editChoice.equals("1")) {
                infoToEdit = "name";
                System.out.println("What is the new name?");
                newInfo = "'" + scan.nextLine() + "'";
            }
            if (editChoice.equals("2")) {
                infoToEdit = "tel";
                System.out.println("What is the new tel number?");
                newInfo = "'" + scan.nextLine() + "'";
            }
            if (editChoice.equals("3")) {
                infoToEdit = "email";
                System.out.println("What is the new email?");
                newInfo = "'" + scan.nextLine() + "'";
            }
            if (editChoice.equals("4")) {
                infoToEdit = "address";
                System.out.println("What is the new address?");
                newInfo = "'" + scan.nextLine() + "'";
            }

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PoisePMS?useSSL=false&allowPublicKeyRetrieval=true", "jen", "bananas");
            Statement stmt = conn.createStatement();

            String personUpdate = "UPDATE " + tableToEdit + " SET " + infoToEdit + " = " + newInfo + " WHERE name LIKE " + personName;

            System.out.println("The SQL query is: " + personUpdate);
            int countInserted = stmt.executeUpdate(personUpdate);
            System.out.println(countInserted + " people updated.\n");

            conn.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //VIEW PROJECTS - displays all projects listed in the projects table
    public static void viewProjects(Scanner scan) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PoisePMS?useSSL=false&allowPublicKeyRetrieval=true", "jen", "bananas");
            Statement stmt = conn.createStatement();

            String projectsAll = "SELECT * FROM projects";
            System.out.println("The SQL query is: " + projectsAll);

            ResultSet rset = stmt.executeQuery(projectsAll);

            System.out.println("The projects listed are:");
            int projectCount = 0;

            while (rset.next()) {
                String projNum = rset.getString("projNum");
                String projName = rset.getString("projName");
                String buildType = rset.getString("buildType");
                String projAddress = rset.getString("projAddress");
                int erf = rset.getInt("erf");
                float totalFee = rset.getFloat("totalFee");
                float totalPaid = rset.getFloat("totalPaid");
                Date deadlineDate = rset.getDate("deadlineDate");
                Date completionDate = rset.getDate("completionDate");
                String custName = rset.getString("custName");
                String strucEngName = rset.getString("strucEngName");
                String archName = rset.getString("archName");
                String projMngrName = rset.getString("projMngrName");

                String projOutput = "\nProject Number:\t\t" + projNum;
                projOutput += "\nProject Name:\t\t" + projName;
                projOutput += "\nBuild Type:\t\t\t" + buildType;
                projOutput += "\nProject Address:\t" + projAddress;
                projOutput += "\nERF:\t\t\t\t" + erf;
                projOutput += "\nTotal Fee:\t\t\t" + totalFee;
                projOutput += "\nTotal Paid:\t\t\t" + totalPaid;
                projOutput += "\nDeadline Date:\t\t" + deadlineDate;
                projOutput += "\nCompletion Date:\t" + completionDate;
                projOutput += "\nCustomer:\t\t\t" + custName;
                projOutput += "\nStruct.Engineer:\t" + strucEngName;
                projOutput += "\nArchitect:\t\t\t" + archName;
                projOutput += "\nProject Manager:\t" + projMngrName + "\n";

                System.out.println(projOutput);
                ++projectCount;
            }
            conn.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //EDIT PROJECT - displays all projects, asks the user if they would like to edit a project,
    //the user can then provide updated information.
    public static void editProject(Scanner scan) {
        String projectInfoToEdit = null;
        String newProjectInfo = null;
        Float newProjectFloat = null;

        viewProjects(scan);
        System.out.println("\nPlease enter the project number of the project you would like to edit");
        String projectToEdit = "'" + scan.nextLine() + "'";

        //Prompts user for specific information to edit:
        System.out.println("Which information in this project would you like to edit?");
        System.out.println("1:\tTotal Fee");
        System.out.println("2:\tTotal Paid");
        System.out.println("3:\tDeadline Date");
        System.out.println("4:\tCompletion Date\n");
        String userEntry = scan.nextLine();

        //options 1 and 2 require a float response:
        if (userEntry.equals("1") || userEntry.equals("2")) {
            if (userEntry.equals("1")) {
                projectInfoToEdit = "totalFee";
                System.out.println("What is the new total fee for this project? (0000,00)");
                newProjectFloat = scan.nextFloat();
                scan.nextLine();
            }
            if (userEntry.equals("2")) {
                projectInfoToEdit = "totalPaid";
                System.out.println("What is the new total paid for this project? (0000,00)");
                newProjectFloat = scan.nextFloat();
                scan.nextLine();
            }
            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PoisePMS?useSSL=false&allowPublicKeyRetrieval=true", "jen", "bananas");
                Statement stmt = conn.createStatement();

                String projectUpdate = "UPDATE projects SET " + projectInfoToEdit + " = " + newProjectFloat + " WHERE projNum LIKE " + projectToEdit;

                System.out.println("The SQL query is: " + projectUpdate);
                int countInserted = stmt.executeUpdate(projectUpdate);
                System.out.println(countInserted + " projects updated.\n");

                conn.close();
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //options 3 and 4 require a string response:
        if (userEntry.equals("3") || (userEntry.equals("4"))) {
            if (userEntry.equals("3")) {
                projectInfoToEdit = "deadlineDate";
                System.out.println("What is the new deadline date for this project? (YYYY-MM-DD)");
                newProjectInfo = "'" + scan.nextLine() + "'";
            }
            if (userEntry.equals("4")) {
                projectInfoToEdit = "completionDate";
                System.out.println("What is the completion date for this project? (YYYY-MM-DD)");
                newProjectInfo = "'" + scan.nextLine() + "'";
            }
            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PoisePMS?useSSL=false&allowPublicKeyRetrieval=true", "jen", "bananas");
                Statement stmt = conn.createStatement();

                String projectUpdate = "UPDATE projects SET " + projectInfoToEdit + " = " + newProjectInfo + " WHERE projNum LIKE " + projectToEdit;

                System.out.println("The SQL query is: " + projectUpdate);
                int countInserted = stmt.executeUpdate(projectUpdate);
                System.out.println(countInserted + " projects updated.\n");

                conn.close();
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //OVERDUE PROJECTS - compares deadlineDate with today's date and checks that completionDate is null
    //if the project is overdue and not complete it is displayed
    public static void overdueProjects() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PoisePMS?useSSL=false&allowPublicKeyRetrieval=true", "jen", "bananas");
            Statement stmt = conn.createStatement();

            String overdueProjects = "SELECT * FROM projects WHERE (deadlineDate < CAST(CURRENT_TIMESTAMP AS DATE) AND completionDate IS NULL)";

            System.out.println("The SQL query is: " + overdueProjects);

            ResultSet rset = stmt.executeQuery(overdueProjects);

            if (!rset.next()) {
                System.out.println("\nThere are no overdue projects at this time\n");
            } else {
                System.out.println("The overdue projects are:");
                int overdueProjectCount = 0;
                do {
                    String projNum = rset.getString("projNum");
                    String projName = rset.getString("projName");
                    String buildType = rset.getString("buildType");
                    String projAddress = rset.getString("projAddress");
                    int erf = rset.getInt("erf");
                    float totalFee = rset.getFloat("totalFee");
                    float totalPaid = rset.getFloat("totalPaid");
                    Date deadlineDate = rset.getDate("deadlineDate");
                    Date completionDate = rset.getDate("completionDate");
                    String custName = rset.getString("custName");
                    String strucEngName = rset.getString("strucEngName");
                    String archName = rset.getString("archName");
                    String projMngrName = rset.getString("projMngrName");

                    String projOutput = "\nProject Number:\t\t" + projNum;
                    projOutput += "\nProject Name:\t\t" + projName;
                    projOutput += "\nBuild Type:\t\t\t" + buildType;
                    projOutput += "\nProject Address:\t" + projAddress;
                    projOutput += "\nERF:\t\t\t\t" + erf;
                    projOutput += "\nTotal Fee:\t\t\t" + totalFee;
                    projOutput += "\nTotal Paid:\t\t\t" + totalPaid;
                    projOutput += "\nDeadline Date:\t\t" + deadlineDate;
                    projOutput += "\nCompletion Date:\t" + completionDate;
                    projOutput += "\nCustomer:\t\t\t" + custName;
                    projOutput += "\nStruct. Engineer:\t" + strucEngName;
                    projOutput += "\nArchitect:\t\t\t" + archName;
                    projOutput += "\nProject Manager:\t" + projMngrName + "\n";

                    System.out.println(projOutput);
                    ++overdueProjectCount;
                }
                while (rset.next());
            }
            conn.close();
            stmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //INCOMPLETE PROJECTS - if a project does not have a completion date, it is displayed
    public static void incompleteProjects() {

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PoisePMS?useSSL=false&allowPublicKeyRetrieval=true", "jen", "bananas");
            Statement stmt = conn.createStatement();

            String notCompleteProjects = "SELECT * FROM projects WHERE completionDate IS NULL";
            System.out.println("The SQL query is: " + notCompleteProjects);

            ResultSet rset = stmt.executeQuery(notCompleteProjects);

            System.out.println("The incomplete projects are:");
            int incompleteProjectCount = 0;

            if (!rset.next()) {
                System.out.println("There are no incomplete projects at this time");
            } else {
                do {
                    String projNum = rset.getString("projNum");
                    String projName = rset.getString("projName");
                    String buildType = rset.getString("buildType");
                    String projAddress = rset.getString("projAddress");
                    int erf = rset.getInt("erf");
                    float totalFee = rset.getFloat("totalFee");
                    float totalPaid = rset.getFloat("totalPaid");
                    Date deadlineDate = rset.getDate("deadlineDate");
                    Date completionDate = rset.getDate("completionDate");
                    String custName = rset.getString("custName");
                    String strucEngName = rset.getString("strucEngName");
                    String archName = rset.getString("archName");
                    String projMngrName = rset.getString("projMngrName");

                    String projOutput = "\nProject Number:\t\t" + projNum;
                    projOutput += "\nProject Name:\t\t" + projName;
                    projOutput += "\nBuild Type:\t\t\t" + buildType;
                    projOutput += "\nProject Address:\t" + projAddress;
                    projOutput += "\nERF:\t\t\t\t" + erf;
                    projOutput += "\nTotal Fee:\t\t\t" + totalFee;
                    projOutput += "\nTotal Paid:\t\t\t" + totalPaid;
                    projOutput += "\nDeadline Date:\t\t" + deadlineDate;
                    projOutput += "\nCompletion Date:\t" + completionDate;
                    projOutput += "\nCustomer:\t\t\t" + custName;
                    projOutput += "\nStruct. Engineer:\t" + strucEngName;
                    projOutput += "\nArchitect:\t\t\t" + archName;
                    projOutput += "\nProject Manager:\t" + projMngrName + "\n";

                    System.out.println(projOutput);
                    ++incompleteProjectCount;
                }
                while (rset.next());
            }
            conn.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //printTaxInvoice reads information from the projects table and the customers table and displays the necessary
    //information to the screen in the form of an invoice
    public static void printTaxInvoice(Scanner scan) {
        viewProjects(scan);

        System.out.println("Please enter the project number of the project for which you need a tax invoice");
        String projectChoice = "'" + scan.nextLine() + "'";

        String projNum = null;
        float totalFee = 0;
        float totalPaid = 0;
        String custName = null;
        String name = null;
        String tel = null;
        String email = null;
        String address = null;

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PoisePMS?useSSL=false&allowPublicKeyRetrieval=true", "jen", "bananas");
            Statement stmt = conn.createStatement();

            String getProjectTaxInfo = "SELECT * FROM projects WHERE projNum = " + projectChoice;
            System.out.println("The SQL query is: " + getProjectTaxInfo);

            ResultSet rset = stmt.executeQuery(getProjectTaxInfo);

            while (rset.next()) {
                projNum = rset.getString("projNum");
                totalFee = rset.getFloat("totalFee");
                totalPaid = rset.getFloat("totalPaid");
                custName = "'" + rset.getString("custName") + "'";
            }
            conn.close();
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PoisePMS?useSSL=false&allowPublicKeyRetrieval=true", "jen", "bananas");
            Statement stmt = conn.createStatement();

            String getCustomerTaxInfo = "SELECT * FROM customers WHERE name = " + custName;
            System.out.println("The SQL query is: " + getCustomerTaxInfo);

            ResultSet rset = stmt.executeQuery(getCustomerTaxInfo);

            while (rset.next()) {
                name = rset.getString("name");
                tel = rset.getString("tel");
                email = rset.getString("email");
                address = rset.getString("address");
            }

            conn.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("\nTAX INVOICE");
        System.out.println("\nProject Number:\t\t" + projNum);
        System.out.println("Customer Name:\t\t" + name);
        System.out.println("Customer Tel:\t\t" + tel);
        System.out.println("Customer Email:\t\t" + email);
        System.out.println("Customer Address:\t" + address + "\n");
        System.out.println("Total Fee:\t\t" + totalFee);
        System.out.println("Total Paid:\t\t" + totalPaid);
        System.out.println("Total Due:\t\t" + (totalFee - totalPaid));
        System.out.println("\nThank you very much for your business\n");
    }
}



