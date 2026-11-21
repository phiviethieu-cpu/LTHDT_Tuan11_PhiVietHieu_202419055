/****************/
// Mã sinh viên: 202419055
// Họ tên: Phí Việt Hiếu
/****************/
package campussafetyhub.observer;

// Hợp đồng cho các bên muốn nhận cảnh báo từ SensorMonitor
public interface IAlertObserver {

    void update(AlertEvent alertEvent);
}
