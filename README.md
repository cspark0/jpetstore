# JPetStore
Sample Spring MVC project for the SSD class in 2020-1

### tiles branch
Apache Tiles를 이용한 view layout 정의 및 활용 예
- pom.xml: Tiles3 라이브러리(tiles-jsp)에 대한 dependency 추가
- web.xml: DispatcherServlet에 대한 설정 파일을 petstore-servlet-tiles.xml로 변경
- petstore-servlet-tiles.xml:   
    + tilesConfigurer, tilesViewResolver 등 tiles 관련 bean 설정 추가
    + 기존 InternalResourceViewResolver의 우선순위를 후순위로 변경
    + "/shop/index.do" 및 "/shop/signonForm.do" 요청에 대한 정적 view mapping을 "tiles/index", "tiles/SignonForm"으로 수정
- WEB-INF/jsp/tiles/template/{layout.jsp, tilesdef.xml}: layout template과 view definition 정의
- WEB-INF/jsp/tiles/*.jsp: index, Category, SignonForm, EditAccountForm 뷰 구현 
    + 그 외의 뷰들은 기존 방식 유지 (WEB-INF/jsp/*.jsp)
- index, Category, SignonForm, EditAccountForm 뷰를 결과 화면으로 이용하는 Controller들을 수정
    + ViewCategoryController, AccountFormController, SignonController, SignoffController, SIgnonInterceptor 수정
    + 반환하는 뷰 이름을 "tiles/index", "tiles/Category" 등으로 변경  

### STS 또는 Eclipse에 import하는 방법 (local repository 생성)

__주의: 먼저 우측 상단의 Fork 버튼을 클릭하여 이 리파지토리(https://github.com/cspark0/jpetstore)를 자신의 Github 계정으로 fork(복사)한 후 이용하기 바랍니다.__

1. File > Import... > Git - Projects from Git (with smart import) > Clone URI 선택
2. fork된  URI 입력(예: https://github.com/your_Github_username/jpetstore.git), username & password 입력  > Next
3. 모든 branch 선택  > Next
4. Browse 버튼을 클릭하여 local repository를 저장할 폴더 선택(예: C:\Users\사용자\git) > Finish  
5. jpetstore 프로젝트가 import되었으면 pom.xml에 등록된 library들이 모두 다운로드되고 프로젝트가 빌드 완료될 때까지 기다림   
6. Window > Perspective > Open Perspective > Other... > Git 선택하고 local repo가 제대로 생성되었는지 확인 후 사용

### 프로젝트 실행 방법
1. Apache Tomcat v9.0을 다운로드 및 설치하고, Eclipse의 Servers 창에 등록함
2. 프로젝트를 Tomcat server에 deploy(drag&drop)하고 Synchronized 상태인지 확인한 후 server를 start함
3. 프로젝트 이름을 right-click 후 Run As > Run On Server를 실행하여  웹 브라우저를 띄우고 접속함     
(URL을 외부의 Chrome brower 등에서 직접 입력해도 됨)

### Git 사용법 참조

* 윤웅식, 만들면서 배우는 Git+GitHub 입문, 한빛미디어, 2015.  
* 오오츠카 히로키, 소셜 코딩으로 이끄는 GitHub 실천 기술, 제이펍, 2015.  
* <https://backlog.com/git-tutorial/kr/>  
* <https://nolboo.kim/blog/2013/10/06/github-for-beginner/> 및 링크된 문서들  
* <http://itmir.tistory.com/461>  
* <http://jwgye.tistory.com/38?category=689862>  
* 그밖의 온라인 자료들  
