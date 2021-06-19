import axios from "axios";

const API_URL = 'http://localhost:8080'

const BookApi = () => {
  let token = localStorage.getItem("currentUser")
    ? JSON.parse(localStorage.getItem("currentUser")).token
    : "";

  const defaultOptions = token !== "" ? {
    headers: {
      Authorization: `Bearer ${token}`
    }
  } : {}

  return {
    get: () => axios.get(`${API_URL}/api/v1/books`, defaultOptions),
    getById: (id) => axios.get(`${API_URL}/api/v1/books/${id}`, defaultOptions),
    create: (book) => axios.post(`${API_URL}/api/v1/books`, {...book}, defaultOptions),
    delete: (id) => axios.delete(`${API_URL}/api/v1/books/${id}`, defaultOptions),
    update: (id, book) => axios.patch(`${API_URL}/api/v1/books/${id}`, {...book}, defaultOptions)
  }
}

export default BookApi()
