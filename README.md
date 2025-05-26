# JPetStore (boot-spring-data-jpa branch)
JPetStore project using JPA, Spring Data JPA, and QueryDSL on Spring Boot 3

####변경 사항     
1. pom.xml: spring-boot-starter-data-jpa, querydsl-jpa, querydsl-apt 의존성 및 apt-maven-plugin 설정 추가
2. application.yml: jpa 및 hibernate 관련 property 설정 추가
3. domain.{Account, Order, Product, Item, LineItem 등}: 기존 domain class들에 대한 ORM mapping 설정 (entity 정의)
4. domain.{Address, Profile, Banner 등}: domain(entity) 클래스 추가
5. dao.jpa.{JpaAccountDao, JpaOrderDao}: JPA 기반 DAO 클래스 정의
6. repository.{CategoryRepository, ProductRepository, ItemRepository}: Spring Data JPA 기반 Repository 인터페이스 정의
7. service.PetStoreImpl: 위 DAO 및 Repository를 이용하도록 수정
8. controller.UpdateCategoryController: Category 이름 변경을 위한 controller 정의 (JPA에 의한 자동 update 테스트)
9. repository.querydsl.{ItemRepository2, ItemRepositoryCustom, ItemRepositoryCustomImpl}: QueryDSL을 이용한 repository 인터페이스 정의 및 클래스 구현 
	* Query type 클래스들은 target/generated-sources/annotations 폴더 밑에 생성됨
10. src/main/test 아래에 ItemRepository2Test 클래스 정의 
	* Run As > JUnit Test 메뉴로 실행
 
####실행
* Eclise: com.example.jpetstore.JpetstoreBootApplication 선택 > Run As > Java Application  
* Maven: mvnw spring-boot:run
* http://localhost:8088/ 
