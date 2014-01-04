package contrib;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import wordcram.Word;
import wordcram.WordSizer;

public class ContribTextSource {
	
	List<String> baseWords;
	public int count;
	Word[] words;
	public WordSizer sizer;
	Random random;
	int index;
	public enum SelectionStrategy { ROUND_ROBIN, RANDOM };
	public SelectionStrategy strategy;

	public ContribTextSource() {
		baseWords = new ArrayList<String>();
		index = 0;
	}
	
	public void addString(String next) {
		baseWords.add(next);
	}

	public Word[] getWords() {
		words = new Word[count];
		for (int i=0;i<words.length;i++) {
			Word current = new Word(selectWord(),0);
			current.weight = sizer.sizeFor(current, i, words.length);
			words[i] = current;
		}
		return words;
	}
	
	public void clearWords() {
		baseWords = new ArrayList<String>();
		index = 0;
	}
	
	private String selectWord() {
		String result = null;
		if (strategy == SelectionStrategy.ROUND_ROBIN) {
			result = baseWords.get(index);
			index = (index + 1) % (baseWords.size());
		} else if (strategy == SelectionStrategy.RANDOM) {
			result = baseWords.get(random.nextInt(baseWords.size()));
		}
		return result;
	}

}
