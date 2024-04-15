package latihan;

public abstract class TaskPerformer extends Helper implements Task {
    protected String name;

    public TaskPerformer(String name, int stamina) {
        this.name = name;
        this.stamina = stamina;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void performTask(String name, String task) {
        System.out.println(name + " finished a " + task + " task");
    }
}
