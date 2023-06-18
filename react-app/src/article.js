import React from 'react';
import ReactDOM from 'react-dom/client';
import ArticleApp from './ArticleApp';
import reportWebVitals from './reportWebVitals';

import './index.css';

const container = document.getElementById('root');
const root = ReactDOM.createRoot(container);
root.render(
//  <React.StrictMode>
//  { 
      <ArticleApp />    
//  }    
//  </React.StrictMode>
);

reportWebVitals();
