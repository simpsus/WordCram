package sketch;

import contrib.CachedEnhancedShapeBasedPlacerNudger;
import contrib.ContribSizers;
import contrib.ImageBasedWordColorer;
import contrib.TangoWordColorer;

public class MinimalSketch extends AbstractSketch {
	
	CachedEnhancedShapeBasedPlacerNudger cspn;
	TangoWordColorer colorer;

	public MinimalSketch() {
		standardPlacer = false;
	}

	@Override
	public int getShapeColor() {
		return color(0,0,0);
	}

	@Override
	public int getWidth() {
		return 437;
	}

	@Override
	public int getHeight() {
		return 532;
	}

	@Override
	protected void prepareCram() {
		cspn = new CachedEnhancedShapeBasedPlacerNudger(imageShape);
		cram.withPlacer(cspn).withNudger(cspn);
		colorer = new TangoWordColorer(this);
		colorer.strategy = TangoWordColorer.STRATEGY_WEIGHTED_ALUMINIUM;
//		cram.withColorer(new TangoHeatMap(this,TangoHeatMap.BRIGHT).getColorer());
		ImageBasedWordColorer ibwc = new ImageBasedWordColorer(loadImage("/home/bastian/colorRect.png"));
		ibwc.method = ImageBasedWordColorer.METHOD_MAJORITY;
		cram.withColorer(ibwc);
		cram.withSizer(ContribSizers.byWeightSquareRoot(10, 20));
		cram.minShapeSize(9);
		cram.maxNumberOfWordsToDraw(200);
	}

	@Override
	public String getImageLocation() {
		return "/home/bastian/blackrect.png";
	}

	@Override
	public String getTextLocation() {
		return "/home/bastian/text.txt";
	}

}
