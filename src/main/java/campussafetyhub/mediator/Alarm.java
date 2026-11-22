/****************/
// Mã sinh viên: 202419055
// Họ tên: Phí Việt Hiếu
/****************/
package campussafetyhub.mediator;

// Còi báo động trong phòng máy
public final class Alarm extends EmergencyDevice {

    private boolean ringing;

    public void beep() {
        System.out.println("[ALARM] Beep canh bao ngan");
    }

    public void ring() {
        ringing = true;
        System.out.println("[ALARM] Coi bao chay dang keu");
    }

    // Tắt còi rồi báo cho mediator để các thiết bị khác trở về bình thường
    public void silence() {
        if (!ringing) {
            return;
        }
        ringing = false;
        System.out.println("[ALARM] Da tat coi");
        if (mediator != null) {
            mediator.notifyEvent(this, IEmergencyMediator.ALARM_SILENCED);
        }
    }

    public boolean isRinging() {
        return ringing;
    }
}
