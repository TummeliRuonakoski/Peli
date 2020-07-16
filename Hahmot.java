package application;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class Hahmot {
	
	private Polygon hahmo;
	protected Point2D liike;
	protected boolean elossa;
	
	
	public Hahmot(Polygon monikulmio,int x,int y) {
	
		this.hahmo = monikulmio;
		this.hahmo.setTranslateX(x);
		this.hahmo.setTranslateY(y);
		this.elossa = true;
		this.liike = new Point2D(0, 0);
		
	}
	
	public Polygon getHahmo() {
		return hahmo;
	}

	public void kaannaVasemmalle() {
		this.hahmo.setRotate(this.hahmo.getRotate() - 1);
	}

	public void kaannaOikealle() {
		this.hahmo.setRotate(this.hahmo.getRotate() + 1);
	}

	public void liiku() {
		this.hahmo.setTranslateX(this.hahmo.getTranslateX() + this.liike.getX());
		this.hahmo.setTranslateY(this.hahmo.getTranslateY() + this.liike.getY());

		if (this.hahmo.getTranslateX() < 0) {
			this.hahmo.setTranslateX(this.hahmo.getTranslateX() + Paaohjelma.LEVEYS);
		}

		if (this.hahmo.getTranslateX() > Paaohjelma.LEVEYS) {
			this.hahmo.setTranslateX(this.hahmo.getTranslateX() % Paaohjelma.LEVEYS);
		}

		if (this.hahmo.getTranslateY() < 0) {
			this.hahmo.setTranslateY(this.hahmo.getTranslateY() + Paaohjelma.KORKEUS);
		}

		if (this.hahmo.getTranslateY() > Paaohjelma.KORKEUS) {
			this.hahmo.setTranslateY(this.hahmo.getTranslateY() % Paaohjelma.KORKEUS);
		}
	}
	
	public void kiihdyta() {
		
		double muutosX = Math.cos(Math.toRadians(this.hahmo.getRotate()));
		double muutosY = Math.sin(Math.toRadians(this.hahmo.getRotate()));

		muutosX *=  0.01;
		muutosY *= 0.01;

		this.liike = this.liike.add(muutosX, muutosY);
	}
	
	public boolean tormaa(Hahmot toinen) {

		Shape tormaysalue = Shape.intersect(this.hahmo, toinen.getHahmo());
		return tormaysalue.getBoundsInLocal().getWidth() != -1;
	}
	
	public Point2D getLiike() {
		return liike;
	}

	public void setLiike(Point2D liike) {
		this.liike = liike;
	}

	public void setElossa(boolean elossa) {
		this.elossa = elossa;
	}

	public boolean elossa() {
		return elossa;
	}
	
	
	
}

