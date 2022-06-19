import './home.style.css'

import { Post } from '../../components'
import { useEffect, useState } from 'react'
import { useGlobalUser } from '../../../contexts'
import { usePostApi, useUserApi } from '../../../hooks'
import InfiniteScroll from 'react-infinite-scroll-component'

const PAGE_SIZE = 3

export const HomeScreen = () => {
  const [hasMore, setHasMore] = useState(true)
  const [postsList, setPostsList] = useState([])
  const [currentPage, setCurrentPage] = useState(0)
  const [globalUser, setGlobalUser] = useGlobalUser()

  const { list, findById } = usePostApi()
  const { detail } = useUserApi()

  const getUserDetails = async () => {
    const user = await detail({ userId: globalUser.user?.id })
    setGlobalUser({ ...globalUser, user })
  }

  const getPostsList = async () => {
    const posts = await list({ page: currentPage, size: PAGE_SIZE })

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

  const updatePost = async postId => {
    const newPost = await findById({ postId })
    const newPostsList = postsList.map(post => (post.id === newPost.id ? newPost : post))

    setPostsList(newPostsList)
  }

  return (
    <InfiniteScroll dataLength={postsList.length} next={getPostsList} hasMore={hasMore}>
      <div className='home__posts'>
        {postsList.map(post => (
          <Post key={post.id} post={post} updatePost={updatePost} />
        ))}
      </div>
    </InfiniteScroll>
  )
}
