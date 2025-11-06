import java.util.ArrayList;
import java.util.Iterator;

public class AssignmentOne {
    // 静态ArrayList存储预约集合（全局可访问）
    private static ArrayList<Appointment> appointmentList = new ArrayList<>();

    public static void main(String[] args) {
        // Part 3 – Using classes and objects
        System.out.println("===== 第三部分：类与对象的使用 =====");
        // 创建3个全科医生对象
        GeneralPractitioner gp1 = new GeneralPractitioner(101, "张小明", "GP2023001", "家庭医疗、常见病诊治、健康体检");
        GeneralPractitioner gp2 = new GeneralPractitioner(102, "李小华", "GP2023002", "儿科常见病、慢性病管理、疫苗接种");
        GeneralPractitioner gp3 = new GeneralPractitioner(103, "王小红", "GP2023003", "老年病护理、感冒发烧、胃肠疾病");

        // 创建2个心脏病专家对象
        Cardiologist cardio1 = new Cardiologist(201, "赵心脏", "CARD2023001", "冠心病介入治疗、心律失常诊治");
        Cardiologist cardio2 = new Cardiologist(202, "孙心康", "CARD2023002", "心力衰竭管理、先天性心脏病诊疗");

        // 打印所有健康专业人员详情
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

        // Part 5 – Collection of appointments
        System.out.println("===== 第五部分：预约集合管理 =====");
        // 创建2个全科医生预约
        createAppointment("陈患者", "13800138001", "08:30", gp1);
        createAppointment("李患者", "13900139001", "10:15", gp2);

        // 创建2个心脏病专家预约
        createAppointment("王患者", "13700137001", "14:00", cardio1);
        createAppointment("赵患者", "13600136001", "15:30", cardio2);

        // 打印现有预约
        System.out.println("\n【第一次打印所有预约】");
        printExistingAppointments();

        // 取消一个预约（通过手机号13900139001）
        System.out.println("\n【执行取消预约操作】");
        cancelBooking("13900139001");

        // 再次打印预约（展示更新后集合）
        System.out.println("\n【第二次打印所有预约】");
        printExistingAppointments();
        System.out.println("------------------------------");
    }

    /**
     * 创建预约并添加到集合（多态支持：接受任意HealthProfessional子类对象）
     * @param patientName 患者姓名
     * @param patientMobile 患者手机号（必填验证）
     * @param timeSlot 预约时间段（必填验证）
     * @param doctor 预约医生（必填验证）
     */
    public static void createAppointment(String patientName, String patientMobile, String timeSlot, HealthProfessional doctor) {
        // 验证必需信息
        if (patientName == null || patientName.isEmpty() ||
                patientMobile == null || patientMobile.isEmpty() ||
                timeSlot == null || timeSlot.isEmpty() ||
                doctor == null) {
            System.out.println("预约创建失败：所有信息均为必填项，不能为空！");
            return;
        }

        // 创建预约对象并添加到集合
        Appointment appointment = new Appointment(patientName, patientMobile, timeSlot, doctor);
        appointmentList.add(appointment);
        System.out.println("预约创建成功！患者：" + patientName + "，预约医生：" + doctor.getName());
    }

    /**
     * 打印现有所有预约，无预约时提示
     */
    public static void printExistingAppointments() {
        if (appointmentList.isEmpty()) {
            System.out.println("当前无任何预约记录！");
            return;
        }

        System.out.println("当前共有 " + appointmentList.size() + " 条预约记录：");
        for (int i = 0; i < appointmentList.size(); i++) {
            System.out.println("\n【预约 " + (i + 1) + "】");
            appointmentList.get(i).printAppointmentDetails();
        }
    }

    /**
     * 通过患者手机号取消预约，未找到时提示
     * @param patientMobile 患者手机号
     */
    public static void cancelBooking(String patientMobile) {
        Iterator<Appointment> iterator = appointmentList.iterator();
        boolean found = false;

        while (iterator.hasNext()) {
            Appointment appointment = iterator.next();
            if (appointment.getPatientMobile().equals(patientMobile)) {
                iterator.remove(); // 安全删除预约
                System.out.println("取消预约成功！患者手机号：" + patientMobile);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("取消预约失败：未找到手机号为 " + patientMobile + " 的预约记录！");
        }
    }
}
