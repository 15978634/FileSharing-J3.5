package client;

public class SharedFile {
	private String name;
	private int id;
	private int size;
	
	SharedFile(String name, int id, int size){
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
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}


}
