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
      <p>Product ID: 
        <input type="text" name="id" 
              placeholder="input product ID..."/></p>              
      <p>Name: 
        <input type="text" name="name" 
              placeholder="input product name..."/></p>              
      <p>Description: 
        <textarea name="desc" rows="5" cols="50"
              placeholder="input description..."/></p>
      <p><input type="submit" value="Create"/></p>
    </form>        
  );
}
