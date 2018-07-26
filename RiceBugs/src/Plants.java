import java.util.ArrayList;
public class Plants {
	
	ArrayList<RiceBugs> bugs;
	boolean infected;
	public Plants() {
		infected = false;
		bugs = new ArrayList<RiceBugs>();
		
	}
	public void fight(){
		if(bugs.size()>1){//check if there is a collision
			RiceBugs bug1 = bugs.get(0);
			for(int i = 1; i<bugs.size(); i++){
					RiceBugs bug2 = bugs.get(i);
					if(bug1.strength > bug2.strength){
						bug2.alive = false;		//bug1 is the strongest
					}
					else if (bug1.strength <bug2.strength){
						bug1.alive = false;
						bug1 = bug2;			//bug2 is now the strongest
				}
			}
			int i = 0;
			while(i < bugs.size()){
				RiceBugs myBug = bugs.get(i);
				if(!myBug.alive){
					bugs.remove(i);  //bug is dead so remove it from the list
					i--;			//the list is now 1 element shorter so the index needs to be decremented to check the next bug
				}
				i++;
			}
		}
		
	}

}
