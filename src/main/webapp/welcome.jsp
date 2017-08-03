<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Aspire Demo by Koy</title>
</head>
<body>
<form action="addDevice.do" method="post">
<table>
<tr>
<th>设备ID:<input type="text" name="deviceId" value="deviceId"></th>
<th>名称:<input type="text" name="deviceName" value="deviceName"></th>
<th>版本：<select name="firmVersion">
            <option value="DTSK3">DTSK3</option>
            <option value="DTSK4">DTSK4</option>
            <option value="DTSK3A">DTSK3A</option>
        </select>
</th>
<th>时间:<input type="text" name="recordDateTime" 
               value="<% 
                         Date date = new Date();
                         SimpleDateFormat t = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                         String time = t.format(date);
                      %><%=time%>">
</th>
<th><input type="submit" value="添加临时设备" onclick="javascript:alert('添加成功')"></th>
</tr>
</table>
</form>
<br/>
<table>
<tr>
<th><a href="selectAllDevice.do"><input type="submit" value="显示临时设备"></a></th>
<th><a href="selectAllDeviceFormal.do"><input type="submit" value="显示正式设备"></a></th>
</tr>
</table>
</body>
</html>