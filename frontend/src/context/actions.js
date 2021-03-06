import axios from "axios";

import UserApi from "../api/user";

export async function loginUser(dispatch, username, password) {
  try {
    dispatch({ type: 'REQUEST_LOGIN' });
    let response = await UserApi.login(username, password);
    let data = response.data;

    if (data.token) {
      dispatch({ type: 'LOGIN_SUCCESS', payload: data });
      localStorage.setItem('currentUser', JSON.stringify(data));

      axios.interceptors.request.use(function (config) {
        config.headers.Authorization = `Bearer ${data.token}`
        return config
      })

      return data
    }

    dispatch({ type: 'LOGIN_ERROR', error: data.errors[0] });
    return;
  } catch (error) {
    dispatch({ type: 'LOGIN_ERROR', error: error });
  }
}

export async function logout(dispatch) {
  dispatch({ type: 'LOGOUT' });

  axios.interceptors.request.use(function (config) {
    config.headers.Authorization = ''
    return config
  })

  localStorage.removeItem('currentUser');
  localStorage.removeItem('token');
}