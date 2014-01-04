package sketch;

import contrib.EnhancedShapeBasedPlacerNudger;
import contrib.ImageBasedWordColorer;

public class TrinityHeartSketch extends AbstractSketch {

	EnhancedShapeBasedPlacerNudger placer;
	
	@Override
	public int getShapeColor() {
		return color(0,0,0);
	}
	
	@Override
	protected void prepareCram() {
		cram.minShapeSize(3);
		cram.sizedByWeight(4, 100);
		placer = new EnhancedShapeBasedPlacerNudger(imageShape);
		cram.withPlacer(placer).withNudger(placer);
		cram.withFont("Futura-CondensedExtraBold");
		ImageBasedWordColorer ibwc = new ImageBasedWordColorer(loadImage(
				"/home/bastian/Dropbox/Camera Uploads/crams/ideas/trinity_heart_colored.png"
				));
		ibwc.method = ImageBasedWordColorer.METHOD_MAJORITY;
		cram.withColorer(ibwc);
	}

	@Override
	public String getImageLocation() {
		return "/home/bastian/Dropbox/Camera Uploads/crams/ideas/trinity_heart_black.png";
	}

	@Override
	public int getWidth() {
		return 820;
	}

	@Override
	public int getHeight() {
		return 800;
	}

	@Override
	public String getTextLocation() {
		return "/home/bastian/Dropbox/Camera Uploads/crams/ideas/humanist_manifesto_i.txt";
	}

}
