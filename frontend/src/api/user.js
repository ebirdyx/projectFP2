import axios from "axios";

const API_URL = process.env.REACT_APP_APP_URL

const UserApi = () => {
  let token = localStorage.getItem("currentUser")
    ? JSON.parse(localStorage.getItem("currentUser")).token
    : "";

  const defaultOptions = token !== "" ? {
    headers: {
      Authorization: `Bearer ${token}`
    }
  } : {}

  return {
    login: (username, password) => axios.post(
        `${API_URL}/api/v1/auth/login`,
        {username, password}),
    register: (username, firstName, lastName, email, password) =>
      axios.post(`${API_URL}/api/v1/auth/register`, {
        username, firstName, lastName, email, password
      }),
    profile: () => axios.get(
        `${API_URL}/api/v1/auth/userinfo`,
        defaultOptions)
  }
}

export default UserApi()