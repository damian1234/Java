public abstract class AnagramFinder {

	public abstract String[] search(String word);

	public void createDictionary(String[] words) {
		for(String s : words){
			if(s!=null){
				add(s);
			}
		}
	}

	protected String getBaseFormOf(String word){
		return "";

	}
	public abstract void add(String w);
}
