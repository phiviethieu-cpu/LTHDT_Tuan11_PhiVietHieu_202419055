/****************/
// Mã sinh viên: 202419055
// Họ tên: Phí Việt Hiếu
/****************/
package campussafetyhub.adapter;

// Adapter bọc cảm biến khói cũ để dùng được qua giao diện ISensor
public final class SmokeSensorAdapter implements ISensor {

    private final LegacySmokeDetector legacy;

    public SmokeSensorAdapter(LegacySmokeDetector legacy) {
        if (legacy == null) {
            throw new IllegalArgumentException("legacy is null");
        }
        this.legacy = legacy;
    }

    // Đổi mức khói 0..1000 của lớp cũ sang phần trăm 0..100
    @Override
    public double readValue() {
        return legacy.getSmokeLevel() / 10.0;
    }

    @Override
    public String getUnit() {
        return "% smoke";
    }
}
