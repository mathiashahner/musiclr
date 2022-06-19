import './button.style.css'

export const Button = ({ type, text, dark, red, onClick }) => {
  return (
    <button
      type={type}
      className={`button ${dark && 'button__dark'} ${red && 'button__red'}`}
      onClick={onClick}
    >
      {text}
    </button>
  )
}

Button.defaultProps = {
  type: 'button',
}
