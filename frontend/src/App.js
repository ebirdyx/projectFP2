import React from "react";
import './App.css';
import ProductPage from "./components/ProductPage";
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';

const App = () => {
  return (
    <Router>
      <div className="App">
        <Switch>
          <Route path='/'>
            <ProductPage />
          </Route>
        </Switch>
      </div>
    </Router>
  );
}

export default App;
