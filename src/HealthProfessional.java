public class HealthProfessional {
    // 必需实例变量：ID（仅数字）、姓名
    protected int id;
    protected String name;
    // 通用实例变量：专业资格证书编号
    protected String qualificationNo;

    // 默认构造方法
    public HealthProfessional() {}

    // 初始化所有实例变量的构造方法
    public HealthProfessional(int id, String name, String qualificationNo) {
        this.id = id;
        this.name = name;
        this.qualificationNo = qualificationNo;
    }

    // 打印所有实例变量的方法
    public void printDetails() {
        System.out.println("健康专业人员ID：" + id);
        System.out.println("姓名：" + name);
        System.out.println("资格证书编号：" + qualificationNo);
    }

    // Getter方法（用于Appointment类访问）
    public int getId() { return id; }
    public String getName() { return name; }
    public String getQualificationNo() { return qualificationNo; }
}