<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"/><title>로그인</title></head>
<body>
  <c:if test="${not empty errorMsg}">
    <div style="color:red; font-size:1.2em;">${errorMsg}</div>
  </c:if>
  <c:if test="${not empty msg}">
    <div style="color:green; font-size:1.2em;">${msg}</div>
  </c:if>

  <form action="<c:url value='/login' />" method="post">
    <!-- CSRF 토큰 반드시 포함 -->
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

    <div>
      <label>아이디:
        <input type="text" name="username" value="${param.username}" required />
      </label>
    </div>
    <div>
      <label>비밀번호:
        <input type="password" name="password" required />
      </label>
    </div>
    <div>
      <button type="submit">로그인</button>
    </div>
  </form>
</body>
</html>
