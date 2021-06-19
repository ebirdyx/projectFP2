import axios from "axios";

const client = () => {
  const defaultOptions = {
    headers: {
      'Content-Type': 'application/json',
      'Accept': 'application/json'
    }
  }

  let instance = axios.create(defaultOptions)

  instance.interceptors.request.use(function (config) {
    let token = localStorage.getItem("currentUser")
      ? JSON.parse(localStorage.getItem("currentUser")).token
      : "";

    config.headers.Authorization = token !== "" ? `Bearer ${token}` : ""
    return config
  })

  return instance
}

export default client()