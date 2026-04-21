import pool from '../db/connection.js'

export const getAll = async () => {
  const [rows] = await pool.query(
    'SELECT * FROM boards ORDER BY created_at DESC'
  )
  return rows
}

export const getOne = async (id) => {
  const [rows] = await pool.query(
    'SELECT * FROM boards WHERE id = ?', [id]
  )
  return rows[0] || null
}

export const create = async ({ title, content, author }) => {
  const [result] = await pool.query(
    'INSERT INTO boards (title, content, author) VALUES (?, ?, ?)',
    [title, content, author]
  )
  const [rows] = await pool.query(
    'SELECT * FROM boards WHERE id = ?', [result.insertId]
  )
  return rows[0]
}

export const update = async (id, { title, content }) => {
  await pool.query(
    'UPDATE boards SET title = ?, content = ? WHERE id = ?',
    [title, content, id]
  )
  const [rows] = await pool.query(
    'SELECT * FROM boards WHERE id = ?', [id]
  )
  return rows[0] || null
}

export const remove = async (id) => {
  const [result] = await pool.query(
    'DELETE FROM boards WHERE id = ?', [id]
  )
  return result.affectedRows > 0
}
