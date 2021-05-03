# JPetStore (boot-tiles)
JPetStore project using Tiles based on Spring Boot 2.4

####boot branch에서 변경 사항     
1. WebMvcConfig.java 수정: 기존 JSP를 위한 view resolver 순서를 2로 변경, addViewControllers()에서 정적 view mapping 수정(Tiles view 이름 지정) 
2. TilesConfig.java 생성: Tiles configurer와 Tiles view resolver bean 설정 (Tiles view resolver의 순서는 1로 지정)
3. jsp/tiles/template에 Tiles definition file과 layout template file 정의
4. jsp/tiles/*.jsp 생성: Tiles view의 component로 이용될 JSP 정의
5. index, Category, SignonForm, EditAccountForm view를 결과 화면으로 이용하는 Controller들을 수정
--> ViewCategoryController, AccountFormController, SignonController, SignoffController, SIgnonInterceptor에서 반환하는 view 이름을 "tiles/index", "tiles/Category" 등으로 변경  

####실행
* Eclise: com.example.jpetstore.JpetstoreBootApplication 선택 > Run As > Java Application  
* Maven: mvnw spring-boot:run
* http://localhost:8088/ 
