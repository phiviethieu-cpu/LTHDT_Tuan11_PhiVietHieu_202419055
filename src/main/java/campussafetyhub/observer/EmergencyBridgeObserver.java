/****************/
// Mã sinh viên: 202419055
// Họ tên: Phí Việt Hiếu
/****************/
package campussafetyhub.observer;

import campussafetyhub.mediator.IEmergencyMediator;

// Chuyển cảnh báo từ cảm biến thành sự kiện cho mediator điều phối thiết bị
public final class EmergencyBridgeObserver implements IAlertObserver {

    private final IEmergencyMediator mediator;

    public EmergencyBridgeObserver(IEmergencyMediator mediator) {
        if (mediator == null) {
            throw new IllegalArgumentException("mediator is null");
        }
        this.mediator = mediator;
    }

    // Đổi mức cảnh báo sang mã sự kiện tương ứng rồi gửi cho mediator
    @Override
    public void update(AlertEvent alertEvent) {
        String event;
        switch (alertEvent.severity()) {
            case "CRITICAL":
                event = IEmergencyMediator.FIRE_DETECTED;
                break;
            case "WARNING":
                event = IEmergencyMediator.SMOKE_WARNING;
                break;
            default:
                return;
        }
        mediator.notifyEvent(this, event);
    }
}
