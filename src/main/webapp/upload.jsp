<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/webuploader.css"
	rel="external nofollow">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/webuploader.js"></script>
</head>
<body>
	<!-- 2.创建页面元素 -->
	<div id="upload">
		<div id="filePicker">文件上传</div>
	</div>
	<!-- 3.添加js代码 -->
	<script type="text/javascript">
		var uploader = WebUploader.create({
			swf : "${pageContext.request.contextPath }/js/Uploader.swf",
			server : "${pageContext.request.contextPath }/FileUploadServlet",
			pick : "#filePicker",
			auto : true
		});
	</script>

</body>
</html>