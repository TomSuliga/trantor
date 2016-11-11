package org.suliga.trantor.service.crossword;

public interface CrosswordPuzzleService {
	public Grid[][] getGrid(String session);
	public String getAutoCheck(String session);
	public void setAutoCheck(String session, String value);
}
