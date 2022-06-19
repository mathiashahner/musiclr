import './edit.style.css'
import closeIcon from '../../../assets/images/close.svg'
import folderIcon from '../../../assets/images/folder.png'

import { useState } from 'react'
import { useUserApi } from '../../../hooks'
import { useGlobalUser } from '../../../contexts'
import { Button, Input, Modal } from '../../components'

export const EditScreen = ({ handleClose }) => {
  const [imagePath, setImagePath] = useState('Imagem de perfil (opcional)')
  const [globalUser, setGlobalUser] = useGlobalUser()
  const { edit, detail } = useUserApi()

  const [credentials, setCredentials] = useState({
    name: globalUser.user.nome,
    nickname: globalUser.user.apelido,
  })

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

  const onEditSubmit = async event => {
    event.preventDefault()

    await edit({
      name: credentials.name,
      nickname: credentials.nickname,
      image: credentials.image,
    })

    const user = await detail({ userId: globalUser.user.id })

    setGlobalUser({ ...globalUser, user })
    // handleClose()
    window.location.reload()
  }

  return (
    <Modal>
      <button className='modal__close' onClick={handleClose}>
        <img className='modal__close-img' src={closeIcon} alt='Voltar' />
      </button>

      <form className='auth__form' onSubmit={onEditSubmit}>
        <h1 className='new-post__title'>Editar perfil</h1>

        <Input name={'name'} value={credentials.name} placeholder={'Nome completo'} onChange={handleChange} />

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

        <Button type={'submit'} text={'EDITAR'} />
      </form>
    </Modal>
  )
}
