package server;
public class ServerFile {
	
	private String name;
	private String location;
	private int id;
	private long size;
	public ServerFile(String name, String location, int id, long size) {
		this.name = name;
		this.setLocation(location);
		this.setId(id);
		this.size = size;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
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


	public void setSize(int size) {
		this.size = size;
	}
}
