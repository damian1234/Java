import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;
public class BSTAnagram extends AnagramFinder{
	TreeMap<String, ArrayList<String>> myTree;
	public BSTAnagram(){
		myTree = new TreeMap<String, ArrayList<String>>();
	}
	@Override
	public void add(String w) {
		char[] x = w.toCharArray();
		Arrays.sort(x);
		String y = new String(x);
		ArrayList<String> array = myTree.get(y);
		if(array !=null){
			array.add(w);
			myTree.put(y, array);
		}
		else{
			ArrayList<String> init = new ArrayList<String>();
			init.add(w);
			myTree.put(y, init);
		}

	}

	@Override
	public String[] search(String w) {
		char[] x = w.toCharArray();
		Arrays.sort(x);
		String word = new String(x);
		ArrayList<String> array = myTree.get(word);
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
