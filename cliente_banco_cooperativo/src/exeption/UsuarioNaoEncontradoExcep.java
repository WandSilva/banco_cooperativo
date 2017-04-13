package exeption;

public class UsuarioNaoEncontradoExcep extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsuarioNaoEncontradoExcep(){
		
	}
	public UsuarioNaoEncontradoExcep(String message) {
		super(message);
	}

	public UsuarioNaoEncontradoExcep(Throwable cause) {
		super(cause);
	}

	public UsuarioNaoEncontradoExcep(String message, Throwable cause) {
		super(message, cause);
	}
	
}
