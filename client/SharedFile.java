package client;

public class SharedFile {
	private String name;
	private int id;
	private long size;
	
	SharedFile(String name, int id, long size){
		this.name = name;
		this.id = id;
		this.size = size;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
