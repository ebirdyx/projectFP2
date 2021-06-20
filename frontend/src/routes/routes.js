import BookPage from "../components/books/BookPage";
import LoginPage from "../components/login/LoginPage";
import PageNotFound from "../components/PageNotFound";
import RegisterPage from "../components/register/RegisterPage";
import UserProfile from "../components/profile/UserProfile";

const routes =[
  {
    path:'/login',
    component: LoginPage,
    isPrivate: false
  },
  {
    path:'/register',
    component: RegisterPage,
    isPrivate: false
  },
  {
    path:'/books',
    component: BookPage,
    isPrivate: true
  },
  {
    path:'/profile',
    component: UserProfile,
    isPrivate: true
  },
  {
    path:'/*',
    component: PageNotFound,
    isPrivate: true
  },
]

export default routes