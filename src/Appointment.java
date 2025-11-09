import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Appointment {
    // 患者信息（确保属性正确定义）
    private String patientName;
    private String patientMobile;
    // 预约信息：拆分日期+时间段
    private LocalDate appointmentDate;
    private LocalTime timeSlot;
    // 预约的医生（多态应用，依赖 HealthProfessional 基类）
    private HealthProfessional doctor;

    // 默认构造方法
    public Appointment() {}

    // 核心构造方法（带时间校验）
    public Appointment(String patientName, String patientMobile, String timeSlot, HealthProfessional doctor) {
        this.patientName = patientName;
        this.patientMobile = patientMobile;
        this.doctor = doctor;
        this.appointmentDate = LocalDate.now(); // 默认预约当天

        // 1. 解析并校验时间段格式（HH:mm）
        LocalTime parsedTime;
        try {
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            parsedTime = LocalTime.parse(timeSlot, timeFormatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("时间段格式错误！请使用 HH:mm 格式（例：08:30、14:00）");
        }

        // 2. 校验工作时段（08:00-18:00）和时效性
        LocalTime nowTime = LocalTime.now();
        if (parsedTime.isBefore(LocalTime.of(8, 0)) || parsedTime.isAfter(LocalTime.of(18, 0))) {
            throw new IllegalArgumentException("预约时间超出工作时段！工作时间：08:00-18:00");
        }
        if (parsedTime.isBefore(nowTime) && appointmentDate.isEqual(LocalDate.now())) {
            throw new IllegalArgumentException("预约时间已过！当前时间：" + nowTime.format(DateTimeFormatter.ofPattern("HH:mm")));
        }

        this.timeSlot = parsedTime;
    }

    // 打印预约详情
    public void printAppointmentDetails() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        System.out.println("=== 预约详情 ===");
        System.out.println("患者姓名：" + patientName);
        System.out.println("患者手机号：" + patientMobile);
        System.out.println("预约日期：" + appointmentDate);
        System.out.println("预约时间段：" + timeSlot.format(timeFormatter));
        System.out.println("预约医生信息：");
        doctor.printDetails(); // 多态调用
    }

    // Getter方法（取消预约用）
    public String getPatientMobile() {
        return patientMobile;
    }

    // 新增：获取患者姓名（优化取消预约提示）
    public String getPatientName() {
        return patientName;
    }
}