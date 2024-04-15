package latihan;

public class Human extends Helper implements Task{
	protected String name;
	
	public Human(){ 
		
		this.stamina=2;
		this.name="Human";
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
