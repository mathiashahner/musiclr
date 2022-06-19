import createGlobalState from 'react-create-global-state'

const USER_LOCAL_STORAGE_KEY = 'musiclr-local-storage-key'
export const DEFAULT_LOCAL_STORAGE = { token: null, user: null }

const getLocalStorage = (localStorageKey, defaultValue) => {
  const localStorageValue = localStorage.getItem(localStorageKey)
  return localStorageValue ? JSON.parse(localStorageValue) : defaultValue
}

const setLocalStorage = (localStorageKey, value) => {
  localStorage.setItem(localStorageKey, JSON.stringify(value))
}

const initialUser = getLocalStorage(USER_LOCAL_STORAGE_KEY, DEFAULT_LOCAL_STORAGE)
const [_useGlobalUser, GlobalUserProvider] = createGlobalState(initialUser)

const useGlobalUser = () => {
  const [globalUser, _setGlobalUser] = _useGlobalUser()

  const setState = value => {
    setLocalStorage(USER_LOCAL_STORAGE_KEY, value)
    _setGlobalUser(value)
  }

  return [globalUser, setState]
}

export { useGlobalUser, GlobalUserProvider }
