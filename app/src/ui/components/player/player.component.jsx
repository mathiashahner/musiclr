import './player.style.css'
import playIcon from '../../../assets/images/play.svg'
import playNext from '../../../assets/images/play-next.svg'
import playPrevious from '../../../assets/images/play-previous.svg'

import { useGlobalPlayer } from '../../../contexts'
import { convertDurationToTimeString } from '../../../core'

export const Player = () => {
  const [globalPlayer] = useGlobalPlayer()

  return (
    <>
      {!!globalPlayer && (
        <div className='player'>
          <div className='player__btns'>
            <button className='player__btn'>
              <img src={playPrevious} alt='Anterior' />
            </button>

            <button className='player__btn-play'>
              <img src={playIcon} alt='Play' />
            </button>

            <button className='player__btn'>
              <img src={playNext} alt='Próxima' />
            </button>
          </div>

          <div className='player__progress'>
            <p>{convertDurationToTimeString(0)}</p>
            <div className='player__slider'>
              <div className='player__slider-empty' />
            </div>
            <p>{convertDurationToTimeString(0)}</p>
          </div>
        </div>
      )}
    </>
  )
}
