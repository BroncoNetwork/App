package cs356.bronconetwork;

public class Mail {

	private String title;
	private String msg;
	
	public Mail(String title, String msg) {
		this.title = title;
		this.msg = msg;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getMsg() {
		return msg;
	}
	
}
