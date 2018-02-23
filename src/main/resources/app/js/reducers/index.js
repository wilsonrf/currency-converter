import { combineReducers } from 'redux';
import CommonReducer from '../components/AppReducer';

const rootReducer = combineReducers({ items: CommonReducer });

export default rootReducer;