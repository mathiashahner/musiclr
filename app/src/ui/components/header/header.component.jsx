import './header.style.css'
import addIcon from '../../../assets/images/add.png'
import logoText from '../../../assets/images/logoText.png'
import searchIcon from '../../../assets/images/search.png'
import defaultImage from '../../../assets/images/default.png'
import notificationIcon from '../../../assets/images/notification.png'

import { useState } from 'react'
import { SCREENS } from '../../../constants'
import { useNavigate } from 'react-router-dom'
import { Input } from '../input/input.component'
import { FriendRequestsScreen, NewPostScreen } from '../../screens'
import { DEFAULT_LOCAL_STORAGE, useGlobalUser } from '../../../contexts'

export const Header = () => {
  const [globalUser, setGlobalUser] = useGlobalUser()
  const [textToSearch, setTextToSearch] = useState('')
  const [hasNewPostScreen, setNewPostScreen] = useState(false)
  const [hasRequestsScreen, setRequestsScreen] = useState(false)

  const navigate = useNavigate()

  const handleChange = event => {
    const { value } = event.target
    setTextToSearch(value)
  }

  const handleSearchClick = () => {
    if (textToSearch.length) {
      navigate(`${SCREENS.SEARCH}/${textToSearch}`)
      window.location.reload()
    }
  }

  const handleRequestsScreen = () => {
    setRequestsScreen(!hasRequestsScreen)
  }

  const handleNewPostScreen = () => {
    setNewPostScreen(!hasNewPostScreen)
  }

  const handleHome = () => {
    navigate(SCREENS.HOME)
  }

  const handleMyProfile = () => {
    navigate(`${SCREENS.USER}/${globalUser.user.id}`)
  }

  const handleMyFriends = () => {
    navigate(SCREENS.FRIENDS)
  }

  const handleLogout = () => {
    setGlobalUser(DEFAULT_LOCAL_STORAGE)
    navigate(SCREENS.LOGIN)
  }

  return (
    <>
      {!!globalUser.user && (
        <header className='header'>
          <div className='header__container'>
            <button className='header__img-btn' onClick={handleHome}>
              <img className='header__img' src={logoText} alt='Musiclr' />
            </button>

            <Input
              image={searchIcon}
              name={'search'}
              onChange={handleChange}
              value={textToSearch}
              placeholder={'Buscar'}
              onClick={handleSearchClick}
            />

            <div className='header__user'>
              <button className='header__icon-btn dropdown' onClick={handleRequestsScreen}>
                <img className='header__icon' src={notificationIcon} alt='Notificações' />

                <div className='dropdown__content'>
                  <p className='dropdown__text'>Solicitações de amizade</p>
                </div>
              </button>

              <button className='header__icon-btn dropdown' onClick={handleNewPostScreen}>
                <img className='header__icon' src={addIcon} alt='Criar publicação' />

                <div className='dropdown__content'>
                  <p className='dropdown__text'>Publicar</p>
                </div>
              </button>

              <div className='dropdown'>
                {!!globalUser.user.imagemPerfil ? (
                  <img
                    className='header__img-user'
                    src={atob(globalUser.user.imagemPerfil)}
                    alt={globalUser.user.nome || 'Usuário'}
                  />
                ) : (
                  <img
                    className='header__img-user'
                    src={defaultImage}
                    alt={globalUser.user.nome || 'Usuário'}
                  />
                )}

                <div className='dropdown__content dropdown__content-user'>
                  <p className='dropdown__title'>{globalUser.user.nome}</p>
                  <p>{globalUser.user.apelido}</p>
                  <p className='dropdown__info'>
                    {new Date(globalUser.user.dataNascimento).toLocaleDateString()}
                  </p>

                  <button className='dropdown__btn' onClick={handleMyProfile}>
                    Meu perfil
                  </button>
                  <button className='dropdown__btn' onClick={handleMyFriends}>
                    Meus amigos
                  </button>
                  <button className='dropdown__btn' onClick={handleLogout}>
                    Sair
                  </button>
                </div>
              </div>
            </div>
          </div>

          {!!hasRequestsScreen && <FriendRequestsScreen handleClose={handleRequestsScreen} />}
          {!!hasNewPostScreen && <NewPostScreen handleClose={handleNewPostScreen} />}
        </header>
      )}
    </>
  )
}
