/****************/
// Mã sinh viên: 202419055
// Họ tên: Phí Việt Hiếu
/****************/
package campussafetyhub;

import campussafetyhub.adapter.ISensor;
import campussafetyhub.adapter.LegacySmokeDetector;
import campussafetyhub.adapter.SmokeSensorAdapter;
import campussafetyhub.mediator.Alarm;
import campussafetyhub.mediator.DoorController;
import campussafetyhub.mediator.EmergencyMediator;
import campussafetyhub.mediator.VentilationController;
import campussafetyhub.observer.AuditLogObserver;
import campussafetyhub.observer.DashboardObserver;
import campussafetyhub.observer.EmailAlertObserver;
import campussafetyhub.observer.EmergencyBridgeObserver;
import campussafetyhub.observer.SensorMonitor;

// Mô phỏng Campus Safety Hub trên console (lab tuần 11: Adapter, Observer, Mediator)
public class Main {

    private static final String SENSOR_NAME = "Phong may A1";

    public static void main(String[] args) {
        System.out.println("=== Campus Safety Hub ===");

        // Dựng các thiết bị và gắn chúng vào mediator
        Alarm alarm = new Alarm();
        DoorController door = new DoorController();
        VentilationController ventilation = new VentilationController();
        EmergencyMediator mediator = new EmergencyMediator(alarm, door, ventilation);

        // Đăng ký các bên nhận cảnh báo với monitor
        SensorMonitor monitor = new SensorMonitor();
        EmailAlertObserver email = new EmailAlertObserver("baove@campus.edu.vn");
        AuditLogObserver audit = new AuditLogObserver();
        monitor.attach(new DashboardObserver());
        monitor.attach(email);
        monitor.attach(audit);
        monitor.attach(new EmergencyBridgeObserver(mediator));

        // Bọc cảm biến cũ để monitor đọc được qua ISensor
        LegacySmokeDetector legacy = new LegacySmokeDetector(0);
        ISensor sensor = new SmokeSensorAdapter(legacy);

        runStep("Khoi 30% - binh thuong", legacy, 300, monitor, sensor);
        runStep("Khoi 55% - canh bao", legacy, 550, monitor, sensor);
        runStep("Khoi 73.5% - nguy hiem", legacy, 735, monitor, sensor);

        System.out.println();
        System.out.println("--- Bao ve tat coi ---");
        alarm.silence();

        // Gỡ email để thấy observer đã detach không còn nhận cảnh báo
        monitor.detach(email);
        runStep("Khoi 60% sau khi go email", legacy, 600, monitor, sensor);

        System.out.println();
        System.out.println("--- Trang thai cuoi ---");
        System.out.println("Coi dang keu: " + alarm.isRinging());
        System.out.println("Cua mo khoa: " + door.isUnlocked());
        System.out.println("Thong gio: " + ventilation.getMode());
        System.out.println("So ban ghi audit: " + audit.getEntries().size());
    }

    // Đặt mức khói cho cảm biến cũ rồi cho monitor kiểm tra một lượt
    private static void runStep(String title, LegacySmokeDetector legacy, int level,
                                SensorMonitor monitor, ISensor sensor) {
        System.out.println();
        System.out.println("--- " + title + " ---");
        legacy.setSmokeLevel(level);
        System.out.println("Doc qua adapter: " + sensor.readValue() + sensor.getUnit());
        monitor.checkSensor(SENSOR_NAME, sensor);
    }
}
