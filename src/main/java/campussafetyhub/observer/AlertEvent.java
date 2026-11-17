/****************/
// Mã sinh viên: 202419055
// Họ tên: Phí Việt Hiếu
/****************/
package campussafetyhub.observer;

import java.time.LocalDateTime;

// Dữ liệu một lần cảnh báo, gửi cho các observer (record nên không sửa được sau khi tạo)
public record AlertEvent(
        String sensorName,
        double value,
        String unit,
        String severity,
        LocalDateTime occurredAt) {
}
