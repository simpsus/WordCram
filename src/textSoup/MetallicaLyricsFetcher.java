package textSoup;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MetallicaLyricsFetcher {

	Document doc;
	Elements links = new Elements();
	String url = "http://www.encycmet.com/lyrics/";
	String result = "";
	String charset = "UTF-8";

	public MetallicaLyricsFetcher() throws IOException {
	}
	
	public void writeToFile(String fileName) throws FileNotFoundException, UnsupportedEncodingException {
		System.out.println("starting to write");
		PrintWriter writer = new PrintWriter(fileName, charset);
		System.out.println("writing!");
		writer.println(result);
		System.out.println("closing!");
		writer.close();
		System.out.println("done!");
	}

	public void fetch() throws IOException {
		doc = Jsoup.connect(url).get();
		for (Element link : doc.select("a")) {
			if (link.attr("href").startsWith("lyr")) {
				links.add(link);
			}
		}
		Document currentDoc;
		int total = links.size();
		int count = 0;
		for (Element link : links) {
			System.out.println("Song " + count++ + " of " + total);
			currentDoc = Jsoup.parse(new URL(url + link.attr("href")).openStream(), charset, url );
			boolean first = true;
			for (Element table : currentDoc.select("td.vanlig")) {
				if (first) {
					result += table.text();
					first = false;
				}
			}
		}
	}
	
	public String getResult() {
		return result;
	}

	public static void main(String[] args) {
		try {
			MetallicaLyricsFetcher mlf = new MetallicaLyricsFetcher();
			mlf.fetch();
			System.out.println(mlf.getResult());
			mlf.writeToFile("/home/bastian/Dropbox/Camera Uploads/crams/ideas/metallicaLyrics.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
