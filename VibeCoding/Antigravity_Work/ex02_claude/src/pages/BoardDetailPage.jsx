import { useState, useEffect } from 'react'
import { useParams, useNavigate } from 'react-router-dom'
import boardApi from '../api/boardApi'
import { useToast } from '../hooks/useToast'
import Toast from '../components/Toast'
import './BoardDetailPage.css'

function BoardDetailPage() {
  const { id } = useParams()
  const navigate = useNavigate()
  const [board, setBoard] = useState(null)
  const [loading, setLoading] = useState(true)
  const { toasts, showToast } = useToast()

  useEffect(() => {
    boardApi.getOne(id)
      .then(res => setBoard(res.data))
      .catch(() => showToast('게시글을 불러오지 못했습니다.', 'error'))
      .finally(() => setLoading(false))
  }, [id])

  const handleDelete = async () => {
    if (!confirm('정말 삭제하시겠습니까?')) return
    try {
      await boardApi.remove(id)
      showToast('삭제되었습니다.', 'success')
      setTimeout(() => navigate('/'), 1000)
    } catch {
      showToast('삭제에 실패했습니다.', 'error')
    }
  }

  if (loading) {
    return (
      <div className="detail-page">
        <div className="skeleton" style={{ height: '400px' }} />
      </div>
    )
  }

  if (!board) {
    return <div className="detail-empty">게시글을 찾을 수 없습니다.</div>
  }

  return (
    <div className="detail-page">
      <button className="btn btn-secondary back-btn" onClick={() => navigate('/')}>
        ← 목록으로
      </button>

      <article className="detail-card glass-card">
        <header className="detail-header">
          <span className="detail-id">#{board.id}</span>
          <h1 className="detail-title">{board.title}</h1>
          <div className="detail-meta">
            <span>{board.author}</span>
            <span>{new Date(board.created_at).toLocaleString('ko-KR')}</span>
          </div>
        </header>

        <div className="detail-divider" />

        <div className="detail-content">{board.content}</div>

        <div className="detail-actions">
          <button
            className="btn btn-secondary"
            onClick={() => navigate(`/edit/${board.id}`)}
          >
            수정
          </button>
          <button className="btn btn-danger" onClick={handleDelete}>
            삭제
          </button>
        </div>
      </article>

      <Toast toasts={toasts} />
    </div>
  )
}

export default BoardDetailPage
