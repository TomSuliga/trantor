package org.suliga.trantor.service.crossword;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class CrosswordPuzzle implements CrosswordPuzzleService {
	// only load words from book once
	private static Map<Integer, List<String>> wordsFromBook;

	// temp just use a stic grid for now
	//private static Grid[][] gridArray;
	
	private static HashMap<String, Grid[][]> gridArrays = new HashMap<>();
	private static HashMap<String, String> autoChecks = new HashMap<>();

	@Override
	public Grid[][] getGrid(String session) {
		if (gridArrays.get(session) == null) {
			synchronized (CrosswordPuzzle.class) {
				if (gridArrays.get(session) == null ) {
					gridArrays.put(session, generateGridArray());
				}
			}
		}
		return gridArrays.get(session);
	}

	private Grid[][] generateGridArray() {
		int gridSize = 15;
		Grid[][] gridArray = new Grid[gridSize][gridSize];
		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				gridArray[i][j] = new Grid(i, j);
			}
		}

		// World length : count
		// 3 : 290 4 : 991 5 : 1525 6 : 1951 7 : 2070
		// 8 : 1912 9 : 1566 10 : 1047 11 : 638

		String[] blocked = new String[gridSize];
		           //  0 1 2 3 4 5 6 7 8 9 0 1 2 3 4
		
/*		Random random = new Random();
		for (int i=0;i<gridSize;i++) {
			StringBuilder sb = new StringBuilder();
			for (int j=0;j<gridSize;j++) {
				sb.append(random.nextInt(2) == 0 ? ". " : "? ");
			}
			blocked[i] = sb.toString();
		}*/
/*
		blocked[0] =  "x x x x x x x x . x x x x x x"; 
		blocked[1] =  "x . x . x . . . x . x . x . x"; 
		blocked[2] =  "x . x . x . a . x . x . x . x"; 
		blocked[3] =  "x . x . x . h . x . x . x . x"; 
		blocked[4] =  "x . . . . . a . . . . . x . x"; 
		blocked[5] =  "x . x . m o b y d i c k . . x"; 		
		blocked[6] =  "x . x . e . . . . . . . x . x"; 
		blocked[7] =  ". . x . l . x x x x x . x . ."; 
		blocked[8] =  "x . x . v . . . . . . . x . i"; 		
		blocked[9] =  "x . . . i . . . x . . . x . s"; 
		blocked[10] = "x . x . l . x x x . x . . . h"; 
		blocked[11] = "x . x . l . . . x . x . x . m"; 		
		blocked[12] = "x . x . e . x x x . x . x . a"; 
		blocked[13] = "x . x . . . . . x . x . x . e"; 
		blocked[14] = "s t a r b u c k . r a c h e l";
	*/
		/*
		blocked[0] = "              x              ";
		blocked[1] = "  x x x   x   x   x   x x x  ";
		blocked[2] = "          x                  ";
		blocked[3] = "  x   x   x   x   x   x   x  ";
		blocked[4] = "        x           x       x";
		blocked[5] = "  x   x   x   x   x   x   x  ";
		blocked[6] = "          x x   x x          ";
		blocked[7] = "  x   x               x   x  ";
		blocked[8] = "x         x x   x x         x";
		blocked[9] = "  x   x x           x x   x  ";
		blocked[10]= "  x         x   x         x  ";
		blocked[11]= "      x x           x x      ";
		blocked[12]= "  x         x   x         x  ";
		blocked[13]= "  x x   x   x   x   x   x x  ";
		blocked[14]= "              x              ";
		*/


		// Save - Primary
		blocked[0] = ". . . . . . . . X . . . . . .";
		blocked[1] = ". X . X . X X . X . X . X X .";
		blocked[2] = ". X . X . X . . . . X . . . .";
		blocked[3] = ". . . . . X X . X X X . X X .";
		blocked[4] = ". X X X . . . . X . . . . X .";
		blocked[5] = ". . . . . X X . . . . X . X .";
		blocked[6] = "X X . X . X . X X . X . . . .";
		blocked[7] = ". . . . X . . . . . . X . X .";
		blocked[8] = ". X . X . X . X X . X X . X X";
		blocked[9] = ". . . . . . . X . . . . . . .";
		blocked[10]= ". X X X . X . . . . X . X X .";
		blocked[11]= ". . . . . X . X . X . . . . .";
		blocked[12]= ". X X . X . . . . X . X . X .";
		blocked[13]= ". X X . . . X X . X . X . X .";
		blocked[14]= ". . . . X . . . X . . . . . .";


		// Fill in Blocked squares
		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize * 2; j += 2) {
				if (blocked[i].substring(j, j + 1).equalsIgnoreCase("X"))
					gridArray[i][j / 2].setBlocked(true);
				else
					gridArray[i][j / 2].setVisible(true);
			}
		}

		// Fill in the small superscript numbers for clue reference - Across /
		// Down
		int number = 1;
		for (int row = 0; row < gridSize; row++) {
			for (int col = 0; col < gridSize; col++) {
				// skip blocked grids
				if (!gridArray[row][col].isBlocked()) {
					// across
					if (col == 0 // All left edge
							|| gridArray[row][col - 1].isBlocked()) {
						// len must be greater than 1
						int count = 0;
						for (int t = col; t < gridSize; t++) {
							if (gridArray[row][t].isBlocked())
								break;
							count++;
						}
						if (count > 1) {
							Grid g = gridArray[row][col];
							g.setAcrossNumber(number);
							g.setAcrossLen(count);
							// set each grid - left and right counts
							for (int temp=0;temp<count;temp++) {
								Grid g2 = gridArray[row][col+temp];
								g2.setRowLeftLen(temp);
								g2.setRowRightLen(count-temp-1);
							}
						}
					}
					// down
					if (row == 0 // All top edge
							|| gridArray[row - 1][col].isBlocked()) {
						// len must be greater than 1
						int count = 0;
						for (int t = row; t < gridSize; t++) {
							if (gridArray[t][col].isBlocked())
								break;
							count++;
						}
						if (count > 1) {
							Grid g = gridArray[row][col];
							g.setDownNumber(number);
							g.setDownLen(count);
							for (int temp=0;temp<count;temp++) {
								Grid g2 = gridArray[row+temp][col];
								g2.setColUpLen(temp);
								g2.setColDownLen(count-temp-1);
							}
						}
					}
					// since across and down are independent, only increment
					// number once
					if (gridArray[row][col].getAcrossNumber() > 0
					 || gridArray[row][col].getDownNumber() > 0)
						number++;
				}
			}
		}

		try {
			/*
			if (wordsFromBook == null) {
				// Get word list from Moby-Dick ascii text file
				ClassLoader classLoader = getClass().getClassLoader();
				File file = new File(classLoader.getResource("data/moby-dick.txt").getFile());
				System.out.println("file:" + file.getAbsolutePath());
				wordsFromBook = Files.lines(file.toPath())
						.parallel()
						.map(s -> Arrays.stream(s.split(" ")))
						.flatMap(s -> s)
						.parallel().filter(s -> s.matches("[a-z]+"))
						.distinct()
						.map(s -> s.toUpperCase())
						//.collect(Collectors.groupingBy(s -> s.length()));
						.collect(Collectors.groupingBy(
								String::length,
								TreeMap::new,
								Collectors.toList()));
			}
			*/
			GridHelper helper = new GridHelper(gridArray, wordsFromBook);
			// Randomize each time for now
			helper.shuffleWordLists();
			// This is the main algorithm to populate the grid with words
			helper.fillWithWords();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return gridArray;
	}

	@Override
	public String getAutoCheck(String session) {
		String autoCheck = autoChecks.get(session);
		if (autoCheck == null) {
			//System.out.println("autoCheck not found, returning true");
			return "true";
		} else {
			//System.out.println("autoCheck found, returning: " + autoCheck);
		}
		return autoCheck;
	}
	
	@Override
	public void setAutoCheck(String session, String value) {
		autoChecks.put(session, value);
	}
}
