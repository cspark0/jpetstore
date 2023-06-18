import {useState } from 'react';

export default function UpdateProduct({product, onNewProduct}) {
  console.log('UpdateProduct render');

  const [name, setName] = useState(product.name);  // 변경되는 값을 저장하는 states
  const [desc, setDesc] = useState(product.description);
  
  return ( 
    <form action="/update"      
      onSubmit={e => {
        e.preventDefault();
        onNewProduct(name, desc);
      }}>                           {/* 수정 form */}
      <p>Product ID: 
        <input type="text" name="id" 
              value={product.productId} readOnly /></p>
      <p>Name: 
        <input type="text" name="name"  
              placeholder="input product name..."
              value={name}
              onChange={e => setName(e.target.value)}/></p>   
      <p>Description: 
        <textarea name="desc" rows="5" cols="50"
              placeholder="input product description..."
              value={desc}
              onChange={e => setDesc(e.target.value)}/></p> 
      <p><input type="submit" value="Update"/></p>
    </form>        
  );
}
