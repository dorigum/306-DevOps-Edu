import { useState, useEffect } from 'react'
import { useParams, useNavigate } from 'react-router-dom'
import boardApi from '../api/boardApi'
import { useToast } from '../hooks/useToast'
import Toast from '../components/Toast'
import './BoardFormPage.css'

function BoardFormPage() {
  const { id } = useParams()
  const navigate = useNavigate()
  const isEdit = !!id
  const [form, setForm] = useState({ title: '', content: '', author: '' })
  const [loading, setLoading] = useState(false)
  const [submitting, setSubmitting] = useState(false)
  const { toasts, showToast } = useToast()

  useEffect(() => {
    if (!isEdit) return
    setLoading(true)
    boardApi.getOne(id)
      .then(res => setForm({
        title: res.data.title,
        content: res.data.content,
        author: res.data.author,
      }))
      .catch(() => showToast('게시글을 불러오지 못했습니다.', 'error'))
      .finally(() => setLoading(false))
  }, [id, isEdit])

  const handleChange = e =>
    setForm(prev => ({ ...prev, [e.target.name]: e.target.value }))

  const validate = () => {
    if (!form.title.trim())            return '제목을 입력해주세요.'
    if (!form.author.trim())           return '작성자를 입력해주세요.'
    if (form.content.trim().length < 10) return '내용을 10자 이상 입력해주세요.'
    return null
  }

  const handleSubmit = async e => {
    e.preventDefault()
    const error = validate()
    if (error) { showToast(error, 'error'); return }

    setSubmitting(true)
    try {
      if (isEdit) {
        await boardApi.update(id, form)
        showToast('수정되었습니다.', 'success')
      } else {
        await boardApi.create(form)
        showToast('등록되었습니다.', 'success')
      }
      setTimeout(() => navigate('/'), 800)
    } catch {
      showToast(isEdit ? '수정에 실패했습니다.' : '등록에 실패했습니다.', 'error')
    } finally {
      setSubmitting(false)
    }
  }

  return (
    <div className="form-page">
      <button className="btn btn-secondary back-btn" onClick={() => navigate(-1)}>
        ← 뒤로가기
      </button>

      <div className="form-card glass-card">
        <h2 className="form-title">{isEdit ? '게시글 수정' : '새 게시글 작성'}</h2>

        {loading ? (
          <div className="skeleton" style={{ height: '300px', marginTop: '1rem' }} />
        ) : (
          <form onSubmit={handleSubmit} className="board-form">
            <div className="field">
              <label className="field-label">
                제목 <span className="required">*</span>
              </label>
              <input
                className="field-input"
                name="title"
                value={form.title}
                onChange={handleChange}
                placeholder="제목을 입력하세요"
              />
            </div>

            <div className="field">
              <label className="field-label">
                작성자 <span className="required">*</span>
              </label>
              <input
                className="field-input"
                name="author"
                value={form.author}
                onChange={handleChange}
                placeholder="작성자 이름을 입력하세요"
                disabled={isEdit}
              />
            </div>

            <div className="field">
              <label className="field-label">
                내용 <span className="required">*</span>
                <span className="field-hint">최소 10자</span>
              </label>
              <textarea
                className="field-input field-textarea"
                name="content"
                value={form.content}
                onChange={handleChange}
                placeholder="내용을 입력하세요..."
                rows={10}
              />
            </div>

            <div className="form-actions">
              <button
                type="button"
                className="btn btn-secondary"
                onClick={() => navigate(-1)}
              >
                취소
              </button>
              <button
                type="submit"
                className="btn btn-primary"
                disabled={submitting}
              >
                {submitting ? '처리 중...' : isEdit ? '수정 완료' : '등록하기'}
              </button>
            </div>
          </form>
        )}
      </div>

      <Toast toasts={toasts} />
    </div>
  )
}

export default BoardFormPage
