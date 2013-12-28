package contrib;

import java.util.ArrayList;
import java.util.List;

import wordcram.Word;
import wordcram.WordCram;

public class WeightFunctionWordGenerator extends AbstractGeneratorTextSource {

	final int MAXPOWER = 20;
	
	public enum WeightFunction {
		descendingDouble, descendingLinear
	}

	public WeightFunction function = WeightFunction.descendingDouble;

	@Override
	public WordCram dressWordCram(WordCram cram) {
		int count = baseWords.size();
		List<Word> list = new ArrayList<Word>();
		Word word;
		int i;
		for (String w : baseWords) {
			i = baseWords.indexOf(w);
			word = new Word(w, calculateFunction(count, i));
			list.add(word);
		}
		cram.fromWords(list.toArray(new Word[list.size()]));
		return cram;
	}

	private float calculateFunction(int total, int position) {
		total = Math.min(total, MAXPOWER);
//		System.out.println(total +" " + position);
		switch (function) {
		case descendingDouble:
			//Search for the right bucket
			for (int i=0;i<=position;i++) {
				if (position < Math.pow(2, i)) {
					float result = (float) Math.pow(2, total-i);
//					System.out.println(result);
					return result;
				}
			}
		case descendingLinear:
			return (total-position);
		}
		System.out.println("unknown function " + function);
		return 0;
	}

}
