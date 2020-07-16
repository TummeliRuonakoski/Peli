package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Ammukset extends Hahmot{

	Color vari = Color.BEIGE;

	public Ammukset(int x, int y) {

		super(new Polygon(2, -2, 2, 2, -2, 2, -2, -2), x, y);

		Polygon hahmo = this.getHahmo();
		hahmo.setFill(vari);	
	}

	@Override
	public void liiku() {

		super.getHahmo().setTranslateX(super.getHahmo().getTranslateX() + super.liike.getX());
		super.getHahmo().setTranslateY(super.getHahmo().getTranslateY() + super.liike.getY());

		boolean reunojenyli = false;

		if(super.getHahmo().getTranslateX() > Paaohjelma.LEVEYS){
			reunojenyli = true;
			super.elossa = false;
		}
		if(super.getHahmo().getTranslateY() > Paaohjelma.KORKEUS){
			reunojenyli = true;
			super.elossa = false;
		}
		if(super.getHahmo().getTranslateX() < 0){
			reunojenyli = true;
			super.elossa = false;
		}
		if(super.getHahmo().getTranslateY() < 0){
			reunojenyli = true;
			super.elossa = false;
		}
	}
}
