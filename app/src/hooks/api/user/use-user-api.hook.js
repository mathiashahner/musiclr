import { useMemo } from 'react'
import { ROUTES } from '../../../constants'
import { useHttp } from '../_base/use-http.hook'
import { useGlobalUser } from '../../../contexts'

export const useUserApi = () => {
  const [user] = useGlobalUser()

  const httpInstance = useHttp(`${ROUTES.BASE_URL}${ROUTES.BASE_URL_USER}`, { 'X-Auth-Token': user.token })

  const detail = async ({ userId }) => {
    return await httpInstance.get(`${ROUTES.USER_DETAIL}/${userId}`)
  }

  const find = async ({ text, page, size }) => {
    return await httpInstance.get(`?texto=${text}&page=${page}&size=${size}`)
  }

  const edit = async ({ name, nickname, image }) => {
    return await httpInstance.put('', { nome: name, apelido: nickname, imagemPerfil: image })
  }

  return useMemo(
    () => ({
      detail,
      find,
      edit,
    }),
    // eslint-disable-next-line react-hooks/exhaustive-deps
    []
  )
}
