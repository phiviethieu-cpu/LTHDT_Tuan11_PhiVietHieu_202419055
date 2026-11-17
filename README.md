# Campus Safety Hub – Thực hành tuần 11 (Mẫu thiết kế)

- Mã sinh viên: 202419055
- Họ tên: Phí Việt Hiếu

Chương trình console (Java) mô phỏng trung tâm giám sát an toàn cho một phòng máy:
tích hợp cảm biến khói cũ, phát cảnh báo cho nhiều bên nhận và điều phối thiết bị ứng phó khẩn cấp.

| Vấn đề | Pattern | Thành phần chính |
|---|---|---|
| Thiết bị cũ không hỗ trợ giao diện chuẩn mới | Adapter | `SmokeSensorAdapter` |
| Nhiều bên cần nhận cảnh báo và thay đổi động | Observer | `SensorMonitor`, `IAlertObserver` |
| Các thiết bị cần phối hợp nhưng không phụ thuộc trực tiếp nhau | Mediator | `EmergencyMediator` |

## Chạy chương trình

```bash
mvn compile
java -cp target/classes campussafetyhub.Main
```

## A. Phác thảo thiết kế

| Lớp/interface | Trách nhiệm |
|---|---|
| `ISensor` | Giao diện chuẩn mà module giám sát sử dụng |
| `LegacySmokeDetector` | Lớp có sẵn, giả định không được sửa |
| `SmokeSensorAdapter` | Chuyển giao diện và thang đo của lớp cũ |
| `AlertEvent` | Đối tượng dữ liệu bất biến mô tả cảnh báo |
| `IAlertObserver` | Hợp đồng nhận cảnh báo |
| `SensorMonitor` | Quản lý observer, đọc sensor, so ngưỡng và phát cảnh báo |
| `EmergencyBridgeObserver` | Chuyển cảnh báo phù hợp thành sự kiện cho mediator |
| `IEmergencyMediator` | Hợp đồng điều phối |
| `EmergencyMediator` | Phối hợp alarm, door và ventilation theo sự kiện |

## Trả lời câu hỏi thiết kế

1. **Adapter nên kế thừa hay chứa `LegacySmokeDetector`?**
   Nên chứa (object adapter). Lớp cũ là `final` nên không kế thừa được; composition giúp adapter
   đọc trực tiếp trạng thái của đối tượng cũ mà không sao chép, và không lộ các phương thức cũ ra `ISensor`.
2. **`SensorMonitor` có nên biết `EmailAlertObserver` cụ thể không?**
   Không. `SensorMonitor` chỉ phụ thuộc `IAlertObserver`, nên có thể thêm/bớt observer lúc chạy mà không phải sửa nó.
3. **`Alarm` có cần tham chiếu đến `DoorController` không?**
   Không. Các thiết bị chỉ biết `IEmergencyMediator`; mọi phối hợp đi qua mediator.
4. **Ai chịu trách nhiệm quyết định giá trị nào là vượt ngưỡng?**
   `SensorMonitor`. Adapter chỉ chuyển đổi đơn vị, observer chỉ phản ứng với `AlertEvent` đã có mức độ.
   Quy ước: dưới 50% bình thường, từ 50% đến dưới 70% là `WARNING`, từ 70% trở lên là `CRITICAL`.
