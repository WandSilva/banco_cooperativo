package exeption;

public class SaldoInsuficienteExcep extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SaldoInsuficienteExcep(){
		
	}
	
	public SaldoInsuficienteExcep(String message){
		super(message);
	}
}
