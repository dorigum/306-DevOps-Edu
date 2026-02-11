package ex0211.sample.sample02;

public class MainApp {
    public static void main(String[] args) {
        FullTime[] full = new FullTime[3];
        PartTime[] part = new PartTime[2];

        // 객체 생성
        full[0] = new FullTime(10, "유재석", "개그맨", 0, "2013-05-01", "무한도전", 8500, 200);
        full[1] = new FullTime(20, "박명수", "가수", 10, "2013-06-20", "무한도전", 7500, 100);
        full[2] = new FullTime(30, "정준하", "예능인", 10, "2013-06-22", "무한도전", 6000, 0);

        part[0] = new PartTime(40, "노홍철", "예능인", 20, "2014-05-01", "무한도전", 20000);
        part[1] = new PartTime(50, "하하", "가수", 30, "2014-05-02", "무한도전", 25000);

        // ----------------------------------------------------------------------------------
        System.out.println("✔sample02: 부모-자식 상속했을 경우✔\n");
        
        // --- 출력 부분 추가 ---
        System.out.println("************** FullTime 정보 **************");
        for (FullTime f : full) {
            System.out.println(f.toString()); // toString 호출
        }

        System.out.println("\n************** PartTime 정보 **************");
        for (PartTime p : part) {
            System.out.println(p.toString());
        }

        System.out.println("\n************** Message **************");
        for (FullTime f : full) f.message();
        for (PartTime p : part) p.message();
    }
}