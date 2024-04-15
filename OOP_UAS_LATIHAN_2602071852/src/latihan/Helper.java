package latihan;

public abstract class Helper implements Task{
    protected int stamina;
    protected String type= "class";
    public void performTask(String name,String task) {
	}
	public int getStamina() {
		return stamina;
	}
	public void setStamina(int stamina) {
		this.stamina = stamina;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}public String getName() {
        return "";
    }
}
