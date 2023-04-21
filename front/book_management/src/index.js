import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import {BrowserRouter} from "react-router-dom";
import {RecoilRoot} from "recoil";
import {QueryClient, QueryClientProvider} from "react-query";

// 비동기 처리를 할때 쓰는 라이브러리 recoil 은 동기 처리를 할때 주로 쓴다.
const queryClient = new QueryClient();

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
      <RecoilRoot>
          <QueryClientProvider client={queryClient}>
              <BrowserRouter>
                  <App />
              </BrowserRouter>
          </QueryClientProvider>
      </RecoilRoot>
  </React.StrictMode>
);

reportWebVitals();
