// -------------------------------------------------------------------------
/**
 *  This class contains only two static methods that search for points on the
 *  same line in three arrays of integers. 
 *
 *  @author  
 *  @version 29/09/14 09:29:00
 */
class Collinear
{

	// ----------------------------------------------------------
	/**
	 * Counts for the number of non-hoizontal lines that go through 3 points in arrays a1, a2, a3.
	 * This method is static, thus it can be called as Collinear.countCollinear(a1,a2,a3)
	 * @param a1: An UNSORTED array of integers. Each integer a1[i] represents the point (a1[i], 1) on the plain.
	 * @param a2: An UNSORTED array of integers. Each integer a2[i] represents the point (a2[i], 2) on the plain.
	 * @param a3: An UNSORTED array of integers. Each integer a3[i] represents the point (a3[i], 3) on the plain.
	 * @return the number of points which are collinear and do not lie on a horizontal line.
	 *
	 * Array a1, a2 and a3 contain points on the horizontal line y=1, y=2 and y=3, respectively.
	 * A non-horizontal line will have to cross all three of these lines. Thus
	 * we are looking for 3 points, each in a1, a2, a3 which lie on the same
	 * line.
	 *
	 * Three points (x1, y1), (x2, y2), (x3, y3) are collinear (i.e., they are on the same line) if
	 * 
	 * x1(y2−y3)+x2(y3−y1)+x3(y1−y2)=0 
	 *
	 * In our case y1=1, y2=2, y3=3. Therefore the condition we need to check for collinearity is
	 *
	 * x1(2-3)+x2(3-1)+x3(1-2)=0
	 *
	 * or equivalently:
	 *
	 * -x1 + 2*x2 - x3 = 0
	 *
	 * Therefore, this method returns the number of triples a1[i], a2[j], a3[k] (for some i,j,k) for which
	 *
	 * 2*a2[j] - a1[i] - a3[k] = 0
	 *
	 * You should implement this using a BRUTE FORCE approach (check all possible combinations of numbers from a1, a2, a3)
	 *
	 * ----------------------------------------------------------
	 *
	 *  Give the performance of the running time of your implementation using the Theta notation. Explain your answer.
	 *
	 *  Performance: TODO
	 *
	 *  Explanation: TODO
	 */
	static int countCollinear(int[] a1, int[] a2, int[] a3)
	{
		int count = 0;			//time on line  theta(1) -----  current time theta(1) 
		for(int i = 0; i < a1.length; i++){	 	// theta(n) -----  theta(n)
			for(int j = 0; j <a2.length;j++){		// theta(n) ----- theta(N^2)
				for(int k = 0; k <a3.length; k++){	// theta(n) ----- theta(N^3)
					int sumOf = a3[k] +a1[i];		// theta(1) ----- theta(N^3)
					if(((2*a2[j])-sumOf) ==0){		// theta(1) ----- theta(N^3)
						count++;					// theta(1) ----- theta(N^3)
					}
				}
			}
		}
		return count;
	}

	// ----------------------------------------------------------
	/**
	 * Counts for the number of non-hoizontal lines that go through 3 points in arrays a1, a2, a3.
	 * This method is static, thus it can be called as Collinear.countCollinearSorted(a1,a2,a3)
	 * @param a1: A SORTED array of integers. Each integer a1[i] represents the point (a1[i], 1) on the plain.
	 * @param a2: A SORTED array of integers. Each integer a2[i] represents the point (a2[i], 2) on the plain.
	 * @param a3: A SORTED array of integers. Each integer a3[i] represents the point (a3[i], 3) on the plain.
	 * @return the number of points which are collinear and do not lie on a horizontal line.
	 *
	 * This time implement the method by the following algorithm:
	 * - consider all combinations of points from a1 and a2
	 * - for each such combination a1[i], a2[j] compute c = 2*a2[j] - a1[i]. If
	 *   a3 contains a point collinear to a1[i], a2[j] then this point is c.
	 * - Search for c in a3. If it exists then you have found a collinear triple.
	 *
	 * Implement and use binary search for looking up c in a3.
	 *
	 */
	static int countCollinearSorted(int[] a1, int[] a2, int[] a3)
	{
		int count = 0;			//time on line  theta(1) -----  current time theta(1) 
		for(int i = 0; i <a1.length; i++){	// theta(N) -----  theta(N)
			for(int j = 0; j <a2.length; j++){ // theta(N) -----  theta(N^2)
				int c = (2*a2[j]) -a1[i];	// theta(1) -----  theta(N^2)
				if(binarySearch(a3, c)){	// theta(logN) ----- theta(N^2 + logN)
					count++;	// theta(1) ----- theta(N^2 + logN)
				}
			}
		}
		return count;
	}

	// ----------------------------------------------------------
	/**
	 * Searches for an integer inside an array of integers.
	 * This method is static, thus it can be called as Collinear.binarySearch(a,x)
	 * @param a: A SORTED array of integers. 
	 * @param x: An integer.
	 * @return true if 'x' is contained in 'a'; false otherwise.
	 *
	 * ----------------------------------------------------------
	 *
	 *  Give the performance of the running time of your implementation using the Theta notation. Explain your answer.
	 *
	 *  Performance: TODO
	 *
	 *  Explanation: 
		My implementation of binary search is to compare the mid value to x. if x was bigger then a new array (b) is made from a[mid] 
		to the last element in a. if x was smaller then a[mid] then b is from a[0] to a[mid]
		this function then calls itself passing the new array b and the number being searched for.
	 *
	 */
	//this function function runs in theta(logN) 
	static boolean binarySearch(int[] a, int x)
	{
		
		int mid = a.length/2;
		if(x ==a[mid]){
			return true;
		}
		if(a.length <= 1){
			return false;
		}
		int[] b = new int[mid];					// half array every time
		int[] c = new int[a.length-mid];		//
		if(x < a[mid]){
			for(int i = 0; i < b.length;i++){
				b[i] = a[i];				// fill new array with the lower half
			}
			return binarySearch(b, x);
		}
		else{
			for(int i = 0; i < c.length;i++){
				c[i] = a[mid+i];				// fill new array with the upper half
			}
			return binarySearch(c, x);			// call the function, with the new array(b or c) which is half the length of a. 
		}
	}
}
