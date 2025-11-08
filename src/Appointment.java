import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Appointment {
    // 患者信息
    private String patientName;
    private String patientMobile;
    // 预约信息：拆分日期+时间段（兼容你原有"08:30"这种时间段格式）
    private LocalDate appointmentDate; // 预约日期（默认用当前日期，可扩展）
    private LocalTime timeSlot; // 原时间段字符串改成 LocalTime（方便校验）
    // 预约的医生（子类对象，多态应用）
    private HealthProfessional doctor;

    // 默认构造方法
    public Appointment() {}

    // 核心构造方法：接收原格式参数，内部做时间校验
    public Appointment(String patientName, String patientMobile, String timeSlot, HealthProfessional doctor) {
        this.patientName = patientName;
        this.patientMobile = patientMobile;
        this.doctor = doctor;
        this.appointmentDate = LocalDate.now(); // 默认预约当天（可扩展为用户传入日期）

        // 1. 解析时间段字符串为 LocalTime（校验格式：HH:mm，比如 "08:30"）
        LocalTime parsedTime;
        try {
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            parsedTime = LocalTime.parse(timeSlot, timeFormatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("时间段格式错误！请使用 HH:mm 格式（例：08:30、14:00）");
        }

        // 2. 校验时间段合法性（必须是未来的时间，且在合理时段：8:00-18:00）
        LocalTime nowTime = LocalTime.now();
        if (parsedTime.isBefore(LocalTime.of(8, 0)) || parsedTime.isAfter(LocalTime.of(18, 0))) {
            throw new IllegalArgumentException("预约时间超出工作时段！工作时间：08:00-18:00");
        }
        if (parsedTime.isBefore(nowTime) && appointmentDate.isEqual(LocalDate.now())) {
            // 当天预约：时间段必须晚于当前时间
            throw new IllegalArgumentException("预约时间已过！当前时间：" + nowTime.format(DateTimeFormatter.ofPattern("HH:mm")));
        }

        this.timeSlot = parsedTime; // 校验通过，赋值
    }

    // 打印方法：保持原有格式，优化时间显示
    public void printAppointmentDetails() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        System.out.println("=== 预约详情 ===");
        System.out.println("患者姓名：" + patientName);
        System.out.println("患者手机号：" + patientMobile);
        System.out.println("预约日期：" + appointmentDate);
        System.out.println("预约时间段：" + timeSlot.format(timeFormatter));
        System.out.println("预约医生信息：");
        doctor.printDetails(); // 多态调用不变
    }

    // Getter方法（取消预约用）
    public String getPatientMobile() { return patientMobile; }
}