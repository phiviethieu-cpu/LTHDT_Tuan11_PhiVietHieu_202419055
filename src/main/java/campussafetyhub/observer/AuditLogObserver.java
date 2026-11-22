/****************/
// Mã sinh viên: 202419055
// Họ tên: Phí Việt Hiếu
/****************/
package campussafetyhub.observer;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

// Lưu lại lịch sử các cảnh báo đã nhận
public final class AuditLogObserver implements IAlertObserver {

    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final List<AlertEvent> entries = new ArrayList<>();

    @Override
    public void update(AlertEvent alertEvent) {
        entries.add(alertEvent);
        System.out.println(String.format(Locale.US, "[AUDIT] %s %s %s %.1f%s",
                alertEvent.occurredAt().format(TIME_FORMAT),
                alertEvent.sensorName(),
                alertEvent.severity(),
                alertEvent.value(),
                alertEvent.unit()));
    }

    // Trả về danh sách chỉ đọc để bên ngoài không sửa được lịch sử
    public List<AlertEvent> getEntries() {
        return Collections.unmodifiableList(entries);
    }
}
