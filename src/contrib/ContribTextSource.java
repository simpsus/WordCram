package contrib;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import wordcram.text.TextSource;

public class ContribTextSource implements TextSource{
	
	private Map<String,Integer> input;

	public ContribTextSource() {
		input = new HashMap<String,Integer>();
	}
	
	public void addInput(String word, int occurences) {
		input.put(word, occurences);
	}

	@Override
	public String getText() {
		String result = "";
		for (Entry<String,Integer> entry:input.entrySet()) {
			for (int i=0;i<entry.getValue();i++) {
				result += entry.getKey() + " ";
			}
		}
		return result;
	}
	
	

}
