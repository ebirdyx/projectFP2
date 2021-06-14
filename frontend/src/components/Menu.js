import {Container, Nav, Navbar} from "react-bootstrap";
import React from "react";

import {AiFillHeart} from "react-icons/all";

const Menu = () => {
  return (
    <Navbar bg="dark" variant="dark">
      <Container>
        <Navbar.Brand href="/">BookShare <AiFillHeart /></Navbar.Brand>

        <Nav className="me-auto">
          <Nav.Link href="/">Explore</Nav.Link>
          <Nav.Link href="/books">My books</Nav.Link>
          <Nav.Link href="/collections">My collections</Nav.Link>
        </Nav>
      </Container>
    </Navbar>
  )
}

export default Menu;