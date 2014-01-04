package sketch;

import contrib.EnhancedShapeBasedPlacerNudger;
import contrib.ImageBasedWordColorer;

public class DeutschlandSketch extends AbstractSketch {

	EnhancedShapeBasedPlacerNudger placer;
	
	@Override
	public int getShapeColor() {
		return color(0,0,0);
	}
	
	@Override
	protected void prepareCram() {
		cram.minShapeSize(6);
		cram.sizedByWeight(5, 120);
		placer = new EnhancedShapeBasedPlacerNudger(imageShape);
		cram.withPlacer(placer).withNudger(placer);
		cram.withFont("Futura-CondensedExtraBold");
		ImageBasedWordColorer ibwc = new ImageBasedWordColorer(loadImage("/home/bastian/Dropbox/Camera Uploads/crams/ideas/germany_colored.png"));
		ibwc.method = ImageBasedWordColorer.METHOD_MAJORITY;
		cram.withColorer(ibwc);
	}

	@Override
	public String getImageLocation() {
		return "/home/bastian/Dropbox/Camera Uploads/crams/ideas/germany_black.png";
	}

	@Override
	public int getWidth() {
		return 1632;
	}

	@Override
	public int getHeight() {
		return 2176;
	}

	@Override
	public String getTextLocation() {
		return "/home/bastian/Downloads/20131127_koalit.txt";
	}

}
