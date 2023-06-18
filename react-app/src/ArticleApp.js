import React, { useEffect, useState } from 'react';
import TOC from './components/TOC';
import Control from './components/Control';
import ReadContent from './components/ReadContent';
import CreateContent from './components/CreateContent';
import UpdateContent from './components/UpdateContent';
import './App.css';

export default function ArticleApp() {
  
  const [contents, setContents] = useState([]);         // contents (articles)
  const [curContId, setCurContId] = useState(0);        // 현재 선택된 content ID
  const [maxContId, setMaxContId] = useState(contents.length);   // 생성된 content ID의 최대값
  const [mode, setMode] = useState('no_article');       // mode (no_article/read/create/update)
  
  useEffect(() => {               // contents 상태 초기화 
    console.log("useEffect()");
    //fetch('/contents.json')        // Ajax로 contents data(JSON)를 가져와서 state에 저장
    fetch('/contents')
      .then(response => response.json())      
      .then(contents => {
        setContents(contents);
        setMaxContId(contents.length);
      })
      .catch(error => console.error(error));
  }, []);   // []: 이 component가 mount된 후 한 번만 호출되고, 그 후 state가 변경되어도 실행되지 않음

  const changeMode = mode => {  // 생성/수정 링크나 삭제 버튼 선택 mode 상태 변경
    switch (mode) {
      case 'create':
        setMode(mode);      // 상태 변경
        break;              
      case 'update':
        if (curContId === 0) 
          alert('no content selected to update!');      
        else setMode(mode);
        break;                                
      case 'delete':        // 삭제 버튼 선택 시 현재 content 삭제 실행
        if (curContId === 0) 
          alert('no content selected to delete!'); 
        else if(window.confirm('really?') === true) {  
          const newContents = contents.filter(       
            content => (content.id !== curContId));   
            // id가 curContId가 아닌 content들만 추출해서 새로운 배열 생성
          setContents(newContents);   // contents 변경
          setCurContId(0);
          setMode('no_article');
          alert('Deleted!');       
        }
    }
  };           

  const getContent = () => {     // mode에 따라 하단에 출력할 component 정의
    let content = null, curCont = null;    
    switch (mode) {
      case 'read':  
        curCont = contents.find(content => (content.id === curContId));
          // 현재 선택된 content 객체 검색
        content = <ReadContent title={curCont.title} desc={curCont.desc}/> 
        break;

      case 'create':      
        content = <CreateContent 
          onNewContent={(title, desc) => {  // 새로 입력된 content data 
            const newId = maxContId + 1;
            console.log(title, desc);                      
            const newContents = [     // 기존 contents와 생성된 content 객체를 포함하는 새로운 배열 생성
              ...contents,
              {id: newId, title, desc}
            ];    // 또는 contents.concat()이나, Array.from(contents) 및 push() 사용 가능 
            setContents(newContents);   // contents 상태 변경
            setCurContId(newId);        // 새 content를 현재 content로 설정
            setMaxContId(newId);  
            setMode('read');            // 'read' mode로 변경     
          }} />
        break;

      case 'update':
        curCont = contents.find(content => (content.id === curContId));        
          // 현재 선택된 content 객체 검색
        content = <UpdateContent content={curCont} 
          onNewContent={(title, desc) => {    // update된 content data 
            console.log(title, desc);                     
            const newContents = contents.map(content =>
              (content.id === curContId) ? {...content, title, desc} : content 
            );    // map()을 통해 새로운 배열 생성: 현재의 content에 대해서는 update된 title과 desc 값을 갖는 content를 생성하고,
                  // 그렇지 않으면 기존 content를 copy해서 저장함
            setContents(newContents);   // contents 상태 변경
            setMode('read');            // 'read' mode로 변경                 
          }} />        
    }
    return content;
  };

  console.log('App render');   
  return (
    <div className="App">
      <TOC entries={contents} curContId={curContId}     // title 목록 생성
        onClickTitle={selectedId => {   // click된 content의 ID를 전달받음
          setMode('read');          
          setCurContId(selectedId);   // curContId 변경
        }}/>
      <Control                    // 생성 및 수정 링크, 삭제 버튼 생성
        hasContent={contents.length > 0 ? true : false} 
        onChangeMode={changeMode}/>           
      <div>        
        <br/>
        {getContent()  // 현재 mode에 따라 ReadContent, CreateContent, UpdateContent 생성
        }           
      </div> 
    </div>
  );
}

