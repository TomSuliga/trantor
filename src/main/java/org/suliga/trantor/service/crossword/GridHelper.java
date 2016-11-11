package org.suliga.trantor.service.crossword;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class GridHelper {
	private Grid[][] gridArray;
	private Set<String> wordsUsed;
	private Map<Integer, List<String>> wordsMap;
	private int seedUsed;

	public GridHelper(Grid[][] gridArray, Map<Integer, List<String>> wordsMap) {
		this.gridArray = gridArray;
		this.wordsMap = wordsMap;
		wordsUsed = new HashSet<>();
	}

	public void fillWithWords() {
		init();
		int numTries = 0;
		int numWords = 0;
		//int loop = 100;
		while (!isPuzzleDone()) {
			StepWord sw = getNextStepWord();
			String nextWord = null;
			
			do {
				nextWord = sw.nextWord();
			} while (wordsUsed.contains(nextWord));
			
			if (nextWord == null) {
				// This happens when a new word cannot be found due to existing letters not matching new word.
				// Eventually add code to back track last added word. This is the purpose of StepWord.
				// For now retry from beginning as it works around 5% of the time, but it is cpu fast
				init();
				numTries++;
				numWords = 0;
				continue;
			}
			
			addWord(sw, nextWord);
			wordsUsed.add(nextWord);
			numWords++;
			
/*			if (isPuzzleDone()) {
				loop--;
				if (loop > 0) {
					System.out.print(" " + seedUsed);
					init();
				}
			}*/
		}
		System.out.println("Num tries = " + numTries);
		System.out.println("Num words = " + numWords);
		System.out.println("Seed used: " + seedUsed);
		// temp - show first letter
		//gridArray[0][0].setVisible(true);
	}
	
	private void init() {
		wordsUsed = new HashSet<>();
		shuffleWordLists();
		initGridArray();
		primeTheGrid();
	}
	
	private void primeTheGrid() {
		String[][] prime = {
/*				{ "5", "4",  "A", "MOBYDICK", "Ahab's obsession" },
				{ "14", "0", "A", "STARBUCK",  "He attempts to stand up to Ahab"  },
				{ "5", "4",  "D", "MELVILLE", "Famous author"  },
				{ "2", "6",  "D", "AHAB",    "Ungodly, god-like man"  },
				{ "8", "14", "D", "ISHMAEL",  "Abraham's first son"  },
				{ "14", "9", "A", "RACHEL",   "She weeps for her children"  },*/
				/*
				
				{ "5", "14", "D", "GIANT",    "Superhuman size" },
				{ "11", "14", "D", "GILL",    "respiratory organ" },
				{ "2", "7", "A", "ANACONDA",    "respiratory organ" },
				{ "0", "9", "A", "PAGODA",    "respiratory organ" },
				
				{ "0", "9", "D", "PEACE",    "respiratory organ" },
				{ "4", "9", "A", "ENEMY",    "respiratory organ" },
				{ "0", "11", "D", "GLOBE",    "respiratory organ" },
				{ "0", "0", "A", "WHISKERS",    "Cats have them" },
				{ "0", "0", "D", "WONDER",    "Cats have them" },
				*/
				/*
				
				{ "9", "7", "D", "EMBARK",     "Ship stern" },*/
				/*{ "0", "12", "A", "AFT",     "Ship stern" },
				{ "0", "0",  "A", "SPIRIT",  "Angel or demon" },
				{ "0", "0",  "D", "STORM",   "Thunder and lightning producer"  },
				{ "0", "14", "D", "TERROR",  "Intense fear"  },
				{ "9", "14", "D", "SPINAL",  "Intense fear"  },
				{ "12", "10", "D","SEA",     "Intense fear"  },
				{ "12", "6", "A", "BLESS",     "Intense fear"  },
				{ "10", "8", "D", "STEM",     "Intense fear"  },
				{ "10", "8", "A", "SWIM",     "Intense fear"  },
				{ "12", "12", "A","KIN",     "Intense fear"  },
				{ "7", "9", "D",  "BROW",     "Intense fear"  },*/
				
	/*			
				{ "0", "14", "D", "SAILOR",  "Person who rarely becomes sick at sea"  },
				{ "0", "12", "D", "RUSH",    "Hurry"  },
				{ "5", "11", "A", "MOOR",    "Open uncultivated upland"  },
				{ "0", "7",  "D", "FRIGHT",  "A sudden intense feeling of fear" },
				
				{ "5", "11", "D", "MAST",    "An upright pole" },
				{ "8", "10", "A", "STORM",   "An outbreak of thunder and lightning" },
				{ "5", "13", "D", "OSTRICH", "Large flightless bird" },
				{ "9", "7",  "A", "HERO", "Large flightless bird" },
				{ "8", "10", "D", "SOLO", "Large flightless bird" },
				{ "11", "9", "A", "PONCHO", "Large flightless bird" },
				{ "11", "9", "D", "PUFF", "Large flightless bird" },
				
				{ "11", "12","D", "COOK", "Large flightless bird" },
				{ "13", "11","A", "BOAT", "Large flightless bird" },
				{ "11", "14","D", "OATH", "A solemn appeal to a deity" },
				{ "10", "0","D",  "DEMON", "An evil spirit" },
				{ "11", "3","D",  "EVIL", " Immoral, wicked" },
				{ "14", "0", "A", "NOBLE", "Distinguished by rank or title" },
				{ "9", "5", "D",  "WHALE", "Marine mammal" },
				{ "6", "2", "D",  "AMBER", "Fossil resin" },
				{ "9", "1", "A",  "BELOW", "Beneath the surface of the water" },
				{ "5", "0", "D",  "ISLE", "Beneath the surface of the water" },
				{ "6", "0", "A",  "SEAS", "Beneath the surface of the water" },
				{ "0", "0", "A",  "REEF", "Beneath the surface of the water" },
				{ "0", "2", "D",  "EVADE", "Beneath the surface of the water" },
				{ "4", "1", "A",  "TEA", "Beneath the surface of the water" },*/
				
				
				{ "0", "0",  "A", "MOBYDICK","Ahab's obsession" },
				{ "7", "0",  "D", "STARBUCK","Chief mate of the Pequod"  },
				{ "12", "5", "A", "AHAB",    "Monomaniacal captain"  },
				{ "4", "9",  "D", "ISHMAEL", "Call me ____"  },
				{ "14", "9", "A", "RACHEL",  "The ship that finds an orphan"  },
				{ "0", "14", "D", "MELVILLE","Famous author"  },
				
				{ "9", "14", "D", "DORSAL", "Of or on the back or upper surface of an animal"  },
				{ "0", "0",  "D", "MORTAL", "Liable or subject to death"  },
				{ "5", "0",  "A", "LEAPS",  "Springs or jumps"  },
				{ "11", "0", "A", "BONES",  "Components of skeletons"  },
				{ "11", "10","A", "SPEAR",  "A shaft with a sharp point and barbs"  },
				{ "2", "6",  "A", "ECHO",   "Repetition of a sound by reflection"  },
				{ "0", "9", "A",  "WIGWAM", "Native American dwelling having a conical framework"  },
				{ "0", "11", "D", "GHOST",  "The spirit of a dead person"  },
				{ "7", "5", "A",  "STORMS", "Thunder and lightning producers"  },
				{ "11", "3", "D", "EVIL",   "Morally bad or wrong"  },
				{ "14", "0", "A", "KEEL",   "The principal structural member of a boat or ship"  },
				{ "13", "3", "A", "ILL",    "Not healthy"  },
				{ "14", "5", "A", "EBB",    "The receding or outgoing tide"  },
				{ "9", "8", "A",  "DELUDED", "Deceived, fooled"  },
				{ "9", "8", "D",  "DOUBT",  "A feeling of uncertainty or lack of conviction"  },
				{ "6", "6", "D",  "STRETCH",  "Made longer or wider without tearing"  },
				{ "10", "6", "A", "TOOL",   "A device used to carry out a particular function"  },
				{ "7", "0", "A",  "SPOT",   "A mark or pip on a playing card"  },
				{ "5", "2", "D",  "ALONE",  "Being apart from others"  },
				{ "0", "2", "D",  "BORN",   "Brought into life"  },
				{ "4", "4", "A",  "URGE",   "Try to persuade someone to do something"  },
				{ "6", "11", "A", "VEIL",   "A length of cloth worn over the head"  },
				{ "8", "4", "D",  "JARS",   "Cylindrical glass or earthenware vessels"  },
				{ "2", "11", "A", "OPAL",   "A mineral of hydrated silica"  },
				{ "4", "12", "D", "OBEYED", "Carried out or fulfilled a command"  },
				{ "9", "11", "D", "URP",    "To vomit"  },
				
				{ "4", "9", "A", "INTO",    "To the inside or interior of"  },
				{ "9", "0", "A", "AVERAGE",  "Middle point between extremes"  },
				{ "0", "7", "D", "KICKED",  "Strike out with the foot or feet"  },
				{ "3", "0", "A", "TANGO",   "A Latin-American dance"  },
				{ "5", "7", "A", "DOSE",     "Amount of a medicine that is taken at one time"  },
				{ "4", "10", "D", "NE",     "Compass heading"  },
				{ "0", "4", "D", "DELOUSE",   "To remove lice from"  },
				{ "0", "9", "D", "WHO",     "The person or persons that"  },
				{ "11", "10", "D", "SOFA",  "A long upholstered seat"  },
				{ "11", "12", "D", "EACH",  "One of two or more"  },
				{ "12", "5", "D", "ALE",    "Full-bodied beer"  }
				
		};
		

		for (String[] line : prime) {
			int row = Integer.parseInt(line[0]);
			int col = Integer.parseInt(line[1]);
			boolean across = line[2].equals("A");
			String word = line[3];
			addWord(new StepWord(new Grid(row,col),across,wordsMap,gridArray),word);
			if (across)
				gridArray[row][col].setAcrossClue(line[4]);
			else
				gridArray[row][col].setDownClue(line[4]);
			wordsUsed.add(word);
		}
	}

	private StepWord getNextStepWord() {
		StepWord sw = getNextStepWord(true);
		if (sw != null)
			return sw;

		return getNextStepWord(false);
	}

	private StepWord getNextStepWord(boolean partiallyFilled) {
		// partiallyFilled: look for already filled in grids, they have priority
		// in order to catch mismatches early
		for (Grid[] gArray : gridArray) {
			for (Grid grid : gArray) {
				if (grid.isHeadLetter()) {
					if (grid.isAcrossWord() && !grid.isAcrossFilled()) {
						if (partiallyFilled) {
							if (isPartiallyFilled(grid, true, grid.getAcrossLen()))
								return new StepWord(grid, true, wordsMap, gridArray);
							;
						} else {
							return new StepWord(grid, true, wordsMap, gridArray);
						}
					}
					if (grid.getDownLen() > 0 && !grid.isDownFilled()) {
						//int wordLen = grid.getDownLen();
						if (partiallyFilled) {
							if (isPartiallyFilled(grid, false, grid.getDownLen()))
								return new StepWord(grid, false, wordsMap, gridArray);
						} else {
							return new StepWord(grid, false, wordsMap, gridArray);
						}
					}
				}
			}
		}
		return null;
	}

	private boolean isPartiallyFilled(Grid grid, boolean across, int wordLen) {
		int row = grid.getRow();
		int col = grid.getCol();
		if (across) {
			for (int i = 0; i < wordLen; i++) {
				if (gridArray[row][col + i].isEmpty())
					continue;
				return true;
			}
			return false;
		} else {
			for (int i = 0; i < wordLen; i++) {
				if (gridArray[row + i][col].isEmpty())
					continue;
				return true;
			}
			return false;
		}
	}

	private boolean isPuzzleDone() {
		for (Grid[] gArray : gridArray) {
			for (Grid g : gArray) {
				if (g.isBlocked())
					continue;
				if (g.isEmpty())
					return false;
			}
		}
		return true;
	}
	
	private void initGridArray() {
		for (Grid[] gArray : gridArray) {
			for (Grid g : gArray) {
				g.init();
			}
		}
	}

	public void shuffleWordLists() {
		if (wordsMap == null)
			return;
		
		// first sort so it always begins the same way
		for (Integer key : wordsMap.keySet()) {
			List<String> list = wordsMap.get(key);
			Collections.sort(list);
		}
		
		// keep track of seed value in order to save successfull ones
		seedUsed = new Random().nextInt();
		//seedUsed = 1409734752;
		Random random = new Random(seedUsed);
		for (Integer key : wordsMap.keySet()) {
			List<String> list = wordsMap.get(key);
			Collections.shuffle(list, random);
		}
	}
	
	private boolean addWord(StepWord sw, String word) {
		//System.out.println("Adding word: " + word);
		if (sw.isAcross()) {
			return addWordAcross(sw.getGrid().getRow(), sw.getGrid().getCol(), word);
		} else {
			return addWordDown(sw.getGrid().getRow(), sw.getGrid().getCol(), word);
		}
	}

	private boolean addWordAcross(int row, int col, String word) {
		if (addWordInternal(row, col, word, 0, 1)) {
			gridArray[row][col].setAcrossFilled(true);
			return true;
		}
		return false;
	}

	private boolean addWordDown(int row, int col, String word) {
		if (addWordInternal(row, col, word, 1, 0)) {
			gridArray[row][col].setDownFilled(true);
			return true;
		}
		return false;
	}

	private boolean addWordInternal(int row, int col, String word, int deltaRow, int deltaCol) {
		if (isWordAlreadyUsed(word))
			return false;
		// System.out.println("Adding word: " + word);
		byte[] bytes = word.getBytes();
		for (int i = 0; i < word.length(); i++) {
			gridArray[row][col].setRealLetter((char) bytes[i]);
			row += deltaRow;
			col += deltaCol;
		}
		return true;
	}

	private boolean isWordAlreadyUsed(String word) {
		return wordsUsed.contains(word);
	}
}
