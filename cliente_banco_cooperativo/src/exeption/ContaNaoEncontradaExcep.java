package exeption;

public class ContaNaoEncontradaExcep extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ContaNaoEncontradaExcep(){
		
	}
	public ContaNaoEncontradaExcep(String message){
		super(message);
	}

}
