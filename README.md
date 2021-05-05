# JPetStore (boot-thymeleaf branch)
JPetStore project using both JSP and Thymeleaf as view templates on Spring Boot 2.4

### boot branch에서 변경된 사항(차이점)     
1. pom.xml에 spring-boot-starter-thymeleaf dependencies 추가
2. src/main/resources/templates 디렉토리 생성: Spring Boot에서는 기본적으로 templates 디렉토리에서 Thymeleaf template 파일을 찾도록 설정됨.
3. templates 디렉토리 아래에 thyme 디렉토리 생성: JSP view와 Thymeleaf view를 구별하기 위함. Thymeleaf view의 이름은 이 디렉토리 이름을 포함해서 정의 (예: "thyme/Category")
4. src/main/resources/application.properties에  spring.thymeleaf.view-names 속성 값을 "thyme/*"로 설정: view 이름이 "thyme/Category"일 경우 Thymeleaf View Resolver가 Thymeleaf view로 인식하고 templates/thyme 디렉토리에서 template file을 찾음 (그렇지 않을 경우 JSP view로 인식되어 JSP file을 찾게 됨)
5. templates/thyme/{Category,Top,Bottom,QuickHeader}.html: Thymeleaf templates (기존 Category view를 Thymeleaf로 구현)
6. 기존 WEB-INF/jsp/Category.jsp 삭제
7. static/{index.html, help.html}에서 이미지 파일에 대한 URL 수정: Spring Boot에서는 classpath:/static 디렉토리를 정적 리소스 파일의 기본 디렉토리로 설정하여 "/images/search.gif"와 같이 절대경로식으로 접근 가능   
 
### 실행
* Eclise: com.example.jpetstore.JpetstoreBootApplication 선택 > Run As > Java Application  
* Maven: mvnw spring-boot:run
* http://localhost:8088/ 
