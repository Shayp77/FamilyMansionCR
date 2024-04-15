package latihan;

public class Robot extends Helper implements Task{
	protected String name;
	public Robot(String name){
		this.stamina=3;
		this.name=name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public void performTask(String name, String task) {
		System.out.println(name+" finished a "+task+" task");
		
	}
}
