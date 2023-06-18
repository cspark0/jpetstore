import { BsTrash } from 'react-icons/bs';

export default function ProductTable({entries, curProdId, onClickId, onClickTrash}) {  
  console.log('ProductTable render');
  
  const trList = entries.map(entry => 
    <tr key={entry.productId} bgcolor="#FF8888">
      <td>
        <a href={"/product/" + entry.productId}
          onClick={e => {
            console.log(entry.productId);          
            console.log(e);
            e.preventDefault();
            onClickId(entry.productId);
          }}>
          <font color="black">
            {(entry.productId === curProdId) ? <em>{entry.productId}</em> : entry.productId}
          </font>
        </a>
      </td>
      <td>{entry.name}</td>
      <td>
        <BsTrash onClick={e => {            
          e.preventDefault();
          onClickTrash(entry.productId);
        }}/>
      </td>
    </tr>
  );     
  
  return (
    <table class="n23">
      <tr bgcolor="#CCCCCC">
        <td><b>Product ID</b></td>
        <td><b>Name</b></td>
      </tr>
      {trList}
    </table>
  );
}
    