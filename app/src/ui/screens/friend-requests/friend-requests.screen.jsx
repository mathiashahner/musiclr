import './friend-requests.style.css'
import closeIcon from '../../../assets/images/close.svg'
import defaultImage from '../../../assets/images/default.png'

import { Modal } from '../../components'
import { useEffect, useState } from 'react'
import { useFriendApi } from '../../../hooks'

export const FriendRequestsScreen = ({ handleClose }) => {
  const [friendRequests, setFriendRequests] = useState([])
  const { requests, accept, remove } = useFriendApi()

  const getFriendRequests = async () => {
    const response = await requests()
    setFriendRequests(response)
  }

  useEffect(() => {
    getFriendRequests()
  }, [])

  const handleAccept = async friendId => {
    await accept({ friendId })
    await getFriendRequests()
  }

  const handleDelete = async friendId => {
    await remove({ friendId })
    await getFriendRequests()
  }

  return (
    <Modal>
      <button className='modal__close' onClick={handleClose}>
        <img className='modal__close-img' src={closeIcon} alt='Voltar' />
      </button>

      {friendRequests.length ? (
        <ul className='request__list'>
          {friendRequests.map(request => (
            <li key={request.id} className='request__item'>
              <div className='post__user'>
                {!!request.solicitante.imagemPerfil ? (
                  <img
                    className='post__img-user request__img-user'
                    src={atob(request.solicitante.imagemPerfil)}
                    alt={request.solicitante.nome}
                  />
                ) : (
                  <img
                    className='post__img-user request__img-user'
                    src={defaultImage}
                    alt={request.solicitante.nome}
                  />
                )}

                <div>
                  <p className='request__user-text'>
                    {request.solicitante.apelido ? request.solicitante.apelido : request.solicitante.nome}
                  </p>
                  <p className='request__user-info'>
                    {!!request.solicitante.apelido && request.solicitante.nome}
                  </p>
                </div>
              </div>

              <div className='request__control'>
                <div>
                  <button className='request_btn' onClick={() => handleAccept(request.id)}>
                    ACEITAR
                  </button>

                  <button
                    className='request_btn request__btn-x'
                    onClick={() => handleDelete(request.solicitante.id)}
                  >
                    X
                  </button>
                </div>

                <p>Enviada em: {new Date(request.dataSolicitacao).toLocaleDateString()}</p>
              </div>
            </li>
          ))}
        </ul>
      ) : (
        <p className='request__empty'>Nenhuma solicitação pendente!</p>
      )}
    </Modal>
  )
}
