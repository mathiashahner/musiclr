import { useEffect, useState } from 'react'
import { useFriendApi, useUserApi } from '../../../hooks'
import { useNavigate, useParams } from 'react-router-dom'
import { Button, FlatList, FlatListItem } from '../../components'
import InfiniteScroll from 'react-infinite-scroll-component'
import { SCREENS } from '../../../constants'
import { useGlobalUser } from '../../../contexts'

const PAGE_SIZE = 3

export const SearchScreen = () => {
  const [hasMore, setHasMore] = useState(true)
  const [usersList, setUsersList] = useState([])
  const [currentPage, setCurrentPage] = useState(0)
  const [globalUser] = useGlobalUser()

  const { find } = useUserApi()
  const navigate = useNavigate()
  const { request } = useFriendApi()
  const { textToSearch } = useParams()

  const getUsersList = async () => {
    const response = await find({ text: textToSearch, page: currentPage, size: PAGE_SIZE })

    if (!response.content.length) {
      setHasMore(false)
    } else {
      setUsersList([...usersList, ...response.content])
      setCurrentPage(currentPage + 1)
    }
  }

  useEffect(() => {
    getUsersList()
  }, [])

  const handleVisit = async userId => {
    navigate(`${SCREENS.USER}/${userId}`)
  }

  const handleRequest = async userId => {
    await request({ userId })
    await getUsersList()
  }

  return (
    <InfiniteScroll dataLength={usersList.length} next={getUsersList} hasMore={hasMore}>
      <h1>Resultados da busca: {textToSearch}</h1>

      <FlatList>
        {usersList.map(user => (
          <FlatListItem key={user.id} name={user.nome} nickname={user.apelido} image={user.imagemPerfil}>
            {user.id !== globalUser.user.id && !user.amigo && (
              <Button text={'SOLICITAR'} onClick={() => handleRequest(user.id)} />
            )}

            <Button text={'VISITAR'} dark onClick={() => handleVisit(user.id)} />
          </FlatListItem>
        ))}
      </FlatList>
    </InfiniteScroll>
  )
}
