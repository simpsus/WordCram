package sketch;

import contrib.EnhancedShapeBasedPlacerNudger;
import contrib.ImageBasedWordColorer;

public class FreedomDoveSketch extends AbstractSketch {

	EnhancedShapeBasedPlacerNudger placer;
	
	@Override
	public int getShapeColor() {
		return color(0,0,0);
	}
	
	@Override
	protected void prepareCram() {
		cram.minShapeSize(3);
		cram.sizedByWeight(10, 20);
		placer = new EnhancedShapeBasedPlacerNudger(imageShape);
		cram.withPlacer(placer).withNudger(placer);
		cram.withFont("Futura-CondensedExtraBold");
		ImageBasedWordColorer ibwc = new ImageBasedWordColorer(loadImage(
				"/home/bastian/Dropbox/Camera Uploads/crams/ideas/freedom_dove_colorer.png"
				));
		ibwc.method = ImageBasedWordColorer.METHOD_MAJORITY;
		cram.withColorer(ibwc);
	}

	@Override
	public String getImageLocation() {
		return "/home/bastian/Dropbox/Camera Uploads/crams/ideas/freedom_dove_placer.png";
	}

	@Override
	public int getWidth() {
		return 1306;
	}

	@Override
	public int getHeight() {
		return 1242;
	}

	@Override
	public String getTextLocation() {
		return "/home/bastian/Dropbox/Camera Uploads/crams/ideas/humanist_manifesto_i.txt";
	}

}
