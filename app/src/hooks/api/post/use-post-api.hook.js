import { useMemo } from 'react'
import { ROUTES } from '../../../constants'
import { useHttp } from '../_base/use-http.hook'
import { useGlobalUser } from '../../../contexts'

export const usePostApi = () => {
  const [user] = useGlobalUser()

  const httpInstance = useHttp(`${ROUTES.BASE_URL}${ROUTES.BASE_URL_POST}`, { 'X-Auth-Token': user.token })

  const create = async ({ description, image, isPublic, title, audio }) => {
    const response = await httpInstance.post('', {
      descricao: description,
      imagem: image,
      isPublica: isPublic,
      titulo: title,
      audio: audio,
    })

    return response.data
  }

  const list = async ({ page, size }) => {
    return await httpInstance.get(`?page=${page}&size=${size}`)
  }

  const findById = async ({ postId }) => {
    return await httpInstance.get(`/${postId}`)
  }

  const find = async ({ userId, page, size }) => {
    return await httpInstance.get(`${ROUTES.POST_FIND}/${userId}?page=${page}&size=${size}`)
  }

  const edit = async ({ postId, isPublic }) => {
    return await httpInstance.put(`/${postId}${ROUTES.POST_EDIT}`, { isPublica: isPublic })
  }

  const like = async ({ postId }) => {
    const response = await httpInstance.post(`/${postId}${ROUTES.POST_LIKE}`)
    return response.data
  }

  const unlike = async ({ postId }) => {
    return await httpInstance.del(`/${postId}${ROUTES.POST_UNLIKE}`)
  }

  const comment = async ({ postId, text }) => {
    const response = await httpInstance.post(`/${postId}${ROUTES.POST_COMMENT}`, { texto: text })
    return response.data
  }

  return useMemo(
    () => ({
      create,
      list,
      findById,
      find,
      edit,
      like,
      unlike,
      comment,
    }),
    // eslint-disable-next-line react-hooks/exhaustive-deps
    []
  )
}
