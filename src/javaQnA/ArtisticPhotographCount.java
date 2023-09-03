package javaQnA;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ArtisticPhotographCount {
	enum PhotographElement {
		PHOTOGRAPHER("P"), ACTOR("A"), BACKDROP("B"), EMPTY(".");

		String symbol;

		PhotographElement(String symbol) {
			this.symbol = symbol;
		}

		private static final Map<String, PhotographElement> LOOKUP = Arrays.stream(PhotographElement.values())
				.collect(Collectors.toMap(PhotographElement::getSymbol, Function.identity()));

		String getSymbol() {
			return symbol;
		}

		static PhotographElement getByChar(String symbol) {
			return LOOKUP.get(symbol);
		}

	}

	class PhotographElementAndPosition {
		PhotographElement photographElement;
		int position;

		public PhotographElementAndPosition(PhotographElement photographElement, int position) {
			super();
			this.photographElement = photographElement;
			this.position = position;
		}

	}

	class Photograph {
		int X, Y;
		PhotographElementAndPosition[] photographElementAndPosition = new PhotographElementAndPosition[3];

		public Photograph(int x, int y, PhotographElementAndPosition[] photographElementAndPosition) {
			super();
			X = x;
			Y = y;
			this.photographElementAndPosition = photographElementAndPosition;
		}

		boolean isArtistic() {
			boolean isArtistic = false;
			Optional<PhotographElementAndPosition> photographerFound = Arrays.asList(photographElementAndPosition)
					.stream().filter(a -> a.photographElement.equals(PhotographElement.PHOTOGRAPHER)).findFirst();
			Optional<PhotographElementAndPosition> actorFound = Arrays.asList(photographElementAndPosition).stream()
					.filter(a -> a.photographElement.equals(PhotographElement.ACTOR)).findFirst();
			Optional<PhotographElementAndPosition> backdropFound = Arrays.asList(photographElementAndPosition).stream()
					.filter(a -> a.photographElement.equals(PhotographElement.BACKDROP)).findFirst();
			if (photographerFound.isPresent() && actorFound.isPresent() && backdropFound.isPresent()) {
				int pPos = photographerFound.get().position;
				int aPos = actorFound.get().position;
				int bPos = backdropFound.get().position;
				// first check actor is between photographer and backdrop
				boolean actorBetweenPhotographerAndBackdrop = ((aPos > pPos && aPos < bPos)
						|| (aPos > bPos && aPos < pPos));
				// Second check if absolute distance is between X and Y
				int distActorPhotographer = Math.abs(aPos - pPos);
				int distActorBackdrop = Math.abs(aPos - bPos);
				boolean absoluteDistanceWithinRange = (distActorPhotographer >= X && distActorPhotographer <= Y
						&& distActorBackdrop >= X && distActorBackdrop <= Y);
				isArtistic = actorBetweenPhotographerAndBackdrop && absoluteDistanceWithinRange;
			}
			return isArtistic;
		}
	}

	public int getArtisticPhotographCount(int N, String C, int X, int Y) {
		if (N < 1 || N > 200 || X < 1 || Y < 1 || Y > N || X > Y || C.length() != N) {
			return 0;
		}
		int artisticPhotos = 0;
		// First create a purged list of photograph elements that are not null or empty
		Map<PhotographElement, List<PhotographElementAndPosition>> photosMap = getPhotographElements(C);
		// Now loop on the list to create potential artistic photos
		for (PhotographElementAndPosition photographer : photosMap.get(PhotographElement.PHOTOGRAPHER)) {
			for (PhotographElementAndPosition actor : photosMap.get(PhotographElement.ACTOR)) {
				for (PhotographElementAndPosition backDrop : photosMap.get(PhotographElement.BACKDROP)) {
					PhotographElementAndPosition[] photographElementAndPosition = new PhotographElementAndPosition[3];
					photographElementAndPosition[0] = photographer;
					photographElementAndPosition[1] = actor;
					photographElementAndPosition[2] = backDrop;
					Photograph photo = new Photograph(X, Y, photographElementAndPosition);
					if (photo.isArtistic()) {
						artisticPhotos++;
					}
				}
			}
		}
		return artisticPhotos;
	}

	private Map<PhotographElement, List<PhotographElementAndPosition>> getPhotographElements(String C) {
		Map<PhotographElement, List<PhotographElementAndPosition>> photoMap = new HashMap<>();
		for (int i = 0; i < C.length(); i++) {
			PhotographElement byChar = PhotographElement.getByChar(String.valueOf(C.charAt(i)));
			if (byChar != null && !byChar.equals(PhotographElement.EMPTY)) {
				if (photoMap.get(byChar) == null) {
					photoMap.put(byChar, new ArrayList<>());
				}
				photoMap.get(byChar).add(new PhotographElementAndPosition(byChar, i));
			}
		}
		return photoMap;
	}

	// These are the tests we use to determine if the solution is correct.
	// You can add your own at the bottom.
	int test_case_number = 1;

	void check(long expected, long output) {
		boolean result = (expected == output);
		char rightTick = '\u2713';
		char wrongTick = '\u2717';
		if (result) {
			System.out.println(rightTick + " Test #" + test_case_number);
		} else {
			System.out.print(wrongTick + " Test #" + test_case_number + ": Expected " + expected);
			System.out.print(" Your output: " + output);
			System.out.println();
		}
		test_case_number++;
	}

	private void run() {
		check(1, getArtisticPhotographCount(5, "APABA", 1, 2));
		check(0, getArtisticPhotographCount(5, "APABA", 2, 3));
		check(3, getArtisticPhotographCount(8, ".PBAAP.B", 1, 3));
	}

	public static void main(String[] args) throws IOException {
		new ArtisticPhotographCount().run();
	}
}
