/****************/
// Mã sinh viên: 202419055
// Họ tên: Phí Việt Hiếu
/****************/
package campussafetyhub.mediator;

// Lớp cha của các thiết bị, chỉ giữ tham chiếu tới mediator chứ không biết thiết bị khác
public abstract class EmergencyDevice {

    protected IEmergencyMediator mediator;

    public void setMediator(IEmergencyMediator mediator) {
        if (mediator == null) {
            throw new IllegalArgumentException("mediator is null");
        }
        this.mediator = mediator;
    }
}
