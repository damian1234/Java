import java.util.*;
public class QuickUnion
{
	private int[] id;
	private int largest =0;
	private int smallest = 10000;
	private int average = 0;
	private int numOfCircles = 0;
	private int[] circleInfo;
	public QuickUnion(int N)
	{
		circleInfo = new int[N];
		id = new int[N];
		for (int i = 0; i < N; i++){
			circleInfo[i] = 1;
			id[i] = i;
		}
	}
	public int find(int i)
	{
		while (i != id[i])
		{
			id[i] = id[id[i]];
			i = id[i];
		}
		return i;
	}
	public void union(int p, int q)
	{
		int i= find(q);
		int j= find(p);
		if(i != j){
			if(circleInfo[i] < circleInfo[j]){
				id[i] = j;
				circleInfo[j] += circleInfo[i];
				circleInfo[i] = 0;
			}
			else{
				id[j] = i;
				circleInfo[i] += circleInfo[j];
				circleInfo[j] = 0;
			}
		}
	}
	public void getAverage(){
		int count = 0;
		int tmp;
		for(int i = 0; i < circleInfo.length; i++){
			if(id[i] == i){
				tmp = circleInfo[i];
				numOfCircles++;
				if(tmp > largest){
					largest = tmp;

				}
				if(tmp < smallest){
					smallest = tmp;

				}
				average +=circleInfo[i];

				count++;
			}
		}
		average = average/count;
	}
	public int getLargest(){
		return largest;
	}
	public int getMedian(){
		return average;
	}
	public int getSmallest(){
		return smallest;
	}
	public int getNumOfCircles(){
		return numOfCircles;
	}
}
