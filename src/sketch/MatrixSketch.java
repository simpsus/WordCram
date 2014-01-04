package sketch;

public class MatrixSketch extends AbstractSketch {
	
	

	public MatrixSketch() {
		width = 600;
		height = 1000;
		
	}

	@Override
	protected void prepareCram() {
		cram.minShapeSize(3);
		cram.sizedByWeight(8, 100);
		cram.withStopWords("INT V.O MAIN ROOM DAY DECK");
		cram.upperCase();
	}

	@Override
	public int getShapeColor() {
		return color(0,0,0);
	}

	@Override
	public int getWidth() {
		return 600;
	}

	@Override
	public int getHeight() {
		return 1000;
	}

	@Override
	public String getImageLocation() {
		return "/home/bastian/Dropbox/Camera Uploads/crams/ideas/agent_smith.png";
	}

	@Override
	public String getTextLocation() {
		return "/home/bastian/Dropbox/Camera Uploads/crams/ideas/matrix_screenplay.txt";
	}

}
