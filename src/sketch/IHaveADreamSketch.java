package sketch;

import java.io.FileNotFoundException;
import java.util.Arrays;

import contrib.StandardConsoleObserver;
import processing.core.PApplet;
import processing.core.PFont;
import wordcram.WordCram;

public class IHaveADreamSketch extends PApplet{

	public IHaveADreamSketch() {
		WordCram cram = new WordCram(this);
//		System.out.println(Arrays.toString(PFont.list()));
		try {
			cram.fromTextFile("/home/bastian/Dropbox/Camera Uploads/crams/ideas/IHaveADream.txt")
			.toSvg("/tmp/out.svg", 1500, 800)
			.sizedByWeight(5, 100)
			.withObserver(new StandardConsoleObserver(1))
			.withFont("Droid Sans Bold")
			.withStopWords("Negro")
			.drawAll();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new IHaveADreamSketch();
	}

}
