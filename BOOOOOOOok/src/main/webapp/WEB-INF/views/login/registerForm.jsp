<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="content d-flex justify-content-center">


  <form action="${pageContext.request.contextPath}/register" method="post" class="w-50" onsubmit="return validateForm();">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <h3>회원가입</h3>

    <div class="mb-3">
      <label for="name" class="form-label">이름</label>
      <input type="text" id="name" name="name" class="form-control" required>
    </div>
    <div class="mb-3">
      <label for="username" class="form-label">아이디</label>
      <input type="text" id="username" name="username" class="form-control" required>
    </div>
    <div class="mb-3">
      <label for="password" class="form-label">비밀번호</label>
      <input type="password" id="password" name="password" class="form-control" required>
    </div>
    <div class="mb-3 row">
      <div class="col-9">
        <input type="password" id="confirmPassword" name="confirmPassword" class="form-control" placeholder="비밀번호 확인" required>
      </div>
      <div class="col-3">
        <button type="button" class="btn btn-secondary w-100" onclick="checkPassword()">비밀번호 확인</button>
      </div>
    </div>
    <div class="mb-3">
      <label for="email" class="form-label">이메일</label>
      <input type="email" id="email" name="email" class="form-control" required>
    </div>

    <div class="d-flex justify-content-between">
      <a href="${pageContext.request.contextPath}/main" class="btn btn-outline-dark">가입 취소</a>
      <button type="submit" class="btn btn-dark">회원가입</button>
    </div>
  </form>
</div>

<script>
  function checkPassword() {
    const pw = document.getElementById('password').value;
    const cpw = document.getElementById('confirmPassword').value;
    if (!pw || !cpw) {
      alert('비밀번호와 확인 입력하세요.');
      return;
    }
    alert(pw === cpw ? '비밀번호가 일치합니다!' : '비밀번호가 일치하지 않습니다.');
  }

  function validateForm() {
    const email = document.getElementById('email').value;
    const emailPattern = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;
    const pw = document.getElementById('password').value;
    const cpw = document.getElementById('confirmPassword').value;
    const pwPattern = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[\W_]).{8,}$/;

    if (!emailPattern.test(email)) {
      alert('올바른 이메일 형식을 입력하세요.');
      return false;
    }

    if (!pwPattern.test(pw)) {
      alert("비밀번호는 8자 이상이며, 대문자, 소문자, 숫자, 특수문자를 포함해야 합니다.");
      return false;
    }

    if (pw !== cpw) {
      alert('비밀번호가 일치하지 않습니다.');
      return false;
    }

    return true;
  }
</script>

     

<!-- 플래시 메시지 출력 -->
<c:if test="${not empty error}">
  <div class="alert alert-danger">${error}</div>
</c:if>
<c:if test="${not empty message}">
  <div class="alert alert-success">${message}</div>
</c:if>
