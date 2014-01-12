package contrib;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import wordcram.Word;
import wordcram.WordSizer;

public class ContribWordCreator {
	
	private List<String> baseWords;
	private int count;
	private Word[] words;
	private WordSizer sizer;
	private Random random;
	private int index;
	public enum SelectionStrategy { ROUND_ROBIN, RANDOM };
	private SelectionStrategy strategy;
	private int buckets;

	public ContribWordCreator() {
		baseWords = new ArrayList<String>();
		index = 0;
		buckets = -1;
	}
	
	public void addString(String next) {
		baseWords.add(next);
	}

	public Word[] getWords() {
		words = new Word[getCount()];
		if (buckets < 0) {
			buckets = words.length;
		}
		for (int i=0;i<words.length;i++) {
			Word current = new Word(selectWord(),0);
			current.weight = getSizer().sizeFor(current, getBucket(i), words.length);
			words[i] = current;
		}
		return words;
	}
	
	private int getBucket(int bucket) {
		int bucketSize = words.length / buckets;
		return bucketSize * ((bucket/bucketSize) + 1);
	}
	
	public void clearWords() {
		baseWords = new ArrayList<String>();
		index = 0;
	}
	
	private String selectWord() {
		String result = null;
		if (getStrategy() == SelectionStrategy.ROUND_ROBIN) {
			result = baseWords.get(index);
			index = (index + 1) % (baseWords.size());
		} else if (getStrategy() == SelectionStrategy.RANDOM) {
			result = baseWords.get(random.nextInt(baseWords.size()));
		}
		return result;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public SelectionStrategy getStrategy() {
		return strategy;
	}

	public void setStrategy(SelectionStrategy strategy) {
		this.strategy = strategy;
	}

	public WordSizer getSizer() {
		return sizer;
	}

	public void setSizer(WordSizer sizer) {
		this.sizer = sizer;
	}

	public int getBuckets() {
		return buckets;
	}

	public void setBuckets(int buckets) {
		this.buckets = buckets;
	}

}
