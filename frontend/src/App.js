import React from "react";
import './App.css';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import BookPage from "./components/books/BookPage";
import {Container, Nav, Navbar} from "react-bootstrap";
import CollectionPage from "./components/collections/CollectionPage";

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
                      <Nav.Link href="/collections">Collections</Nav.Link>
                  </Nav>
              </Container>
          </Navbar>

        <Switch>
            <Route path='/collections'>
                <CollectionPage />
            </Route>

            <Route path='/'>
                <BookPage />
            </Route>
        </Switch>
      </div>
    </Router>
  );
}

export default App;
