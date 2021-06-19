import UserApi from "../api/user";

export async function loginUser(dispatch, username, password) {
  try {
    dispatch({ type: 'REQUEST_LOGIN' });
    let response = await UserApi.login(username, password);
    let data = response.data;

    if (data.token) {
      dispatch({ type: 'LOGIN_SUCCESS', payload: data });
      localStorage.setItem('currentUser', JSON.stringify(data));
      return data
    }

    // if (data.user) {
    //   dispatch({ type: 'LOGIN_SUCCESS', payload: data });
    //   localStorage.setItem('currentUser', JSON.stringify(data));
    //   return data
    // }

    dispatch({ type: 'LOGIN_ERROR', error: data.errors[0] });
    return;
  } catch (error) {
    dispatch({ type: 'LOGIN_ERROR', error: error });
  }
}

export async function logout(dispatch) {
  dispatch({ type: 'LOGOUT' });
  localStorage.removeItem('currentUser');
  localStorage.removeItem('token');
}