# E-commerce
E-commerce Server 구축 Project
<br /><br />
## 요구사항 분석(Sequence Diagram) ##
### 📁 Money 충전
![image](https://github.com/lemoneeing/e-commerce/assets/138500282/698fc3ae-1f47-4486-96f4-5889862276b6)
<br /><br />
### 📁 Money 조회
![image](https://github.com/lemoneeing/e-commerce/assets/138500282/dde2450d-d869-4316-a262-90674680de68)
<br /><br />
### 📁 상품 조회
![image](https://github.com/lemoneeing/e-commerce/assets/138500282/fc44772b-1ffe-46aa-8112-76b7db974088)
<br /><br />
### 📁 주문
![image](https://github.com/lemoneeing/e-commerce/assets/138500282/b79f38e2-9645-4a62-8626-2cc73c7c970a)
<br /><br />
### 📁 결제
![image](https://github.com/lemoneeing/e-commerce/assets/138500282/c74f3a91-27c0-4270-a8d5-b222eafb1a66)
<br /><br />
## API 명세
#### 📗 Money 충전
@Patch<br/>
/money/charge/user/{userId}
<br /><br />
#### 📗 Money 조회
@Get<br/>
/money/user/{userId}
<br /><br />
#### 📗 상품 조회(기본 상세 조회)
@Get<br/>
/product/{productId}
<br /><br />
#### 📗 상품 주문
@Patch<br/>
/order/
<br /><br />
#### 📗 주문 결제
@Patch<br/>
<I>상품 주문에서 Redirect로 결제 요청을 통해 시작할지 다른 방법을 쓸 지 아직 결정 못 함.</I>
<br/><br/>
## ERD 설계
![image](https://github.com/lemoneeing/e-commerce/assets/138500282/dd148bc0-4157-48e6-acb6-b81ca38da2ac)


