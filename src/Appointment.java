//https://github.com/dzjhy/yzhou59-A1.git
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Appointment {
    // Patient information (ensure correct attribute definition)
    private String patientName;
    private String patientMobile;
    // Appointment information: split into date + time slot
    private LocalDate appointmentDate;
    private LocalTime timeSlot;
    // Appointed doctor (polymorphism application, depends on HealthProfessional base class)
    private HealthProfessional doctor;

    // Default constructor
    public Appointment() {}

    // Core constructor (with time validation)
    public Appointment(String patientName, String patientMobile, String timeSlot, HealthProfessional doctor) {
        this.patientName = patientName;
        this.patientMobile = patientMobile;
        this.doctor = doctor;
        this.appointmentDate = LocalDate.now(); // Default to same-day appointment

        // 1. Parse and validate time slot format (HH:mm)
        LocalTime parsedTime;
        try {
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            parsedTime = LocalTime.parse(timeSlot, timeFormatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid time slot format! Please use HH:mm format (e.g., 08:30, 14:00)");
        }

        // 2. Validate working hours (08:00-18:00) and timeliness
        LocalTime nowTime = LocalTime.now();
        if (parsedTime.isBefore(LocalTime.of(8, 0)) || parsedTime.isAfter(LocalTime.of(18, 0))) {
            throw new IllegalArgumentException("Appointment time is outside working hours! Working hours: 08:00-18:00");
        }
        if (parsedTime.isBefore(nowTime) && appointmentDate.isEqual(LocalDate.now())) {
            throw new IllegalArgumentException("Appointment time has passed! Current time: " + nowTime.format(DateTimeFormatter.ofPattern("HH:mm")));
        }

        this.timeSlot = parsedTime;
    }

    // Print appointment details
    public void printAppointmentDetails() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        System.out.println("=== Appointment Details ===");
        System.out.println("Patient Name: " + patientName);
        System.out.println("Patient Mobile: " + patientMobile);
        System.out.println("Appointment Date: " + appointmentDate);
        System.out.println("Appointment Time Slot: " + timeSlot.format(timeFormatter));
        System.out.println("Appointed Doctor Information:");
        doctor.printDetails(); // Polymorphic call
    }

    // Getter method (for appointment cancellation)
    public String getPatientMobile() {
        return patientMobile;
    }

    // New: Get patient name (optimize cancellation prompt)
    public String getPatientName() {
        return patientName;
    }
}