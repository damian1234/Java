import java.util.ArrayList;

public class Field {
	ArrayList<RiceBugs> bugs;
	Plants[][] myPlants;
	int xBound;
	int yBound;
	public Field(){

	}
	public Field(int xs, int ys){
		xBound = xs;
		yBound = ys;
		bugs = new ArrayList<RiceBugs>();
		myPlants = new Plants[xBound][yBound];
		for(int i = 0; i < xs; i++){
			for(int j =0; j<ys; j++){
				myPlants[i][j] = new Plants();			
			}
		}
	}
	public void enterRiceBug(RiceBugs r){
		bugs.add(r);
		Plants p = myPlants[r.position[0]][r.position[1]];
		p.bugs.add(r);
	}
	public void Move(){
		for(int i = 0; i < bugs.size(); i++){
			RiceBugs bug = bugs.get(i);
			Plants plant = myPlants[bug.position[0]][bug.position[1]];
			int j = 0;
			boolean found = false;
			while(!found && j < plant.bugs.size()){	//bugs moving from current plant
				if(bug == plant.bugs.get(j)){
					plant.bugs.remove(j--);
					found = true;
				}
				j++;
			}
			if(bug.alive){
				switch(bug.direction){
				case "A":
					bug.position[0] = bug.position[0] +1;
					bug.position[1] = bug.position[1] -1;

					break;
				case "B":
					bug.position[0] = bug.position[0] +1;
					break;
				case "C":
					bug.position[0] = bug.position[0] +1;
					bug.position[1] = bug.position[1] +1;
					break;
				case "D":
					bug.position[1] = bug.position[1] -1;
					break;
				case "E":
					bug.position[1] = bug.position[1] +1;
					break;
				case "F": 
					bug.position[0] = bug.position[0] -1;
					bug.position[1] = bug.position[1] -1;
					break;
				case "G":
					bug.position[0] = bug.position[0] -1;
					break;
				case "H":
					bug.position[0] = bug.position[0] -1;
					bug.position[1] = bug.position[1] +1;
					break;
				default:
				}
				if((bug.position[0] >= xBound||bug.position[0] < 0)||(bug.position[1] >= yBound||bug.position[1] <0)){//if the bug hits the fence it dies
					bug.alive = false;
					bugs.remove(i);
				}
				else{
					Plants nextPlant = myPlants[bug.position[0]][bug.position[1]];	//bug moved to next plant
					nextPlant.bugs.add(bug);
				}
			}
			else{
				bugs.remove(i);
				i--;
			}
		}
	}
	public void infectePlant(){
		for(int i =0; i <xBound; i++){
			for(int j =0; j<yBound; j++){
				Plants plant = myPlants[i][j];
				plant.fight();
				if(!plant.infected){
					for(int k = 0; k < plant.bugs.size(); k++){		//if there are 1 or more bugs at a plant
						plant.infected=true;
						RiceBugs bug = bugs.get(k);					//give each bug plus one strength
						bug.strength++;					//collision happens first, so all bugs at a plant will have the same strength
					}
				}
			}
		}
	}
	
}
