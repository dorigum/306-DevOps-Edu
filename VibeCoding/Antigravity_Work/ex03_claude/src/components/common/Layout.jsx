import Header from './Header'
import Footer from './Footer'

function Layout({ children }) {
  return (
    <div className="wrapper">
      <Header />
      {children}
      <Footer />
    </div>
  )
}

export default Layout
