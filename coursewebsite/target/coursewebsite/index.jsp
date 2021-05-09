<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<html>
<body>
<h2>Hello World!</h2>
</body>
   <form action="${pageContext.request.contextPath}/attendance/all" method="post">
      账号<input type="text" name="id" />
      班级<input type="text" name="clazzId" />
      <input type="submit" name="submit" />
   </form>
</html>
