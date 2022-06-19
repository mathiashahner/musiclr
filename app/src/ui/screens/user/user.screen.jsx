import './user.style.css'
import editIcon from '../../../assets/images/edit.svg'
import defaultImage from '../../../assets/images/default.png'

import { EditScreen } from '..'
import { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import { Button, Post } from '../../components'
import { useGlobalUser } from '../../../contexts'
import InfiniteScroll from 'react-infinite-scroll-component'
import { useFriendApi, usePostApi, useUserApi } from '../../../hooks'

const PAGE_SIZE = 3

export const UserScreen = () => {
  const [hasMore, setHasMore] = useState(true)
  const [postsList, setPostsList] = useState([])
  const [currentPage, setCurrentPage] = useState(0)
  const [selectedUser, setSelectedUser] = useState({})
  const [hasEditScreen, setEditScreen] = useState(false)
  const [globalUser] = useGlobalUser()

  const { request, remove } = useFriendApi()
  const { find, findById } = usePostApi()
  const { detail } = useUserApi()
  const { userId } = useParams()

  const getUserDetails = async () => {
    const user = await detail({ userId })
    setSelectedUser(user)
  }

  const getPostsList = async () => {
    const posts = await find({ userId, page: currentPage, size: PAGE_SIZE })

    if (!posts.content.length) {
      setHasMore(false)
    } else {
      setPostsList([...postsList, ...posts.content])
      setCurrentPage(currentPage + 1)
    }
  }

  useEffect(() => {
    getUserDetails()
    getPostsList()
  }, [])

  const handleRequest = async userId => {
    await request({ userId })
    await getUserDetails()
  }

  const handleDelete = async userId => {
    await remove({ userId })
    await getUserDetails()
  }

  const updatePost = async postId => {
    const newPost = await findById({ postId })
    const newPostsList = postsList.map(post => (post.id === newPost.id ? newPost : post))

    setPostsList(newPostsList)
  }

  const handleEditScreen = () => {
    setEditScreen(!hasEditScreen)
  }

  return (
    <div className='user__container'>
      <div className='user__details'>
        <div className='post__user'>
          {!!selectedUser.imagemPerfil ? (
            <img className='user__img' src={atob(selectedUser.imagemPerfil)} alt={selectedUser.nome} />
          ) : (
            <img className='user__img' src={defaultImage} alt={selectedUser.nome} />
          )}

          <div>
            <p className='user__title'>{selectedUser.apelido ? selectedUser.apelido : selectedUser.nome}</p>
            <p className='user__text'>{!!selectedUser.apelido && selectedUser.nome}</p>
            <p className='user__text'>{new Date(selectedUser.dataNascimento).toLocaleDateString()}</p>
          </div>
        </div>

        {selectedUser.id !== globalUser.user.id &&
          (selectedUser.amigo ? (
            <Button text={'DESFAZER AMIZADE'} red onClick={() => handleDelete(selectedUser.id)} />
          ) : (
            <Button text={'SOLICITAR AMIZADE'} onClick={() => handleRequest(selectedUser.id)} />
          ))}

        {selectedUser.id === globalUser.user.id && (
          <button className='user__edit-btn' onClick={handleEditScreen}>
            <img className='user__edit-img' src={editIcon} />
          </button>
        )}
      </div>

      <InfiniteScroll dataLength={postsList.length} next={getPostsList} hasMore={hasMore}>
        <div className='home__posts'>
          {postsList.map(post => (
            <Post key={post.id} post={post} updatePost={updatePost} />
          ))}
        </div>
      </InfiniteScroll>

      {!!hasEditScreen && <EditScreen handleClose={handleEditScreen} />}
    </div>
  )
}
