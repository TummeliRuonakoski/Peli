package application;

import java.util.Random;

public class ElamienLiike extends Hahmot{
	
private double pyorimisliike;

	

	public ElamienLiike(int x, int y) {
		super(new Elama().elama(), x, y);
		


		Random rnd = new Random();

		super.getHahmo().setRotate(rnd.nextInt(360));

		int kiihdytystenMaara = 1 + rnd.nextInt(10);
		for (int i = 0; i < kiihdytystenMaara; i++) {
			kiihdyta();
		}

		this.pyorimisliike = 0.5 - rnd.nextDouble();
	}

	
	
	@Override
	public void liiku() {
		super.liiku();
		super.getHahmo().setRotate(super.getHahmo().getRotate() + pyorimisliike);
	}

}

