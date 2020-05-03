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

### 새로운 branch를 본인의 repository로 가져오는 방법

Eclipse에서 Git perspective를 열고 본인의 local repo에서 다음 작업들을 차례대로 실행함
(또는 Git bash에서 git command를 이용)

1. cspark0/jpetstore repo를 새로운 remote repo로 추가 
    - 왼쪽의 Git Repositories 창에서 jpetstore [master] > Remotes 선택 후 마우스 우클릭 > Create Remote... 선택
    - Remote name: "upstream" 입력, Configure fetch 옵션 선택 , Create 버튼 클릭
    - Change 버튼 클릭 후 URI: "https://github.com/cspark0/jpetstore.git" 입력, Finish 버튼 클릭,  Save and Fetch 버튼 클릭
2. upstream의 새로운 branch (tiles) 를 local repo로 fetch
    - Branches > Remote Tracking > upstream/tiles를 선택 후 마우스 우클릭, Check Out.. 선택, Check out as a new local branch 버튼 클릭, Finish
3. 가져온 local repo (tiles)를  본인의 remote repo로 push
    - Branches > Local > tiles를 선택 후 마우스 우클릭, Push branch... 선택, Destination: Remote: 에서 본인의 remote repo 입력 또는 선택, Preview 버튼 클릭, Username&password 입력 > Log-in 클릭, Push 버튼 클릭, Username&password 입력 > Log-in 클릭, Close

### 프로젝트 실행 방법
1. Apache Tomcat v9.0을 다운로드 및 설치하고, Eclipse의 Servers 창에 등록함
2. 프로젝트를 Tomcat server에 deploy(drag&drop)하고 Synchronized 상태인지 확인한 후 server를 start함
3. 프로젝트 이름을 right-click 후 Run As > Run On Server를 실행하여  웹 브라우저를 띄우고 접속함     
(URL을 외부의 Chrome brower 등에서 직접 입력해도 됨)
