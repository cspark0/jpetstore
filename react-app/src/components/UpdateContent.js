import {useState } from 'react';

export default function UpdateContent({content, onNewContent}) {
  console.log('UpdateContent render');

  const [name, setName] = useState(content.name);  // 변경되는 값을 저장하는 states
  const [desc, setDesc] = useState(content.description);
  
  return (
    <form action="/update"      // 수정 form
      onSubmit={e => {
                  e.preventDefault();
                  onNewContent(name, desc);
                }}>
      <p>Product ID: <input type="text" name="id" value={content.productId} readOnly /></p>
      <p>Name: <input type="text" name="name"  
                placeholder="input product name..."
                value={name}
                onChange={e => setName(e.target.value)}/></p>   
      <p>Description: <textarea name="desc" rows="5" cols="50"
                placeholder="input product description..."
                value={desc}
                onChange={e => setDesc(e.target.value)}/></p> 
      <p><input type="submit" /></p>
    </form>        
  );
}
