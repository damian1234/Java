import java.util.HashMap;

/**
 * Class FacebookCircles: calculates the statistics about the friendship circles in facebook data.
 *
 * @author
 *
 * @version 01/12/15 02:03:28
 */
public class FacebookCircles {
	  QuickUnion list;
  /**
   * Constructor
   * @param numberOfFacebookUsers : the number of users in the sample data.
   * Each user will be represented with an integer id from 0 to numberOfFacebookUsers-1.
   */
  public FacebookCircles(int numberOfFacebookUsers) {
	 list= new QuickUnion(numberOfFacebookUsers);
  }

  /**
   * creates a friendship connection between two users, represented by their corresponding integer ids.
   * @param user1 : int id of first user
   * @param user2 : int id of second  user
   */
  public void friends( int user1, int user2 ) {
    list.union(user1, user2);
  }
  
  /**
   * @return the number of friend circles in the data already loaded.
   */
  public int numberOfCircles() {
	  list.getAverage();
	  return list.getNumOfCircles();
  }

  /**
   * @return the size of the largest circle in the data already loaded.
   */
  public int sizeOfLargestCircle() {
	  return list.getLargest();
  }

  /**
   * @return the size of the median circle in the data already loaded.
   */
  public int sizeOfAverageCircle() {
   return list.getMedian();
  }

  /**
   * @return the size of the smallest circle in the data already loaded.
   */
  public int sizeOfSmallestCircle() {
    return list.getSmallest();
  }


}
