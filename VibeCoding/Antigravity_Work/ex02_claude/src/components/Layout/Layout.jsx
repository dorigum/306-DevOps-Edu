import { Outlet, Link } from 'react-router-dom'
import './Layout.css'

function Layout() {
  return (
    <div className="layout">
      <header className="header">
        <div className="header-inner">
          <Link to="/" className="logo">
            <span className="logo-icon">◈</span>
            <span className="logo-text">
              Vibe<span className="logo-accent">Board</span>
            </span>
          </Link>
        </div>
      </header>

      <main className="main-content">
        <Outlet />
      </main>

      <footer className="footer">
        <p>© 2026 VibeCoding · Built with Claude Code</p>
      </footer>
    </div>
  )
}

export default Layout
