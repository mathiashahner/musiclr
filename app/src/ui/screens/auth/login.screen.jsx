import './auth.style.css'
import logoIcon from '../../../assets/images/icon.svg'
import logoText from '../../../assets/images/logoText.svg'

import { useEffect, useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { useGlobalUser } from '../../../contexts'
import { SCREENS } from '../../../constants'
import { useAuthApi } from '../../../hooks'
import { Button, Input } from '../../components'

export const LoginScreen = () => {
  const [globalUser] = useGlobalUser()
  const [credentials, setCredentials] = useState({ username: '', password: '' })

  const { login } = useAuthApi()
  const navigate = useNavigate()

  useEffect(() => {
    if (globalUser.user) {
      navigate(SCREENS.HOME)
    }
  }, [globalUser, navigate])

  const handleChange = event => {
    const { name, value } = event.target
    setCredentials({ ...credentials, [name]: value })
  }

  const onLoginSubmit = async event => {
    event.preventDefault()
    await login({ username: credentials.username, password: credentials.password })
  }

  return (
    <div className='auth__wrapper'>
      <div className='auth__container'>
        <img className='auth__img-logo' src={logoIcon} alt='Musiclr' />

        <form className='auth__form' onSubmit={onLoginSubmit}>
          <img className='auth__img-text' src={logoText} alt='Musiclr' />

          <Input
            name={'username'}
            value={credentials.username}
            placeholder={'E-mail'}
            onChange={handleChange}
          />

          <Input
            name={'password'}
            type={'password'}
            value={credentials.password}
            placeholder={'Senha'}
            onChange={handleChange}
          />

          <Button type={'submit'} text={'LOGIN'} />

          <Link to={SCREENS.REGISTER}>
            <button className='auth__btn-register'>Cadastre-se</button>
          </Link>
        </form>
      </div>
    </div>
  )
}
