import { useAxios } from './use-axios.hook'
import { SCREENS } from '../../../constants'
import { useNavigate } from 'react-router-dom'
import { DEFAULT_LOCAL_STORAGE, useGlobalError, useGlobalUser } from '../../../contexts'

export const useHttp = (baseURL, headers) => {
  const [, setUser] = useGlobalUser()
  const [, setError] = useGlobalError()
  const instance = useAxios(baseURL, headers)

  const navigate = useNavigate()

  const formatErrors = error => {
    const errorTitle = <p>{error.mensagem}</p>

    if (error.erros?.length) {
      const errorFields = error.erros.map((erro, index) => (
        <p key={index}>{`O campo ${erro.campo} ${erro.mensagem}`}</p>
      ))

      setError({ message: [errorTitle, ...errorFields] })
    } else {
      setError({ message: errorTitle })
    }
  }

  const get = async url => {
    try {
      const response = await instance.get(url)
      return response.data
    } catch (error) {
      if (error.response.status === 401) {
        setUser(DEFAULT_LOCAL_STORAGE)
        setError({ message: <p>Sua sessão expirou, faça login novamente.</p> })
        navigate(SCREENS.LOGIN)
      } else {
        formatErrors(error.response?.data)
      }
    }
  }

  const post = async (url, data, headers) => {
    try {
      return await instance.post(url, data, headers)
    } catch (error) {
      if (error.response.status === 401) {
        setError({ message: <p>E-mail ou senha inválidos.</p> })
      } else {
        formatErrors(error.response?.data)
      }
    }
  }

  const put = async (url, data) => {
    try {
      const response = await instance.put(url, data)
      return response.data
    } catch (error) {
      if (error.response.status === 401) {
        setUser(DEFAULT_LOCAL_STORAGE)
        setError({ message: <p>Sua sessão expirou, faça login novamente.</p> })
        navigate(SCREENS.LOGIN)
      } else {
        formatErrors(error.response?.data)
      }
    }
  }

  const del = async url => {
    try {
      const response = await instance.delete(url)
      return response.data
    } catch (error) {
      if (error.response.status === 401) {
        setUser(DEFAULT_LOCAL_STORAGE)
        setError({ message: <p>Sua sessão expirou, faça login novamente.</p> })
        navigate(SCREENS.LOGIN)
      } else {
        formatErrors(error.response?.data)
      }
    }
  }

  return {
    get,
    post,
    put,
    del,
  }
}
