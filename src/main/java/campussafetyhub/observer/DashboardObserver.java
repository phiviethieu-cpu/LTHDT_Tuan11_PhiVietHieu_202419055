/****************/
// Mã sinh viên: 202419055
// Họ tên: Phí Việt Hiếu
/****************/
package campussafetyhub.observer;

import java.util.Locale;

// Hiển thị cảnh báo lên màn hình giám sát
public final class DashboardObserver implements IAlertObserver {

    @Override
    public void update(AlertEvent alertEvent) {
        System.out.println(String.format(Locale.US, "[DASHBOARD] %s: %.1f%s [%s]",
                alertEvent.sensorName(),
                alertEvent.value(),
                alertEvent.unit(),
                alertEvent.severity()));
    }
}
