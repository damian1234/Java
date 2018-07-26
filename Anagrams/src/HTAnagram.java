import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Arrays;
public class HTAnagram extends AnagramFinder {
	Hashtable<Integer, ArrayList<String>> myTable;
	public HTAnagram(){
		myTable = new Hashtable<Integer, ArrayList<String>>();
	}
	@Override
	public void add(String w) {
		char[] x = w.toCharArray();
		Arrays.sort(x);
		String y = new String(x);
		int hashedVal = y.hashCode();
		ArrayList<String> array = myTable.get(hashedVal);
		if(array !=null){
			array.add(w);
			myTable.put(hashedVal, array);
		}
		else{
			ArrayList<String> init = new ArrayList<String>();
			init.add(w);
			myTable.put(hashedVal, init);
		}

	}
	@Override
	public String[] search(String w) {
		char[] x = w.toCharArray();
		Arrays.sort(x);
		String word = new String(x);
		int hashedVal = word.hashCode();
		ArrayList<String> array =myTable.get(hashedVal);
		String[] result = new String[0];
		if(array ==null||array.size()==0){
			return result;
		}
		result = new String[array.size()];
		for(int i = 0; i < result.length;i++){
			result[i] = (String)array.get(i);
		}

		return result;
	}
}
