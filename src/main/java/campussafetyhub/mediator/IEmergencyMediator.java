/****************/
// Mã sinh viên: 202419055
// Họ tên: Phí Việt Hiếu
/****************/
package campussafetyhub.mediator;

// Hợp đồng điều phối giữa các thiết bị ứng phó khẩn cấp
public interface IEmergencyMediator {

    // Các mã sự kiện trao đổi qua mediator
    String SMOKE_WARNING = "SMOKE_WARNING";
    String FIRE_DETECTED = "FIRE_DETECTED";
    String ALARM_SILENCED = "ALARM_SILENCED";

    void notifyEvent(Object sender, String event);
}
