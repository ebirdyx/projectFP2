import React from "react";
import './App.css';
import ProductPage from "./components/ProductPage";
import { BrowserRouter as Router, Switch, Route, Link } from 'react-router-dom';
import BookPage from "./components/BookPage";
import {Container, Image, Nav, Navbar} from "react-bootstrap";
import CollectionPage from "./components/CollectionPage";

const App = () => {
  return (
    <Router>
      <div className="App">
          <Navbar bg="dark" variant="dark">
              <Container>
                  <Navbar.Brand href="/">Bookstore</Navbar.Brand>
                  <Nav className="me-auto">
                      <Nav.Link href="/">Home</Nav.Link>
                      <Nav.Link href="/books">Books</Nav.Link>
                      <Nav.Link href="/products">Products</Nav.Link>
                      <Nav.Link href="/collections">Collections</Nav.Link>

                  </Nav>
              </Container>
          </Navbar>

        <Switch>
            <Route path='/collections'>
                <CollectionPage />
            </Route>

            <Route path='/books'>
                <BookPage />
            </Route>

            <Route path='/'>
                <ProductPage />
            </Route>
        </Switch>
      </div>
    </Router>
  );
}

export default App;
