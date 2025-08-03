## 1. 비회원 / 일반 사용자 흐름

### 메인 페이지 (전체 책 목록 보기)

- **URL:** `/` 또는 `/BookStore/book`
- 설명: 전체 도서 목록 조회 (로그인 없이도 가능)

### 책 상세 페이지

- **URL:** `/BookStore/book/{bookId}`
- 설명: 특정 책 상세 보기

### 장바구니 담기 버튼 클릭 (책 상세에서)

- **동작:** POST `/cart/add` (파라미터: `bookId`, `quantity`)
- **로그인 상태 체크**
    - O → 장바구니에 담기 성공 → 리다이렉트 `/cart`
    - X → 리다이렉트 `/loginForm?redirect=/BookStore/book/{bookId}` (로그인 후 원래 장바구니로 복귀)

### 바로 구매하기 클릭 (책 상세에서)

- **동작:** POST `/order/prepare` (파라미터: `bookId`, `quantity`)
- **로그인 상태 체크**
    - O → 결제 페이지 `/order/checkout`
    - X → 리다이렉트 `/loginForm?redirect=/order/checkout?bookId=...` (로그인 후 결제 페이지로 복귀)

### 장바구니 페이지

- **URL:** `/cart`
- 설명: 담긴 책 목록 확인, “주문하기” 버튼
- **주문하기 클릭**
    - 로그인 상태 체크
        - O → 주문 준비/결제 흐름 (`/order/prepare` → `/order/checkout`)
        - X → `/loginForm?redirect=/order/checkout`

### 결제 페이지

- **URL:** `/order/checkout`
- 설명: 결제 폼 표시
- **로그인 상태 체크**
    - O → 폼 노출
    - X → `/loginForm?redirect=/order/checkout`

### 결제 완료

- **URL:** `/order/complete`
- 설명: 완료 메시지 또는 주문 내역 보기 (`/order/{orderId}`)

---

## 2. 인증 관련 공통 URL

### 로그인 페이지

- **URL:** `/loginForm`
- 설명: 로그인 폼 표시
- 파라미터: `error=true` (실패), `redirect=...` (성공 후 복귀할 원래 목적지)

### 로그인 처리

- **URL:** `/login` (POST)
- 설명: 인증 시도
    - 성공 시:
        - `authentication-success-handler`가 `redirect` 파라미터가 있으면 해당 경로로, 없으면 기본 (`/BookStore/book` 등)
    - 실패 시: `/loginForm?error=true&redirect=...`

### 로그아웃

- **URL:** `/logout` (POST)
- 설명: 세션 정리 후 `/` 또는 `/loginForm?logout=true`

---

## 3. 회원가입 흐름

### 가입 페이지

- **URL:** `/signup` (GET)
- 설명: 가입 폼

### 가입 처리

- **URL:** `/signup` (POST)
- 설명:
    1. `existsByUsername`, `existsByEmail`로 중복 체크
    2. 성공 → 저장 (`UserMapper.insertUser`) → `/loginForm`으로 이동
    3. 실패 (중복 등) → 같은 `/signup`에 오류 표시

---

## 4. 마이페이지 / 주문 내역 (로그인한 사용자)

### 내 주문 목록

- **URL:** `/mypage/orders`
- 설명: `OrderMapper.findByUserId(userId)` 호출

### 주문 상세

- **URL:** `/mypage/orders/{orderId}`
- 설명: `OrderMapper.findOrderDetail(orderId)`

---

## 5. 관리자 흐름 (ROLE_ADMIN 필요)

### 관리자 로그인

- **URL:** `/admin/loginForm` 또는 일반 로그인 후 role 검사
- 성공 시: 관리자 대시보드

### 관리자 대시보드

- **URL:** `/admin`
- 메뉴: 회원 관리, 주문 관리, 도서 관리

### 회원 관리

- **URL:** `/admin/users` (목록)
- **URL:** `/admin/users/{userId}` (조회 / role 변경 처리)

### 주문 관리

- **URL:** `/admin/orders` (전체 주문)
- **URL:** `/admin/orders/{orderId}` (주문 상세 / 상태 변경)

### 도서 관리

- **URL:** `/admin/books` (도서 목록)
- **URL:** `/admin/books/new` (등록 폼)
- **URL:** `/admin/books` (POST: 등록)
- **URL:** `/admin/books/{bookId}/edit` (수정 폼)
- **URL:** `/admin/books/{bookId}` (PUT: 수정, DELETE: 삭제)
- **URL:** `/admin/books/{bookId}/stock` (재고 조정)
- **URL:** `/admin/books/price-update` (가격 일괄/프로모션)

---

## 6. 추천 / 베스트 / 신간 / 개인화 (메인 페이지 확장)

### 추천 배너

- **URL:** (내부 호출) `MainBannerMapper.getRecommendedBooks()` → 표시용 데이터
- 관리자: `/admin/recommended` (추천 도서 설정)

### 베스트셀러

- **URL:** (조회용) `/books/bestseller` → `BestSellerMapper.getTop10Books()`

### 신간

- **URL:** `/books/new` → `NewBookMapper.getLatestBooks()`

### 맞춤형 추천 (로그인 시)

- **URL:** `/books/curation` → `CurationMapper.getPersonalPicks(userId)`

### 장바구니 카운트 (헤더)

- **URL:** 내부 API `/cart/count` → `CartMapper.getCartCount(userId)` (로그인 시)

---

## 7. 인증/권한 예외 처리

- 비회원이 장바구니/결제/주문 시도 → `/loginForm?redirect=원래주소`
    
    ---
    
- 일반 사용자가 관리자 URL 접근 → 접근 거부 → `/access-denied` 혹은 관리자 로그인 페이지
