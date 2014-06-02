package cs356.bronconetwork;

public class Mail {

	private String id, from, to, timestamp, msg;
	
	public Mail(String id, String from, String to, String timestamp, String msg) {
		this.id = id;
		this.from = from;
		this.to = to;
		this.timestamp = timestamp;
		this.msg = msg;
	}
	
	public String getId() {
		return id;
	}
	
	public String getFrom() {
		return from;
	}
	
	public String getTo() {
		return to;
	}
	
	public String getTimeStamp() {
		return timestamp;
	}
	
	public String getMsg() {
		return msg;
	}
	
}
