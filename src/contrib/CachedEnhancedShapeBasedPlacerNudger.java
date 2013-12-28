package contrib;

import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import processing.core.PVector;
import wordcram.Word;
import wordcram.WordNudger;
import wordcram.WordPlacer;

public class CachedEnhancedShapeBasedPlacerNudger implements WordPlacer, WordNudger{

	Set<PVector> positions;
	Map<Word, PVector> wordPositions;
	Area area;
	float minX;
	float minY;
	float maxX;
	float maxY;
	Random random;
	private static PVector fail = new PVector(-10000, -10000);

	public CachedEnhancedShapeBasedPlacerNudger(Shape shape) {
		this.area = new Area(shape);
	    random = new Random();
	    Rectangle2D areaBounds = area.getBounds2D();
	    this.minX = (float) areaBounds.getMinX();
	    this.minY = (float) areaBounds.getMinY();
	    this.maxX = (float) areaBounds.getMaxX();
	    this.maxY = (float) areaBounds.getMaxY();
	    positions = new HashSet<PVector>();
	    wordPositions = new HashMap<Word, PVector>();
	}

	public PVector place(Word w, int rank, int count, int ww, int wh, int fw,
			int fh) {
		w.setProperty("width", ww);
		w.setProperty("height", wh);
		PVector guess = aSpot(w);
		if (!positions.contains(guess)) {
			positions.add(guess);
			wordPositions.put(w, guess);
		}
		return fail;
	}

	public PVector nudgeFor(Word word, int attempt) {
		PVector guess = aSpot(word);
		positions.remove(wordPositions.get(word));
		positions.add(guess);
		wordPositions.put(word, guess);
		return PVector.sub(guess, word.getTargetPlace());
	}

	private PVector aSpot(Word word) {
		float ww = (Integer) word.getProperty("width");
		float wh = (Integer) word.getProperty("height");
		float newX, newY;
		for (int i = 0; i < 100; i++) {
			newX = randomBetween(minX, maxX);
			newY = randomBetween(minY, maxY);
			if (area.contains(newX, newY, ww, wh)) {
				return new PVector(newX, newY);
			}
		}
		return fail;
	}

	private float randomBetween(float a, float b) {
		return a + random.nextFloat() * (b - a);
	}

}
