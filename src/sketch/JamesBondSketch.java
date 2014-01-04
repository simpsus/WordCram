package sketch;

import contrib.CachedEnhancedShapeBasedPlacerNudger;
import contrib.EnhancedShapeBasedPlacerNudger;


public class JamesBondSketch extends AbstractSketch {
	
	EnhancedShapeBasedPlacerNudger cspn;
	CachedEnhancedShapeBasedPlacerNudger ccspn;
	
	public JamesBondSketch() {
		standardPlacer = false;
	}
	
	

	@Override
	public int getShapeColor() {
		return color(0,0,0);
	}

	@Override
	public int getWidth() {
		return 1578;
	}

	@Override
	public int getHeight() {
		return 1190;
	}

	@Override
	protected void prepareCram() {
		cram.lowerCase();
		cram.minShapeSize(4);
		cram.sizedByWeight(4, 200);
		cspn = new EnhancedShapeBasedPlacerNudger(imageShape);
		ccspn = new CachedEnhancedShapeBasedPlacerNudger(imageShape);
		cram.withPlacer(ccspn).withNudger(ccspn);
//		cram.withPlacer(ccspn).withNudger(ccspn);
	}

	@Override
	public String getImageLocation() {
		return "/home/bastian/Dropbox/Camera Uploads/crams/ideas/JamesBond.png";
	}

	@Override
	public String getTextLocation() {
//		return "/home/bastian/Dropbox/Camera Uploads/crams/ideas/jamesBondTNDScreenplay.txt";
		return "/home/bastian/Dropbox/Camera Uploads/crams/ideas/devilwithjamesbond.txt";
	}

}
