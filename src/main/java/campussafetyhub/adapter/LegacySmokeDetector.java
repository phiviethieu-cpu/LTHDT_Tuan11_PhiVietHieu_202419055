/****************/
// Mã sinh viên: 202419055
// Họ tên: Phí Việt Hiếu
/****************/
package campussafetyhub.adapter;

// Cảm biến khói đời cũ có sẵn (giả định không được sửa), đo theo thang 0..1000
public final class LegacySmokeDetector {

    private int smokeLevel;

    public LegacySmokeDetector(int smokeLevel) {
        this.smokeLevel = smokeLevel;
    }

    public int getSmokeLevel() {
        return smokeLevel; // 0..1000
    }

    public String getDescription() {
        return "Legacy smoke detector";
    }

    // Đặt mức khói mới, giới hạn trong khoảng 0..1000
    public void setSmokeLevel(int value) {
        smokeLevel = Math.max(0, Math.min(1000, value));
    }
}
