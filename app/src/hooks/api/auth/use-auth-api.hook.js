import { useMemo } from 'react'
import { ROUTES } from '../../../constants'
import { useHttp } from '../_base/use-http.hook'
import { DEFAULT_LOCAL_STORAGE, useGlobalUser } from '../../../contexts'

export const useAuthApi = () => {
  const [user, setUser] = useGlobalUser()

  const httpInstance = useHttp(ROUTES.BASE_URL)

  const login = async ({ username, password }) => {
    const response = await httpInstance.post(`${ROUTES.BASE_URL_LOGIN}`, null, {
      auth: { username, password },
    })

    if (response) {
      setUser({
        token: response.headers['x-auth-token'],
        user: {
          id: response.data.usuarioId,
        },
      })
    }
  }

  const logout = async () => {
    await httpInstance.post(`${ROUTES.BASE_URL_LOGOUT}`, {}, { 'X-Auth-Token': user.token })
    setUser(DEFAULT_LOCAL_STORAGE)
  }

  const create = async ({ name, birthDate, image, nickname, email, password, confirmedPassword }) => {
    const response = await httpInstance.post(ROUTES.BASE_URL_USER, {
      nome: name,
      dataNascimento: birthDate,
      imagemPerfil: image,
      apelido: nickname,
      usuario: {
        email: email,
        senha: password,
        confirmacaoSenha: confirmedPassword,
      },
    })

    return response?.data
  }

  return useMemo(
    () => ({
      login,
      logout,
      create,
    }),
    // eslint-disable-next-line react-hooks/exhaustive-deps
    []
  )
}
