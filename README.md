# boot-rest-react branch
Spring boot를 기반으로 RESTful Service 및 React를 이용
- webapp/WEB-INF/view/reactView.jsp 참조


### 실행 방법

1. src/main/resources/react-app에서 React production build를 생성한 후   
그 결과를 target/classes/static/ 폴더로 copy하기 위해 
`Maven > Update Project...`를 실행함    
   (pom.xml의 maven-resources-plugin 설정 참조)   
    
2. Eclipse에서 `Run As > Spring Boot App`으로 실행하거나 CMD/Terminal 창에서 `mvnw spring-boot:run` 명령을 실행

3. 브라우저에서 다음과 같이 요청 실행   
<http://localhost:8088/>  :  ArticleController#main() --> reactView.jsp --> react-app의 index.js 이용   
<http://localhost:8088/greeting> :  ArticleController#greeting() --> reactView.jsp --> react-app의 greeting.js 이용  