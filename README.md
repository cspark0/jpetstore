# JPetStore (boot-spring-data-jpa branch)
JPetStore project using JPA and Spring Data JPA on Spring Boot 2.6

####변경 사항     
1. pom.xml에 spring-boot-starter-data-jpa 설정 추가
2. application.properties에 jpa 및 hibernate 관련 property 설정 추가
3. Account, Order, Product, Item, LineItem 등 기존 domain class들에 대한 ORM mapping 설정 (entity 정의)
4. Address, Profile, Banner 등 domain(entity) 클래스 추가
5. dao.jpa package에 JPA 기반 DAO 클래스 구현: JpaAccountDao, JpaOrderDao
6. repository package에 Spring Data JPA 기반 Repository 인터페이스 정의: CategoryRepository, ProductRepository, ItemRepository
7. 위 DAO 및 Repository를 이용해서 PetStoreImpl 클래스 수정
8. Category 이름 변경을 위한 UpdateCategoryController 추가 (JPA에 의한 자동 update 테스트)

####실행
* Eclise: com.example.jpetstore.JpetstoreBootApplication 선택 > Run As > Java Application  
* Maven: mvnw spring-boot:run
* http://localhost:8088/ 
