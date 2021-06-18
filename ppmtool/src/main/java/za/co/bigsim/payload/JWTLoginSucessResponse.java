package za.co.bigsim.payload;

public class JWTLoginSucessResponse {
	
	private boolean succes;
	private String token;
	public JWTLoginSucessResponse(boolean succes, String token) {
		super();
		this.succes = succes;
		this.token = token;
	}
	public boolean isSucces() {
		return succes;
	}
	public void setSucces(boolean succes) {
		this.succes = succes;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	@Override
	public String toString() {
		return "JWTLoginSucessResponse [succes=" + succes + ", token=" + token + "]";
	}
}
