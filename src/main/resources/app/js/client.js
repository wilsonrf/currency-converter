import React from 'react';
import { render } from 'react-dom';
import { BrowserRouter, Switch, Route } from 'react-router-dom';
import { createStore, applyMiddleware, compose } from 'redux';
import thunk from 'redux-thunk';
import { Provider } from 'react-redux';
import reducer from './reducers';
import App from './components/App';

const togglePluing = () => {
  return window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__();
};

const devTools = togglePluing();

const store = createStore(reducer, window.__PRELOADED_STATE__, compose(applyMiddleware(thunk), devTools));

const Markup = props => (
  <Provider store={store}>
    <BrowserRouter>
      <Switch>
        <Route path="/" key="index" component={App} />
      </Switch>
    </BrowserRouter>
  </Provider>
);

render(<Markup/>, document.getElementById('app'));
