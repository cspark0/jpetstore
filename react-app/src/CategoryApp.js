import React, { useEffect, useState } from 'react';
import ProductTable from './components/ProductTable';
import CreateProduct from './components/CreateProduct';
import UpdateProduct from './components/UpdateProduct';
import './App.css';

export default function CategoryApp({categoryId}) {
  const [productList, setProductList] = useState([]);   // list of products in the category
  const [curProduct, setCurProduct] = useState(null);   // 현재 선택된 product 
  const [curProdId, setCurProdId] = useState(0);        // 현재 선택된 product의 ID
  const [mode, setMode] = useState('list');       // mode: list/create/update
  
  const updateList = () => {  
    fetch(`/rest/category/${categoryId}/products`)
      .then(response => response.json())      
      .then(list => {
        setProductList(list);
        setCurProduct(null);
        setCurProdId(0);
        setMode('list');
      })
      .catch(error => console.error(error));   
  }; 

  useEffect(() => {
    console.log("useEffect()");
    updateList();
  }, []);  // productList 상태 초기화: component가 mount된 후 한 번만 호출됨
  
  console.log('App render');   
  return (
    <div className="App">
      <ProductTable entries={productList} curProdId={curProdId}   // product list 출력
        onClickId={selectedId => {    // click된 product의 ID를 전달받음
          if (curProdId !== selectedId) {
            let curProd = productList.find(p => (p.productId === selectedId)); 
            setCurProduct(curProd);  
            setCurProdId(selectedId);
          }
          if (mode !== 'update') setMode('update');
        }}
        onClickTrash={selectedId => {
          if(window.confirm('really?') === true) {  
            fetch(`/rest/product/${selectedId}`, {method: "DELETE"})
              .then(() => alert('Deleted!'))
              .then(() => updateList())
              .catch(error => console.error(error));                          
          }
        }}
      />                        
      <div>        
        <br/>
        {/* 현재 mode에 따라 CreateProduct, UpdateProduct, Create link 생성 */}
        {(mode === 'create') ?
          <CreateProduct 
            onNewProduct={(productId, name, description) => {  // 새로 입력된 product data 
              console.log(productId, name, description);                      
              fetch('/rest/product', 
                {
                  method: "POST",
                  headers: {"Content-Type": "application/json"},
                  body: JSON.stringify({
                    productId, categoryId, name, description,                
                  })
                })
                .then(() => alert('Created!'))
                .then(() => updateList())
                .catch(error => console.error(error));      // ID 중복 오류 처리 필요!              
            }}
          /> : 
          <>
            {(mode === 'update') ?
              <UpdateProduct key={curProdId} product={curProduct} 
                onNewProduct={(name, description) => {    // update된 product data 
                  console.log(name, description);                     
                  fetch(`/rest/product/${curProdId}`, 
                    {
                      method: "PUT",
                      headers: {"Content-Type": "application/json"},
                      body: JSON.stringify({
                        productId: curProdId,       
                        categoryId, name, description                
                      })
                    })    
                    .then(() => alert('Updated!'))
                    .then(() => updateList())
                    .catch(error => console.error(error));             
                }}
              /> : null} 
            <a href="/product/create" 
              onClick={e => {
                e.preventDefault();  
                setCurProduct(null);
                setCurProdId(0);
                setMode('create');
              }}>Create</a>
          </>  
        }           
      </div> 
    </div>
  );
}
