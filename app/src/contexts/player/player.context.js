import createGlobalState from 'react-create-global-state'

const [useGlobalPlayer, GlobalPlayerProvider] = createGlobalState(null)

export { useGlobalPlayer, GlobalPlayerProvider }
