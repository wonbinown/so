<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>	
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>도서 목록 - BookMarket</title>
  <style>
    body { font-family: Arial, sans-serif; background: #1f1f50; color: #fff; margin:0; padding:0;}
    .container { max-width: 900px; margin: 60px auto; padding: 30px; background: #2e237a; border-radius: 16px; box-shadow: 0 20px 40px rgba(0,0,0,0.3); }
    h1 { margin-top: 0; }
    .book-list { display: grid; grid-template-columns: repeat(auto-fit, minmax(220px, 1fr)); gap: 20px; }
    .book-card { background: #3b2f99; padding: 16px; border-radius: 12px; position: relative; }
    .book-title { font-size: 1.2em; font-weight: bold; margin: 0 0 8px; }
    .book-author { margin: 0 0 4px; font-size: 0.9em; color: #ddd; }
    .book-price { font-weight: bold; margin-top: 8px; }
    .actions { margin-top: 16px; }
    .btn { padding: 8px 14px; border: none; border-radius: 8px; cursor: pointer; font-weight: bold; }
    .btn-primary { background: #ffcc33; color: #1f1f50; }
    .btn-secondary { background: transparent; color: #fff; border: 1px solid #fff; }
    .top-bar { display:flex; justify-content: space-between; align-items:center; margin-bottom:20px; }
    .logout-form { display:inline; }
  </style>
</head>
<body>
  <div class="container">
    <div class="top-bar">
      <div>
        <h1>BookMarket 도서 목록</h1>
        <p>총 <strong><c:out value="${fn:length(books)}"/></strong>권</p>
      </div>
      <div>
        <!-- 로그아웃 -->
        <form action="${pageContext.request.contextPath}/logout" method="post" class="logout-form">
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
          <button type="submit" class="btn btn-secondary">로그아웃</button>
        </form>
      </div>
    </div>

    <div class="book-list">
      <c:forEach var="book" items="${books}">
        <div class="book-card">
          <div class="book-title"><c:out value="${book.title}"/></div>
          <div class="book-author">저자: <c:out value="${book.author}"/></div>
          <div class="book-price">가격: <c:out value="${book.price}"/>원</div>
          <div class="actions">
            <a href="${pageContext.request.contextPath}/BookStore/book/${book.id}" class="btn btn-primary">상세보기</a>
          </div>
        </div>
      </c:forEach>
      <c:if test="${empty books}">
        <div>등록된 도서가 없습니다.</div>
      </c:if>
    </div>
  </div>
</body>
</html>
