import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


//-------------------------------------------------------------------------
/**
 *  Test class for Collinear.java
 *
 *  @author  
 *  @version 29/09/14 13:22:29
 */
@RunWith(JUnit4.class)
public class Simulationtest{
	@Test
	public void testConstructor(){
		new Field();
		new Field(6,6);
	}
	@Test
	public void testEmpty(){
		Field field = new Field(6,6);
		assertEquals(0, field.bugs.size());
	}
	@Test
	public void testBugConstructor(){
		RiceBugs bug = new RiceBugs(1, 1, 5, "A");
		assertEquals(1, bug.position[0]);
		assertEquals(1, bug.position[1]);
		assertEquals(5, bug.entryTime);
		assertEquals("A", bug.direction);
		assertEquals(true, bug.alive);
	}
	@Test
	public void testPlantConstructor(){
		Plants plant = new Plants();
		assertEquals(0, plant.bugs.size());
		assertEquals(false, plant.infected);
	}
	@Test
	public void testfight(){
		Plants plant = new Plants();
		assertEquals(0, plant.bugs.size());
		RiceBugs a = new RiceBugs(1, 1, 5, "A");
		a.strength++;
		RiceBugs b = new RiceBugs(1, 1, 5, "B");
		b.strength++;
		RiceBugs c = new RiceBugs(1, 1, 5, "B");
		plant.bugs.add(a);
		plant.bugs.add(b);
		plant.bugs.add(c);
		plant.fight();
		assertEquals(2, plant.bugs.size());
	}
	@Test
	public void testMove(){
		Field field  = new Field(6,6);
		RiceBugs bug = new RiceBugs(1, 1, 5, "B");
		field.enterRiceBug(bug);
		assertEquals(1, field.myPlants[1][1].bugs.size());		//starting place
		assertEquals(0, field.myPlants[2][1].bugs.size());		//where the bug will move to
		field.Move();
		assertEquals(0, field.myPlants[1][1].bugs.size());		//where the bug left
		assertEquals(1, field.myPlants[2][1].bugs.size());		//where the bug is now
		
	}
	@Test
	public void testInfected(){
		Field field  = new Field(6,6);
		RiceBugs bug = new RiceBugs(1, 1, 5, "B");
		field.enterRiceBug(bug);
		assertEquals(false, field.myPlants[1][1].infected);		
		field.infectePlant();
		assertEquals(true, field.myPlants[1][1].infected);	
		
	}
}

