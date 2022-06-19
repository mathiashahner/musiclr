import App from './App'
import React from 'react'
import ReactDOM from 'react-dom'
import { Header, Player } from './ui/components'
import { BrowserRouter } from 'react-router-dom'
import { GlobalUserProvider, GlobalErrorProvider, GlobalPlayerProvider } from './contexts'

ReactDOM.render(
  <React.StrictMode>
    <BrowserRouter>
      <GlobalUserProvider>
        <GlobalErrorProvider>
          <GlobalPlayerProvider>
            <Header />
            <App />
            <Player />
          </GlobalPlayerProvider>
        </GlobalErrorProvider>
      </GlobalUserProvider>
    </BrowserRouter>
  </React.StrictMode>,
  document.getElementById('root')
)
