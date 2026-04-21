import { BrowserRouter, Routes, Route } from 'react-router-dom'
import Layout from './components/Layout/Layout'
import BoardListPage from './pages/BoardListPage'
import BoardDetailPage from './pages/BoardDetailPage'
import BoardFormPage from './pages/BoardFormPage'

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<BoardListPage />} />
          <Route path="boards/:id" element={<BoardDetailPage />} />
          <Route path="create" element={<BoardFormPage />} />
          <Route path="edit/:id" element={<BoardFormPage />} />
        </Route>
      </Routes>
    </BrowserRouter>
  )
}

export default App
