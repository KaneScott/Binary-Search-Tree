package account;

@SuppressWarnings("rawtypes")
public class AccountNode<T extends Comparable<T>> extends Node {
	private float balance = 0;

	@SuppressWarnings("unchecked")
	public AccountNode(T value) {
		super(value);
	}
	public float getBalance() {
		return balance;
	}
	
	public String toString(){
		return "ID: "+value+"\tBalance: "+balance;
	}
	
	public void setBalance(float b){
		balance = b;
	}
	/**
	 * Adds/removes the specified float to the balance held within the object.
	 * @param balance
	 */
	public void addBalance(float b) {
			balance+=b;
	}

}
