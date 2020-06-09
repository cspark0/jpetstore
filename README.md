# JPetStore - rest branch
RESTful Order Service를 구현함      

### master에 대한 변경 사항
1. pom.xml
    * Java 객체 <-> JSON 텍스트 변환을 위해 필요한 Jackson-databind library에 대한 dependency 설정 추가

2. web.xml
    * remoting servlet에 대한 설정 변경 (url-pattern: /rest/*)  
      
3. remoting-servlet.xml (remoting servlet에 대한 설정 파일)
    * RestfulOrderController와 HttpMessageConverter bean 등록
    
4. RestfulOrderController 
    * REST service에 대한 request handler methods 정의
    * public Order getOrderInfo(@PathVariable("orderId") int orderId, HttpServletResponse response)
    * public List<Order> getOrderInfoByUsername(@PathVariable("username") String username,HttpServletResponse response)
    * public Order deleteOrder(@PathVariable("orderId") int orderId, HttpServletResponse response)   

5. OrderService, OrderServiceImpl 
    * public Order removeOrder(int orderId) 선언 및 구현 추가 
     
6. OrderDao, MyBatisOrderDao, OrderMapper, LineItemMapper 
    * Order removeOrder(int orderId) 선언 및 구현 
    * void deleteOrder(int orderId) 선언 및 SQL 정의 
    * void deleteOrderStatus(int orderId) 선언 및 SQL 정의
    * int deleteLineItems(int orderId) 선언 및 SQL 정의

7. client/OrderServiceClient_rest 클래스 정의
    * REST order service를 호출하는 client program
