/****************/
// Mã sinh viên: 202419055
// Họ tên: Phí Việt Hiếu
/****************/
package campussafetyhub.mediator;

// Phối hợp còi, cửa và thông gió theo từng sự kiện khẩn cấp
public final class EmergencyMediator implements IEmergencyMediator {

    private final Alarm alarm;
    private final DoorController door;
    private final VentilationController ventilation;

    public EmergencyMediator(Alarm alarm, DoorController door, VentilationController ventilation) {
        if (alarm == null || door == null || ventilation == null) {
            throw new IllegalArgumentException("device is null");
        }
        this.alarm = alarm;
        this.door = door;
        this.ventilation = ventilation;

        alarm.setMediator(this);
        door.setMediator(this);
        ventilation.setMediator(this);
    }

    // Nhận sự kiện từ một bên và ra lệnh cho các thiết bị liên quan
    @Override
    public void notifyEvent(Object sender, String event) {
        System.out.println("[MEDIATOR] Nhan " + event + " tu " + sender.getClass().getSimpleName());

        switch (event) {
            case SMOKE_WARNING:
                alarm.beep();
                ventilation.boost();
                break;
            case FIRE_DETECTED:
                alarm.ring();
                door.unlockAll();
                ventilation.shutdown();
                break;
            case ALARM_SILENCED:
                door.lockAll();
                ventilation.normal();
                break;
            default:
                System.out.println("[MEDIATOR] Bo qua su kien khong ho tro: " + event);
                break;
        }
    }
}
