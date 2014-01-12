package sketch;

import contrib.ImageBasedWordColorer;


public class MetallicaSketch extends AbstractSketch {

	@Override
	protected void prepareCram() {
		cram.minShapeSize(1)
		.withFont("Futura-CondensedExtraBold")
		.withColorer(new ImageBasedWordColorer(loadImage("/home/bastian/Dropbox/Camera Uploads/crams/ideas/Metallica-metallica.jpeg")))
		.sizedByWeight(3, 30);
	}

	@Override
	public int getShapeColor() {
		return color(0,0,0);
	}

	@Override
	public int getWidth() {
		return 900;
	}

	@Override
	public int getHeight() {
		return 700;
	}

	@Override
	public String getImageLocation() {
		return "/home/bastian/Dropbox/Camera Uploads/crams/ideas/Metallica-metallica-stencil_2.png";
	}

	@Override
	public String getTextLocation() {
		return "/home/bastian/Dropbox/Camera Uploads/crams/ideas/metallicaLyrics.txt";
	} 
	

}
