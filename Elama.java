package application;

import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Elama {
	
	public Polygon elama() {
        Random rnd = new Random();

        double koko = 10 + rnd.nextInt(10);

        Polygon elamat = new Polygon();
        double c1 = Math.cos(Math.PI * 2 / 5);
        double c2 = Math.cos(Math.PI / 5);
        double s1 = Math.sin(Math.PI * 2 / 5);
        double s2 = Math.sin(Math.PI * 4 / 5);
        
        Color vari = Color.CRIMSON;
        elamat.setFill(vari);

        elamat.getPoints().addAll(
            koko, 0.0,
            koko * c1, -1 * koko * s1,
            -1 * koko * c2, -1 * koko * s2,
            -1 * koko * c2, koko * s2,
            koko * c1, koko * s1);

        for (int i = 0; i < elamat.getPoints().size(); i++) {
            int muutos = rnd.nextInt(5) - 2;
            elamat.getPoints().set(i, elamat.getPoints().get(i) + muutos);
        }

        return elamat;
    }


}
