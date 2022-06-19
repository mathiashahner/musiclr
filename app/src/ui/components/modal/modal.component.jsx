import './modal.style.css'

import { Button } from '../../components'
import { useGlobalError } from '../../../contexts'

export const Modal = ({ message, buttonMessage, children }) => {
  const [, setError] = useGlobalError()

  const handleCloseInterno = () => {
    setError(null)
  }

  return (
    <div className='modal'>
      <div className='modal__content'>
        {message ? (
          <>
            {message}
            <Button text={buttonMessage} onClick={handleCloseInterno} />
          </>
        ) : (
          <>{children}</>
        )}
      </div>
    </div>
  )
}

Modal.defaultProps = {
  message: '',
  buttonMessage: 'OK',
}
