  
export default function CreateContent({onNewContent}) {
  console.log('CreateContent render');
  
  const submit = e => {
    e.preventDefault();
    onNewContent(e.target.id.value, e.target.name.value, e.target.desc.value);
    e.target.id.value = "";
    e.target.name.value = "";      // e.target: submit event가 발생한 element(<form/>)
    e.target.desc.value = "";
  };

  return (
    <form action="/create" onSubmit={submit}>   // 생성 form        
      <p><input type="text" name="id" 
                placeholder="input product ID..."/></p>              
      <p><input type="text" name="name" 
                placeholder="input product name..."/></p>              
      <p><textarea name="desc" rows="5" cols="50"
                placeholder="input description..."/></p>
      <p><input type="submit"/></p>
    </form>        
  );
}
