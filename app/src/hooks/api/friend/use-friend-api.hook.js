import { useMemo } from 'react'
import { ROUTES } from '../../../constants'
import { useHttp } from '../_base/use-http.hook'
import { useGlobalUser } from '../../../contexts'

export const useFriendApi = () => {
  const [user] = useGlobalUser()

  const httpInstance = useHttp(`${ROUTES.BASE_URL}${ROUTES.BASE_URL_FRIEND}`, { 'X-Auth-Token': user.token })

  const list = async ({ page, size }) => {
    return await httpInstance.get(`?page=${page}&size=${size}`)
  }

  const requests = async () => {
    return await httpInstance.get(`${ROUTES.FRIEND_REQUESTS}`)
  }

  const request = async ({ userId }) => {
    const response = await httpInstance.post(`${ROUTES.FRIEND_REQUEST}/${userId}`)
    return response.data
  }

  const accept = async ({ friendId }) => {
    return await httpInstance.put(`${ROUTES.FRIEND_ACCEPT}/${friendId}`)
  }

  const remove = async ({ userId }) => {
    return await httpInstance.del(`${ROUTES.FRIEND_REMOVE}/${userId}`)
  }

  return useMemo(
    () => ({
      list,
      requests,
      request,
      accept,
      remove,
    }),
    // eslint-disable-next-line react-hooks/exhaustive-deps
    []
  )
}
