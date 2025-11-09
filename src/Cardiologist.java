public class Cardiologist extends HealthProfessional {
    // Unique instance variable: Cardiology specialization (e.g., coronary heart disease, arrhythmia)
    private String cardioSpecialization;

    // Default constructor
    public Cardiologist() {}

    // Constructor to initialize all instance variables (including superclass)
    public Cardiologist(int id, String name, String qualificationNo, String cardioSpecialization) {
        super(id, name, qualificationNo);
        this.cardioSpecialization = cardioSpecialization;
    }

    // Print cardiologist details (including type and superclass information)
    @Override
    public void printDetails() {
        System.out.println("=== Health Professional Details ===");
        System.out.println("Type: Cardiologist");
        super.printDetails();
        System.out.println("Cardiology Specialization: " + cardioSpecialization);
    }

    // Getter method
    public String getCardioSpecialization() { return cardioSpecialization; }
}