# JPetStore (boot-jdbcTemplates)
JPetStore project based on Spring Boot 2.4
using JDBC Templates

####변경 사항     
[jpetstore-jdbcTemplate branch의 README.md](https://github.com/cspark0/jpetstore/tree/jdbcTemplate)
 참조
 
####실행
* Eclise: com.example.jpetstore.JpetstoreBootApplication 선택 > Run As > Java Application  
* Maven: mvnw spring-boot:run
* http://localhost:8088/ 

####Oralce 대신 H2 in-memory database 이용 방법
* pom.xml에 com.h2database:h2 dependency 추가
* application.properties 파일의 spring.datasource.* 설정들을 H2 관련 값으로 변경
* src/main/resources/{schema.sql, data.sql} 파일 생성(DB schema 생성 및 초기 data load)
 