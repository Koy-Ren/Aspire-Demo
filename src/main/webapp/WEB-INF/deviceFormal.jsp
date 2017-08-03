<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Formal Device</title>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
<form action="searchDevice.do" method="post">
<table>
<tr>
<th>设备ID：<input type="text" name="deviceId" value=""></th>
<th>名称：<input type="text" name="deviceName" value=""></th>
<th>版本：<select name="firmVersion">
            <option value="">全部版本</option>
            <option value="DTSK3">DTSK3</option>
            <option value="DTSK4">DTSK4</option>
            <option value="DTSK3A">DTSK3A</option>
        </select>
</th>
<th>状态：<select name="deviceStatus">
            <option value="">全部状态</option>
            <option value="online">online</option>
            <option value="offline">offline</option>
        </select>
</th>
<th><input type="submit" value="查询设备"></th>
</tr>
</table>
</form>
<form action="searchByTime.do" method="post">
<table>
<tr>
<th>提交时间：<input type="text" id="timeBegin" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate"/></th>
<th>至<input type="text" id="timeEnd" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate"/></th>
<th><input type="submit" value="查询设备"></th>
</tr>
</table>
</form>
<br/>
    <table border="1">
        <tr>
            <th>设备Id</th>
            <th>名称</th>
            <th>版本</th>
            <th>时间</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${requestScope.deviceAllFormal}" var="deviceFormal">
            <tr>
                <td>${deviceFormal.deviceId}</td>
                <td>${deviceFormal.deviceName}</td>
                <td>${deviceFormal.firmVersion}</td>
                <td><fmt:formatDate value="${deviceFormal.recordDateTime}" type="both"/></td>
                <td>${deviceFormal.deviceStatus}</td>
                <td><input type="button" value="详情" onclick=""></td>
                <td><input type="button" value="删除" onclick=""></td>
                <td><input type="button" value="日志" onclick=""></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>