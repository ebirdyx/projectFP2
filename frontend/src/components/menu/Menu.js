import {Container, Nav, Navbar, NavDropdown} from "react-bootstrap";
import React from "react";

import {AiFillHeart} from "react-icons/all";
import {logout, useAuthDispatch, useAuthState} from "../../context";
import {useHistory} from "react-router-dom";

const Menu = () => {
  const dispatch = useAuthDispatch()
  const history = useHistory()
  const authState = useAuthState()

  const handleLogout = async () => {
      await logout(dispatch)
      history.push("/login")
  }

  return (
    <Navbar bg="dark" variant="dark">
      <Container>
        <Navbar.Brand href="/books">BookShare <AiFillHeart /></Navbar.Brand>

        {authState.token &&
        <Nav>
          <NavDropdown
            id="nav-dropdown-dark-example"
            title={
              <img
                src="https://s3.eu-central-1.amazonaws.com/bootstrapbaymisc/blog/24_days_bootstrap/fox.jpg"
                width="40"
                className="rounded-circle"
              />
            }
            menuVariant="dark"
          >
            <NavDropdown.Item href="/profile">Profile</NavDropdown.Item>
            <NavDropdown.Divider />
            <NavDropdown.Item onClick={handleLogout} >Logout</NavDropdown.Item>
          </NavDropdown>
        </Nav>
        }
      </Container>
    </Navbar>
  )
}

export default Menu;