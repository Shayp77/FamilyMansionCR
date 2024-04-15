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
    	if(robocount==2) {
    		System.out.println("You cannot buy more robots!");
    		System.out.println("Press enter to continue");
    		scan.nextLine();
    		return;
    	}
    	System.out.println("Choose a robot type");
    	System.out.println("1. Chef robot");
    	System.out.println("2. Cleaner robot");
    	System.out.print(">>");
    	choice =scan.nextInt();
    	scan.nextLine();
    	if(choice==1) {
    		robocount++;
    		helper.add(new Robot("ChefRobot"));
    		cookflag++;
    		System.out.println("You bought a chef robot!");
    		System.out.println("Press enter to continue");
    		scan.nextLine();
    	}else if(choice==2) {
    		robocount++;
    		helper.add(new Robot("CleanerRobot"));
    		cleanflag++;
    		System.out.println("You bought a cleaner robot!");
    		System.out.println("Press enter to continue");
    		scan.nextLine();
    	}
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
  //
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
    	}else {
    		if(cookflag>0) {
    			for (int i = 0; i <helper.size(); i++) {
            	    if(helper.get(i).getName().equals("ChefRobot") && helper.get(i).getStamina()>0) {
            	    	int curr=helper.get(i).getStamina();
            	    	helper.get(i).setStamina(curr-1);
            	    	moves--;
            	    	cooktasks--;
            	    	if(helper.get(i).getStamina()==0) {           	    		
            	    		cookflag--;     	    		
            	    	}
            	    	helper.get(i).performTask("Chef robot", "cooking");
            	    	flag=false;
            	 
            	    	System.out.println("Press enter to continue");
                		scan.nextLine();
                		return;
            	    	} 
            		}
        	}else {
        		for (int i = 0; i < helper.size(); i++) {
            	    if(helper.get(i).getName().equals("Human")&& helper.get(i).getStamina()>0) {
            	    	int curr=helper.get(i).getStamina();
            	    	helper.get(i).setStamina(curr-1);
            	    	moves--;
            	    	cooktasks--;
            	    	if(helper.get(i).getStamina()==0) {           	    		
            	    		humanflag--;           	    		
            	    	}
            	    	helper.get(i).performTask("Human helper", "cooking");
            	    	flag=false;
            	    	System.out.println("Press enter to continue");
                		scan.nextLine();
                		return;
            	    	}
            		}
        		}
    		}
    	
    	

    }
    public void clean() {
    	if(cleantasks==0) {
    		System.out.println("There are no cleaning tasks left!");
    		System.out.println("Press enter to continue");
    		scan.nextLine();
    		return;
    	}
    	if(humanflag>0||cleanflag>0) {
    		flag=true;
    	}
    	
    	if(flag==false) {
    		System.out.println("There are no available human or robot heloers to work");
    		System.out.println("Press enter to continue");
    		scan.nextLine();
    		return;
    	}else {
    		if(cleanflag>0) {
    			for (int i = 0; i <helper.size(); i++)  {
            	    if(helper.get(i).getName().equals("CleanerRobot")&& helper.get(i).getStamina()>0) {
            	    	int curr=helper.get(i).getStamina();
            	    	helper.get(i).setStamina(curr-1);
            	    	moves--;
            	    	cleantasks--;
            	    	if(helper.get(i).getStamina()==0) {           	    		
            	    		cleanflag--;           	    		
            	    	}            	    	
            	    	flag=false;
            	    	helper.get(i).performTask("Cleaner robot", "cleaning");

            	    	System.out.println("Press enter to continue");
                		scan.nextLine();
                		return;
            	    	} 
            		}
        	}else {
        		for (int i = 0; i < helper.size(); i++) {
            	    if(helper.get(i).getName().equals("Human")&& helper.get(i).getStamina()>0) {
            	    	int curr=helper.get(i).getStamina();
            	    	helper.get(i).setStamina(curr-1);
            	    	moves--;
            	    	cleantasks--;
            	    	if(helper.get(i).getStamina()==0) {           	    		
            	    		humanflag--;           	    		
            	    	}
            	    	helper.get(i).performTask("Human helper", "cleaning");
            	    	flag=false;
            	    	System.out.println("Press enter to continue");
                		scan.nextLine();
                		return;
            	    	}
            		}
        		}
    		}
    	
    	

    }
}
