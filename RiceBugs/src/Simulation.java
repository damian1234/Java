import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
public class Simulation {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("enter the dimentions sepeated by a space");
		int xBound = scan.nextInt();
		int yBound = scan.nextInt();
		System.out.print("enter the length of time");
		int time = scan.nextInt();
		System.out.print("enter the number of bugs");
		int numOfBugs = scan.nextInt();
		ArrayList<RiceBugs> bugs = new ArrayList<RiceBugs>();
		Field myField = new Field(xBound, yBound);
		for(int i =0; i < numOfBugs; i++){
			System.out.print("enter the next bugs details");
			int xpos = scan.nextInt();
			int ypos = scan.nextInt();
			int enter = scan.nextInt();
			String dir =  scan.next();
			RiceBugs b = new RiceBugs(xpos, ypos, enter, dir);
			bugs.add(b);
		}
		for(int i = 0; i < time; i++){
			for(int j = 0; j< bugs.size();j++){
				RiceBugs bug = bugs.get(j);
				if(bug.entryTime == i){
					myField.enterRiceBug(bug);
					bugs.remove(j--);				//removing bug from this list so not to check if it has to enter again
				}									//j-- because the list is now shorter so the element at j now was previously at j+1
			}
			myField.infectePlant();
			myField.Move();
		}
		int alive = 0;
		for(int i = 0; i < xBound; i++){
			for(int j = 0; j<yBound; j++){
				Plants p =myField.myPlants[i][j];
				if(!p.infected){
					alive++;
				}
			}
		}
		System.out.print("Plants alive: " + alive + "\nBugs alive" + myField.bugs.size());
		scan.close();
		
	}
	
}
