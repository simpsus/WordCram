package sketch;

import java.awt.Shape;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import processing.core.PApplet;
import processing.core.PImage;
import wordcram.ImageShaper;
import wordcram.ShapeBasedPlacer;
import wordcram.Word;
import wordcram.WordCram;
import wordcram.WordSkipReason;

public abstract class AbstractSketch extends PApplet{
	
	List<Word> skipped, drawn;
	int progressTick = 100;
	PImage image;
	Shape imageShape;
	ShapeBasedPlacer placer;
	WordCram cram;
	
	public AbstractSketch() {
		skipped = new ArrayList<Word>();
		drawn = new ArrayList<Word>();
	}
	
	public abstract int getShapeColor();
	public abstract int getWidth();
	public abstract int getHeight();
	protected abstract void prepareCram();
	public abstract String getImageLocation();
	public abstract String getTextLocation();
	
	public void setup() {
		size(getWidth(), getHeight());
	}

	
	public void wordSkipped(Word word) {
		skipped.add(word);
	}

	public void wordDrawn(Word word) {
		drawn.add(word);
	}
	
	protected void drawProgress(WordCram cram) {
		int i = 0;
		while (cram.hasMore()) {
			cram.drawNext();
			i++;
			if (i == progressTick) {
				i = 0;
				System.out.println(cram.getProgress());
			}
		}
		cram.getRenderer().finish();
		endDraw();
	}
	
	public void endDraw() {
		System.out.println("done!!");
		System.out.println("Drew: " + drawn.size());
		System.out.println("Skipped: " + skipped.size());
		Map<WordSkipReason,Integer> reasons = new HashMap<WordSkipReason, Integer>();
		for (Word word: skipped) {
			if (!reasons.containsKey(word.wasSkippedBecause())) {
				reasons.put(word.wasSkippedBecause(), 1);
			} else {
				reasons.put(word.wasSkippedBecause(), reasons.get(word.wasSkippedBecause()) + 1);
			}
		}
		for (Entry<WordSkipReason,Integer> entry:reasons.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	}
	
	public void draw() {
		PImage image = loadImage(getImageLocation());
		imageShape = new ImageShaper().shape(image, getShapeColor());
		placer = new ShapeBasedPlacer(imageShape);
		try {
			cram = new WordCram(this);
			cram.fromTextFile(getTextLocation());
			cram.withPlacer(placer).withNudger(placer);
			cram.toSvg("/tmp/out.svg", width, height);
			prepareCram();
			drawProgress(cram);
		}catch (IOException x) {
				x.printStackTrace();
		}
		noLoop();
	}
	


}
