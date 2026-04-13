import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'
import App02 from './App02.jsx'
import UserCRUD from './UserCRUD.jsx'

createRoot(document.getElementById('root')).render(
    // <App />
    // <App02 />
    <UserCRUD />
)
