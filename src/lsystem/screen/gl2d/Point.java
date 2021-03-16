package lsystem.screen.gl2d;

public class Point {
	private float x;
	private float y;
	
	public Point () {
		
	}
	
	public Point (float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Point (Point old, int angle) {
		angle = compactAngle(angle);
		//Point a = new Point();
		//x = getNewOrigin(old, angle, 0.1f);
		getNewOrigin(old, angle, 0.1f);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	public void getNewOrigin (Point old, int angle, float echelle) {
		
		int newX=0, newY=0;
		switch (angle) {
		case 0:
			newX = 1;
			newY = 1;
			break;
		case 45:
			newX = 1;
			newY = 0;
			break;
		case 90:
			newX = 1;
			newY = -1;
			break;
		case 135:
			newX = 0;
			newY = -1;
			break;
		case 180:
			newX = -1;
			newY = -1;
			break;
		case 225:
			newX = -1;
			newY = 0;
			break;
		case 270:
			newX = -1;
			newY = 1;
			break;
		case 315:
			newX = 0;
			newY = 1;
			break;
		}
		
		this.x = old.getX() + echelle * newX;
		this.y+= old.getY() + echelle * newY;
		//return old;
	}
	
	public String toString () {
		return "("+this.x+";"+this.y+")";
	}
	
	public static int compactAngle (int angle) {
		angle = angle - ((angle / 360) * 360);
		switch (angle) {
		case -315:
			angle = 45;
			break;
		case -270:
			angle = 90;
			break;
		case -225:
			angle = 135;
			break;
		case -180:
			angle = 180;
			break;
		case -135:
			angle = 225;
			break;
		case -90:
			angle = 270;
			break;
		case -45:
			angle = 315;
			break;
		}
		return angle;
	}

}
