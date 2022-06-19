import './input.style.css'

export const Input = ({ type, image, name, value, placeholder, isSmall, onChange, onClick }) => {
  return (
    <label className={`input__label ${isSmall && 'input__label-small'}`}>
      <input
        className='input'
        type={type}
        name={name}
        value={value}
        onChange={onChange}
        placeholder={placeholder}
      />

      {!!image && (
        <button className={`input__btn ${isSmall && 'input__btn-small'}`} onClick={onClick}>
          <img className='input__img' src={image} alt={name} />
        </button>
      )}
    </label>
  )
}

Input.defaultProps = {
  image: '',
  type: 'text',
}
