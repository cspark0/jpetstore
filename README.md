# boot-rest-react branch
Spring boot를 기반으로 RESTful Service 및 React view 구현

### 변경 사항
- /react-app 구현
     * /src/category.js, CategoryApp.js, /components/*.js 
     * react-app/README.md 설명 참조
- controller.rest.{RestCategoryController, RestProductController} 
- controller.ViewCategoryController, webapp/WEB-INF/jsp/Category.jsp 수정



### 실행 방법

1. /react-app에서 React production build를 생성한 후
그 결과를 target/classes/static/ 폴더로 copy하기 위해 
`Maven > Update Project...`를 실행함    
      * pom.xml의 maven-resources-plugin 설정 참조
    
2. Eclipse에서 `Run As > Spring Boot App`으로 실행하거나 CMD/Terminal 창에서 `mvnw spring-boot:run` 명령을 실행

3. 브라우저에서 다음과 같이 요청 실행   
     - <http://localhost:8088/shop/viewCategory.do?categoryId=DOGS>
         *  ViewCategoryController#handleRequest() --> Category.jsp --> react-app의 category.js    
