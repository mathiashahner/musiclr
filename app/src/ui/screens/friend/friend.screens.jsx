import { useEffect, useState } from 'react'
import { useFriendApi } from '../../../hooks'
import { Button, FlatList, FlatListItem } from '../../components'
import InfiniteScroll from 'react-infinite-scroll-component'

const PAGE_SIZE = 3

export const FriendScreen = () => {
  const [hasMore, setHasMore] = useState(true)
  const [currentPage, setCurrentPage] = useState(0)
  const [friendsList, setFriendsList] = useState([])

  const { list, remove } = useFriendApi()

  const getFriendsList = async () => {
    const response = await list({ page: currentPage, size: PAGE_SIZE })

    if (!response.content.length) {
      setHasMore(false)
    } else {
      setFriendsList([...friendsList, ...response.content])
      setCurrentPage(currentPage + 1)
    }
  }

  useEffect(() => {
    getFriendsList()
  }, [])

  const handleRemove = async userId => {
    await remove({ userId })
    window.location.reload()
  }

  return (
    <InfiniteScroll dataLength={friendsList.length} next={getFriendsList} hasMore={hasMore}>
      <h1>{friendsList.length} amigos</h1>

      <FlatList>
        {friendsList.map(friend => (
          <FlatListItem
            key={friend.id}
            name={friend.nome}
            nickname={friend.apelido}
            image={friend.imagemPerfil}
          >
            <Button text={'REMOVER'} red onClick={() => handleRemove(friend.id)} />
          </FlatListItem>
        ))}
      </FlatList>
    </InfiniteScroll>
  )
}
