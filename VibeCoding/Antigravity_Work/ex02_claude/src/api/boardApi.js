import axiosInstance from './axiosInstance'

const boardApi = {
  getAll:  ()         => axiosInstance.get('/boards'),
  getOne:  (id)       => axiosInstance.get(`/boards/${id}`),
  create:  (data)     => axiosInstance.post('/boards', data),
  update:  (id, data) => axiosInstance.put(`/boards/${id}`, data),
  remove:  (id)       => axiosInstance.delete(`/boards/${id}`),
}

export default boardApi
