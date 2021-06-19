import axios from "axios";

const API_URL = 'http://localhost:8080'

const UserApi = {
  login: (username, password) => axios.post(`${API_URL}/api/v1/auth/login`, {username, password}),
  register: (username, firstName, lastName, email, password) => axios.post(`${API_URL}/api/v1/auth/register`, {
    username, firstName, lastName, email, password
  })
}

export default UserApi