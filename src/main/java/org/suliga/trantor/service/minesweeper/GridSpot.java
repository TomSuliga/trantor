package org.suliga.trantor.service.minesweeper;

public class GridSpot {
	private boolean visible;
	private GridType type;
	
	public GridSpot() {
		visible = false;
		type = GridType.EMPTY;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public GridType getType() {
		return type;
	}
	public void setType(GridType type) {
		this.type = type;
	}
}
