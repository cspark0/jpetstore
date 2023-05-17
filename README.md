# JPetStore (boot-spring-data-jpa branch)
JPetStore project using JPA and Spring Data JPA on Spring Boot 2.7

####변경 사항     
1. pom.xml: spring-boot-starter-data-jpa 설정 추가
2. application.yml: jpa 및 hibernate 관련 property 설정 추가
3. domain.{Account, Order, Product, Item, LineItem 등}: 기존 domain class들에 대한 ORM mapping 설정 (entity 정의)
4. domain.{Address, Profile, Banner 등}: domain(entity) 클래스 추가
5. dao.jpa.{JpaAccountDao, JpaOrderDao}: JPA 기반 DAO 클래스 정의
6. repository.{CategoryRepository, ProductRepository, ItemRepository}: Spring Data JPA 기반 Repository 인터페이스 정의
7. service.PetStoreImpl: 위 DAO 및 Repository를 이용하도록 수정
8. controller.UpdateCategoryController: Category 이름 변경을 위한 controller 정의 (JPA에 의한 자동 update 테스트)

####실행
* Eclise: com.example.jpetstore.JpetstoreBootApplication 선택 > Run As > Java Application  
* Maven: mvnw spring-boot:run
* http://localhost:8088/ 
