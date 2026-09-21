# Ví dụ 1 — Login bằng Spring Security 6 (email/password)

> README này được tạo bởi AI (Claude).

Bài tập: Cho bảng `User`, `Role`, viết chức năng login bằng email/password, thông tin
user (họ tên, avatar, vai trò) hiển thị ở header. Dùng Spring Boot 4 + Spring Security +
MapStruct + Thymeleaf (không dùng thymeleaf-layout-dialect, layout ghép bằng `th:replace`).

## Công nghệ

- Spring Boot 4.1.1, Java 21
- Spring Security 7 (form login, BCrypt)
- Spring Data JPA + SQL Server
- Thymeleaf + thymeleaf-extras-springsecurity6
- MapStruct (Entity → DTO)

## Cấu hình database

Mặc định dùng **Windows Authentication** (Integrated Security) tới SQL Server local:

```properties
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=vidu1_login;integratedSecurity=true;encrypt=false;trustServerCertificate=true
```

Tạo database trước khi chạy:

```sql
CREATE DATABASE vidu1_login;
```

### Bắt buộc: DLL xác thực Windows cho JDBC driver

Driver `mssql-jdbc` cần file gốc `mssql-jdbc_auth-13.4.0.x64.dll` để dùng Windows
Authentication. Tải file zip chính thức từ Microsoft và giải nén vào `native-lib/`:

1. Tải: https://github.com/microsoft/mssql-jdbc/releases/download/v13.4.0/mssql-jdbc_auth.zip
2. Giải nén, lấy `x64/mssql-jdbc_auth-13.4.0.x64.dll`
3. Đặt vào `native-lib/mssql-jdbc_auth-13.4.0.x64.dll` (đã có sẵn trong `.gitignore`, mỗi máy tự tải)

Chạy app với `java.library.path` trỏ tới thư mục đó:

```bash
mvn "-Dspring-boot.run.jvmArguments=-Djava.library.path=native-lib" spring-boot:run
```

### Thay thế: dùng SQL Server Authentication (username/password)

Nếu không muốn dùng Windows Auth, sửa `application.properties`:

```properties
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=vidu1_login;encrypt=false;trustServerCertificate=true
spring.datasource.username=sa
spring.datasource.password=<mat_khau_cua_ban>
```

rồi chạy bình thường: `mvn spring-boot:run` (không cần DLL).

## Tài khoản mẫu

Dữ liệu mẫu được tạo tự động khi chạy lần đầu (`DataInitializer`):

| Email | Password | Role |
|---|---|---|
| user01@gmail.com | 123456 | USER |
| trungnh@hcmute.edu.vn | 123456 | ADMIN |

## Chạy ứng dụng

```bash
mvn spring-boot:run
```

Mở http://localhost:8081/login
