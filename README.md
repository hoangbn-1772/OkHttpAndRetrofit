# OkHttpAndRetrofit

# OkHttp
## Overview
- Là một thư viện được Square phát triển với mục đích gửi và nhận các request mạng dựa trên HTTP.
- Là một máy khách HTTP nahnh, hiệu quả:
	+ Hỗ trợ HTTP/2 cho phép tất cả các request đến cùng một máy chủ để chia sẻ một socket.
	+ Gộp connection giảm độ trễ request
	+ GZIP: Giảm kích thước tải xuống.
	+ Caching
	+ Khôi phục từ các sự cố mạng
	+ Hỗ trợ các cuộc gọi đồng bộ, không đồng bộ
	+ Chuyển hướng
	+ Thử lại
- OkHttp cung cấp một triển khai các giao diện HttpUrlConnection và Apache Client bằng cách làm việc trực tiếp trên Java Socket mà không cần sử dụng bất kỳ phụ thuộc nào.
- OkHttp được xây dựng dựa trên Okio, một thư viện bổ sung cho java.io và java.nio để giúp truy cập, lưu trữ và xử lý dữ liệu của bạn dễ dang hơn, nó cung cấp I/O nhanh và 
bộ đệm thay đổi kích thước. Vì thế OkHttp phụ thuộc vào Okio nhưng Okio có thể được sử dụng riêng.
- Sử dụng OkHttp rất dễ dàng, Request/response được thiết kế với fluent builders và immutability.

## Thực hành

# Retrofit
## Overview
- Retrofit là một type-safe HTTP client cho Java và Android được phát triển bởi Square, giúp dễ dàng kết nối đến một dịch vụ REST trên web bằng cách chuyển đổi API thành Java Interface.
- Từ Retrofit 2 tích hợp thêm phụ thuộc vào OkHttp, phụ thuộc vào Okio. Có nghĩa khi sử dụng Retrofit là bạn đang sử dụng OkHttp và Okio. Ngoài ra không tích hợp bất kỳ bộ chuyển đổi JSON nào để phân tích từ JSON thành Java Object,
thay vào đó nó đi kèm với các thư viện chuyển đổi JSON như Gson, Jackson, Moshi.
- Điểm mạnh:
	+ Dễ dàng kết nối với web-services bằng cách chuyển đổi API sang Java hoặc Kotlin
	+ Dễ dàng add Headers và request type
	+ Dễ dàng tùy chỉnh, thêm bất kuf trình chuyển đổi nào như Gson, JackSon, Moshi, XML... Bạn cũng có thể thêm các interceptor và cache khác nhau.
	+ Cung cấp các chức năng bổ sung như custom header, file uploads, downloads, mocking responses

# Tài liệu tham khảo
- OkHttp: https://square.github.io/okhttp/
- https://medium.com/@sotti/android-networking-ii-okhttp-retrofit-moshi-and-picasso-c381f6c0efd8
- https://medium.com/@ssaurel/use-okhttp-to-make-network-requests-on-android-d3845e3c3f50
- https://medium.com/@ssaurel/use-okhttp-to-make-network-requests-on-android-d3845e3c3f50
- https://medium.com/mindorks/tagged/retrofit