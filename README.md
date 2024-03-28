# JPetStore (boot)
JPetStore project based on Spring Boot 2.7

####변경 사항 (from master)  
1. pom.xml: Spring Boot Starter Dependencies 사용 
2. com.example.jpetstore.JpetstoreBootApplication: 시작 및 설정 클래스 
3. com.example.jpetstore.WebMvcConfig: Spring MVC 관련 설정 클래스
4. com.example.jpetstore.controller.SignonInterceptor: @Component 추가 (bean scan 대상)
5. com.example.jpetstore.controller.dao.mybatis.mapper.*: @Mapper 추가 (mapper scan 대상)
6. src/main/resources/{기존 properties, xml 설정 파일} 삭제
7. src/main/resources/application.yml: bean property 설정
8. src/main/webapp/{images, style, *.html}를 src/main/resources/static/ 으로 이동 
9. src/main/webapp/META-INF 삭제
10. src/main/webapp/WEB-INF/*.xml 삭제
 
####실행
* Eclipse: com.example.jpetstore.JpetstoreBootApplication 선택 > Run As > Java Application  
* Maven: mvnw spring-boot:run
* http://localhost:8088/ 

