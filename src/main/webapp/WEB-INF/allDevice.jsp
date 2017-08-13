<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Temp Deive</title>
<script language="javascript" type="text/javascript"
	src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<!-- VENDOR CSS -->
<link rel="stylesheet"
	href="assets/vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="assets/vendor/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="assets/vendor/linearicons/style.css">
<!-- MAIN CSS -->
<link rel="stylesheet" href="assets/css/main.css">
<!-- FOR DEMO PURPOSES ONLY. You should remove this in your project -->
<link rel="stylesheet" href="assets/css/demo.css">
<!-- GOOGLE FONTS -->
<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700"
	rel="stylesheet">
<link rel="stylesheet"
	href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../../css/demo.css" />
<link rel="stylesheet" type="text/css" href="css/webuploader.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/webuploader.css"
	rel="external nofollow">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/webuploader.js"></script>
<script src="${pageContext.request.contextPath }/audiojs/audio.min.js"></script>
</head>

<body>

	<!-- WRAPPER -->
	<div id="wrapper">
		<!-- NAVBAR -->
		<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container-fluid">
			<form action="addDevice.do" method="post"
				class="navbar-form navbar-left">
				<table>
					<tr>
						<th>
							设备ID：
							<input type="text" name="deviceId" value="deviceId">
						</th>
						<th>
							名称：
							<input type="text" name="deviceName" value="deviceName">
						</th>
						<th>
							版本：
							<select name="firmVersion">
								<option value="DTSK3">DTSK3</option>
								<option value="DTSK4">DTSK4</option>
								<option value="DTSK3A">DTSK3A</option>
							</select>
						</th>
						<th>
							时间:
							<input type="text" name="recordDateTime"
								value="<%Date date = new Date();
			SimpleDateFormat t = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = t.format(date);%><%=time%>">
						</th>
						<th>
							<input type="submit" value="添加临时设备" class="btn btn-primary"
								onclick="javascript:alert('添加成功')">
						</th>
					</tr>
				</table>
			</form>
		</div>
		</nav>
		<!-- END NAVBAR -->
		<!-- LEFT SIDEBAR -->
		<div id="sidebar-nav" class="sidebar">
			<div class="sidebar-scroll">
				<nav>
				<ul class="nav">
					<li>
						<a href="welcome.jsp" class="active">
							<i class="lnr lnr-dice"></i>
							<span>主页</span>
						</a>
						<a href="selectAllDevice.do" class="active">
							<i class="lnr lnr-dice"></i>
							<span>临时设备</span>
						</a>
						<a href="devicePage.do?pageNow=1" class="active">
							<i class="lnr lnr-dice"></i>
							<span>正式设备</span>
						</a>
						<a href="searchAllMusicFile.do?pageNow=1" class="active">
							<i class="lnr lnr-dice"></i>
							<span>文件</span>
						</a>
					</li>
				</ul>
				</nav>
			</div>
		</div>
		<!-- END LEFT SIDEBAR -->
		<!-- MAIN -->
		<div class="main">
			<!-- MAIN CONTENT -->
			<div class="main-content">
				<div class="container-fluid">
					<h3 class="page-title">临时设备</h3>
					<div class="row">
						<div class="panel">
							<div class="panel-body">
								<table class="table">
									<thead>
										<tr>
											<th>设备Id</th>
											<th>名称</th>
											<th>版本</th>
											<th>时间</th>
											<th>操作</th>
										</tr>
									</thead>
									<c:forEach items="${requestScope.deviceAll}" var="device"
										varStatus="status">
										<tbody>
											<tr>
												<td>${device.deviceId}</td>
												<td>${device.deviceName}</td>
												<td>${device.firmVersion}</td>
												<td>
													<fmt:formatDate value="${device.recordDateTime}"
														type="both" />
												</td>
												<td>
													<a href="active.do?deviceId=${device.deviceId}">
														<input type="submit" value="激活">
													</a>
												</td>
											</tr>
										</tbody>
									</c:forEach>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- END MAIN CONTENT -->
			<!-- 分页功能 start -->
			<div class="panel-body">
				<font size="2">共 ${requestScope.page.totalPageCount} 页</font>
				<font size="2">第 ${requestScope.page.pageNow} 页</font>
				<a
					href="searchDevice.do?deviceId=${requestScope.deviceId}&deviceName=${requestScope.deviceName}&firmVersion=${requestScope.firmVersion}&deviceStatus=${requestScope.deviceStatus}&timeBegin=${requestScope.timeBegin}&timeEnd=${requestScope.timeEnd}&pageNow=1">首页</a>
				<c:choose>
					<c:when test="${requestScope.page.pageNow - 1 > 0}">
						<a
							href="searchDevice.do?deviceId=${requestScope.deviceId}&deviceName=${requestScope.deviceName}&firmVersion=${requestScope.firmVersion}&deviceStatus=${requestScope.deviceStatus}&timeBegin=${requestScope.timeBegin}&timeEnd=${requestScope.timeEnd}&pageNow=${requestScope.page.pageNow - 1}">上一页</a>
					</c:when>
					<c:when test="${requestScope.page.pageNow - 1 <= 0}">
						<a
							href="searchDevice.do?deviceId=${requestScope.deviceId}&deviceName=${requestScope.deviceName}&firmVersion=${requestScope.firmVersion}&deviceStatus=${requestScope.deviceStatus}&timeBegin=${requestScope.timeBegin}&timeEnd=${requestScope.timeEnd}&pageNow=1">上一页</a>
					</c:when>
				</c:choose>
				<c:choose>
					<c:when test="${requestScope.page.totalPageCount==0}">
						<a
							href="searchDevice.do?deviceId=${requestScope.deviceId}&deviceName=${requestScope.deviceName}&firmVersion=${requestScope.firmVersion}&deviceStatus=${requestScope.deviceStatus}&timeBegin=${requestScope.timeBegin}&timeEnd=${requestScope.timeEnd}&pageNow=${requestScope.page.pageNow}">下一页</a>
					</c:when>
					<c:when
						test="${requestScope.page.pageNow + 1 < requestScope.page.totalPageCount}">
						<a
							href="searchDevice.do?deviceId=${requestScope.deviceId}&deviceName=${requestScope.deviceName}&firmVersion=${requestScope.firmVersion}&deviceStatus=${requestScope.deviceStatus}&timeBegin=${requestScope.timeBegin}&timeEnd=${requestScope.timeEnd}&pageNow=${requestScope.page.pageNow + 1}">下一页</a>
					</c:when>
					<c:when
						test="${requestScope.page.pageNow + 1 >= requestScope.page.totalPageCount}">
						<a
							href="searchDevice.do?deviceId=${requestScope.deviceId}&deviceName=${requestScope.deviceName}&firmVersion=${requestScope.firmVersion}&deviceStatus=${requestScope.deviceStatus}&timeBegin=${requestScope.timeBegin}&timeEnd=${requestScope.timeEnd}&pageNow=${requestScope.page.totalPageCount}">下一页</a>
					</c:when>
				</c:choose>
				<c:choose>
					<c:when test="${requestScope.page.totalPageCount==0}">
						<a
							href="searchDevice.do?deviceId=${requestScope.deviceId}&deviceName=${requestScope.deviceName}&firmVersion=${requestScope.firmVersion}&deviceStatus=${requestScope.deviceStatus}&timeBegin=${requestScope.timeBegin}&timeEnd=${requestScope.timeEnd}&pageNow=${requestScope.page.pageNow}">尾页</a>
					</c:when>
					<c:otherwise>
						<a
							href="searchDevice.do?deviceId=${requestScope.deviceId}&deviceName=${requestScope.deviceName}&firmVersion=${requestScope.firmVersion}&deviceStatus=${requestScope.deviceStatus}&timeBegin=${requestScope.timeBegin}&timeEnd=${requestScope.timeEnd}&pageNow=${requestScope.page.totalPageCount}">尾页</a>
					</c:otherwise>
				</c:choose>
			</div>
			<!-- 分页功能 End -->
		</div>
		<!-- END MAIN -->
	</div>

	<!-- Javascript -->
	<script src="assets/vendor/jquery/jquery.min.js"></script>
	<script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<script src="assets/scripts/klorofil-common.js"></script>

</body>
</html>