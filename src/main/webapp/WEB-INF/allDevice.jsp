<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Temp Deive</title>

<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>

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
            <th>名称</th>
            <th>版本</th>
            <th>时间</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${requestScope.deviceAll}" var="device" varStatus="status">
            <tr>
                <td>${device.deviceId}</td>
                <td>${device.deviceName}</td>
                <td>${device.firmVersion}</td>
                <td><fmt:formatDate value="${device.recordDateTime}" type="both"/></td>
                <td><a href="active.do?deviceId=${device.deviceId}"><input type="submit" value="激活"></a></td>
            </tr>
        </c:forEach>
    </table>
<br/>
<!-- 不同格式的时间 -->
<!-- <input id="d11" type="text" onClick="WdatePicker()"/> -->
<!-- <input id="d12" type="text"/> -->
<%-- <img onclick="WdatePicker({el:'d12'})" src="${pageContext.request.contextPath}/My97DatePicker/skin/datePicker.gif" width="16" height="22"> --%>

<!-- <input type="text" id="timeBegin" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate"/> -->

<!-- <input type="text" id="timeEnd" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate"/> -->
</body>
</html>