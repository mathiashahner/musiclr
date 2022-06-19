import './App.css'
import './assets/styles/variables.css'

import { SCREENS } from './constants'
import { useGlobalError } from './contexts'
import { Modal, PrivateRoute } from './ui/components'
import { Navigate, Route, Routes } from 'react-router-dom'
import { FriendScreen, HomeScreen, LoginScreen, RegisterScreen, SearchScreen, UserScreen } from './ui/screens'

const App = () => {
  const [error] = useGlobalError()

  return (
    <div className='container'>
      <Routes>
        <Route path={SCREENS.LOGIN} element={<LoginScreen />} />
        <Route path={SCREENS.REGISTER} element={<RegisterScreen />} />

        <Route
          path={SCREENS.HOME}
          element={
            <PrivateRoute>
              <HomeScreen />
            </PrivateRoute>
          }
        />

        <Route
          path={`${SCREENS.SEARCH}/:textToSearch`}
          element={
            <PrivateRoute>
              <SearchScreen />
            </PrivateRoute>
          }
        />

        <Route
          path={`${SCREENS.USER}/:userId`}
          element={
            <PrivateRoute>
              <UserScreen />
            </PrivateRoute>
          }
        />

        <Route
          path={`${SCREENS.FRIENDS}`}
          element={
            <PrivateRoute>
              <FriendScreen />
            </PrivateRoute>
          }
        />

        <Route path='*' element={<Navigate to={SCREENS.LOGIN} />} />
      </Routes>

      {!!error && <Modal message={error.message} />}
    </div>
  )
}

export default App
