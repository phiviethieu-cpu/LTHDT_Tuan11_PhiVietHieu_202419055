/****************/
// Mã sinh viên: 202419055
// Họ tên: Phí Việt Hiếu
/****************/
package campussafetyhub.adapter;

// Giao diện chuẩn để module giám sát đọc dữ liệu từ mọi loại cảm biến
public interface ISensor {

    double readValue();

    String getUnit();
}
