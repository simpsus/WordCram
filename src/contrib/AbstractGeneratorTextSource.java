package contrib;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import wordcram.WordCram;

public abstract class AbstractGeneratorTextSource {
	
	List<String> baseWords;
	List<String> generatedWords;
	
	public AbstractGeneratorTextSource() {
		baseWords = new ArrayList<String>();
		generatedWords = new ArrayList<String>();
	}
	
	public AbstractGeneratorTextSource withBaseWords(String[] words) {
		for (String w: words) {
			baseWords.add(w);
		}
		return this;
	}
	
	public AbstractGeneratorTextSource fromTextFile(String adress) {
		Path path = FileSystems.getDefault().getPath(adress);
	    try {
			BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
			String line;
			while ((line = reader.readLine()) != null) {
				for (String word: line.split(" ")) {
					baseWords.add(word);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	    return this;
	}
	
	public abstract WordCram dressWordCram(WordCram cram);

}
