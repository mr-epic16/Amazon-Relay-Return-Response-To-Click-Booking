package epl.amazonrelay;

public class Test {
	public static void main(String[] args) {

		int click = 0;
		for (int i = 0; i < 12; i++) {
			System.out.println(" index " + i);
			int ab = (click) + i;
			System.out.println(" reduce " + ab);
			click = -1;

		}

	}
}
