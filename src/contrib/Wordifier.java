package contrib;

import java.io.FileNotFoundException;

import processing.core.PApplet;
import processing.core.PImage;
import wordcram.ImageShaper;
import wordcram.ShapeBasedPlacer;
import wordcram.Sizers;
import wordcram.WordRenderer;
import contrib.ImageAnalyzer.Pair;



public class Wordifier extends PApplet{

	PImage source;
	ImageAnalyzer analyzer;
	WordRenderer renderer = null;
	RendererAwareWordCram cram = null;
	String filename = "/tmp/out.svg";
	StandardConsoleObserver observer;
	
	public Wordifier(String sourcePath) {
		source = loadImage(sourcePath);
		analyzer = new ImageAnalyzer(source);
		analyzer.createImages();
	}
	
	public void wordify() {
		ShapeBasedPlacer placer;
		ColorName colorName;
		ContribWordCreator cts = new ContribWordCreator();
		cts.setCount(100);
		cts.setBuckets(10);
		cts.setStrategy(ContribWordCreator.SelectionStrategy.ROUND_ROBIN);
		cts.setSizer(ContribSizers.byWeightOrRankPowered(1, 100, false, 1));
		for (Pair<Integer,PImage> pair: analyzer) {
			cram = new RendererAwareWordCram(this);
			observer = new StandardConsoleObserver();
			observer.withLevel(2);
			cram.withObserver(observer);
			if (renderer == null) {
				try {
					cram.toSvg(filename, source.width, source.height);
					renderer = cram.getRenderer();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					return;
				}
			} else {
				cram.withRenderer(renderer);
			}
			placer = new ShapeBasedPlacer(new ImageShaper().shape(pair.getSecond(), pair.getFirst()));
			cram.withPlacer(placer).withNudger(placer);
			cram.withColor(pair.getFirst());
			colorName = new ColorName(pair.getFirst()).nearestNeighbor(ColorName.builtinNames());
			cts.clearWords();
			cts.addString(colorName.name);
//			cram.fromWords(cts.getWords());
			cram.fromWebPage("http://www.spiegel.de");
			cram.minShapeSize(1);
			cram.withSizer(Sizers.byRank(1, 100));
			cram.drawAllWithoutFinish();
		}
		renderer.finish();
	}
	
	public static void main(String[] args) {
		Wordifier w = new Wordifier("/home/bastian/trivial.png");
		w.wordify();
	}
}
