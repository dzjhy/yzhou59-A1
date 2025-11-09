public class GeneralPractitioner extends HealthProfessional {
    // Unique instance variable: Specialized general practice areas (e.g., family medicine, common disease treatment)
    private String specializedAreas;

    // Default constructor
    public GeneralPractitioner() {}

    // Constructor to initialize all instance variables (including superclass)
    public GeneralPractitioner(int id, String name, String qualificationNo, String specializedAreas) {
        super(id, name, qualificationNo);
        this.specializedAreas = specializedAreas;
    }

    // Print general practitioner details (including type and superclass information)
    @Override
    public void printDetails() {
        System.out.println("=== Health Professional Details ===");
        System.out.println("Type: General Practitioner");
        super.printDetails();
        System.out.println("Specialized Areas: " + specializedAreas);
    }

    // Getter method
    public String getSpecializedAreas() { return specializedAreas; }
}