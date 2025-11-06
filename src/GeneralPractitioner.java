public class GeneralPractitioner extends HealthProfessional {
    // 独特实例变量：擅长全科领域（如家庭医疗、常见病诊疗）
    private String specializedAreas;

    // 默认构造方法
    public GeneralPractitioner() {}

    // 初始化所有实例变量（含基类）的构造方法
    public GeneralPractitioner(int id, String name, String qualificationNo, String specializedAreas) {
        super(id, name, qualificationNo);
        this.specializedAreas = specializedAreas;
    }

    // 打印全科医生详情（含类型和基类信息）
    @Override
    public void printDetails() {
        System.out.println("=== 健康专业人员详情 ===");
        System.out.println("类型：全科医生");
        super.printDetails();
        System.out.println("擅长领域：" + specializedAreas);
    }

    // Getter方法
    public String getSpecializedAreas() { return specializedAreas; }
}