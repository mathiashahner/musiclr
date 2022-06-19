import './auth.style.css'
import logo from '../../../assets/images/logo.svg'
import folderIcon from '../../../assets/images/folder.png'

import { useState } from 'react'
import { SCREENS } from '../../../constants'
import { useAuthApi } from '../../../hooks'
import { Button, Input } from '../../components'
import { useGlobalError } from '../../../contexts'
import { Link, useNavigate } from 'react-router-dom'

const DEFAULT_REGISTER_INFO = {
  name: '',
  birthDate: '',
  email: '',
  password: '',
  confirmedPassword: '',
}

export const RegisterScreen = () => {
  const [, setError] = useGlobalError()
  const [credentials, setCredentials] = useState(DEFAULT_REGISTER_INFO)
  const [imagePath, setImagePath] = useState('Imagem de perfil (opcional)')

  const { create } = useAuthApi()
  const navigate = useNavigate()

  const handleChange = event => {
    const { name, value } = event.target

    if (name === 'image') {
      const file = event.target.files[0]
      const reader = new FileReader()

      reader.addEventListener('load', loadEvent => {
        setCredentials({ ...credentials, [name]: btoa(loadEvent.target.result) })
      })

      reader.readAsDataURL(file)
      setImagePath(file.name)
    } else {
      setCredentials({ ...credentials, [name]: value })
    }
  }

  const onRegisterSubmit = async event => {
    event.preventDefault()

    const response = await create({
      name: credentials.name,
      birthDate: credentials.birthDate,
      nickname: credentials.nickname,
      image: credentials.image,
      email: credentials.email,
      password: credentials.password,
      confirmedPassword: credentials.confirmedPassword,
    })

    if (response) {
      setError({ message: <p>Usuário criado com sucesso! Faça login para continuar.</p> })
      navigate(SCREENS.HOME)
    }
  }

  return (
    <div className='auth__wrapper'>
      <div className='auth__container'>
        <form className='auth__form' onSubmit={onRegisterSubmit}>
          <img className='auth__img-text' src={logo} alt='Musiclr' />

          <Input
            name={'name'}
            value={credentials.name}
            placeholder={'Nome completo'}
            onChange={handleChange}
          />

          <Input
            name={'birthDate'}
            type={'date'}
            value={credentials.birthDate}
            placeholder={'Data de Nascimento'}
            onChange={handleChange}
          />

          <Input
            name={'nickname'}
            value={credentials.nickname}
            placeholder={'Apelido (opcional)'}
            onChange={handleChange}
          />

          <label className='input__label input__label-file'>
            <span>{imagePath}</span>
            <img className='input__img-file' src={folderIcon} alt='Carregar imagem' />
            <input type='file' name='image' className='input__file' onChange={handleChange} />
          </label>

          <Input name={'email'} value={credentials.email} placeholder={'E-mail'} onChange={handleChange} />

          <Input
            name={'password'}
            type={'password'}
            value={credentials.password}
            placeholder={'Senha'}
            onChange={handleChange}
          />

          <Input
            name={'confirmedPassword'}
            type={'password'}
            value={credentials.confirmedPassword}
            placeholder={'Confirmação da senha'}
            onChange={handleChange}
          />

          <Button type={'submit'} text={'CADASTRAR'} />

          <Link to={SCREENS.LOGIN}>
            <button className='auth__btn-register'>Fazer login</button>
          </Link>
        </form>
      </div>
    </div>
  )
}
