import createGlobalState from 'react-create-global-state'

const [useGlobalError, GlobalErrorProvider] = createGlobalState(null)

export { useGlobalError, GlobalErrorProvider }
