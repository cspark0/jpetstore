import { BsTrash } from 'react-icons/bs';
import './components.css';

export default function ProductTable({entries, curProdId, onClickId, onClickTrash}) {  
  console.log('ProductTable render');
  
  const trList = entries.map(entry => 
    <tr key={entry.productId}>
      <td>
        <a href={"/product/" + entry.productId}
          onClick={e => {
            console.log(entry.productId);          
            console.log(e);
            e.preventDefault();
            onClickId(entry.productId);
          }}>
          {(entry.productId === curProdId) ? <em>{entry.productId}</em> : entry.productId}  
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
    <table className="prodTbl">
      <thead>
        <tr>
          <th><b>Product ID</b></th>
          <th><b>Name</b></th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        {trList}
      </tbody>
    </table>
  );
}
    