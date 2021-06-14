import React from "react";
import './App.css';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import BookPage from "./components/books/BookPage";
import Menu from "./components/Menu";

const App = () => {
  return (
    <Router>
      <div className="App">
        <Menu />

        <Switch>
            <Route path='/'>
                <BookPage />
            </Route>
        </Switch>
      </div>
    </Router>
  );
}

export default App;
