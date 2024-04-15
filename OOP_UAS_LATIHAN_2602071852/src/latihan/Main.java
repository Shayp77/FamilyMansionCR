package latihan;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
	ArrayList<Helper> helper=new ArrayList<>();
	Scanner scan = new Scanner(System.in);
	int moves=10;
	int cooktasks = (int)(Math.random() * 5) + 2;
	int cleantasks=8-cooktasks;
	
	int choice;
	boolean flag=false;
	int cookflag=0;
	int cleanflag=0;
	int humanflag=2;
	int robocount=0;
    public static void main(String[] args) {
    	
    	new Main();
    }
    public Main(){
    	int task;
    	helper.add(new Human());
    	helper.add(new Human());
    	do {
    		
    		task=cooktasks+cleantasks;
    		if(moves==0) {
    			System.out.println("The game is over you lose!");
    			break;
    		}else if(task==0) {
    			System.out.println("The game is over you win!");
    			System.out.println("Congratulations!!");
    			break;
    		}
    		System.out.println("Remaining Moves: "+moves);
        	System.out.println("Cooking tasks: "+cooktasks);
        	System.out.println("Cleaning tasks: "+cleantasks);
        	System.out.println("----------------------------------");
        	System.out.println("Helper type\t\tStamina");
        	for(int i=0;i<helper.size();i++) {
        		if(helper.get(i).getName().equals("CleanerRobot") || helper.get(i).getName().equals("ChefRobot") ) {
        			System.out.println(helper.get(i).getType()+"  "+helper.get(i).getName()+"\t"+helper.get(i).getStamina());
        		}else {
        			System.out.println(helper.get(i).getType()+"  "+helper.get(i).getName()+"\t\t"+helper.get(i).getStamina());
        		}
        	}
        	System.out.println("----------------------------------");
        	System.out.println("1. Buy new robot");
        	System.out.println("2. Recharge a robot");
        	System.out.println("3. Perform a cooking task");
        	System.out.println("4. Perfrom a cleaning task");
        	System.out.println("5. Exit");
        	System.out.print(">>");
        	choice =scan.nextInt();
        	scan.nextLine();
        	switch(choice) {
        	case 1:
        		buy();
        		break;
        	case 2:
        		recharge();
        		break;
        	case 3:
        		cook();
        		break;
        	case 4:
        		clean();
        		break;
        	}
    	}while(choice!=5);
    	
    }
	public void buy() {
		if(robocount == 2){
			System.out.println("You cannot buy more robot");
			System.out.println("press enter to continue");
			scan.nextLine();
			return;

		}

		System.out.println("Choose a robot type: ");
		System.out.println("1. Chef Robot");
		System.out.println("2. Cleaner Robot");
		System.out.println(">>");
		choice = scan.nextInt();

		if (choice == 1){
			addRobot("ChefRobot");
		} else if (choice == 2) {
			addRobot("CleanerRobot");
		}
	}

	private void addRobot(String robotType){
		robocount++;
		helper.add(new Robot(robotType));

		if(robotType.equals("ChefRobot")){
			cookflag++;
			System.out.println("You bought a chef robot!");
		}else if(robotType.equals("CleanerRobot")){
			cleanflag++;
			System.out.println("You bought a cleaner robot");
		}
		System.out.println("Press enter to continue");
		scan.nextInt();
	}

    public void recharge() {
    	if(robocount==0) {
    		System.out.println("You do not have any robots!");
    		System.out.println("Press enter to continue");
    		scan.nextLine();
    		return;
    	}
    	System.out.println("No.  Helper type\t\tStamina");
    	for(int i=0;i<helper.size();i++) {
    		if(!helper.get(i).getName().equals("Human")) {
    			if(helper.get(i).getName().equals("CleanerRobot")) {
        			System.out.println(i-1+".   "+helper.get(i).getType()+"  "+helper.get(i).getName()+"\t"+helper.get(i).getStamina());
        		}else {
        			System.out.println(i-1+".   "+helper.get(i).getType()+"  "+helper.get(i).getName()+"\t\t"+helper.get(i).getStamina());
        		}
    		}
    		
    		
    	}
    	System.out.print("Enter robot number to recharge:");
    	choice=scan.nextInt();
    	scan.nextLine();
    	if(helper.get(choice+1).getStamina()==3) {
    		System.out.println("This robot has full stamina!");
    	}else {
    		int curr=helper.get(choice+1).getStamina();
    		helper.get(choice+1).setStamina(curr+1);
    		if(curr==0) {
    			if(helper.get(choice+1).getName().equals("ChefRobot")) {
    				cookflag++;
    			}else if(helper.get(choice+1).getName().equals("CleanerRobot")) {
    				cleanflag++;
    			}
    		}
    		System.out.println("This robot is recharged (+1 stamina)");
    		moves--;
    	}
    	
    	System.out.println("Press enter to continue");
		scan.nextLine();
    }
    public void cook() {
    	if(cooktasks==0) {
    		System.out.println("There are no cooking tasks left!");
    		System.out.println("Press enter to continue");
    		scan.nextLine();
    		return;
    	}
    	if(humanflag>0||cookflag>0) {
    		flag=true;
    	}
    	
    	if(flag==false) {
    		System.out.println("There are no available human or robot heloers to work");
    		System.out.println("Press enter to continue");
    		scan.nextLine();
    		return;
    }

	Helper helperForCooking = findAvailableHelperForTask("ChefRobot");
	if(helperForCooking != null){
		performTaskAndHandleFlags(helperForCooking, "Chef robot", "cooking", cookflag);
	}else{
		helperForCooking = findAvailableHelperForTask("Human");
		if(helperForCooking != null){
			performTaskAndHandleFlags(helperForCooking, "Human helper", "cookinh", humanflag);
		}else{
			System.out.println("There are no available human or robot helpers to work");
			System.out.println("Press enter to continue");
			scan.nextLine();
		}
	}
}

private Helper findAvailableHelperForTask(String HelperName){
	for (Helper currentHelper : helper){
		if(currentHelper.getName().equals(HelperName) && currentHelper.getStamina() > 0){
			return currentHelper;
		}
	}
	return null;
}

private void performTaskAndHandleFlags(Helper helper, String helperType, String tassk, int flag){
	int currentStamina = helper.getStamina();
	helper.setStamina(currentStamina -1);
	moves--;
	if(currentStamina == 0){
		flag--;
	}
	helper.performTask(helperType, tassk);
	System.out.println("Press enter to continue");
	scan.nextLine();
}


    public void clean() {
    	if(cleantasks == 0){
			displayNoTaskMessage("cleaning");
			return;
		}

		if(!checkAvailableHelpers()){
			return ;
		}

		Helper helperForCleaning = findAvailableHelperForTasks("CleanerRobot");
		if(helperForCleaning != null){
			performsTasksAndHandleFlags(helperForCleaning, cleanflag,"CleanRobot", "Cleaning");
		}else {
			helperForCleaning = findAvailableHelperForTasks("Human");
			if(helperForCleaning != null){
				performsTasksAndHandleFlags(helperForCleaning, humanflag, "Human", "cleaning");
			}
		}
	}

		private Helper findAvailableHelperForTasks(String taskType){
			for(Helper helper : helper){
				if(helper.getName().equals(taskType) && helper.getStamina() > 0){
					return helper;
				}
			}
			return null;
		}

		private void displayNoTaskMessage(String taskType){
			System.out.println("There are no " + taskType + " tasks left!");
			System.out.println("Press enter to continue");
			scan.nextLine();
		}

		private boolean checkAvailableHelpers(){
			if(humanflag > 0 || cleanflag > 0){
				return true;
			}else {
				System.out.println("There are no available human or robot helpers to work");
				System.out.println("Press enter to continue");
				scan.nextLine();
				return false;
			}
		}

		private void performsTasksAndHandleFlags(Helper helper, int flag, String helperName, String task){
			int currentStamina = updateHelperStamina(helper);
			helper.setStamina(currentStamina -1);
			moves--;
			if(currentStamina == 0){
				flag--;
			}
			helper.performTask(helperName, task);
			System.out.println("Press enter to continue");
			scan.nextLine();
		}

		private int updateHelperStamina(Helper helper){
			int currentStamina = helper.getStamina();
			helper.setStamina(currentStamina - 1);
			return currentStamina;
		}

    }
//p





