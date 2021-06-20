import React from "react";
import './App.css';
import { BrowserRouter as Router, Switch } from 'react-router-dom';
import Menu from "./components/menu/Menu";
import routes from "./routes/routes";
import {AuthProvider} from "./context";
import AppRoute from "./AppRoute";

const App = () => {
  return (
    <AuthProvider>
      <Router>
        <div className="App">
          <Menu />

          <Switch>
            {routes.map(route => (
              <AppRoute
                key={route.path}
                path={route.path}
                component={route.component}
                isPrivate={route.isPrivate}
              />
            ))
            }
          </Switch>
        </div>
      </Router>
    </AuthProvider>
  );
}

export default App;
