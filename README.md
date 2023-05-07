# boot-rest branch
Spring boot를 기반으로 RESTful Service를 구현

### boot branch에 대한 변경 사항
1. contoller.rest에 RestOrderController, RestProductController 클래스 정의
    * REST service를 위한 request handler methods 정의
 
2. OrderService, OrderServiceImpl 클래스 수정
    * removeOrder() method 선언 및 구현 
     
3. ProductService, ProductServiceImpl 클래스 추가
    * Product에 대한 CRUD methods 선언 및 구현

4. OrderDao, MyBatisOrderDao, OrderMapper, LineItemMapper 클래스 수정
    * Order removeOrder(int orderId) 선언 및 구현 
    * void deleteOrder(int orderId) 선언 및 SQL 정의 
    * void deleteOrderStatus(int orderId) 선언 및 SQL 정의
    * int deleteLineItems(int orderId) 선언 및 SQL 정의

5. Product, ProductDao 수정 및 ProductRepository 클래스 추가
    * Product Entity 정의 (based on JPA)
    * ProductDao에 createProduct(), updateProduct(), removeProduct() methods 선언 추가
    * ProductRepository interface 정의 (based on Spring Data JPA)
  
6. WEB-INF/jsp/ListOrder.jsp 수정
    * 주문 목록에서 특정 price 셀을 클릭하면 Ajax 호출을 통해 /rest/order/{orderId}에 대한 GET 요청 생성  
    * RestfulOrderController#getOrder()를 통해 주문 정보를 구하고 결과를 JSON data로 return
    * 필요한 정보를 price 셀 안에 추가  
    * 기존 price 셀을 다시 클릭하면 추가된 주문 정보를 삭제  
 
6. WEB-INF/jsp/Category.jsp 수정
    * 새로운 Product 생성 및 삭제를 위한 인터페이스 및 Javascript 함수 추가
    * Ajax 호출을 통해 POST 및 DELETE 요청 생성

8. client 패키지에 OrderServiceClient_rest, ProductServiceClient_rest 클래스 정의
    * 위 Restful Service를 호출하는 client program (RestTemplate 이용)
    
