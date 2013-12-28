package contrib;

import processing.core.PApplet;
import wordcram.Word;
import wordcram.WordColorer;

public class WeightWordColorer implements WordColorer {
	
	PApplet parent;
	
	public WeightWordColorer(PApplet parent) {
		this.parent = parent;
	}

	@Override
	public int colorFor(Word word) {
		int value = new Float(255 - (255*word.weight)).intValue();
		return parent.color(value);
	}

}
