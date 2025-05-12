import './components.css';

export default function CreateProduct({onNewProduct}) {
  console.log('CreateProduct render');
  
  const submit = e => {
    e.preventDefault();
    onNewProduct(e.target.id.value, e.target.name.value, e.target.desc.value);
    e.target.id.value = "";
    e.target.name.value = "";      // e.target: submit event가 발생한 element(<form/>)
    e.target.desc.value = "";
  };

  return (  
    <form action="/create" onSubmit={submit}>   {/* 생성 form */} 
      <div>
        <div>Product ID:</div>
        <input type="text" name="id" 
              placeholder="input product ID..."/>
      </div>
      <div>
        <div>Name:</div>
        <input type="text" name="name" 
              placeholder="input product name..."/>         
      </div>
      <div>
        <div>Description:</div>
        <textarea name="desc" rows="5" cols="50"
              placeholder="input description..."/>      
      </div>
      <div><input type="submit" value="Create"/></div>
    </form>        
  );
}
