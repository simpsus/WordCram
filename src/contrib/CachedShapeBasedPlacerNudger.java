package contrib;

import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import processing.core.PVector;
import wordcram.Word;
import wordcram.WordNudger;
import wordcram.WordPlacer;

public class CachedShapeBasedPlacerNudger implements WordNudger, WordPlacer {

	Random random;
	Map<Word,PVector> cache;
	int max = 10;
	Area area;
	float minX;
	float minY;
	float maxX;
	float maxY;
	float stdDev = 0.6f;

	public CachedShapeBasedPlacerNudger(Area area) {
		random = new Random();
		cache = new HashMap<Word,PVector>();
		this.area = area;
		Rectangle2D areaBounds = area.getBounds2D();
		this.minX = (float) areaBounds.getMinX();
		this.minY = (float) areaBounds.getMinY();
		this.maxX = (float) areaBounds.getMaxX();
		this.maxY = (float) areaBounds.getMaxY();
	}

	public int getCacheSize() {
		return cache.size();
	}

	public Map<Word, PVector> getCache() {
		return cache;
	}

	@Override
	public PVector place(Word word, int wordIndex, int wordsCount,
			int wordImageWidth, int wordImageHeight, int fieldWidth,
			int fieldHeight) {
		word.setProperty("width", wordImageHeight);
		word.setProperty("height", wordImageWidth);
		PVector guess = guess(word);
		int i = 0;
		while (isCached(guess) && i++ < max) {
			guess = guess(word);
		}
		cache(word, guess);
		return guess;
	}

	@Override
	public PVector nudgeFor(Word word, int attemptNumber) {
		PVector guess = guess(word);
		int i = 0;
		while (isCached(guess) && i++ < max) {
			guess = guess(word);
			System.out.println("bad hit");
		}
		cache(word, guess);
		return PVector.sub(guess,word.getTargetPlace());
	}

	private PVector guess(Word word) {
		float ww = (Integer) word.getProperty("width");
		float wh = (Integer) word.getProperty("height");
		PVector guess = new PVector(random.nextInt(new Float(maxX-ww).intValue()), 
				random.nextInt(new Float(maxY-wh).intValue()));
		int guesses = 0;
		while (!filter(guess, word)) {
			guess = new PVector(random.nextInt(new Float(maxX-ww).intValue()), 
					random.nextInt(new Float(maxY-wh).intValue()));
			guesses++;
		}
		System.out.println(guesses);
		return guess;
	}

	private boolean filter(PVector guess, Word word) {
		return area.contains(guess.x, guess.y, (int)word.getProperty("width"), (int) word.getProperty("height"));
	}

	private boolean isCached(PVector guess) {
		return cache.values().contains(guess);
	}

	private void cache (Word word, PVector guess) {
		cache.put(word, guess);
	}


}
