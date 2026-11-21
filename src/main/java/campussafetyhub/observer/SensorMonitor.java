/****************/
// Mã sinh viên: 202419055
// Họ tên: Phí Việt Hiếu
/****************/
package campussafetyhub.observer;

import campussafetyhub.adapter.ISensor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// Subject: quản lý danh sách observer, đọc cảm biến, so ngưỡng và phát cảnh báo
public final class SensorMonitor {

    private final List<IAlertObserver> observers = new ArrayList<>();

    // Đăng ký observer, bỏ qua nếu đã đăng ký rồi
    public void attach(IAlertObserver observer) {
        if (observer == null) {
            throw new IllegalArgumentException("observer is null");
        }
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void detach(IAlertObserver observer) {
        observers.remove(observer);
    }

    // Đọc giá trị cảm biến, xác định mức độ và gửi cảnh báo nếu vượt ngưỡng
    public void checkSensor(String sensorName, ISensor sensor) {
        double value = sensor.readValue();
        String severity;
        if (value >= 70) {
            severity = "CRITICAL";
        } else if (value >= 50) {
            severity = "WARNING";
        } else {
            return;
        }

        AlertEvent alertEvent = new AlertEvent(
                sensorName,
                value,
                sensor.getUnit(),
                severity,
                LocalDateTime.now());

        notifyObservers(alertEvent);
    }

    // Gửi cảnh báo cho từng observer trên bản sao danh sách, observer lỗi thì ghi log và bỏ qua
    private void notifyObservers(AlertEvent alertEvent) {
        for (IAlertObserver observer : new ArrayList<>(observers)) {
            try {
                observer.update(alertEvent);
            } catch (RuntimeException ex) {
                System.out.println("[MONITOR] " + observer.getClass().getSimpleName()
                        + " loi: " + ex.getMessage());
            }
        }
    }
}
