import { Navigate } from 'react-router-dom'
import { SCREENS } from '../../../constants'
import { useGlobalUser } from '../../../contexts'

export const PrivateRoute = ({ children }) => {
  const [user] = useGlobalUser()

  if (user.token) {
    return <>{children}</>
  } else {
    return <Navigate to={SCREENS.LOGIN} />
  }
}
