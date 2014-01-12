package contrib;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import wordcram.Observer;
import wordcram.Word;
import wordcram.WordSkipReason;

public class StandardConsoleObserver implements Observer {

	private List<Word> skipped, drawn;
	private Word[] words;
	private DecimalFormat df, wdf;
	private long start, end;
	private  int level = 0;
	
	public StandardConsoleObserver() {
		skipped = new ArrayList<Word>();
		drawn = new ArrayList<Word>();
		df = new DecimalFormat("000%");
		wdf = new DecimalFormat("0.##");
	}

	public StandardConsoleObserver(int level) {
		this();
		withLevel(level);
	}

	public void wordsCounted(Word[] words) {
		this.words = words;
	}

	
	public void wordSkipped(Word word) {
		skipped.add(word);
		if (getLevel() >= 3) {
			System.out.println(df.format((skipped.size() + drawn.size())/(float)words.length) + " skip " + skipped.size() + " " + word.word  + " "+ wdf.format(word.weight)+" " + word.wasSkippedBecause());
		}
	}

	public void wordDrawn(Word word) {
		drawn.add(word);
		if (getLevel() >= 2) {
		 System.out.println(df.format((skipped.size() + drawn.size())/(float)words.length) + " draw " + drawn.size() + " " + word.word  + " weight:" + wdf.format(word.weight) + " size:" + wdf.format(word.getRenderedSize()));
		}
	}
	
	public void  beginDraw() {
		if (getLevel() >= 1) {
			System.out.println("Starting draw");
		}
		start = System.currentTimeMillis();
	}
	
	public void endDraw() {
		end = System.currentTimeMillis();
		if (getLevel() >= 1) {
			System.out.println("Draw Finished in " + (end - start)/1000 + " seconds");
			System.out.println("Drew: " + drawn.size());
			System.out.println("Skipped: " + skipped.size());
			Map<WordSkipReason,Integer> reasons = new HashMap<WordSkipReason, Integer>();
			for (Word word: skipped) {
				if (!reasons.containsKey(word.wasSkippedBecause())) {
					reasons.put(word.wasSkippedBecause(), 1);
				} else {
					reasons.put(word.wasSkippedBecause(), reasons.get(word.wasSkippedBecause()) + 1);
				}
			}
			for (Entry<WordSkipReason,Integer> entry:reasons.entrySet()) {
				System.out.println(entry.getKey() + ": " + entry.getValue());
			}
		}
	}

	public int getLevel() {
		return level;
	}

	public StandardConsoleObserver withLevel(int level) {
		this.level = level;
		return this;
	}

}
