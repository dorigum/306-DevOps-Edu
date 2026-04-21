import { useState, useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import boardApi from '../api/boardApi'
import { useToast } from '../hooks/useToast'
import Toast from '../components/Toast'
import './BoardListPage.css'

function BoardListPage() {
  const [boards, setBoards] = useState([])
  const [filtered, setFiltered] = useState([])
  const [query, setQuery] = useState('')
  const [loading, setLoading] = useState(true)
  const navigate = useNavigate()
  const { toasts, showToast } = useToast()

  useEffect(() => {
    boardApi.getAll()
      .then(res => {
        const data = Array.isArray(res.data) ? res.data : []
        setBoards(data)
        setFiltered(data)
      })
      .catch(() => showToast('목록을 불러오지 못했습니다.', 'error'))
      .finally(() => setLoading(false))
  }, [])

  useEffect(() => {
    const q = query.toLowerCase()
    setFiltered(
      boards.filter(b =>
        b.title.toLowerCase().includes(q) || b.author.toLowerCase().includes(q)
      )
    )
  }, [query, boards])

  const handleDelete = async (e, id) => {
    e.stopPropagation()
    if (!confirm('정말 삭제하시겠습니까?')) return
    try {
      await boardApi.remove(id)
      setBoards(prev => prev.filter(b => b.id !== id))
      showToast('삭제되었습니다.', 'success')
    } catch {
      showToast('삭제에 실패했습니다.', 'error')
    }
  }

  return (
    <div className="list-page">
      <div className="list-header">
        <div>
          <h1 className="list-title">게시판</h1>
          <p className="list-subtitle">총 {filtered.length}개의 게시글</p>
        </div>
        <button className="btn btn-primary" onClick={() => navigate('/create')}>
          + 글쓰기
        </button>
      </div>

      <div className="search-wrap">
        <input
          className="search-input"
          type="text"
          placeholder="제목 또는 작성자로 검색..."
          value={query}
          onChange={e => setQuery(e.target.value)}
        />
      </div>

      {loading ? (
        <div className="cards-grid">
          {Array.from({ length: 6 }).map((_, i) => (
            <div key={i} className="skeleton" style={{ height: '200px' }} />
          ))}
        </div>
      ) : filtered.length === 0 ? (
        <div className="empty-state">
          <p>게시글이 없습니다.</p>
        </div>
      ) : (
        <div className="cards-grid">
          {filtered.map((board, i) => (
            <div
              key={board.id}
              className="board-card glass-card"
              style={{ animationDelay: `${i * 0.05}s` }}
              onClick={() => navigate(`/boards/${board.id}`)}
            >
              <div className="card-id">#{board.id}</div>
              <h3 className="card-title">{board.title}</h3>
              <p className="card-preview">
                {board.content.length > 80
                  ? board.content.slice(0, 80) + '...'
                  : board.content}
              </p>
              <div className="card-footer">
                <span className="card-author">{board.author}</span>
                <span className="card-date">
                  {new Date(board.created_at).toLocaleDateString('ko-KR')}
                </span>
              </div>
              <div className="card-actions">
                <button
                  className="btn btn-secondary"
                  onClick={e => { e.stopPropagation(); navigate(`/edit/${board.id}`) }}
                >
                  수정
                </button>
                <button
                  className="btn btn-danger"
                  onClick={e => handleDelete(e, board.id)}
                >
                  삭제
                </button>
              </div>
            </div>
          ))}
        </div>
      )}

      <Toast toasts={toasts} />
    </div>
  )
}

export default BoardListPage
