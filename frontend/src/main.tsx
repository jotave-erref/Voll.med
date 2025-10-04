// Arquivo: frontend/src/main.tsx
import React from 'react'
import ReactDOM from 'react-dom/client'
import { RouterProvider } from 'react-router-dom'
import { router } from './routes' // Importa nosso roteador
import './index.css' // Importa os estilos do Tailwind

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    {/* Em vez de renderizar o <App />, agora renderizamos o nosso provedor de rotas */}
    <RouterProvider router={router} />
  </React.StrictMode>,
)