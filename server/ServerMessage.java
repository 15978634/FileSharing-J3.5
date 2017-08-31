package server;

public class ServerMessage {

	private int code;
	private String message;
	private int id;
	private long size;
	public ServerMessage(int code, String message, int id, long size) {
		this.setCode(code);
		this.setMessage(message);
		this.setId(id);
		this.setSize(size);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}
	
}
