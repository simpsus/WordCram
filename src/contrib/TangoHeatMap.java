package contrib;

import processing.core.PApplet;
import wordcram.Word;
import wordcram.WordColorer;

public class TangoHeatMap {
	
	TangoColorEnum[] heatMap;
	public static final int DARK = 0;
	public static final int MIDDLE = 1;
	public static final int BRIGHT = 2;
	PApplet parent;
	
	public TangoHeatMap(PApplet parent, int darkMiddleBright) {
		this.parent = parent;
		if (darkMiddleBright == DARK) {
			heatMap = new TangoColorEnum[]{TangoColorEnum.plumL,TangoColorEnum.skyBlueL,
					TangoColorEnum.chameleonL,TangoColorEnum.butterL,TangoColorEnum.orangeL,
					TangoColorEnum.scarletRedL};
		} else if (darkMiddleBright == MIDDLE) {
			heatMap = new TangoColorEnum[]{TangoColorEnum.plumM,TangoColorEnum.skyBlueM,
					TangoColorEnum.chameleonM,TangoColorEnum.butterM,TangoColorEnum.orangeM,
					TangoColorEnum.scarletRedM};
		} else if (darkMiddleBright == BRIGHT) {
			heatMap = new TangoColorEnum[]{TangoColorEnum.plumH,TangoColorEnum.skyBlueH,
					TangoColorEnum.chameleonH,TangoColorEnum.butterH,TangoColorEnum.orangeH,
					TangoColorEnum.scarletRedH};
		} else throw new RuntimeException ("Unsupported darkMiddleBright " + darkMiddleBright);
	}
	
	public WordColorer getColorer() {
		return new WordColorer() {
			@Override
			public int colorFor(Word word) {
				int position = new Float(5*word.weight).intValue();
				return parent.color(heatMap[position].hex);
			}
		};
	}
}
