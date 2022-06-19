import './new-post.style.css'
import closeIcon from '../../../assets/images/close.svg'
import audioIcon from '../../../assets/images/audio.png'
import folderIcon from '../../../assets/images/folder.png'

import { useState } from 'react'
import { usePostApi } from '../../../hooks'
import { Button, Input, Modal } from '../../components'

const DEFAULT_POST = {
  description: '',
  image: '',
  isPublic: true,
}

export const NewPostScreen = ({ handleClose }) => {
  const [inputValues, setInputValues] = useState(DEFAULT_POST)
  const [nameFiles, setNameFiles] = useState({ image: 'Imagem', audio: 'Áudio (opcional)' })

  const { create } = usePostApi()

  const handleChange = event => {
    const { name, value } = event.target

    if (name === 'image' || name === 'audio') {
      const file = event.target.files[0]
      const reader = new FileReader()

      reader.addEventListener('load', loadEvent => {
        setInputValues({ ...inputValues, [name]: btoa(loadEvent.target.result) })
      })

      reader.readAsDataURL(file)
      setNameFiles({ ...nameFiles, [name]: file.name })
    } else {
      setInputValues({ ...inputValues, [name]: value })
    }
  }

  const onCreateSubmit = async event => {
    event.preventDefault()
    await create({
      description: inputValues.description,
      image: inputValues.image,
      isPublic: inputValues.isPublic,
      title: inputValues.title,
      audio: inputValues.audio,
    })

    handleClose()
    window.location.reload()
  }

  return (
    <Modal>
      <button className='modal__close' onClick={handleClose}>
        <img className='modal__close-img' src={closeIcon} alt='Voltar' />
      </button>

      <form className='new-post__form' onSubmit={onCreateSubmit}>
        <h1 className='new-post__title'>Criar publicação</h1>

        <Input
          name={'description'}
          value={inputValues.description}
          placeholder={'Descrição'}
          onChange={handleChange}
        />

        <label className='input__label input__label-file'>
          <span>{nameFiles.image}</span>
          <img className='input__img-file' src={folderIcon} alt='Carregar imagem' />
          <input type='file' name='image' className='input__file' onChange={handleChange} />
        </label>

        <select className='input__label' value={inputValues.isPublic} onChange={handleChange} name='isPublic'>
          <option value={null} disabled>
            Quem pode ver esta publicação?
          </option>

          <option value={true}>Todos</option>
          <option value={false}>Apenas amigos</option>
        </select>

        <Input
          name={'title'}
          value={inputValues.title}
          placeholder={'Título (opcional)'}
          onChange={handleChange}
        />

        <label className='input__label input__label-file'>
          <span>{nameFiles.audio}</span>
          <img className='input__img-file' src={audioIcon} alt='Carregar música' />
          <input type='file' name='audio' className='input__file' onChange={handleChange} />
        </label>

        <Button type={'submit'} text={'PUBLICAR'} />
      </form>
    </Modal>
  )
}
