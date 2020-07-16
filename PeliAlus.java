package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class PeliAlus extends Hahmot{
static Color vari = Color.OLIVE;
	

	public PeliAlus(int x, int y) {
		
		super(new Polygon(-10, -10, 20, 0, -10, 10),x,y);
		Polygon hahmo = this.getHahmo();
		hahmo.setFill(vari);		
		
	
    }

}
