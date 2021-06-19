import './RegisterPage.css'
import {useState} from "react";
import UserApi from "../../api/user";
import {useHistory} from "react-router-dom";

const RegisterPage = () => {
  const history = useHistory()

  const [firstName, setFirstName] = useState("")
  const [lastName, setLastName] = useState("")
  const [email, setEmail] = useState("")
  const [username, setUsername] = useState("")
  const [password, setPassword] = useState("")

  const handleRegister = async (e) => {
    e.preventDefault()

    await UserApi.register(username, firstName, lastName, email, password)

    history.push("/login")
  }

  return (
    <div className="login-form">
      <form>
        <h2 className="text-center">Register</h2>
        <div className="form-group">
          <input
            type="text"
            className="form-control"
            placeholder="first name"
            required="required"
            onChange={(e) => setFirstName(e.target.value)}
          />
        </div>
        <div className="form-group">
          <input
            type="text"
            className="form-control"
            placeholder="last name"
            required="required"
            onChange={(e) => setLastName(e.target.value)}
          />
        </div>
        <div className="form-group">
          <input
            type="text"
            className="form-control"
            placeholder="email"
            required="required"
            onChange={(e) => setEmail(e.target.value)}
          />
        </div>
        <div className="form-group">
          <input
            type="text"
            className="form-control"
            placeholder="username"
            required="required"
            onChange={(e) => setUsername(e.target.value)}
          />
        </div>
        <div className="form-group">
          <input
            type="password"
            className="form-control"
            placeholder="password"
            required="required"
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>
        <div className="form-group">
          <button
            type="submit"
            className="btn btn-primary btn-block"
            onClick={handleRegister}
          >Register</button>
        </div>
      </form>
    </div>
  )
}

export default RegisterPage