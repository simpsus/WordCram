package sketch;


public class MetallicaSketch extends AbstractSketch {

	@Override
	protected void prepareCram() {
		cram.minShapeSize(5)
		.withFont("Futura-CondensedExtraBold")
		.withColor(color(11, 0, 0))
		.sizedByWeight(8, 40);
	}

	@Override
	public int getShapeColor() {
		return color(0,0,0);
	}

	@Override
	public int getWidth() {
		return 2250;
	}

	@Override
	public int getHeight() {
		return 600;
	}

	@Override
	public String getImageLocation() {
		return "/home/bastian/Dropbox/Camera Uploads/crams/ideas/metallica_logo.png";
	}

	@Override
	public String getTextLocation() {
		return "/home/bastian/Dropbox/Camera Uploads/crams/ideas/metallicaLyrics.txt";
	} 
		

}
