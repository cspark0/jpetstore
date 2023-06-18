import React from 'react';
import ReactDOM from 'react-dom/client';
import CategoryApp from './CategoryApp';
import reportWebVitals from './reportWebVitals';

import './index.css';

const container = document.getElementById('root');
const root = ReactDOM.createRoot(container);
console.log(window.categoryId);
root.render(
//  <React.StrictMode>
//  { 
      <CategoryApp categoryId={window.categoryId} />    
//  }    
//  </React.StrictMode>
);

reportWebVitals();
