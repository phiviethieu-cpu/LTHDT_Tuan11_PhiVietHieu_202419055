/****************/
// Mã sinh viên: 202419055
// Họ tên: Phí Việt Hiếu
/****************/
package campussafetyhub.mediator;

// Điều khiển khóa các cửa thoát hiểm
public final class DoorController extends EmergencyDevice {

    private boolean unlocked;

    public void unlockAll() {
        unlocked = true;
        System.out.println("[DOOR] Mo khoa tat ca cua thoat hiem");
    }

    public void lockAll() {
        unlocked = false;
        System.out.println("[DOOR] Khoa lai cac cua");
    }

    public boolean isUnlocked() {
        return unlocked;
    }
}
