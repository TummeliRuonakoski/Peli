package application;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Paaohjelma extends Application {

	

	public static int LEVEYS = 800;
	public static int KORKEUS = 600;
	int pisteet = 0;
	int elamat1 = 5;
	long tormaysImmutiteettiAika = 200000000;
	long ammusvalit = 200000000;

	@Override
	public void start(Stage ikkuna) throws Exception {

		BorderPane ensimmainennaytto = new BorderPane();

		Font fontti = new Font(18);
		Font fontti2 = new Font(22);
		Insets ulkonako = new Insets(10, 10, 10, 10);
		ensimmainennaytto.setPrefSize(800, 300);
		ensimmainennaytto.setBackground(new Background(new BackgroundFill(Color.BLACK, null, Insets.EMPTY)));


		VBox ohjeboxi = new VBox();
		Text tervetuloa = new Text("Tervetuloa pelaamaan! " + "\n");
		Text ohjeet = new Text("Ohjaa nuolinäppäimillä sekä välilyönnillä. "
				+ "Välilyönnillä ammut, ylöspäin nuolella kaasutat sekä alaspäin jarrutat. "
				+ "\n"	+ "Esc nappulalla lopetat pelin.");
		Text tyhja = new Text(" ");
		tervetuloa.setFill(Color.WHITE);
		ohjeet.setFill(Color.WHITE);
		tervetuloa.setFont(fontti2);
		ohjeet.setFont(fontti);
		ohjeboxi.getChildren().addAll(tervetuloa,ohjeet, tyhja);
		ohjeboxi.setPadding(ulkonako);
		ohjeboxi.setAlignment(Pos.BASELINE_CENTER);

		VBox buttonit = new VBox();
		Button pelaamaan = new Button("pelaamaan");
		Button poistu = new Button("poistu");
		buttonit.getChildren().addAll(pelaamaan, poistu);
		buttonit.setPadding(ulkonako);
		poistu.setFont(fontti);
		pelaamaan.setFont(fontti);
		pelaamaan.setMaxWidth(Double.MAX_VALUE);
		poistu.setMaxWidth(Double.MAX_VALUE);
		buttonit.setAlignment(Pos.BASELINE_CENTER);
		buttonit.setSpacing(10);
		ensimmainennaytto.setTop(ohjeboxi);
		ensimmainennaytto.setCenter(buttonit);


		Scene ekanakyma = new Scene(ensimmainennaytto);
		BorderPane naytto = new BorderPane();
		Scene nakyma = new Scene(naytto);


		pelaamaan.setOnAction((even)-> {
			ikkuna.setScene(nakyma);
		});
		poistu.setOnAction((even)-> {
			ikkuna.close();
		});

		FileInputStream input = new FileInputStream("/Users/tummeliruonakoski/eclipse-workspace/PeliNettiin/Resurssit/Taustakuva.png"); 
		Image kuva = new Image(input); 
		BackgroundImage taustakuva = new BackgroundImage(kuva,  
				BackgroundRepeat.NO_REPEAT,  
				BackgroundRepeat.NO_REPEAT,  
				BackgroundPosition.DEFAULT,  
				BackgroundSize.DEFAULT); 
		Background tausta = new Background(taustakuva); 
		naytto.setPrefSize(LEVEYS, KORKEUS);
		naytto.setBackground(tausta);
		naytto.setPadding(new Insets(20, 20, 20, 20));


		Text pistemaara = new Text(10, 20, " Pisteet: 0");
		pistemaara.setFont(fontti);
		pistemaara.setFill(Color.WHITE);
		Text elamamaara = new Text(10, 40, " Elämät: 5" );
		elamamaara.setFont(fontti);
		elamamaara.setFill(Color.WHITE);
		naytto.getChildren().add(pistemaara);
		naytto.getChildren().add(elamamaara);

		BorderPane viimeinennaytto = new BorderPane();
		viimeinennaytto.setPrefSize(300, 200);
		VBox v = new VBox();
		v.setPadding(new Insets(20, 20, 20, 20));
		v.setBackground(new Background(new BackgroundFill(Color.BLACK, null, Insets.EMPTY)));
		Text lopetus = new Text("Peli loppui." + "\n" + "Sait " + pisteet + " pistettä!");
		lopetus.setFont(fontti);
		lopetus.setFill(Color.WHITE);
		v.getChildren().add(lopetus);
		viimeinennaytto.setCenter(v);
		Scene loppuu = new Scene(viimeinennaytto);


		PeliAlus alus = new PeliAlus(LEVEYS / 2 , KORKEUS / 2);
		List<Ammukset> ammukset = new ArrayList<>();
		List<AsteroidienLiike> asteroidit = new ArrayList<>();
		List<ElamienLiike> elamat = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			Random rnd = new Random();
			AsteroidienLiike asteroidi = new AsteroidienLiike(rnd.nextInt(LEVEYS - 50), rnd.nextInt(KORKEUS - 50));
			asteroidit.add(asteroidi);
		}

		for (int i = 0; i < 2; i++) {
			Random rnd = new Random();
			ElamienLiike elama = new ElamienLiike(rnd.nextInt(LEVEYS - 50), rnd.nextInt(KORKEUS - 50));
			elamat.add(elama);
		}

		naytto.getChildren().add(alus.getHahmo());
		asteroidit.forEach(asteroidi -> naytto.getChildren().add(asteroidi.getHahmo()));
		elamat.forEach(elama -> naytto.getChildren().add(elama.getHahmo()));


		Map<KeyCode, Boolean> painetutNapit = new HashMap<>();

		nakyma.setOnKeyPressed(event -> {
			painetutNapit.put(event.getCode(), Boolean.TRUE);
		});

		nakyma.setOnKeyReleased(event -> {
			painetutNapit.put(event.getCode(), Boolean.FALSE);
		});

		
		
		new AnimationTimer() {

			private long edellinenaika = 0;
			private long immuniteettiaika = tormaysImmutiteettiAika;

			@Override
			public void handle(long nykyhetki) {

				long aikavali =	nykyhetki - edellinenaika;
				edellinenaika = nykyhetki;
				immuniteettiaika -= aikavali;
				ammusvalit -= aikavali;



				if (painetutNapit.getOrDefault(KeyCode.LEFT, false)) {
		            alus.kaannaVasemmalle();
		        }

		        if (painetutNapit.getOrDefault(KeyCode.RIGHT, false)) {
		            alus.kaannaOikealle();
		        }

		        if (painetutNapit.getOrDefault(KeyCode.UP, false)) {
		            alus.kiihdyta();
		        }

				if (painetutNapit.getOrDefault(KeyCode.SPACE, false) && ammukset.size() < 10 ) {
					if(ammusvalit < 0) {
						Ammukset ammus = new Ammukset((int) alus.getHahmo().getTranslateX(), (int) alus.getHahmo().getTranslateY());
						ammus.getHahmo().setRotate(alus.getHahmo().getRotate());
						ammukset.add(ammus);
						ammusvalit = 200000000;
						ammus.kiihdyta();
						ammus.setLiike(ammus.getLiike().normalize().multiply(10));
						naytto.getChildren().add(ammus.getHahmo());
					}
				}

				alus.liiku();
				asteroidit.forEach(asteroidi -> asteroidi.liiku());
				ammukset.forEach(ammus -> ammus.liiku());
				elamat.forEach(elama -> elama.liiku());


				asteroidit.forEach(asteroidi -> {
					if(immuniteettiaika <= 0) {
						if(alus.tormaa(asteroidi) && elamat1 > 0) {
							immuniteettiaika = tormaysImmutiteettiAika;
							elamat1 --;
							elamamaara.setText("Elämät: " + elamat1);
							asteroidi.setElossa(false);
						}
					}
					if (alus.tormaa(asteroidi) && elamat1 == 0) {
						stop();
						ikkuna.setScene(loppuu);
						lopetus.setText("Peli loppui." + "\n" + "Sait " + pisteet + " pistettä!");
					}

				});

				elamat.forEach(elama ->{
					if(alus.tormaa(elama)) {
						elamat1++;
						elamamaara.setText("Elämät: " + elamat1);
						elama.setElossa(false);
					}
				});


				ammukset.forEach(ammus -> {
					asteroidit.forEach(asteroidi -> {
						if(ammus.tormaa(asteroidi)) {
							pisteet++;
							pistemaara.setText("Pisteet: " + pisteet);
							ammus.setElossa(false);
							asteroidi.setElossa(false);
						}
					});
				});

				List<Ammukset> poistettavatAmmukset = ammukset.stream().filter(ammus -> {
					List<AsteroidienLiike> tormatyt = asteroidit.stream().
							filter(asteroidi -> asteroidi.tormaa(ammus)).
							collect(Collectors.toList());

					if(tormatyt.isEmpty()) {
						return false;
					}

					tormatyt.stream().forEach(tormatty -> {
						asteroidit.remove(tormatty);
						naytto.getChildren().remove(tormatty.getHahmo());
					});

					return true;
				}).collect(Collectors.toList());



				poistettavatAmmukset.forEach(ammus -> {
					naytto.getChildren().remove(ammus.getHahmo());
					ammukset.remove(ammus);
				});

			
				ammukset.stream()
				.filter(ammus -> !ammus.elossa())
				.forEach(ammus -> naytto.getChildren().remove(ammus.getHahmo()));
				ammukset.removeAll(ammukset.stream()
						.filter(ammus -> !ammus.elossa())
						.collect(Collectors.toList()));

				asteroidit.stream()
				.filter(asteroidi -> !asteroidi.elossa())
				.forEach(asteroidi -> naytto.getChildren().remove(asteroidi.getHahmo()));
				asteroidit.removeAll(asteroidit.stream()
						.filter(asteroidi -> !asteroidi.elossa())
						.collect(Collectors.toList()));

				elamat.stream()
				.filter(elama -> !elama.elossa())
				.forEach(elama -> naytto.getChildren().remove(elama.getHahmo()));
				elamat.removeAll(elamat.stream()
						.filter(elama -> !elama.elossa())
						.collect(Collectors.toList()));


				if(Math.random() < 0.005) {
					AsteroidienLiike asteroidi = new AsteroidienLiike(LEVEYS - 50, KORKEUS - 50);
					if(!asteroidi.tormaa(alus)) {
						asteroidit.add(asteroidi);
						naytto.getChildren().add(asteroidi.getHahmo());
					}
				}
			}

		}.start();

		ikkuna.setScene(ekanakyma);
		ikkuna.show();

	}
	public static void main(String[] args) {
		launch(args);
	}
}
