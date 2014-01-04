package sketch;


public class HetfieldSketch extends AbstractSketch {

	@Override
	protected void prepareCram() {
		cram.minShapeSize(1)
		.withFont("Futura-CondensedExtraBold")
		.sizedByWeight(1, 100);
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
		return 800;
	}

	@Override
	public String getImageLocation() {
		return "/home/bastian/Dropbox/Camera Uploads/crams/ideas/Metallica-metallica-stencil.png";
	}

	@Override
	public String getTextLocation() {
		return "/home/bastian/Dropbox/Camera Uploads/crams/ideas/metallicaLyrics.txt";
	} 
		

}
