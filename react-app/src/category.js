import React from 'react';
import ReactDOM from 'react-dom/client';
import reportWebVitals from './reportWebVitals';

import './index.css';
import ProductApp from './ProductApp';

const container = document.getElementById('root');
const root = ReactDOM.createRoot(container);
console.log(window.categoryId);
root.render(
//  <React.StrictMode>
//  { 
      <ProductApp categoryId={window.categoryId} />    
//  }    
//  </React.StrictMode>
);

reportWebVitals();
