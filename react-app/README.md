
/react-app은 React로 구현된 front-end program으로,
`npm run eject` 실행을 통해 내부 설정 및 스크립트 파일들이 추출된 상태임  

### 수정 사항
- package.json 
    - "proxy": "http://localhost:8088" 속성 추가 (back-end server 주소 지정)
  
- config/webpack.config.js    
    - 203~206행 : view 페이지를 생성하기 위한 js 파일과 이름 지정 --> 각각 build/static/js 아래에 bundle 파일 생성  
    - 215,219행 : bundle 파일과 chunk 파일의 이름 지정 (해쉬 값은 생략)   
    
### production build 생성 방법
Terminal 창을 열고 아래 명령을 차례로 실행
- `npm install`  
- `npm run build`
