import './flat-list.style.css'
import defaultImage from '../../../assets/images/default.png'

export const FlatListItem = ({ name, nickname, image, children }) => {
  return (
    <li className='flat-list__item'>
      <div className='post__user'>
        {!!image ? (
          <img className='flat-list__img' src={atob(image)} alt={name} />
        ) : (
          <img className='flat-list__img' src={defaultImage} alt={name} />
        )}

        <div>
          <p className='flat-list__text'>{nickname ? nickname : name}</p>
          <p className='flat-list__info'>{!!nickname && name}</p>
        </div>
      </div>

      <div className='flat-list__btn'>{children}</div>
    </li>
  )
}

export const FlatList = ({ children }) => {
  return <ul className='flat-list'>{children}</ul>
}
