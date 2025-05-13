import { useState } from 'react';
import './components.css';

export default function UpdateProduct({product, onNewProduct}) {
  console.log('UpdateProduct render');

  const [name, setName] = useState(product.name);  // 변경되는 값을 저장하는 states
  const [desc, setDesc] = useState(product.description);
  
  return ( 
    <form className="prodForm" action="/update" 
      onSubmit={e => {
        e.preventDefault();
        onNewProduct(name, desc);
      }}>                           {/* 수정 form */}
      <div>
        <div>Product ID:</div>
        <input type="text" name="id" 
              value={product.productId} readOnly />
      </div>
      <div>
        <div>Name:</div>
        <input type="text" name="name"  
              placeholder="input product name..."
              value={name}
              onChange={e => setName(e.target.value)}/>
       </div>
      <div>
        <div>Description:</div>
        <textarea name="desc" rows="5" cols="50"
              placeholder="input product description..."
              value={desc}
              onChange={e => setDesc(e.target.value)}/>
      </div>
      <div><input type="submit" value="Update"/></div>    
    </form>        
  );
}
