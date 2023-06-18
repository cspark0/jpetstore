import { BsTrash } from 'react-icons/bs';
export default function TOC({entries, curContId, onClickProductId, onClickTrash}) {  
  console.log('==> TOC render');
  
  const trList = entries.map(entry => 
    <tr key={entry.productId} bgcolor="#FF8888">
      <td>
        <a href={"/content/" + entry.productId}
          onClick={e => {
            console.log(e);
            e.preventDefault();
            onClickProductId(entry.productId);
          }
        }><font color="black">
          {(entry.id === curContId) ? <em>{entry.productId}</em> : entry.productId }</font>
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
    