import static org.junit.Assert.assertEquals;
import java.util.Random;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @author  
 *  @version 12/10/15 20:43:36
 */
@RunWith(JUnit4.class)
public class DoublyLinkedListTest
{
	//~ Constructor ........................................................
	@Test
	public void testConstructor()
	{
		new DoublyLinkedList<Integer>();

	}

	//~ Public Methods ........................................................

	// ----------------------------------------------------------
	/**
	 * Check if the insertBefore works
	 */
	@Test
	public void testInsertBefore()
	{
		// test non-empty list
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(0,1);
		testDLL.insertBefore(1,2);
		testDLL.insertBefore(2,3);

		testDLL.insertBefore(0,4);
		assertEquals( "Checking insertBefore to a list containing 3 elements at position 0", "4,1,2,3", testDLL.toString() );
		testDLL.insertBefore(1,5);
		assertEquals( "Checking insertBefore to a list containing 4 elements at position 1", "4,5,1,2,3", testDLL.toString() );
		testDLL.insertBefore(2,6);       
		assertEquals( "Checking insertBefore to a list containing 5 elements at position 2", "4,5,6,1,2,3", testDLL.toString() );
		testDLL.insertBefore(-1,7);        
		assertEquals( "Checking insertBefore to a list containing 6 elements at position -1 - expected the element at the head of the list", "7,4,5,6,1,2,3", testDLL.toString() );
		testDLL.insertBefore(7,8);        
		assertEquals( "Checking insertBefore to a list containing 7 elemenets at position 8 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8", testDLL.toString() );
		testDLL.insertBefore(700,9);        
		assertEquals( "Checking insertBefore to a list containing 8 elements at position 700 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8,9", testDLL.toString() );

		// test empty list
		testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(0,1);        
		assertEquals( "Checking insertBefore to an empty list at position 0 - expected the element at the head of the list", "1", testDLL.toString() );
		testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(10,1);        
		assertEquals( "Checking insertBefore to an empty list at position 10 - expected the element at the head of the list", "1", testDLL.toString() );
		testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(-10,1);        
		assertEquals( "Checking insertBefore to an empty list at position -10 - expected the element at the head of the list", "1", testDLL.toString() );
	}

	// TODO: add more tests here. Each line of code in DoublyLinkedList.java should
	// be executed at least once from at least one test.

	@Test
	public void testGet()
	{
		// test non-empty list
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(1,2);
		testDLL.insertBefore(2,3);
		testDLL.insertBefore(0,4);
		testDLL.insertBefore(1,5);
		testDLL.insertBefore(2,6);       
		testDLL.insertBefore(-1,7);        
		testDLL.insertBefore(7,8);        
		testDLL.insertBefore(700,9);  
		int result = testDLL.get(0);
		assertEquals( "Checking get correct node in position 0,  expected data from the list", 7, result);
		result = testDLL.get(2);
		assertEquals( "Checking get correct node in position 2,  expected data from the list", 5, result );
		result =testDLL.get(-41);
		assertEquals( "Checking get correct node in position -41,  expected data from the list", 7, result );
		result = testDLL.get(1000);
		assertEquals( "Checking get correct node in position 1000,  expected data from the list", 9, result );

		// test empty list
		testDLL = new DoublyLinkedList<Integer>();        
		assertNull( testDLL.get(0) );
		testDLL = new DoublyLinkedList<Integer>();
		testDLL.get(140);        
		assertNull(testDLL.get(140) );
		testDLL = new DoublyLinkedList<Integer>();
		testDLL.get(-99);        
		assertNull( testDLL.get(-99) );
	}

	@Test
	public void testDeleteAt()
	{
		// test non-empty list
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(1,2);
		testDLL.insertBefore(2,3);
		testDLL.insertBefore(0,4);
		testDLL.insertBefore(1,5);
		testDLL.insertBefore(2,6);       
		testDLL.insertBefore(-1,7);        
		testDLL.insertBefore(7,8);        
		testDLL.insertBefore(700,9);  
		assertTrue(testDLL.deleteAt(0));
		assertTrue( testDLL.deleteAt(2) );
		assertFalse( testDLL.deleteAt(-41) );
		assertFalse( testDLL.deleteAt(1000) );

		// test empty list
		testDLL = new DoublyLinkedList<Integer>();        
		assertFalse(testDLL.deleteAt(0) );
		testDLL = new DoublyLinkedList<Integer>();
		assertFalse(testDLL.deleteAt(140));
		testDLL = new DoublyLinkedList<Integer>();
		assertFalse( testDLL.deleteAt(-99) );
	}

	@Test
	public void testPush()
	{
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		testDLL.push(10);
		assertEquals( "Checking get correct node in position 0,  expected data from the list", "10", testDLL.toString());
		testDLL.push(11);
		assertEquals( "Checking get correct node in position 2,  expected data from the list", "11,10", testDLL.toString());
		testDLL.push(-1213);
		assertEquals( "Checking get correct node in position -41,  expected data from the list", "-1213,11,10", testDLL.toString());
		testDLL.push(53434);
		assertEquals( "Checking get correct node in position 1000,  expected data from the list", "53434,-1213,11,10", testDLL.toString());

	
	}
	@Test
	public void testPop()
	{
	
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		testDLL.push(10);
		testDLL.push(1);
		testDLL.push(-213);
		testDLL.push(534);
		testDLL.push(54);
		testDLL.push(0);
		assertEquals( "Checking get correct node in position 0,  expected data from the list", 0, (int)testDLL.pop());
		assertEquals( "Checking get correct node in position 0,  expected data from the list", 54, (int)testDLL.pop());
		assertEquals( "Checking get correct node in position 0,  expected data from the list", 534, (int)testDLL.pop());
		assertEquals( "Checking get correct node in position 0,  expected data from the list", -213, (int)testDLL.pop());
		assertEquals( "Checking get correct node in position 0,  expected data from the list", 1, (int)testDLL.pop());
		testDLL = new DoublyLinkedList<Integer>();
		assertNull(testDLL.pop());
	}
	@Test
	public void testEnqueue()
	{
		
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		testDLL.enqueue(0);
		assertEquals( "Checking get correct node in position 0,  expected data from the list", "0", testDLL.toString());
		testDLL.enqueue(100);
		assertEquals( "Checking get correct node in position 0,  expected data from the list", "100,0", testDLL.toString());
		testDLL.enqueue(42);
		assertEquals( "Checking get correct node in position 0,  expected data from the list", "42,100,0", testDLL.toString());
		testDLL.enqueue(-911);
		assertEquals( "Checking get correct node in position 0,  expected data from the list", "-911,42,100,0", testDLL.toString());
		testDLL.enqueue(-9);
		assertEquals( "Checking get correct node in position 0,  expected data from the list", "-9,-911,42,100,0", testDLL.toString());
		
		
	}
	@Test
	public void testDequeue()
	{
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		testDLL.enqueue(0);
		testDLL.enqueue(10);
		testDLL.enqueue(205);
		testDLL.enqueue(-21);
		testDLL.enqueue(88);
		assertEquals( "Checking get correct node in position 0,  expected data from the list", 0, (int)testDLL.dequeue());
		assertEquals( "Checking get correct node in position 0,  expected data from the list", 10, (int)testDLL.dequeue());
		assertEquals( "Checking get correct node in position 0,  expected data from the list", 205, (int)testDLL.dequeue());
		assertEquals( "Checking get correct node in position 0,  expected data from the list", -21, (int)testDLL.dequeue());
		assertEquals( "Checking get correct node in position 0,  expected data from the list", 88, (int)testDLL.dequeue());
		testDLL = new DoublyLinkedList<Integer>();
		assertNull(testDLL.dequeue());
	}
}

