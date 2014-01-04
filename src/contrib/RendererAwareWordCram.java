package contrib;

import processing.core.PApplet;
import wordcram.Anglers;
import wordcram.BBTreeBuilder;
import wordcram.Colorers;
import wordcram.Fonters;
import wordcram.Placers;
import wordcram.Sizers;
import wordcram.SpiralWordNudger;
import wordcram.WordCounter;
import wordcram.WordCram;
import wordcram.WordCramEngine;
import wordcram.WordRenderer;
import wordcram.WordShaper;
import wordcram.WordSorterAndScaler;

public class RendererAwareWordCram extends WordCram {

	public RendererAwareWordCram(PApplet parent) {
		super(parent);
	}
	
    public WordCram withRenderer(WordRenderer renderer) {
    	this.renderer = renderer;
    	return this;
    }
    
    public WordRenderer getRenderer() {
    	return renderer;
    }
    
    @Override
    protected WordCramEngine getWordCramEngine() {
        if (wordCramEngine == null) {
            if (words == null && !textSources.isEmpty()) {
                String text = joinTextSources();

                text = textCase == TextCase.Lower ? text.toLowerCase()
                     : textCase == TextCase.Upper ? text.toUpperCase()
                     : text;

                words = new WordCounter().withExtraStopWords(extraStopWords).shouldExcludeNumbers(excludeNumbers).count(text, renderOptions);
                observer.wordsCounted(words);
                if (words.length == 0) {
                	warnScripterAboutEmptyWordArray();
                }
            }
            words = new WordSorterAndScaler().sortAndScale(words);

            if (fonter == null) fonter = Fonters.alwaysUse(parent.createFont("sans", 1));
            if (sizer == null) sizer = Sizers.byWeight(5, 70);
            if (colorer == null) colorer = Colorers.alwaysUse(parent.color(0));
            if (angler == null) angler = Anglers.mostlyHoriz();
            if (placer == null) placer = Placers.horizLine();
            if (nudger == null) nudger = new SpiralWordNudger();

            WordShaper shaper = new WordShaper(renderOptions.rightToLeft); 
            wordCramEngine = new RendererAwareWordCramEngine(renderer, words, fonter, sizer, colorer, angler, placer, nudger, shaper, new BBTreeBuilder(), renderOptions, observer);
        }
        return wordCramEngine;
    }

	public void drawAllWithoutFinish() {
		((RendererAwareWordCramEngine) getWordCramEngine()).drawAllWithoutFinish();
	}
}
