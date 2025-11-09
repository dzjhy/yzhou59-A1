public class HealthProfessional {
    // Required instance variables: ID (numeric only), Name
    protected int id;
    protected String name;
    // Common instance variable: Professional Qualification Certificate Number
    protected String qualificationNo;

    // Default constructor
    public HealthProfessional() {}

    // Constructor to initialize all instance variables
    public HealthProfessional(int id, String name, String qualificationNo) {
        this.id = id;
        this.name = name;
        this.qualificationNo = qualificationNo;
    }

    // Method to print all instance variables
    public void printDetails() {
        System.out.println("Health Professional ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Qualification Certificate Number: " + qualificationNo);
    }

    // Getter methods (for access in Appointment class)
    public int getId() { return id; }
    public String getName() { return name; }
    public String getQualificationNo() { return qualificationNo; }
}