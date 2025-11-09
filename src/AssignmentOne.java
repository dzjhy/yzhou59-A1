import java.util.ArrayList;
import java.util.Iterator;

public class AssignmentOne {
    // Static ArrayList to store appointments (globally accessible)
    private static ArrayList<Appointment> appointmentList = new ArrayList<>();

    public static void main(String[] args) {
        // Part 3 – Usage of Classes and Objects
        System.out.println("===== Part 3: Usage of Classes and Objects =====");
        // Create 3 General Practitioner objects (Polymorphism: Subclass objects assigned to superclass references)
        HealthProfessional gp1 = new GeneralPractitioner(101, "Zhang Xiaoming", "GP2023001", "Family Medicine, Common Disease Treatment, Health Checkups");
        HealthProfessional gp2 = new GeneralPractitioner(102, "Li Xiaohua", "GP2023002", "Pediatric Common Diseases, Chronic Disease Management, Vaccination");
        HealthProfessional gp3 = new GeneralPractitioner(103, "Wang Xiaohong", "GP2023003", "Geriatric Care, Cold & Fever, Gastrointestinal Diseases");

        // Create 2 Cardiologist objects
        HealthProfessional cardio1 = new Cardiologist(201, "Zhao Xinzang", "CARD2023001", "Coronary Intervention, Arrhythmia Treatment");
        HealthProfessional cardio2 = new Cardiologist(202, "Sun Xinkang", "CARD2023002", "Heart Failure Management, Congenital Heart Disease Diagnosis & Treatment");

        // Print details of all health professionals (Polymorphic call)
        gp1.printDetails();
        System.out.println();
        gp2.printDetails();
        System.out.println();
        gp3.printDetails();
        System.out.println();
        cardio1.printDetails();
        System.out.println();
        cardio2.printDetails();
        System.out.println("------------------------------");

        // Part 5 – Appointment Collection Management
        System.out.println("\n===== Part 5: Appointment Collection Management =====");
        // Test various appointment scenarios (valid/invalid cases)
        createAppointment("Liu Patient", "13500135001", "8:30", gp1);    // Failed: Invalid format
        createAppointment("Huang Patient", "13400134001", "19:00", gp2);   // Failed: Outside working hours
        createAppointment("Zhou Patient", "13300133001", "10:00", gp3);   // Failed: Time has passed
        createAppointment("", "13200132001", "11:00", gp1);        // Failed: Empty name
        createAppointment("Chen Patient", "13800138001", "08:30", gp1);   // Successful
        createAppointment("Li Patient", "13900139001", "10:15", gp2);   // Successful
        createAppointment("Wang Patient", "13700137001", "14:00", cardio1); // Successful
        createAppointment("Zhao Patient", "13600136001", "15:30", cardio2); // Successful

        // Print existing appointments
        System.out.println("\n[First Print of All Appointments]");
        printExistingAppointments();

        // Cancel appointments (test success/failure scenarios)
        System.out.println("\n[Executing Appointment Cancellation]");
        cancelBooking("13900139001"); // Successful: Li Patient
        cancelBooking("13800138009"); // Failed: Not found

        // Print appointments again
        System.out.println("\n[Second Print of All Appointments]");
        printExistingAppointments();
        System.out.println("------------------------------");
    }

    /**
     * Create an appointment and add it to the collection (supports polymorphism)
     */
    public static void createAppointment(String patientName, String patientMobile, String timeSlot, HealthProfessional doctor) {
        // Basic mandatory field validation
        if (patientName == null || patientName.isEmpty() ||
                patientMobile == null || patientMobile.isEmpty() ||
                timeSlot == null || timeSlot.isEmpty() ||
                doctor == null) {
            System.out.println("❌ Appointment creation failed: All fields are mandatory and cannot be empty!");
            return;
        }

        // Time validity check (catch exceptions)
        try {
            Appointment appointment = new Appointment(patientName, patientMobile, timeSlot, doctor);
            appointmentList.add(appointment);
            System.out.println("✅ Appointment created successfully! Patient: " + patientName + ", Appointed Doctor: " + doctor.getName());
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Appointment creation failed: " + e.getMessage());
        }
    }

    /**
     * Print all existing appointments
     */
    public static void printExistingAppointments() {
        if (appointmentList.isEmpty()) {
            System.out.println("📭 No appointment records found!");
            return;
        }

        System.out.println("📋 There are currently " + appointmentList.size() + " appointment records:");
        for (int i = 0; i < appointmentList.size(); i++) {
            System.out.println("\n[Appointment " + (i + 1) + "]");
            appointmentList.get(i).printAppointmentDetails();
        }
    }

    /**
     * Cancel an appointment by patient's mobile number (Optimization: Display patient name)
     */
    public static void cancelBooking(String patientMobile) {
        Iterator<Appointment> iterator = appointmentList.iterator();
        boolean found = false;

        while (iterator.hasNext()) {
            Appointment appointment = iterator.next();
            if (appointment.getPatientMobile().equals(patientMobile)) {
                iterator.remove();
                // Optimized prompt: Display patient name + mobile number
                System.out.println("✅ Appointment cancelled successfully! Patient: " + appointment.getPatientName() + ", Mobile Number: " + patientMobile);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("❌ Appointment cancellation failed: No appointment record found with mobile number " + patientMobile + "!");
        }
    }
}