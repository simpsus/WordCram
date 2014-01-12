package contrib;

import wordcram.Word;
import wordcram.WordSizer;

public class ContribSizers {
	
	public static WordSizer byWeight(final int minSize, final int maxSize) {
		return byWeightOrRankPowered(minSize, maxSize, true, 1);
	}
	
	public static WordSizer byRank(final int minSize, final int maxSize) {
		return byWeightOrRankPowered(minSize, maxSize, false, 1);
	}

	public static WordSizer byWeightSquare(final int minSize, final int maxSize) {
		return byWeightOrRankPowered(minSize, maxSize, true, 2);
	}
	
	public static WordSizer byRankSquare(final int minSize, final int maxSize) {
		return byWeightOrRankPowered(minSize, maxSize, false, 2);
	}
	
	public static WordSizer byRankCubic(final int minSize, final int maxSize) {
		return byWeightOrRankPowered(minSize, maxSize, false, 3);
	}
	
	public static WordSizer byWeightCubic(final int minSize, final int maxSize) {
		return byWeightOrRankPowered(minSize, maxSize, true, 3);
	}
	
	public static WordSizer byRankSquareRoot(final int minSize, final int maxSize) {
		return byWeightOrRankPowered(minSize, maxSize, false, 0.5f);
	}
	
	public static WordSizer byWeightSquareRoot(final int minSize, final int maxSize) {
		return byWeightOrRankPowered(minSize, maxSize, true, 0.5f);
	}
	
	public static WordSizer byWeightOrRankPowered(final int minSize, final int maxSize, final boolean weight, final float power) {
		return new WordSizer() {
			@Override
			public float sizeFor(Word word, int wordRank, int wordCount) {
				return (float) (minSize + (maxSize-minSize) * Math.pow(weight ? word.weight : (float)wordRank/wordCount, power));
			}
			
		};
	}
}
