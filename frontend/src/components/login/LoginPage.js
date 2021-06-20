import {useState} from "react";
import {loginUser, useAuthDispatch} from "../../context";
import {useHistory} from 'react-router-dom';

import './LoginPage.css'

const LoginPage = () => {
  const dispatch = useAuthDispatch()
  const history = useHistory()

  const [username, setUsername] = useState("")
  const [password, setPassword] = useState("")

  const handleLogin = async (e) => {
    e.preventDefault()

    const response = await loginUser(dispatch, username, password)
    if (response?.token)
      history.push("/books")
  }

  return (
    <div className="login-form">
      <form>
        <h2 className="text-center">Log in</h2>
        <div className="form-group">
          <input
            type="text"
            className="form-control"
            placeholder="Username"
            required="required"
            onChange={(e) => setUsername(e.target.value)}
          />
        </div>
        <div className="form-group">
          <input
            type="password"
            className="form-control"
            placeholder="Password"
            required="required"
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>
        <div className="form-group">
          <button
            type="submit"
            className="btn btn-primary btn-block"
            onClick={handleLogin}
          >Log in</button>
        </div>
      </form>

      <p className="text-center"><a href="/register">Create an Account</a></p>
    </div>
  )
}

export default LoginPage