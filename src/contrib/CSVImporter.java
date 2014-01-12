package contrib;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import wordcram.Word;
import au.com.bytecode.opencsv.CSVReader;

public class CSVImporter {
	
	CSVReader reader;
	List<String[]> data;
	Map<Index,Integer> indices;
	public enum Index {WORD,WEIGHT}
	String[] header;

	public CSVImporter(String fileName) {
		try {
			indices = new HashMap<Index,Integer>();
			reader = new CSVReader(new FileReader(fileName),';');
			data = reader.readAll();
			header = data.get(0);
			data.remove(header);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addIndex(Index index, Integer value) {
		indices.put(index, value);
	}
	
	public Word[] getWords() {
		Word[] result = new Word[data.size()];
		Word current;
		int index = 0;
		for (String[] line:data) {
			current = new Word(line[indices.get(Index.WORD)],0f);
			current.weight = Float.parseFloat(line[indices.get(Index.WEIGHT)]);
			result[index++] = current;
		}
		return result;
	}

}
