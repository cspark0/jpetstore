# boot-rest branch
Spring boot를 기반으로 RESTful Order Service를 구현

### boot branch에 대한 변경 사항
1. contoller.rest 패키지에 RestfulOrderController 클래스 정의
    * REST service에 대한 request handler methods 정의
    * public Order getOrder(@PathVariable("orderId") int orderId, HttpServletResponse response)
    * public List<Order> getOrderInfoByUsername(@PathVariable("username") String username,HttpServletResponse response)
    * public Order deleteOrder(@PathVariable("orderId") int orderId, HttpServletResponse response)   

2. OrderService, OrderServiceImpl 클래스 수정
    * public Order removeOrder(int orderId) 선언 및 구현 추가 
     
3. OrderDao, MyBatisOrderDao, OrderMapper, LineItemMapper 클래스 수정
    * Order removeOrder(int orderId) 선언 및 구현 
    * void deleteOrder(int orderId) 선언 및 SQL 정의 
    * void deleteOrderStatus(int orderId) 선언 및 SQL 정의
    * int deleteLineItems(int orderId) 선언 및 SQL 정의

4. service.client 패키지에 OrderServiceClient_rest 클래스 정의
    * 위 Restful Order Service를 호출하는 client program
    
5. WEB-INF/jsp/ListOrder.jsp 수정
    * 주문 목록에서 특정 price 셀을 클릭하면 Ajax 호출을 통해 /rest/order/{orderId}에 대한 요청 생성  
    * RestfulOrderController#getOrder()를 통해 주문 정보를 구하고 결과를 JSON data로 return
    * 필요한 정보를 price 셀 안에 추가  
    * 기존 price 셀을 다시 클릭하면 추가된 주문 정보를 삭제  
 