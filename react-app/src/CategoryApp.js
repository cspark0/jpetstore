import React, { useEffect, useState } from 'react';
import TOC from './components/TOC';
import CreateContent from './components/CreateContent';
import UpdateContent from './components/UpdateContent';
import './App.css';

export default function CategoryApp({categoryId}) {
  const [contents, setContents] = useState([]);         // contents (articles)
  const [curContId, setCurContId] = useState(0);        // 현재 선택된 content ID
  const [mode, setMode] = useState('read');       // mode (no_article/read/create/update)
  
  const updateContents = () => {          
    console.log("updateContents");
    fetch(`/rest/category/${categoryId}/products`)
      .then(response => response.json())      
      .then(contents => {
        setContents(contents);        
      })
      .catch(error => console.error(error));
  }; 

  useEffect(() => {
    console.log("useEffect()");
    updateContents();
  }, []);  // contents 상태 초기화: component가 mount된 후 한 번만 호출됨
  
  const getContent = () => {     // mode에 따라 하단에 출력할 component 정의
    let content = null, curCont = null;    
    switch (mode) {
      case 'read':
        content = <a href="/product/create" 
                    onClick={e => {
                      e.preventDefault();  
                      setMode('create');
                    }}>create</a>;              
        break;
        
      case 'create':      
        content = <CreateContent 
          onNewContent={(productId, name, description) => {  // 새로 입력된 content data 
            console.log(productId, name, description);                      
            fetch('/rest/product', {
              method: "POST",
              headers: {"Content-Type": "application/json"},
              body: JSON.stringify({
                productId, categoryId, name, description,                
              })})
              .then(() => alert('Created!'))
              .then(() => {                
                updateContents();              
                setCurContId(productId);        // 새 content를 현재 content로 설정            
                setMode('read');            // 'read' mode로 변경                 
                console.log(curContId, mode);                       
              })
              .catch(error => console.error(error));      // ID 중복 오류 처리 필요!              
          }} />
        break;

      case 'update':
        curCont = contents.find(content => (content.productId === curContId));        
          // 현재 선택된 content 객체 검색
        content = <UpdateContent content={curCont} 
          onNewContent={(name, description) => {    // update된 content data 
            console.log(name, description);                     
            fetch(`/rest/product/${curContId}`, {
              method: "PUT",
              headers: {"Content-Type": "application/json"},
              body: JSON.stringify({
                productId: curContId, 
                categoryId, name, description                
              })})
              .then(() => alert('Updated!'))
              .then(() => {                
                updateContents();              
                setMode('read');            // 'read' mode로 변경                 
              })
              .catch(error => console.error(error));             
          }} />        
    }
    return content;
  };

  console.log('App render');   
  return (
    <div className="App">
      <TOC entries={contents} curContId={curContId}     // title 목록 생성
        onClickProductId={selectedId => {   // click된 content의 ID를 전달받음
          /*fetch(`/rest/product/${selectedId}`)
            .then(response => response.json())      
            .then(product => setCurCont(product));
          */
          if (selectedId !== curContId) setCurContId(selectedId);         // curContId 변경
          if (mode !== 'update') setMode('update');          
          console.log(curContId, mode);                     
        }}
        onClickTrash={selectedId => {
          if(window.confirm('really?') === true) {  
            fetch(`/rest/product/${selectedId}`, {method: "DELETE"})
              .then(() => alert('Deleted!'))
              .then(() => {                
                updateContents();
                setCurContId(0);
                setMode('read');
                console.log(curContId, mode);                       
              })
              .catch(error => console.error(error));                          
          }}}/>                        
      <div>        
        <br/>
        {getContent()  // 현재 mode에 따라 CreateContent, UpdateContent 생성
        }           
      </div> 
    </div>
  );
}

