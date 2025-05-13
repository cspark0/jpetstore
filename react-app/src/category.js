import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import ProductApp from './ProductApp';
import reportWebVitals from './reportWebVitals';

console.log(window.categoryId);
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
//  <React.StrictMode>
    <ProductApp categoryId={window.categoryId} />
//  </React.StrictMode>
);

reportWebVitals();
