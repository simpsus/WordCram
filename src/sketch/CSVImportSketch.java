package sketch;

import java.io.FileNotFoundException;
import java.util.Arrays;

import processing.core.PApplet;
import wordcram.Word;
import wordcram.WordCram;
import contrib.CSVImporter;
import contrib.CSVImporter.Index;
import contrib.StandardConsoleObserver;

public class CSVImportSketch extends PApplet{

	public CSVImportSketch() {
		WordCram cram = new WordCram(this);
		CSVImporter csvImport = new CSVImporter("/home/bastian/Dropbox/Camera Uploads/crams/ideas/genres_sample.csv");
		csvImport.addIndex(Index.WORD, 1);
		csvImport.addIndex(Index.WEIGHT, 4);
		try {
			Word[] words = csvImport.getWords();
			StandardConsoleObserver observer = new StandardConsoleObserver(1);
			System.out.println(Arrays.toString(words));
			observer.wordsCounted(words);
			cram.fromWords(words)
			.toSvg("/tmp/out.svg", 1500, 800)
			.withObserver(observer)
			.sizedByWeight(10,70)
			.maxNumberOfWordsToDraw(200)
			.withFont("Droid Sans Bold");
			cram.drawAll();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new CSVImportSketch();
	}

}
