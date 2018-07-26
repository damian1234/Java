
public class RiceBugs {
	int strength;
	boolean alive;
	String direction;
	int entryTime;
	int position[] = new int[2];
	public RiceBugs(int xpos, int ypos, int enter,  String dir){
		alive = true;
		strength = 0;
		entryTime = enter;
		direction = dir;
		position[0] = xpos;
		position[1] = ypos;
	}
	
}

