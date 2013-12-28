package contrib;

import java.util.Random;

import processing.core.PApplet;
import wordcram.Observer;
import wordcram.Word;
import wordcram.WordColorer;

public class TangoWordColorer implements WordColorer{

	public static final int STRATEGY_ROUND_ROBIN = 1;
	public static final int STRATEGY_RANDOM = 2;
	public static final int STRATEGY_WEIGHTED_ALUMINIUM = 3;
	
	PApplet parent;
	public int strategy;
	Random random;
	TangoColorEnum[] colors;
	int index;
	
	public TangoWordColorer(PApplet parent) {
		this.parent = parent;
		random = new Random();
		colors = TangoColorEnum.values();
		index = 0;
		strategy = STRATEGY_ROUND_ROBIN;
	}
	
	@Override
	public int colorFor(Word word) {
		if (strategy == STRATEGY_ROUND_ROBIN) {
			index = (index + 1)%(colors.length - 1);
			return parent.color(colors[index].hex);
		} else if (strategy == STRATEGY_RANDOM) {
			return parent.color(colors[random.nextInt(colors.length)].hex);
		} else if (strategy == STRATEGY_WEIGHTED_ALUMINIUM) {
			int suffix = new Float(6*word.weight).intValue();
			return parent.color(TangoColorEnum.valueOf("Aluminum" + suffix).hex);
		}
		throw new RuntimeException("Unsupported Strategy " + strategy);
	}



}
