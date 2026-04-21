import { BrowserRouter, Routes, Route } from 'react-router-dom'
import { ThemeProvider } from 'styled-components'
import GlobalStyle from './styles/GlobalStyle'
import theme from './styles/theme'
import Layout from './components/common/Layout'
import Home from './pages/Home'

function App() {
  return (
    <BrowserRouter>
      <ThemeProvider theme={theme}>
        <GlobalStyle />
        <Routes>
          <Route path="/" element={<Layout><Home /></Layout>} />
        </Routes>
      </ThemeProvider>
    </BrowserRouter>
  )
}

export default App
