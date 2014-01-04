package sketch;

import java.awt.Shape;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import contrib.StandardConsoleObserver;
import processing.core.PApplet;
import processing.core.PImage;
import wordcram.ImageShaper;
import wordcram.ShapeBasedPlacer;
import wordcram.Word;
import wordcram.WordCram;
import wordcram.WordSkipReason;

public abstract class AbstractSketch extends PApplet{
	
	int progressTick = 100;
	PImage image;
	Shape imageShape;
	WordCram cram;
	boolean standardPlacer;
	StandardConsoleObserver observer;
	
	public AbstractSketch() {
		image = loadImage(getImageLocation());
		imageShape = new ImageShaper().shape(image, getShapeColor());
		standardPlacer = true;
		observer = new StandardConsoleObserver();
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
	
	
	public void draw() {
		cram = new WordCram(this);
		cram.withObserver(observer);
		if (standardPlacer) {
			ShapeBasedPlacer shp = new ShapeBasedPlacer(imageShape);
			cram.withNudger(shp).withPlacer(shp);
		}
		try {
			cram.fromTextFile(getTextLocation());
			cram.toSvg("/tmp/out.svg", width, height);
			prepareCram();
			cram.drawAll();
		}catch (IOException x) {
				x.printStackTrace();
		}
		noLoop();
	}
	


}
