package contrib;

import wordcram.BBTreeBuilder;
import wordcram.Observer;
import wordcram.RenderOptions;
import wordcram.Word;
import wordcram.WordAngler;
import wordcram.WordColorer;
import wordcram.WordCramEngine;
import wordcram.WordFonter;
import wordcram.WordNudger;
import wordcram.WordPlacer;
import wordcram.WordRenderer;
import wordcram.WordShaper;
import wordcram.WordSizer;

public class RendererAwareWordCramEngine extends WordCramEngine {

	public RendererAwareWordCramEngine(WordRenderer renderer, Word[] words,
			WordFonter fonter, WordSizer sizer, WordColorer colorer,
			WordAngler angler, WordPlacer placer, WordNudger nudger,
			WordShaper shaper, BBTreeBuilder bbTreeBuilder,
			RenderOptions renderOptions, Observer observer) {
		super(renderer, words, fonter, sizer, colorer, angler, placer, nudger,
				shaper, bbTreeBuilder, renderOptions, observer);
	}

	protected void drawAllWithoutFinish() {
		observer.beginDraw();
    	while(hasMore()) {
            drawNext();
        }
    	observer.endDraw();
    }
	
}
