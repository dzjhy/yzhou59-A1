public class Appointment {
    // 患者信息
    private String patientName;
    private String patientMobile;
    // 预约信息
    private String timeSlot;
    // 预约的医生（子类对象，多态应用）
    private HealthProfessional doctor;

    // 默认构造方法
    public Appointment() {}

    // 初始化所有实例变量的构造方法
    public Appointment(String patientName, String patientMobile, String timeSlot, HealthProfessional doctor) {
        this.patientName = patientName;
        this.patientMobile = patientMobile;
        this.timeSlot = timeSlot;
        this.doctor = doctor;
    }

    // 打印所有实例变量的方法
    public void printAppointmentDetails() {
        System.out.println("=== 预约详情 ===");
        System.out.println("患者姓名：" + patientName);
        System.out.println("患者手机号：" + patientMobile);
        System.out.println("预约时间段：" + timeSlot);
        System.out.println("预约医生信息：");
        doctor.printDetails(); // 多态调用：根据医生类型执行对应printDetails方法
    }

    // Getter方法（用于取消预约功能）
    public String getPatientMobile() { return patientMobile; }
}