import axios from "axios";

const API_URL = process.env.REACT_APP_APP_URL

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
    update: (id, book) => axios.patch(`${API_URL}/api/v1/books/${id}`, {...book}, defaultOptions),
    upload: (id, content) => {
      const form = new FormData();
      form.append("file", content, content.name);

      return axios.post(
        `${API_URL}/api/v1/books/${id}/upload`,
        form,
        defaultOptions
      )
    },
    download: (id) => axios.get(`${API_URL}/api/v1/books/${id}/download`, defaultOptions)
  }
}

export default BookApi()
