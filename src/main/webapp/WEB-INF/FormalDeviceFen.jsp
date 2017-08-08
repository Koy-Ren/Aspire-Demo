<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
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
<!-- 分页功能 start -->  
    <div>  
        <font size="2">共 ${requestScope.page.totalPageCount} 页</font>
        <font size="2">第  ${requestScope.page.pageNow} 页</font><a href="devicePage.do?pageNow=1">首页</a>
      <c:choose>
        <c:when test="${requestScope.page.pageNow - 1 > 0}">
          <a href="devicePage.do?pageNow=${requestScope.page.pageNow - 1}">上一页</a>
        </c:when>
        <c:when test="${requestScope.page.pageNow - 1 <= 0}">  
          <a href="devicePage.do?pageNow=1">上一页</a>  
        </c:when>  
      </c:choose>  
      <c:choose>  
        <c:when test="${requestScope.page.totalPageCount==0}">  
            <a href="devicePage.do?pageNow=${requestScope.page.pageNow}">下一页</a>  
        </c:when>  
        <c:when test="${requestScope.page.pageNow + 1 < requestScope.page.totalPageCount}">  
            <a href="devicePage.do?pageNow=${requestScope.page.pageNow + 1}">下一页</a>  
        </c:when>  
            <c:when test="${requestScope.page.pageNow + 1 >= requestScope.page.totalPageCount}">  
                <a href="devicePage.do?pageNow=${requestScope.page.totalPageCount}">下一页</a>  
            </c:when>  
        </c:choose>  
        <c:choose>  
            <c:when test="${requestScope.page.totalPageCount==0}">  
                <a href="devicePage.do?pageNow=${requestScope.page.pageNow}">尾页</a>  
            </c:when>  
            <c:otherwise>  
                <a href="devicePage.do?pageNow=${requestScope.page.totalPageCount}">尾页</a>  
            </c:otherwise>  
        </c:choose>  
    </div>  
    <!-- 分页功能 End -->
</body>
</html>