import * as boardService from '../services/boardService.js'

export const getAll = async (req, res) => {
  try {
    const boards = await boardService.getAll()
    res.json(boards)
  } catch (err) {
    console.error('[getAll]', err.message)
    res.status(500).json({ message: err.message })
  }
}

export const getOne = async (req, res) => {
  try {
    const board = await boardService.getOne(req.params.id)
    if (!board) return res.status(404).json({ message: '게시글을 찾을 수 없습니다.' })
    res.json(board)
  } catch (err) {
    console.error('[getOne]', err.message)
    res.status(500).json({ message: err.message })
  }
}

export const create = async (req, res) => {
  const { title, content, author } = req.body
  if (!title || !content || !author) {
    return res.status(400).json({ message: '제목, 내용, 작성자는 필수입니다.' })
  }
  try {
    const board = await boardService.create({ title, content, author })
    res.status(201).json(board)
  } catch (err) {
    console.error('[create]', err.message)
    res.status(500).json({ message: err.message })
  }
}

export const update = async (req, res) => {
  const { title, content } = req.body
  if (!title || !content) {
    return res.status(400).json({ message: '제목과 내용은 필수입니다.' })
  }
  try {
    const board = await boardService.update(req.params.id, { title, content })
    if (!board) return res.status(404).json({ message: '게시글을 찾을 수 없습니다.' })
    res.json(board)
  } catch (err) {
    console.error('[update]', err.message)
    res.status(500).json({ message: err.message })
  }
}

export const remove = async (req, res) => {
  try {
    const ok = await boardService.remove(req.params.id)
    if (!ok) return res.status(404).json({ message: '게시글을 찾을 수 없습니다.' })
    res.json({ message: '삭제되었습니다.' })
  } catch (err) {
    console.error('[remove]', err.message)
    res.status(500).json({ message: err.message })
  }
}
