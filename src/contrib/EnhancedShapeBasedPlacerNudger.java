package contrib;

import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import processing.core.PApplet;
import processing.core.PVector;
import wordcram.Word;
import wordcram.WordNudger;
import wordcram.WordPlacer;

public class EnhancedShapeBasedPlacerNudger implements WordPlacer, WordNudger {

	  Area area;
	  float minX;
	  float minY;
	  float maxX;
	  float maxY;
	  Random random;

	  public EnhancedShapeBasedPlacerNudger(Shape shape) {
	    this.area = new Area(shape);
	    random = new Random();
	    Rectangle2D areaBounds = area.getBounds2D();
	    this.minX = (float) areaBounds.getMinX();
	    this.minY = (float) areaBounds.getMinY();
	    this.maxX = (float) areaBounds.getMaxX();
	    this.maxY = (float) areaBounds.getMaxY();
	  }

	  public PVector place(Word w, int rank, int count, int ww, int wh, int fw, int fh) {
	    w.setProperty("width", ww);
	    w.setProperty("height", wh);

	    return aSpot(w);
	  }

	  public PVector nudgeFor(Word word, int attempt) {
	    PVector target = word.getTargetPlace();
	    return PVector.sub(aSpot(word), target);
	  }

	  private PVector aSpot(Word word) {
	    float ww = (Integer) word.getProperty("width");
	    float wh = (Integer) word.getProperty("height");

	    for (int i = 0; i < 1000; i++) {
	      float newX = PApplet.map(new Double(random.nextGaussian() * Math.pow(1 - word.weight, 3)).floatValue(),
	    		  -2f, 2f,
	    		  minX, maxX);
	      float newY = randomBetween(minY, maxY);

	      if (area.contains(newX, newY, ww, wh)) {
	        return new PVector(newX, newY);
	      }
	    }

	    return new PVector(-10000, -10000);
	  }

	  private float randomBetween(float a, float b) {
	    return a + random.nextFloat() * (b - a);
	  }
	}
