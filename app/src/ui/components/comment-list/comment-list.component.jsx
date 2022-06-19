import './comment-list.style.css'
import defaultImage from '../../../assets/images/default.png'

export const Comment = ({ user, text, date }) => {
  return (
    <li className='comment'>
      {!!user.imagemPerfil ? (
        <img className='post__img-user' src={atob(user.imagemPerfil)} alt={user.nome} />
      ) : (
        <img className='post__img-user' src={defaultImage} alt={user.nome} />
      )}

      <div>
        <p className='comment-title'>{user.apelido ? user.apelido : user.nome}</p>

        <p className='comment-text'>{text}</p>
      </div>
    </li>
  )
}

export const CommentList = ({ children }) => {
  return <ul className='comment-list'>{children}</ul>
}
