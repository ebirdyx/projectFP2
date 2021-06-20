import {Container, Nav, Navbar} from "react-bootstrap";
import React from "react";

import {AiFillHeart} from "react-icons/all";
import Button from "react-bootstrap/Button";
import {logout, useAuthDispatch} from "../context";
import {useHistory} from "react-router-dom";

const Menu = () => {
  const dispatch = useAuthDispatch()
  const history = useHistory()

  const handleSelect = async (eventKey) => {
    if (eventKey === "logout") {
      await logout(dispatch)
      history.push("/login")
    }
  }

  return (
    <Navbar bg="dark" variant="dark">
      <Container>
        <Navbar.Brand href="/">BookShare <AiFillHeart /></Navbar.Brand>

        <Nav className="me-auto" onSelect={handleSelect}>
          <Nav.Link eventKey="logout" >Logout</Nav.Link>
        </Nav>
      </Container>
    </Navbar>
  )
}

export default Menu;