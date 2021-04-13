import { createStore } from 'redux';
import { reducer } from './reducers';
import { AppState } from './AppState';

const initialState = new AppState();
initialState.isLoggedIn = false;

export const store = createStore(reducer, initialState);