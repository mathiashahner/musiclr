import './post.style.css'
import playIcon from '../../../assets/images/play.svg'
import likeIcon from '../../../assets/images/like.png'
import publicIcon from '../../../assets/images/public.png'
import padlockIcon from '../../../assets/images/padlock.png'
import unlikeIcon from '../../../assets/images/unlike.png'
import commentIcon from '../../../assets/images/comment.png'
import commentAction from '../../../assets/images/comment2.png'
import defaultImage from '../../../assets/images/default.png'

import { useState } from 'react'
import { Comment, CommentList, Input } from '..'
import { usePostApi } from '../../../hooks'
import { useGlobalPlayer, useGlobalUser } from '../../../contexts'

export const Post = ({ post, updatePost }) => {
  const [globalUser] = useGlobalUser()
  const [commentText, setCommentText] = useState('')
  const [, setGlobalPlayer] = useGlobalPlayer()

  const { comment, like, unlike, edit } = usePostApi()

  const handleChange = event => {
    const { value } = event.target
    setCommentText(value)
  }

  const handleComment = async postId => {
    setCommentText('')
    await comment({ postId, text: commentText })
    await updatePost(postId)
  }

  const handleLike = async post => {
    if (post.curtida) {
      await unlike({ postId: post.id })
    } else {
      await like({ postId: post.id })
    }
    await updatePost(post.id)
  }

  const handleEdit = async post => {
    await edit({ postId: post.id, isPublic: !post.publica })
    await updatePost(post.id)
  }

  const handlePlay = audio => {
    setGlobalPlayer(audio)
  }

  return (
    <div className='post__container'>
      <div className='post__content'>
        <div className='post__infos'>
          <div className='post__user'>
            {!!post.pessoa.imagemPerfil ? (
              <img className='post__img-user' src={atob(post.pessoa.imagemPerfil)} alt={post.pessoa.nome} />
            ) : (
              <img className='post__img-user' src={defaultImage} alt={post.pessoa.nome} />
            )}

            <div>
              <p className='post__user-name'>
                {post.pessoa.apelido ? post.pessoa.apelido : post.pessoa.nome}
              </p>
              <p className='post__date'>{new Date(post.dataCriacao).toLocaleDateString()}</p>
            </div>
          </div>

          <div className='post__reactions'>
            <button className='post__reactions-btn' onClick={() => handleLike(post)}>
              {post.curtida ? (
                <img className='post__like-img' src={unlikeIcon} alt='Descurtir' />
              ) : (
                <img className='post__like-img' src={likeIcon} alt='Curtir' />
              )}
            </button>
            <p>{post.numCurtidas > 0 && post.numCurtidas}</p>

            <img className='post__comment-img' src={commentIcon} alt='Comentar' />
            <p>{post.numComentarios > 0 && post.numComentarios}</p>
          </div>
        </div>

        <img className='post__img' src={atob(post.imagem)} alt='Publicação' />

        <div className='post__desciption'>
          {!!post.audio && (
            <button className='post__play-btn' onClick={() => handlePlay(post.audio)}>
              <img className='post__play-img' src={playIcon} alt='Tocar' />
            </button>
          )}

          <div>
            {!!post.titulo && <h1 className='post__desciption-title'>{post.titulo}</h1>}
            <p className='post__desciption-text'>{post.descricao}</p>
          </div>

          {post.pessoa.id === globalUser.user.id && (
            <button className='post__edit' onClick={() => handleEdit(post)}>
              <img src={post.publica ? publicIcon : padlockIcon} alt='Editar publicação' />
            </button>
          )}
        </div>
      </div>

      <div className='post__comments'>
        {post.comentarios.length ? (
          <CommentList>
            {post.comentarios.map(comment => (
              <Comment
                key={comment.id}
                user={comment.pessoa}
                text={comment.texto}
                date={comment.dataCriacao}
              />
            ))}
          </CommentList>
        ) : (
          <p>Seja o primeiro a comentar!</p>
        )}

        <Input
          name={'texto'}
          value={commentText}
          image={commentAction}
          onChange={handleChange}
          onClick={() => handleComment(post.id)}
          placeholder={'Comentar'}
          isSmall
        />
      </div>
    </div>
  )
}
