package org.suliga.trantor.service.crossword;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StepWord {
	private boolean across;
	private int wordLen;
	private String currentWord;
	private List<RowCol> lettersAdded;
	private int currentIndex = -1;
	//private List<String> wordList;
	private Grid[][] gridArray;
	private Grid grid;
	
	@Override
	public String toString() {
		return "grid:" + grid + " " + currentWord;
	}
	
	private static class RowCol {
		private int row;
		private int col;
		public RowCol(int row, int col) {
			this.row = row;
			this.col = col;
		}
		public int getRow() {
			return row;
		}
		public int getCol() {
			return col;
		}
	}
	
	public StepWord(Grid grid, boolean across, Map<Integer, List<String>> wordsMap, Grid[][] gridArray) {
		this.grid = grid;
		this.across = across;
		if (across)
			this.wordLen = grid.getAcrossLen();
		else
			this.wordLen = grid.getDownLen();
		//this.wordList = wordsMap.get(wordLen);
		this.gridArray = gridArray;
		lettersAdded = new ArrayList<>();
	}
	public String nextWord() {
		return "NONE";
		
/*		while (true) {
			currentIndex++;
			if (currentIndex >= wordList.size())
				return null;
			String word = wordList.get(currentIndex);
			if (isLegalWord(word)) {
				currentWord = word;
				return word;
			}
		}*/
	}
	
	private boolean isLegalWord(String word) {
		int row = grid.getRow();
		int col = grid.getCol();
		if (across) {
			for (int i=0;i<wordLen;i++) {
				if (gridArray[row][col+i].isEmpty())
					continue;
				if (gridArray[row][col+i].getRealLetter() != word.getBytes()[i])
					return false;
			}
			return true;
		}
		else {
			for (int i=0;i<wordLen;i++) {
				if (gridArray[row+i][col].isEmpty())
					continue;
				if (gridArray[row+i][col].getRealLetter() != word.getBytes()[i])
					return false;
			}
			return true;
		}
	}
	
	public boolean isCompletelyFilled() {
		int row = grid.getRow();
		int col = grid.getCol();
		if (across) {
			for (int i=0;i<wordLen;i++) {
				if (gridArray[row][col+i].isEmpty())
					return false;
			}
		}
		else {
			for (int i=0;i<wordLen;i++) {
				if (gridArray[row+i][col].isEmpty())
					return false;
			}
		}
		return true;
	}
	
	public String getFilledInWord() {
		int row = grid.getRow();
		int col = grid.getCol();
		StringBuilder sb = new StringBuilder();
		if (across) {
			for (int i=0;i<wordLen;i++) {
				sb.append(gridArray[row][col+i].getRealLetter());
			}
		}
		else {
			for (int i=0;i<wordLen;i++) {
				sb.append(gridArray[row+i][col].getRealLetter());
			}
		}
		return sb.toString();
	}
	
	public void pushStep(Grid[][] gridArray) {
		int row = grid.getRow();
		int col = grid.getCol();
		// need to populate list of letters added for this step (empty grids)
		if (across) {
			for (int i=0;i<wordLen;i++) {
				if (gridArray[row][col+i].isEmpty())
					lettersAdded.add(new RowCol(row,col+1));
			}
		}
		else {
			for (int i=0;i<wordLen;i++) {
				if (gridArray[row+i][col].isEmpty())
					lettersAdded.add(new RowCol(row+i,col));
			}
		}
		
	}
	public void removePreviousLetters() {
		for (RowCol rc : lettersAdded) {
			gridArray[rc.getRow()][rc.getCol()].setEmpty();
		}
		
	}
	public boolean isAcross() {
		return across;
	}
	public void setAcross(boolean across) {
		this.across = across;
	}
	public int getWordLen() {
		return wordLen;
	}
	public void setWordLen(int wordLen) {
		this.wordLen = wordLen;
	}
	public String getCurrentWord() {
		return currentWord;
	}
	public void setCurrentWord(String currentWord) {
		this.currentWord = currentWord;
	}
	public List<RowCol> getLettersAdded() {
		return lettersAdded;
	}
	public void setLettersAdded(List<RowCol> lettersAdded) {
		this.lettersAdded = lettersAdded;
	}
	public int getCurrentIndex() {
		return currentIndex;
	}
	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
	}
	public Grid getGrid() {
		return grid;
	}
	public void setGrid(Grid grid) {
		this.grid = grid;
	}

}
