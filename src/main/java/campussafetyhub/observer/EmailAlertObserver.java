/****************/
// Mã sinh viên: 202419055
// Họ tên: Phí Việt Hiếu
/****************/
package campussafetyhub.observer;

import java.util.Locale;

// Mô phỏng gửi email cảnh báo, chỉ in nội dung ra console
public final class EmailAlertObserver implements IAlertObserver {

    private final String recipient;

    public EmailAlertObserver(String recipient) {
        if (recipient == null || recipient.isBlank()) {
            throw new IllegalArgumentException("recipient is empty");
        }
        this.recipient = recipient;
    }

    @Override
    public void update(AlertEvent alertEvent) {
        System.out.println(String.format(Locale.US, "[EMAIL] To: %s | [%s] %s = %.1f%s",
                recipient,
                alertEvent.severity(),
                alertEvent.sensorName(),
                alertEvent.value(),
                alertEvent.unit()));
    }
}
