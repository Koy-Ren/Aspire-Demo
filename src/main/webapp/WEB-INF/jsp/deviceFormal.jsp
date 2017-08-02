<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<br>
    <table border="1">
        <tr>
            <th>设备Id</th>
            <th>版本</th>
            <th>名称</th>
            <th>时间</th>
            <th>状态</th>
        </tr>
        <c:forEach items="${requestScope.deviceAllFormal}" var="deviceFormal">
            <tr>
                <td>${deviceFormal.deviceId}</td>
                <td>${deviceFormal.deviceName}</td>
                <td>${deviceFormal.firmVersion}</td>
                <td>${deviceFormal.recordDateTime}</td>
                <td>${deviceFormal.deviceStatus}</td>
                <td><input type="button" value="注销" onclick=""></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>