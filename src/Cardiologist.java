public class Cardiologist extends HealthProfessional {
    // 独特实例变量：心脏病诊疗专长（如冠心病、心律失常）
    private String cardioSpecialization;

    // 默认构造方法
    public Cardiologist() {}

    // 初始化所有实例变量（含基类）的构造方法
    public Cardiologist(int id, String name, String qualificationNo, String cardioSpecialization) {
        super(id, name, qualificationNo);
        this.cardioSpecialization = cardioSpecialization;
    }

    // 打印心脏病专家详情（含类型和基类信息）
    @Override
    public void printDetails() {
        System.out.println("=== 健康专业人员详情 ===");
        System.out.println("类型：心脏病专家");
        super.printDetails();
        System.out.println("心脏病诊疗专长：" + cardioSpecialization);
    }

    // Getter方法
    public String getCardioSpecialization() { return cardioSpecialization; }
}