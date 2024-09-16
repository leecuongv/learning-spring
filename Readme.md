<div align="center">

# Back Farm Management System - Hệ thống quản lý trang trại

<!-- ![Completion](https://img.shields.io/badge/style-100%25-00e600?label=Completion&logo=java&logoColor=red&style=for-the-badge) -->

</div>

Dự án tập trung vào việc xử lý các thao tác cơ bản cho một trang web tổ chức và quản lý các trang trại. Hệ thống Bao gồm các chức năng cơ bản như đăng ký, đăng nhập, quản lý hồ sơ người dùng, quản lý vật nuôi, quản lý cây trồng, quản lý trang thiết bị.

- [Công nghệ sử dụng](#công-nghệ-sử-dụng)
- [Mô tả hệ thống](#mô-tả-hệ-thống)

<br>

## Công Nghệ Sử Dụng
### Ngôn ngữ lập trình
- Java 17
### Kiến trúc chung của hệ thống
#### MongoDB
- Phiên bản MongoDB sử dụng: 6.0.
- Dùng làm hệ quản trị cơ sở dữ liệu.

#### Spring Boot
- Phiên bản sử dụng: 3.3.2.
- Backend Restful API với Java.

## Mô tả hệ thống
### Chủ trang trại: 
- Quản lý được nhiều trang trại cùng một lúc
- Quản lý, lên kế hoạch, phân công công việc theo ngày, tuần, tháng
- Quản lý được số lượng cá thể trong đàn: Số lượng nhập chuồng, xuất chuồng, số lượng heo con,… tránh tình trạng lạc, mất
- Thống kê, chọn và lai tạo giống, từ đó dự đoán được sản lượng thế hệ, lứa tiếp theo
- Ghi lại nhật ký chăn nuôi: Ngày đẻ, ngày nhập/xuất chuồng, thức ăn, vaccine, thuốc uống đã sử dụng…
### Công nhân: 
- Dễ dàng theo dõi và thực hiện công việc theo kế hoạch từng ngày, tuần, tháng; linh động thay đổi, sắp xếp công việc.
- Theo dõi được quá trình chăn nuôi, ăn uống của heo khu vực chuồng trại được phân công phụ trách
- Theo dõi sát sao sức kh

### Quản trị viên
- Quản lý người dùng trên hệ thống.
- Quản lý quyền hạn người dùng trên hệ thống.
- Quản lý các khoá học.
- Quản lý và thống kê doanh thu.