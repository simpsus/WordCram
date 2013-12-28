package contrib;

import processing.core.PApplet;
import wordcram.Word;
import wordcram.WordColorer;

public enum TangoColorEnum {
	
	butterH(0xfce94f), butterM(0xedd400), butterL(0xc4a000), 
	orangeH(0xfcaf3e), orangeM(0xf57900), orangeL(0xce5c00),
	chocolateH(0xe9b06e), chocolateM(0xc17d11), chocolateL(0x8f5902),
	chameleonH(0x8ae234), chameleonM(0x73d216), chameleonL(0x4e9a06),
	skyBlueH(0x729fcf), skyBlueM(0x3465a4), skyBlueL(0x204a87),
	plumH(0xad7fa8), plumM(0x75507b), plumL(0x5c3566),
	scarletRedH(0xef2929), scarletRedM(0xcc0000), scarletRedL(0xa40000),
	Aluminum1(0xeeeeec),Aluminum2(0xd3d7cf),Aluminum3(0xbabdb6),
	Aluminum4(0x888a85),Aluminum5(0x555753),Aluminum6(0x2e3436);
	
	public int hex;
	
	private TangoColorEnum(int hex) {
		this.hex = hex;
	}
	
}
