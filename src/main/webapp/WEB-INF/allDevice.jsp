<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<script>
    var deviceId;
    function submit(deviceId){
    	
        }
</script>
    <br>
    <table border="1">
        <tr>
            <th>设备Id</th>
            <th>版本</th>
            <th>名称</th>
            <th>时间</th>
        </tr>
        <c:forEach items="${requestScope.deviceAll}" var="device" varStatus="status">
            <tr>
                <td>${device.deviceId}</td>
                <td>${device.deviceName}</td>
                <td>${device.firmVersion}</td>
                <td>${device.recordDateTime}</td>
                <td><a href="active.do?deviceId=${device.deviceId}"><input type="submit" value="激活"></a></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>