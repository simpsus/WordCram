package contrib;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import processing.core.PImage;
import contrib.ImageAnalyzer.Pair;

public class ImageAnalyzer implements Iterable<Pair<Integer,PImage>>{
	
	public class Pair<K, V> {

	    private final K first;
	    private final V second;

	    public Pair(K first, V second) {
	        this.first = first;
	        this.second = second;
	    }

	    public K getFirst() {
	        return first;
	    }

	    public V getSecond() {
	        return second;
	    }

	}

	private Map<Integer, Set<Pair<Integer,Integer>>> colorDistribution;
	private Set<Integer> colors;
	private Set<Integer> stopColors;
	private Set<Pair<Integer, PImage>> images;
	private PImage image;

	public ImageAnalyzer(PImage image) {
		this.image = image;
		stopColors = new HashSet<Integer>();
		images = new HashSet<Pair<Integer,PImage>>();
		colorDistribution = new HashMap<Integer, Set<Pair<Integer,Integer>>>();
		int color;
		for (int w = 0;w<image.width;w++) {
			for (int h=0;h<image.height;h++) {
				color = image.get(w,h);
				if (!colorDistribution.containsKey(color)) {
					colorDistribution.put(color, new HashSet<Pair<Integer,Integer>>());
				}
				colorDistribution.get(color).add(new Pair<Integer,Integer>(w,h));
			}
		}
		colors = colorDistribution.keySet();
	}
	
	public int getColorCount() {
		return colors.size();
	}
	
	public ImageAnalyzer withStopColor(int color) {
		stopColors.add(color);
		return this;
	}
	
	public ImageAnalyzer createImages() {
		PImage currentImage;
		for (Integer color: colors) {
			if (stopColors.contains(color)) {
				continue;
			}
			currentImage = new PImage(image.width, image.height);
			for (Pair<Integer,Integer> point: colorDistribution.get(color)) {
				currentImage.set(point.getFirst(), point.getSecond(), color);
			}
			images.add(new Pair<Integer,PImage>(color, currentImage));
		}
		return this;
	}

	@Override
	public Iterator<Pair<Integer, PImage>> iterator() {
		return images.iterator();
	}
	
	
}
