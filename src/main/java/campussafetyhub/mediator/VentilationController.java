/****************/
// Mã sinh viên: 202419055
// Họ tên: Phí Việt Hiếu
/****************/
package campussafetyhub.mediator;

// Điều khiển hệ thống thông gió của phòng máy
public final class VentilationController extends EmergencyDevice {

    private String mode = "NORMAL";

    public void boost() {
        mode = "BOOST";
        System.out.println("[VENTILATION] Tang cong suat hut khoi");
    }

    public void shutdown() {
        mode = "OFF";
        System.out.println("[VENTILATION] Tat thong gio de han che lua lan");
    }

    public void normal() {
        mode = "NORMAL";
        System.out.println("[VENTILATION] Tro ve che do binh thuong");
    }

    public String getMode() {
        return mode;
    }
}
