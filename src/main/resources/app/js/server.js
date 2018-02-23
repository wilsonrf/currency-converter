import React from 'react';
import { renderToString } from 'react-dom/server';
import { StaticRouter } from 'react-router';
import { Provider } from 'react-redux';
import { createStore } from 'redux';
import serialize from 'serialize-javascript';
import reducer from './reducers';
import App from './components/App';

window.render = (template, model) => {
  const context = {};
  const req = JSON.parse(model.req);
  const initialState = JSON.parse(model.state);

  const store = createStore(reducer, initialState);

  const markup = renderToString(
    <Provider store={store}>
      <StaticRouter location={req.location} context={context}>
        <App />
      </StaticRouter>
    </Provider>
  );
  return template
    .replace('SERVER_RENDERED_HTML', markup)
    .replace('SERVER_RENDERED_STATE', serialize(initialState, { isJSON: true }));
};
