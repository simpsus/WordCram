package contrib;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import processing.core.PImage;
import processing.core.PVector;
import wordcram.Word;
import wordcram.WordColorer;

public class ImageBasedWordColorer implements WordColorer {
	
	public static final int METHOD_MAJORITY = 1;
	public static final int METHOD_ORIGIN = 2;
	PImage image;
	public int method = METHOD_MAJORITY;

	public ImageBasedWordColorer(PImage image) {
		super();
		this.image = image;
	}

	@Override
	public int colorFor(Word word) {
		if (method == METHOD_ORIGIN) {
			return colorMethodMajority(word);
		} else if (method == METHOD_MAJORITY) {
			return colorMethodOrigin(word);
		} 
		throw new RuntimeException("Unsupported Coloring Method " + method);
	}
	
	private int colorMethodMajority(Word word) {
		PVector start = word.getRenderedPlace();
		float width = word.getRenderedWidth();
		float height = word.getRenderedHeight();
		Map<Integer,Integer> count = new HashMap<Integer,Integer>();
		int i = new Float(start.x).intValue();
		int j = new Float(start.y).intValue();
		int color = 0;
		while (i < start.x+width) {
			j = new Float(start.y).intValue();
			while (j < start.y+height) {
				color = image.get(i,j);
				if (!count.containsKey(color)) {
					count.put(color, 0);
				}
				count.put(color, count.get(color) + 1);
				j++;
			}
			i++;
		}
		int current = Integer.MIN_VALUE;
		for (Entry<Integer,Integer> entry:count.entrySet()) {
			if (entry.getValue()>current) {
				current = entry.getValue();
				color = entry.getKey();
			}
		}
		return color;
	}
	
	private int colorMethodOrigin(Word word) {
		PVector place = word.getRenderedPlace();
		return image.get(new Float(place.x).intValue(), new Float(place.y).intValue());
	}

}
