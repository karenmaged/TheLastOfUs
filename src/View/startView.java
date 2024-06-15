package View;






import java.awt.Point;
import java.io.File;
import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;
import model.characters.Direction;
import model.characters.Character;
import model.characters.Fighter;
import model.characters.Hero;
import model.characters.Medic;
import model.characters.Zombie;
import model.collectibles.Collectible;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.Cell;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;




public class startView extends Application {

	static Stage theStage = new Stage();
	
	Button button, nextbutton ;
	Scene scene1 , scenehtp, scene2 , scene3;
	static Hero currenthero;
	static Button currentherobutton;
	
	static BorderPane layout3;
	BorderPane layouthtp;
	static GridPane mapgrid = new GridPane();
    
	 //	static HBox info;
	static VBox rightvbox;
	static VBox leftvbox;
    static StackPane currentherobox = new StackPane();
	static StackPane currentherodetailsbox = new StackPane();
    
	static Point targetLocation = new Point(-1,-1);
	
	public static void main (String [] args){
		String path = "src\\soundtrack.mp3";
		Media st = new Media(new File(path).toURI().toString());
		MediaPlayer mediaplayer = new MediaPlayer(st);
		mediaplayer.setAutoPlay(true);
		Timeline timeline = new Timeline(new KeyFrame (Duration.seconds(0) , event ->mediaplayer.play()));
		timeline.play();
		mediaplayer.setOnEndOfMedia(()-> mediaplayer.seek(Duration.ZERO));
		launch(args);
	}


	 
	public static Label heroinfo (Hero hero){
		 Label name = new Label("Name:" + hero.getName());
		 Label type = new Label();
		 if (hero instanceof Fighter) type.setText("Fighter");
		 else if (hero instanceof Medic) type.setText("Medic");
		 else type.setText("Explorer");
		 Label actions = new Label("Actions Left:" + hero.getActionsAvailable());
		 Label attackdmg = new Label("Attack Damage:" + hero.getAttackDmg());
		 Label health = new Label("Current HP:" + hero.getCurrentHp());
		 Label supplies = new Label("Supply Count: " + hero.getSupplyInventory().size());		 
		 Label vaccines = new Label("Vaccines Count: " + hero.getVaccineInventory().size());
		 //info.getChildren().addAll(name, type, actions, health);
		 String s = name.getText() + "\n" + type.getText() + "\n" + actions.getText() + "\n" + attackdmg.getText() + "\n"
				 + health.getText() + "\n" + supplies.getText() + "\n" + vaccines.getText();
		 
		 Label zz = new Label ();
		 
		 zz.setPadding(new Insets(10,10,10,10));
		 zz.setText(s);
		 zz.setStyle("-fx-background-color:black; -fx-text-fill:white; -fx-font-size:16px; -fx-font-weight:bold; -fx-border-radius: 15px; -fx-background-radius: 15px;");
		 zz.setTextAlignment(TextAlignment.CENTER);
		 zz.setWrapText(true);
		 zz.setAlignment(Pos.CENTER);
		 return zz;
		 
		
	 }
	
	public static Label miniheroinfo (Hero hero){
		 Label name = new Label("Name:" + hero.getName());
		 Label type = new Label();
		 if (hero instanceof Fighter) type.setText("Fighter");
		 else if (hero instanceof Medic) type.setText("Medic");
		 else type.setText("Explorer");
		 Label actions = new Label("Actions Left:" + hero.getActionsAvailable());
		 Label attackdmg = new Label("Attack Damage:" + hero.getAttackDmg());
		 Label health = new Label("Current HP:" + hero.getCurrentHp());
		 String s = name.getText() + "\n" + type.getText() + "\n" + actions.getText() + "\n" + attackdmg.getText() + "\n"
				 + health.getText();
		 
		 Label zz = new Label ();
		 zz.getStylesheets();
		 zz.setId("panekaroun");
		 zz.setText(s);
		 //zz.setStyle("-fx-background-color:black; -fx-text-fill:white; -fx-font-size:16px;  -fx-font-weight:bold;");
		 zz.setTextAlignment(TextAlignment.CENTER);
		 zz.setWrapText(true);
		 zz.setAlignment(Pos.CENTER);
		 return zz;
		
	 }
	 
	 


	@Override
	public void start(Stage primaryStage) throws Exception {
	
		

		
		theStage.setTitle("the last of us");
		Image image = new Image ("View/icon.jpg");
		theStage.getIcons().add(image);
		
//		BorderStroke borderStroke = new BorderStroke(
//				Color.BLACK,
//				borderStrokeStyle,
//				new CornerRadii (0),
//				new BorderWidths (8)
//				);
//		Border border= new Border(borderStroke);
		

		button = new Button();
		button.setStyle("-fx-background-color: transparent;");
		Image image1 = new Image("startbutton2.png");
        ImageView imageView = new ImageView(image1);
        button.setGraphic(imageView);
		button.setMaxSize(100, 100);
		
		button.setOnAction(e -> theStage.setScene(scenehtp));
		BorderPane layout = new BorderPane(button);
		layout.setPadding(new Insets(50, 50, 50, 50)); 
		layout.setAlignment(button, Pos.BOTTOM_RIGHT);
		
		
		scene1 = new Scene(layout,1500,1000);
		scene1.getStylesheets().add("Viper.css");
		layout.setId("pane1");
		
		
		nextbutton = new Button();
		nextbutton.setStyle("-fx-background-color: transparent;");
		Image next = new Image("NEXTBUTTON.png");
        ImageView nextView = new ImageView(next);
        nextView.setFitHeight(150);
        //nextView.setFitWidth(100);
        nextView.setPreserveRatio(true);
        nextbutton.setGraphic(nextView);
        nextbutton.setMaxSize(100, 100);
		
        nextbutton.setOnAction(e -> theStage.setScene(scene2));
		BorderPane layouthtp = new BorderPane(nextbutton);
		layouthtp.setPadding(new Insets(50, 50, 50, 50)); 
		layouthtp.setAlignment(nextbutton, Pos.BOTTOM_RIGHT);
		
		
		scenehtp = new Scene(layouthtp,1500,1000);
		scenehtp.getStylesheets().add("Viper.css");
		layouthtp.setId("panehtp");
		
		

		BorderPane layout2 = new BorderPane();
		scene2 = new Scene (layout2,1500,1000);
		scene2.getStylesheets().add("Viper.css");
		layout2.setId("pane2");
		layout2.setPadding(new Insets(0, 0, 0, 0));
		GridPane heroesgrid = new GridPane();
	    heroesgrid.setVgap(50); 
	    heroesgrid.setHgap(50);
	    
	    Game.loadHeroes("src/Heroes.csv");

	    Image JoelInfo = new Image("Joelinfo.png");
	    Image Joel = new Image("Joel.png");
	    ImageView ViewJoelInfo = new ImageView(JoelInfo);
	    ImageView ViewJoel = new ImageView(Joel);
	    ViewJoel.setFitHeight(300);
	    ViewJoel.setPreserveRatio(true);
	    ViewJoelInfo.setFitHeight(300);
	    ViewJoelInfo.setPreserveRatio(true);
	    Button button1 = new Button();
	    button1.setTranslateX(60);
	    button1.setTranslateY(150);
	    button1.setPrefSize(150,150);
	    button1.setGraphic(ViewJoel); 
	    button1.setStyle("-fx-background-color:transparent;");
	    button1.setOnAction(e ->{ 
	        theStage.setScene(scene3);
	    	currenthero = Game.availableHeroes.get(0);
	    	Game.startGame(currenthero);
	    	setmap();
	    	

	    	
	    });
	    button1.setOnMouseEntered(e -> button1.setGraphic(ViewJoelInfo));
	    button1.setOnMouseExited(e ->button1.setGraphic(ViewJoel));
	    
	    
	    
	    Image EllieInfo = new Image("Ellieinfo.png");
	    Image Ellie = new Image("Ellie.png");
	    ImageView ViewEllieInfo = new ImageView(EllieInfo);
	    ImageView ViewEllie = new ImageView(Ellie);
	    ViewEllie.setFitHeight(300);
	    ViewEllie.setPreserveRatio(true);
	    ViewEllieInfo.setFitHeight(300);
	    ViewEllieInfo.setPreserveRatio(true);
	    Button button2 = new Button();
	    button2.setTranslateX(420);
	    button2.setTranslateY(150);
	    button2.setPrefSize(150,150);
	    button2.setGraphic(ViewEllie); 
	    button2.setStyle("-fx-background-color:transparent;");
	    button2.setOnAction(e -> {	
	    	currenthero = Game.availableHeroes.get(1);
	    	Game.startGame(currenthero);
	        theStage.setScene(scene3);
	    	setmap();


	    	

	    });
	    button2.setOnMouseEntered(e -> button2.setGraphic(ViewEllieInfo));
	    button2.setOnMouseExited(e -> button2.setGraphic(ViewEllie));
	    

	    Image TessInfo = new Image("Tessinfo.png");
	    Image Tess = new Image("Tess.png");
	    ImageView ViewTessInfo = new ImageView(TessInfo);
	    ImageView ViewTess = new ImageView(Tess);
	    ViewTess.setFitHeight(300);
	    ViewTess.setPreserveRatio(true);
	    ViewTessInfo.setFitHeight(300);
	    ViewTessInfo.setPreserveRatio(true);
	    Button button3 = new Button();
	    button3.setTranslateX(780);
	    button3.setTranslateY(150);
	    button3.setPrefSize(150,150);
	    button3.setGraphic(ViewTess); 
	    button3.setStyle("-fx-background-color:transparent;");
	    button3.setOnAction(e -> {	
	    	currenthero = Game.availableHeroes.get(2);
	    	Game.startGame(currenthero);
	        theStage.setScene(scene3);

	    	setmap();

	    });
	    button3.setOnMouseEntered(e -> button3.setGraphic(ViewTessInfo));
	    button3.setOnMouseExited(e -> button3.setGraphic(ViewTess));
	    

	    Image RileyInfo = new Image("Rileyinfo.png");
	    Image Riley = new Image("Riley.png");
	    ImageView ViewRileyInfo = new ImageView(RileyInfo);
	    ImageView ViewRiley = new ImageView(Riley);
	    ViewRiley.setFitHeight(300);
	    ViewRiley.setPreserveRatio(true);
	    ViewRileyInfo.setFitHeight(300);
	    ViewRileyInfo.setPreserveRatio(true);
	    Button button4 = new Button();
	    button4.setTranslateX(1140);
	    button4.setTranslateY(150);    
	    button4.setPrefSize(150,150);
	    button4.setGraphic(ViewRiley); 
	    button4.setStyle("-fx-background-color:transparent;");
	    button4.setOnAction(e -> {	
	    	currenthero = Game.availableHeroes.get(3);
	    	Game.startGame(currenthero);
	        theStage.setScene(scene3);
	    	setmap();

	    });
	    button4.setOnMouseEntered(e -> button4.setGraphic(ViewRileyInfo));
	    button4.setOnMouseExited(e -> button4.setGraphic(ViewRiley));
	    

	    Image TommyInfo = new Image("Tommyinfo.png");
	    Image Tommy = new Image("Tommy.png");
	    ImageView ViewTommyInfo = new ImageView(TommyInfo);
	    ImageView ViewTommy = new ImageView(Tommy);
	    ViewTommy.setFitHeight(300);
	    ViewTommy.setPreserveRatio(true);
	    ViewTommyInfo.setFitHeight(300);
	    ViewTommyInfo.setPreserveRatio(true);
	    Button button5 = new Button();
	    button5.setTranslateX(60);
	    button5.setTranslateY(550);
	    button5.setPrefSize(150,150);
	    button5.setGraphic(ViewTommy); 
	    button5.setStyle("-fx-background-color:transparent;");
	    button5.setOnAction(e -> {	
	    	currenthero = Game.availableHeroes.get(4);
	    	Game.startGame(currenthero);
	        theStage.setScene(scene3);
	    	setmap();
	    	

	    });
	    button5.setOnMouseEntered(e -> button5.setGraphic(ViewTommyInfo));
	    button5.setOnMouseExited(e -> button5.setGraphic(ViewTommy));
	    

	    Image BillInfo = new Image("Billinfo.png");
	    Image Bill = new Image("Bill.png");
	    ImageView ViewBillInfo = new ImageView(BillInfo);
	    ImageView ViewBill = new ImageView(Bill);
	    ViewBill.setFitHeight(300);
	    ViewBill.setPreserveRatio(true);
	    ViewBillInfo.setFitHeight(300);
	    ViewBillInfo.setPreserveRatio(true);
	    Button button6 = new Button();
	    button6.setTranslateX(420);
	    button6.setTranslateY(550);
	    button6.setPrefSize(150,150);
	    button6.setGraphic(ViewBill);
	    button6.setStyle("-fx-background-color:transparent;");
	    button6.setOnAction(e -> {	
	    	currenthero = Game.availableHeroes.get(5);
	    	Game.startGame(currenthero);
	        theStage.setScene(scene3);

	    	setmap();

	    });	    button6.setOnMouseEntered(e -> button6.setGraphic(ViewBillInfo));
	    button6.setOnMouseExited(e -> button6.setGraphic(ViewBill));
	    

	    Image DavidInfo = new Image("Davidinfo.png");
	    Image David = new Image("David.png");
	    ImageView ViewDavidInfo = new ImageView(DavidInfo);
	    ImageView ViewDavid = new ImageView(David);
	    ViewDavid.setFitHeight(300);
	    ViewDavid.setPreserveRatio(true);
	    ViewDavidInfo.setFitHeight(300);
	    ViewDavidInfo.setPreserveRatio(true);
	    Button button7 = new Button();
	    button7.setTranslateX(780);
	    button7.setTranslateY(550);
	    button7.setPrefSize(150,150);
	    button7.setGraphic(ViewDavid);
	    button7.setStyle("-fx-background-color:transparent;");
	    button7.setOnAction(e -> {	
	    	currenthero = Game.availableHeroes.get(6);
	    	Game.startGame(currenthero);
	        theStage.setScene(scene3);


	    	setmap();

	    });	    button7.setOnMouseEntered(e -> button7.setGraphic(ViewDavidInfo));
	    button7.setOnMouseExited(e -> button7.setGraphic(ViewDavid));
	    
	    Image HenryInfo = new Image("Henryinfo.png");
	    Image Henry = new Image("Henry.png");
	    ImageView ViewHenryInfo = new ImageView(HenryInfo);
	    ImageView ViewHenry = new ImageView(Henry);
	    ViewHenry.setFitHeight(300);
	    ViewHenry.setPreserveRatio(true);
	    ViewHenryInfo.setFitHeight(300);
	    ViewHenryInfo.setPreserveRatio(true);
	    Button button8 = new Button();
	    button8.setTranslateX(1140);
	    button8.setTranslateY(550);
	    button8.setPrefSize(150,150);
	    button8.setGraphic(ViewHenry); 
	    button8.setStyle("-fx-background-color:transparent;");
	    button8.setOnAction(e -> {	
	    	currenthero = Game.availableHeroes.get(7);
	    	Game.startGame(currenthero);
	        theStage.setScene(scene3);
	        heroinfo(currenthero);
	    	setmap();

	    });	    
	    button8.setOnMouseEntered(e -> button8.setGraphic(ViewHenryInfo));
	    button8.setOnMouseExited(e -> button8.setGraphic(ViewHenry));

	    heroesgrid.getChildren().addAll(button1,button2,button3,button4,button5,button6,button7,button8);

		layout2.getChildren().add(heroesgrid);
		layout2.setAlignment(heroesgrid, Pos.CENTER);
	    
	    
	  //set scene3
	    //BorderPane 
	    layout3 = new BorderPane();
	    
	    scene3 = new Scene (layout3,1500,1000);
		scene3.getStylesheets().add("Viper.css");
		layout3.setId("pane3");
		layout3.setPadding(new Insets(0, 5, 0, 5));

		
		rightvbox = new VBox();	    
		leftvbox = new VBox();	    
	    
		leftvbox.setMinWidth(350);
	    rightvbox.setMinWidth(350);
	    rightvbox.setAlignment(Pos.CENTER);
		
		layout3.setLeft(leftvbox);
	    layout3.setRight(rightvbox);
	    
		setmap();
		layout3.setCenter(mapgrid);
	    
	    Label l = new Label("YOU'RE NOW PLAYING WITH");
	    l.setStyle("-fx-font-weight: bold");
	    l.setWrapText(true);
	    l.setFont(Font.font(20));
	    
	    rightvbox.getChildren().addAll(l);	   

	    Button cure = new Button();
	    cure.setStyle("-fx-background-color: transparent;");
	    Image c = new Image("CURE.png");
	    ImageView cview = new ImageView(c);
	    cure.setGraphic(cview);
	    cview.setFitHeight(70);
	    cview.setPreserveRatio(true);	    
	    cure.setPadding(new Insets(50,0,0,0));
	    
	    Button attack = new Button ();
	    attack.setStyle("-fx-background-color: transparent;");
	    Image a = new Image("ATTACK.png");
	    ImageView aview = new ImageView(a);
	    attack.setGraphic(aview);
	    aview.setFitHeight(70);
	    aview.setPreserveRatio(true);
	    
	    Button special = new Button ();
	    special.setStyle("-fx-background-color: transparent;");
	    Image SA = new Image("SPECIALACTION.png");
	    ImageView SAview = new ImageView(SA);
	    special.setGraphic(SAview);
	    SAview.setFitHeight(70);
	    SAview.setPreserveRatio(true);
//	    special.setOnAction(new EventHandler<ActionEvent>() {
//	        public void handle(ActionEvent event) {
//	           
//	        }
//	    });
	    

	    Button endturn = new Button ();
	    endturn.setStyle("-fx-background-color: transparent;");
	    Image ET = new Image("ENDTURN!.png");
	    ImageView ETView = new ImageView(ET);
	    endturn.setGraphic(ETView);
	    ETView.setFitHeight(70);
	    ETView.setPreserveRatio(true);
	    
	    currentherobox.setMinHeight(150);
	    currentherodetailsbox.setMinHeight(200);
	    
	    rightvbox.setSpacing(15);
	    rightvbox.getChildren().addAll(currentherobox,currentherodetailsbox,cure,attack,special,endturn);
	    
	    
	    attack.setOnAction( e -> {
	    	try {
	    		int i = targetLocation.x;
	    		int j = targetLocation.y;
	    		
	    		currenthero.setTarget(((CharacterCell)Game.map[j][i]).getCharacter());
				currenthero.attack();
				setmap();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				if(e1 instanceof NotEnoughActionsException){
					layout3.getChildren().add(getException("noactionpoints"));
				}
				
				if(e1 instanceof InvalidTargetException){
					layout3.getChildren().add(getException("invalidtarget"));
				}

				if(currenthero.getTarget()==null){
					layout3.getChildren().add(getException("notarget"));
				}
				
				e1.printStackTrace();
			}
	    });

	    
	    
	    cure.setOnAction(e -> {
	    	
	    	
	    	try {
	    		int i = targetLocation.x;
	    		int j = targetLocation.y;
	    		
	    		Character target = ((CharacterCell)Game.map[j][i]).getCharacter();
	    		currenthero.setTarget(target);
				currenthero.cure();
				setmap();
			} catch (Exception e1) {
				
				if(currenthero.getVaccineInventory().isEmpty()){
					layout3.getChildren().add(getException("novaccine"));
				}
				
				if(currenthero.getActionsAvailable()==0){
					layout3.getChildren().add(getException("noactionpoints"));
				}
				
				if(currenthero.getTarget()==null){
					layout3.getChildren().add(getException("notarget"));
				}
				
				if(currenthero.getTarget() instanceof Hero){
					layout3.getChildren().add(getException("invalidtarget"));
				}

				
				e1.printStackTrace();
				
			}
	    });
	    	    
	    special.setOnAction(e -> {
	    	try {
	    		if (currenthero instanceof Medic)
	    			currenthero.setTarget(((CharacterCell)Game.map[targetLocation.y][targetLocation.x]).getCharacter());
				currenthero.useSpecial();
			} catch (Exception e1) {
				if(currenthero.getSupplyInventory().isEmpty()){
					layout3.getChildren().add(getException("nosupply"));
				}
				
				if(currenthero.getActionsAvailable()==0){
					layout3.getChildren().add(getException("noactionpoints"));
				}
				
				if(currenthero instanceof Medic && currenthero.getTarget()==null){
					layout3.getChildren().add(getException("notarget"));
				}
				
				if(currenthero instanceof Medic && currenthero.getTarget() instanceof Zombie){
					layout3.getChildren().add(getException("invalidtarget"));
				}
				e1.printStackTrace();
			}
	    	setmap();
	    });
	    
	    endturn.setOnAction(e -> {
	    	
	    	try {
				Game.endTurn();
				setmap();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    });
	    
	    Button trapcell = new Button();
	    trapcell.setStyle("-fx-background-color: transparent;");
	    Image tp= new Image("trapcell.png");
	    ImageView tpview = new ImageView(tp);
	    trapcell.setGraphic(tpview);
	    tpview.setFitHeight(100);
	    tpview.setPreserveRatio(true);
	    trapcell.setOnAction(e->{
	    	layout3.getChildren().remove(trapcell);
	    });


	    trapcell.setTranslateX(750);
	    trapcell.setTranslateY(450);
       scene3.setOnKeyPressed(event -> {

    	  
    	   switch (event.getCode()) {
                case A:
                	
                	
					try {

						currenthero.move(Direction.LEFT);
						
						
						setmap();
						if (Game.traps.contains(currenthero.getLocation())){
							layout3.getChildren().add(trapcell);
						}
						
					} catch (Exception e2) {
														
						if(e2 instanceof NotEnoughActionsException){
							
							layout3.getChildren().add(getException("noactionpoints"));
							
						}
						if (e2.getMessage() == "You cannot move outside the borders of the map." || e2.getMessage() == "You cannot move to an occuppied cell.") {
							layout3.getChildren().add(getException("invalidmovement"));
						}
						e2.printStackTrace();
					}
                    break;
                case D:
					try {

						currenthero.move(Direction.RIGHT);
						//System.out.print(Game.traps);
						
						setmap();
						if (Game.traps.contains(currenthero.getLocation())){
							//System.out.print("YARABBB");
							layout3.getChildren().add(trapcell);
						}
					
					} catch (Exception e2) {
						if(e2 instanceof NotEnoughActionsException){
							layout3.getChildren().add(getException("noactionpoints"));
						}
						if (e2.getMessage() == "You cannot move outside the borders of the map." || e2.getMessage() == "You cannot move to an occuppied cell.") {
							layout3.getChildren().add(getException("invalidmovement"));
						}
						e2.printStackTrace();
					}
					
                    break;
                case W:
					try {

						currenthero.move(Direction.UP);
						
						setmap();
						if (Game.traps.contains(currenthero.getLocation())){
							layout3.getChildren().add(trapcell);
						}

					} catch (Exception e2) {
						if(e2 instanceof NotEnoughActionsException){
							layout3.getChildren().add(getException("noactionpoints"));
						}
						if (e2.getMessage() == "You cannot move outside the borders of the map." || e2.getMessage() == "You cannot move to an occuppied cell.") {
							layout3.getChildren().add(getException("invalidmovement"));
						}
						e2.printStackTrace();
					}
					
                    break;
                case S:
					try {

						currenthero.move(Direction.DOWN);
						
						setmap();
						if (Game.traps.contains(currenthero.getLocation())){
							layout3.getChildren().add(trapcell);
						}

					} catch (Exception e2) {
						if(e2 instanceof NotEnoughActionsException){
							layout3.getChildren().add(getException("noactionpoints"));
						}
						if (e2.getMessage() == "You cannot move outside the borders of the map." || e2.getMessage() == "You cannot move to an occuppied cell.") {
							layout3.getChildren().add(getException("invalidmovement"));
						}
						e2.printStackTrace();
					}
                    break;
                default:
                    break;
            }
    	   
    	   
        });
    
     

        
        theStage.setScene(scene1);
		theStage.show();

		
	}

	public static void setmap(){
		
		GridPane updatedMap = new GridPane();
	    

		
		 for (int i = 0; i <= 14; i++){
			 for (int j =0; j <= 14; j++){

				 
				 Cell cell = Game.map[i][j];
				 Rectangle rAdd = new Rectangle(); 
				 
				 rAdd.setWidth(50);
				 rAdd.setHeight(50);
		
				 if (cell != null){
					 if (cell.isVisible()){
						 // stack.getChildren().addAll(rAdd, text);
						 Image visiblecell = new Image("visiblecell.png");
						 rAdd.setFill(new ImagePattern(visiblecell));
						 if (cell instanceof CollectibleCell && ((CollectibleCell)cell).getCollectible() instanceof Supply ){
							 Image supply = new Image("supply2.png");
							 rAdd.setFill(new ImagePattern(supply));
						 }
//						 if ( cell instanceof TrapCell ){
//							 Image trap = new Image("trapdamage.png");
//							 rAdd.setFill(new ImagePattern(trap));	
//						 }
						 if (cell instanceof CollectibleCell && ((CollectibleCell)cell).getCollectible() instanceof Vaccine ){
							 Image vaccine = new Image("vaccine2.png");
							 rAdd.setFill(new ImagePattern(vaccine));	
							 }
						 if (cell instanceof CharacterCell && ((CharacterCell)cell).getCharacter() instanceof Hero ){
							 if (((CharacterCell)cell).getCharacter().getName().equals("Joel Miller")){
								 Image joel2 = new Image("joel2.png");
								 rAdd.setFill(new ImagePattern(joel2));
							 }
							 else if (((CharacterCell)cell).getCharacter().getName().equals("Tess")){
								 Image tess2 = new Image("tess2.png");
								 rAdd.setFill(new ImagePattern(tess2));
							 }
							 else if (((CharacterCell)cell).getCharacter().getName().equals("Riley Abel")){
								 Image riley2 = new Image("riley2.png");
								 rAdd.setFill(new ImagePattern(riley2));
							 }
							 else if (((CharacterCell)cell).getCharacter().getName().equals("Tommy Miller")){
								 Image tommy2 = new Image("tommy2.png");
								 rAdd.setFill(new ImagePattern(tommy2));
							 }
							 else if (((CharacterCell)cell).getCharacter().getName().equals("Bill")){
								 Image bill2 = new Image("bill2.png");
								 rAdd.setFill(new ImagePattern(bill2));
							 }
							 else if (((CharacterCell)cell).getCharacter().getName().equals("David")){
								 Image david2 = new Image("david2.png");
								 rAdd.setFill(new ImagePattern(david2));
							 }
							 else if (((CharacterCell)cell).getCharacter().getName().equals("Henry Burell")){
								 Image henry2 = new Image("henry2.png");
								 rAdd.setFill(new ImagePattern(henry2));
							 }
							 else if (((CharacterCell)cell).getCharacter().getName().equals("Ellie Williams")){
								 Image ellie2 = new Image("ellie2.png");
								 rAdd.setFill(new ImagePattern(ellie2));
							 }
							 
						 }
					  
						 if (cell instanceof CharacterCell && ((CharacterCell)cell).getCharacter() instanceof Zombie ){
							 Image clicker = new Image("clicker2.png");
							 rAdd.setFill(new ImagePattern(clicker));						 }
						 if (cell instanceof TrapCell){
							 rAdd.setFill(new ImagePattern(visiblecell));						 }
					 } 
						 else {
						 Image invisiblecell = new Image("inviso2.png");
						 rAdd.setFill(new ImagePattern(invisiblecell));
					 }
//					 if ( cell instanceof TrapCell ){
//						 Image trap = new Image("trapdamage.png");
//						 rAdd.setFill(new ImagePattern(trap));	
//					 }
					
					 
				 }
				 
				 final int currentI = i;
				 final int currentJ = j;
				 
				 rAdd.setOnMouseClicked(e -> {
					 targetLocation.setLocation(new Point(currentJ, currentI));
				 });
				 
				 updatedMap.add(rAdd,j,14-i);
				 
//				 karoun.getChildren().add(rAdd);
			 }
		}
		 
		 refreshCurrentHeroes();
		 
		 mapgrid.getChildren().clear();
		 mapgrid = updatedMap;
		 layout3.getChildren().clear();
		 layout3.setCenter(mapgrid);
		 layout3.setRight(rightvbox);
		 layout3.setLeft(leftvbox);
		 mapgrid.setAlignment(Pos.CENTER);
		 
		 if (Game.checkWin()){
				StackPane parentNode = new StackPane();
				Scene winScene = new Scene(parentNode,1500,1000);
				//Label message = new Label("You win");
//				parentNode.setStyle("-fx-background-color:black;");
//				message.setStyle("-fx-text-fill:green; -fx-font-size: 48px;");
//				message.setTextAlignment(TextAlignment.CENTER);
//				parentNode.getChildren().add(message);
//				parentNode.setAlignment(Pos.CENTER);
//				message.setAlignment(Pos.CENTER);
				winScene.getStylesheets().add("Viper.css");
				parentNode.setId("panewin");
				
				theStage.setScene(winScene);
			} else if (Game.checkGameOver()) {
				StackPane parentNode = new StackPane();
				Scene loseScene = new Scene(parentNode,1500,1000);
				
//				Label message = new Label("You lose\nYa Fashel!");
//				parentNode.setStyle("-fx-background-color:black;");
//				message.setStyle("-fx-text-fill:red; -fx-font-size: 48px;");
//				message.setTextAlignment(TextAlignment.CENTER);
//				parentNode.getChildren().add(message);
//				parentNode.setAlignment(Pos.CENTER);
//				message.setAlignment(Pos.CENTER);
				
				loseScene.getStylesheets().add("Viper.css");
				parentNode.setId("panelose");
				theStage.setScene(loseScene);
				
			}
	}
		 
		 
	public static Button getException(String exceptionType) {
	    Button invalidmovement = new Button();
	    invalidmovement.setStyle("-fx-background-color: transparent;");
	    Image invmove= new Image("invalidmovement.png");
	    ImageView invmoveview = new ImageView(invmove);
	    invalidmovement.setGraphic(invmoveview);
	    invmoveview.setFitHeight(100);
	    invmoveview.setPreserveRatio(true);
	    invalidmovement.setOnAction(e->{
	    	layout3.getChildren().remove(invalidmovement);
	    });
	    
	    Button invalidtarget = new Button();
	    invalidtarget.setStyle("-fx-background-color: transparent;");
	    Image invtarg= new Image("invalidtarget.png");
	    ImageView invtargview = new ImageView(invtarg);
	    invalidtarget.setGraphic(invtargview);
	    invtargview.setFitHeight(100);
	    invtargview.setPreserveRatio(true);
	    invalidtarget.setOnAction(e->{
	    	layout3.getChildren().remove(invalidtarget);
	    });
	    
	    Button notarget = new Button();
	    notarget.setStyle("-fx-background-color: transparent;");
	    Image notarg= new Image("pleasesettarget.png");
	    ImageView notargview = new ImageView(notarg);
	    notarget.setGraphic(notargview);
	    notargview.setFitHeight(100);
	    notargview.setPreserveRatio(true);
	    notarget.setOnAction(e->{
	    	layout3.getChildren().remove(notarget);
	    });
	    
	    Button noactionpoints = new Button();
	    noactionpoints.setStyle("-fx-background-color: transparent;");
	    Image noactions= new Image("notenoughactionpoints.png");
	    ImageView noactionsview = new ImageView(noactions);
	    noactionpoints.setGraphic(noactionsview);
	    noactionsview.setFitHeight(100);
	    noactionsview.setPreserveRatio(true);
	    noactionpoints.setOnAction(e->{
	    	layout3.getChildren().remove(noactionpoints);
	    });
	    
	    Button novaccine = new Button();
	    novaccine.setStyle("-fx-background-color: transparent;");
	    Image novacc= new Image("notenoughvaccines.png");
	    ImageView novaccview = new ImageView(novacc);
	    novaccine.setGraphic(novaccview);
	    novaccview.setFitHeight(100);
	    novaccview.setPreserveRatio(true);
	    novaccine.setOnAction(e->{
	    	layout3.getChildren().remove(novaccine);
	    });
	    
	    Button nosupply = new Button();
	    nosupply.setStyle("-fx-background-color: transparent;");
	    Image nosupp= new Image("notenoughsupplies.png");
	    ImageView nosuppview = new ImageView(nosupp);
	    nosupply.setGraphic(nosuppview);
	    nosuppview.setFitHeight(100);
	    nosuppview.setPreserveRatio(true);
	    nosupply.setOnAction(e->{
	    	layout3.getChildren().remove(nosupply);
	    });
	    
	    

	    invalidmovement.setTranslateX(750);
	    invalidmovement.setTranslateY(450);
	    
	    invalidtarget.setTranslateX(750);
	    invalidtarget.setTranslateY(450);
	    
	    notarget.setTranslateX(750);
	    notarget.setTranslateY(450);
	    
	    noactionpoints.setTranslateX(750);
	    noactionpoints.setTranslateY(450);
	    
	    novaccine.setTranslateX(750);
	    novaccine.setTranslateY(450);

	    nosupply.setTranslateX(750);
	    nosupply.setTranslateY(450);
	    

	    switch (exceptionType) {
	    case "invalidmovement":
	    	return invalidmovement;
	    case "invalidtarget":
	    	return invalidtarget;
	    case "notarget":
	    	return notarget;
	    case "nosupply":
	    	return nosupply;
	    case "novaccine":
	    	return novaccine;
	    case "noactionpoints":
	    	return noactionpoints;
    	default:
    		return null;
	    }

	}
		 
	public static void refreshCurrentHeroes() {
		VBox heroesVBox = new VBox();
		
		Hero hero1 = null;
		Hero hero2 = null;
		Hero hero3 = null;
		Hero hero4 = null;
		Hero hero5 = null;
		Hero hero6 = null;
		Hero hero7 = null;
		Hero hero8 = null;
		
		for (int i = 0; i<Game.heroes.size(); i++) {
			if (Game.heroes.get(i).getName().equals("Joel Miller"))
				hero1 = Game.heroes.get(i);
			if (Game.heroes.get(i).getName().equals("Ellie Williams"))
				hero2 = Game.heroes.get(i);
			if (Game.heroes.get(i).getName().equals("Tess"))
				hero3 = Game.heroes.get(i);
			if (Game.heroes.get(i).getName().equals("Riley Abel"))
				hero4 = Game.heroes.get(i);
			if (Game.heroes.get(i).getName().equals("Tommy Miller"))
				hero5 = Game.heroes.get(i);
			if (Game.heroes.get(i).getName().equals("Bill"))
				hero6 = Game.heroes.get(i);
			if (Game.heroes.get(i).getName().equals("David"))
				hero7 = Game.heroes.get(i);
			if (Game.heroes.get(i).getName().equals("Henry Burell"))
				hero8 = Game.heroes.get(i);
			
		}
		
		ArrayList<Hero> tempHeroes = new ArrayList<Hero>();
		
		for (int i=0; i<Game.heroes.size(); i++) {
			tempHeroes.add(Game.heroes.get(i));
		}

	    Image Joel = new Image("Joel.png");
	    Image JoelInfo = new Image("Joelinfo.png");
	    Label JoelLabel = new Label();
	   // JoelLabel.setStyle("-fx-background-color:black; -fx-text-fill:white; -fx-font-size:16px; -fx-font-weight:bold;");
	    JoelLabel.setMinHeight(180);
	    if (hero1 != null){
	    	JoelLabel = miniheroinfo(hero1);
	    }
	    ImageView ViewJoelInfo = new ImageView(JoelInfo);
	    ImageView ViewJoel = new ImageView(Joel);
	    ViewJoel.setFitHeight(180);
	    ViewJoel.setPreserveRatio(true);
	    ViewJoelInfo.setPreserveRatio(true);
	    ViewJoelInfo.setFitHeight(180);
	    Button button1 = new Button();
	    button1.setPrefSize(180,180);
	    button1.setGraphic(ViewJoel); 
	    button1.setStyle("-fx-background-color:transparent;");
	    button1.setOnAction(e ->{ 
	    	for (int i = 0; i<Game.heroes.size(); i++) {
	    		if (Game.heroes.get(i).getName().equals("Joel Miller")){
	    			currenthero = Game.heroes.get(i);
	    			currentherobutton = button1;
	    			currentherobox.getChildren().clear();
	    			currentherobox.getChildren().add(currentherobutton);
	    			currentherodetailsbox.getChildren().clear();
	    			currentherodetailsbox.getChildren().add(heroinfo(currenthero));
	    		}
	    	}
	    	setmap();
	    });
	    
    	final Label theText1 = JoelLabel;
    	final Hero finalhero1 = hero1;
    	
	    button1.setOnMouseEntered(e -> {
	    	if (currenthero != finalhero1) {
	    		button1.setStyle("-fx-background-color:Black; -fx-text-fill:white; -fx-font-size:16px; -fx-font-weight:bold; -fx-border-radius: 15px; -fx-background-radius: 15px;");
		    	button1.setGraphic(null);
		    	button1.setText(theText1.getText());
		    
	    	} else {
	    		
	    		button1.setGraphic(ViewJoelInfo);
	    	}
	    });
	    button1.setOnMouseExited(e ->{
	    	if (currenthero != finalhero1) {
	    		button1.setStyle("-fx-background-color:transparent");
	    		button1.setText(""); 	    
	    	}
	    	button1.setGraphic(ViewJoel);
	    	
	    });
	    
	    
	    
	    Image EllieInfo = new Image("Ellieinfo.png");
	    Image Ellie = new Image("Ellie.png");
	    ImageView ViewEllieInfo = new ImageView(EllieInfo);
	    ImageView ViewEllie = new ImageView(Ellie);
	    ViewEllie.setFitHeight(180);
	    ViewEllie.setPreserveRatio(true);
	    ViewEllieInfo.setFitHeight(180);
	    ViewEllieInfo.setPreserveRatio(true);
	    Button button2 = new Button();
	    button2.setPrefSize(180,180);
	    button2.setGraphic(ViewEllie); 
	    button2.setStyle("-fx-background-color:transparent; ");
	    button2.setOnAction(e -> {	
	    	for (int i = 0; i<Game.heroes.size(); i++) {
	    		if (Game.heroes.get(i).getName().equals("Ellie Williams")){
	    			currenthero = Game.heroes.get(i);
	    			currentherobutton = button2;
	    			currentherobox.getChildren().clear();
	    			currentherobox.getChildren().add(currentherobutton);
	    			currentherodetailsbox.getChildren().clear();
	    			currentherodetailsbox.getChildren().add(heroinfo(currenthero));
	    		}
	    	}
	    	setmap();
	    	});
	    
	    Label EllieLabel = new Label();
	    EllieLabel.setMinHeight(180);
	    if (hero2 != null){
	    	EllieLabel = miniheroinfo(hero2);
	    }	    
	    
    	final Label theText2 = EllieLabel;
    	final Hero finalhero2 = hero2;
    	
	    button2.setOnMouseEntered(e -> {
	    	if (currenthero != finalhero2) {
	    		button2.setStyle("-fx-background-color:Black; -fx-text-fill:white; -fx-font-size:16px; -fx-font-weight:bold; -fx-border-radius: 15px; -fx-background-radius: 15px;");

		    	button2.setGraphic(null);
		    	button2.setText(theText2.getText());
	    	} else {
	    		button2.setGraphic(ViewEllieInfo);
	    	}
	    });
	    button2.setOnMouseExited(e ->{
	    	if (currenthero != finalhero2) {
	    		button2.setStyle("-fx-background-color:transparent");
	    		button2.setText(""); 	    
	    	}
	    	button2.setGraphic(ViewEllie);
	    });
	    

	    Image TessInfo = new Image("Tessinfo.png");
	    Image Tess = new Image("Tess.png");
	    ImageView ViewTessInfo = new ImageView(TessInfo);
	    ImageView ViewTess = new ImageView(Tess);
	    ViewTess.setFitHeight(180);
	    ViewTess.setPreserveRatio(true);
	    ViewTessInfo.setFitHeight(180);
	    ViewTessInfo.setPreserveRatio(true);
	    Button button3 = new Button();
	    button3.setPrefSize(180,180);
	    button3.setGraphic(ViewTess); 
	    button3.setStyle("-fx-background-color:transparent;");
	    button3.setOnAction(e -> {	
	    	for (int i = 0; i<Game.heroes.size(); i++) {
	    		if (Game.heroes.get(i).getName().equals("Tess")){
	    			currenthero = Game.heroes.get(i);
	    			currentherobutton = button3;
	    			currentherobox.getChildren().clear();
	    			currentherobox.getChildren().add(currentherobutton);
	    			currentherodetailsbox.getChildren().clear();
	    			currentherodetailsbox.getChildren().add(heroinfo(currenthero));
	    		}
	    	}
	    	System.out.println(heroinfo(currenthero));
	    	setmap();
	    });

	    Label TessLabel = new Label();
	    TessLabel.setMinHeight(180);
	    if (hero3 != null){
	    	TessLabel = miniheroinfo(hero3);
	    }	    
	    
    	final Label theText3 = TessLabel;
    	final Hero finalhero3 = hero3;
    	
	    button3.setOnMouseEntered(e -> {
	    	if (currenthero != finalhero3) {
	    		button3.setStyle("-fx-background-color:Black; -fx-text-fill:white; -fx-font-size:16px; -fx-font-weight:bold; -fx-border-radius: 15px;-fx-background-radius: 15px; ");

		    	button3.setGraphic(null);
		    	button3.setText(theText3.getText());
	    	} else {
	    		button3.setGraphic(ViewTessInfo);
	    	}
	    });
	    button3.setOnMouseExited(e ->{
	    	if (currenthero != finalhero3) {
	    		button3.setStyle("-fx-background-color:transparent");
	    		button3.setText(""); 	    
	    	}
	    	button3.setGraphic(ViewTess);
	    });
	    

	    Image RileyInfo = new Image("Rileyinfo.png");
	    Image Riley = new Image("Riley.png");
	    ImageView ViewRileyInfo = new ImageView(RileyInfo);
	    ImageView ViewRiley = new ImageView(Riley);
	    ViewRiley.setFitHeight(180);
	    ViewRiley.setPreserveRatio(true);
	    ViewRileyInfo.setFitHeight(180);
	    ViewRileyInfo.setPreserveRatio(true);
	    Button button4 = new Button();
	    button4.setPrefSize(180,180);
	    button4.setGraphic(ViewRiley); 
	    button4.setStyle("-fx-background-color:transparent;");
	    button4.setOnAction(e -> {	
	    	for (int i = 0; i<Game.heroes.size(); i++) {
	    		if (Game.heroes.get(i).getName().equals("Riley Abel")){
	    			currenthero = Game.heroes.get(i);
	    			currentherobutton = button4;
	    			currentherobox.getChildren().clear();
	    			currentherobox.getChildren().add(currentherobutton);
	    			currentherodetailsbox.getChildren().clear();
	    			currentherodetailsbox.getChildren().add(heroinfo(currenthero));
	    		}
	    	}
	    	setmap();
	    });

	    Label RileyLabel = new Label();
	    RileyLabel.setMinHeight(180);
	    if (hero4 != null){
	    	RileyLabel = miniheroinfo(hero4);
	    }	    
	    
    	final Label theText4 = RileyLabel;
    	final Hero finalhero4 = hero4;
    	
	    button4.setOnMouseEntered(e -> {
	    	if (currenthero != finalhero4) {
	    		button4.setStyle("-fx-background-color:Black; -fx-text-fill:white; -fx-font-size:16px; -fx-font-weight:bold; -fx-border-radius: 15px; -fx-background-radius: 15px;");

		    	button4.setGraphic(null);
		    	button4.setText(theText4.getText());
	    	} else {
	    		button4.setGraphic(ViewRileyInfo);
	    	}
	    });
	    button4.setOnMouseExited(e ->{
	    	if (currenthero != finalhero4) {
	    		button4.setStyle("-fx-background-color:transparent");
	    		button4.setText(""); 	    
	    	}
	    	button4.setGraphic(ViewRiley);
	    });	    

	    Image TommyInfo = new Image("Tommyinfo.png");
	    Image Tommy = new Image("Tommy.png");
	    ImageView ViewTommyInfo = new ImageView(TommyInfo);
	    ImageView ViewTommy = new ImageView(Tommy);
	    ViewTommy.setFitHeight(180);
	    ViewTommy.setPreserveRatio(true);
	    ViewTommyInfo.setFitHeight(180);
	    ViewTommyInfo.setPreserveRatio(true);
	    Button button5 = new Button();
	    button5.setPrefSize(180,180);
	    button5.setGraphic(ViewTommy); 
	    button5.setStyle("-fx-background-color:transparent;");
	    button5.setOnAction(e -> {	
	    	for (int i = 0; i<Game.heroes.size(); i++) {
	    		if (Game.heroes.get(i).getName().equals("Tommy Miller")){
	    			currenthero = Game.heroes.get(i);
	    			currentherobutton = button5;
	    			currentherobox.getChildren().clear();
	    			currentherobox.getChildren().add(currentherobutton);
	    			currentherodetailsbox.getChildren().clear();
	    			currentherodetailsbox.getChildren().add(heroinfo(currenthero));
	    		}
	    	}
	    	setmap();
	    });

	    Label TommyLabel = new Label();
	    TommyLabel.setMinHeight(180);
	    if (hero5 != null){
	    	TommyLabel = miniheroinfo(hero5);
	    }	    
	    
    	final Label theText5 = TommyLabel;
    	final Hero finalhero5 = hero5;
    	
	    button5.setOnMouseEntered(e -> {
	    	if (currenthero != finalhero5) {
	    		button5.setStyle("-fx-background-color:Black; -fx-text-fill:white; -fx-font-size:16px; -fx-font-weight:bold; -fx-border-radius: 15px; -fx-background-radius: 15px;");

		    	button5.setGraphic(null);
		    	button5.setText(theText5.getText());
	    	} else {
	    		button5.setGraphic(ViewTommyInfo);
	    	}
	    });
	    button5.setOnMouseExited(e ->{
	    	if (currenthero != finalhero5) {
	    		button5.setStyle("-fx-background-color:transparent");
	    		button5.setText(""); 	    
	    	}
	    	button5.setGraphic(ViewTommy);
	    });	    
	    

	    Image BillInfo = new Image("Billinfo.png");
	    Image Bill = new Image("Bill.png");
	    ImageView ViewBillInfo = new ImageView(BillInfo);
	    ImageView ViewBill = new ImageView(Bill);
	    ViewBill.setFitHeight(180);
	    ViewBill.setPreserveRatio(true);
	    ViewBillInfo.setFitHeight(180);
	    ViewBillInfo.setPreserveRatio(true);
	    Button button6 = new Button();
	    button6.setPrefSize(180,180);
	    button6.setGraphic(ViewBill);
	    button6.setStyle("-fx-background-color:transparent;");
	    button6.setOnAction(e -> {	
	    	for (int i = 0; i<Game.heroes.size(); i++) {
	    		if (Game.heroes.get(i).getName().equals("Bill")){
	    			currenthero = Game.heroes.get(i);
	    			currentherobutton = button6;
	    			currentherobox.getChildren().clear();
	    			currentherobox.getChildren().add(currentherobutton);
	    			currentherodetailsbox.getChildren().clear();
	    			currentherodetailsbox.getChildren().add(heroinfo(currenthero));
	    		}
	    	}
	    	setmap();
	    });

	    Label BillLabel = new Label();
	    BillLabel.setMinHeight(180);
	    if (hero6 != null){
	    	BillLabel = miniheroinfo(hero6);
	    }	    
	    
    	final Label theText6 = BillLabel;
    	final Hero finalhero6 = hero6;
    	
	    button6.setOnMouseEntered(e -> {
	    	if (currenthero != finalhero6) {
	    		button6.setStyle("-fx-background-color:Black; -fx-text-fill:white; -fx-font-size:16px; -fx-font-weight:bold; -fx-border-radius: 15px; -fx-background-radius: 15px;");
		    	button6.setGraphic(null);
		    	button6.setText(theText6.getText());
	    	} else {
	    		button6.setGraphic(ViewBillInfo);
	    	}
	    });
	    button6.setOnMouseExited(e ->{
	    	if (currenthero != finalhero6) {
	    		button6.setStyle("-fx-background-color:transparent");
	    		button6.setText(""); 	    
	    	}
	    	button6.setGraphic(ViewBill);
	    });	    

	    Image DavidInfo = new Image("Davidinfo.png");
	    Image David = new Image("David.png");
	    ImageView ViewDavidInfo = new ImageView(DavidInfo);
	    ImageView ViewDavid = new ImageView(David);
	    ViewDavid.setFitHeight(180);
	    ViewDavid.setPreserveRatio(true);
	    ViewDavidInfo.setFitHeight(180);
	    ViewDavidInfo.setPreserveRatio(true);
	    Button button7 = new Button();
	    button7.setPrefSize(180,180);
	    button7.setGraphic(ViewDavid);
	    button7.setStyle("-fx-background-color:transparent;");
	    button7.setOnAction(e -> {	
	    	for (int i = 0; i<Game.heroes.size(); i++) {
	    		if (Game.heroes.get(i).getName().equals("David")){
	    			currenthero = Game.heroes.get(i);
	    			currentherobutton = button7;
	    			currentherobox.getChildren().clear();
	    			currentherobox.getChildren().add(currentherobutton);
	    			currentherodetailsbox.getChildren().clear();
	    			currentherodetailsbox.getChildren().add(heroinfo(currenthero));
	    		}
	    	}
	    	setmap();
	    });
	    
	    Label DavidLabel = new Label();
	    DavidLabel.setMinHeight(180);
	    if (hero7 != null){
	    	DavidLabel = miniheroinfo(hero7);
	    }	    
	    
    	final Label theText7 = DavidLabel;
    	final Hero finalhero7 = hero7;
    	
	    button7.setOnMouseEntered(e -> {
	    	if (currenthero != finalhero7) {
	    		button7.setStyle("-fx-background-color:Black; -fx-text-fill:white; -fx-font-size:16px; -fx-font-weight:bold; -fx-border-radius: 15px; -fx-background-radius: 15px;");
		    	button7.setGraphic(null);
		    	button7.setText(theText7.getText());
	    	} else {
	    		button7.setGraphic(ViewDavidInfo);
	    	}
	    });
	    button7.setOnMouseExited(e ->{
	    	if (currenthero != finalhero7) {
	    		button7.setStyle("-fx-background-color:transparent");
	    		button7.setText(""); 	    
	    	}
	    	button7.setGraphic(ViewDavid);
	    });
	    
	    Image HenryInfo = new Image("Henryinfo.png");
	    Image Henry = new Image("Henry.png");
	    ImageView ViewHenryInfo = new ImageView(HenryInfo);
	    ImageView ViewHenry = new ImageView(Henry);
	    ViewHenry.setFitHeight(180);
	    ViewHenry.setPreserveRatio(true);
	    ViewHenryInfo.setFitHeight(180);
	    ViewHenryInfo.setPreserveRatio(true);
	    Button button8 = new Button();
	    button8.setPrefSize(180,180);
	    button8.setGraphic(ViewHenry); 
	    button8.setStyle("-fx-background-color:transparent;");
	    button8.setOnAction(e -> {	
	    	for (int i = 0; i<Game.heroes.size(); i++) {
	    		if (Game.heroes.get(i).getName().equals("Henry Burell")){
	    			currenthero = Game.heroes.get(i);
	    			currentherobutton = button8;
	    			currentherobox.getChildren().clear();
	    			currentherobox.getChildren().add(currentherobutton);
	    			currentherodetailsbox.getChildren().clear();
	    			currentherodetailsbox.getChildren().add(heroinfo(currenthero));
	    		}
	    	}
	    	setmap();
	    });	    

	    Label HenryLabel = new Label();
	    HenryLabel.setMinHeight(180);
	    if (hero8 != null){
	    	HenryLabel = miniheroinfo(hero8);
	    }	    
	    
    	final Label theText8 = HenryLabel;
    	final Hero finalhero8 = hero8;
    	
	    button8.setOnMouseEntered(e -> {
	    	if (currenthero != finalhero8){
	    		button8.setStyle("-fx-background-color:Black; -fx-text-fill:white; -fx-font-size:16px; -fx-font-weight:bold; -fx-border-radius: 15px; -fx-background-radius: 15px;");

		    	button8.setGraphic(null);
		    	button8.setText(theText8.getText());
	    	} else {
	    		button8.setGraphic(ViewHenryInfo);
	    	}
	    });
	    button8.setOnMouseExited(e ->{
	    	if (currenthero != finalhero8) {
	    		button8.setStyle("-fx-background-color:transparent");
	    		button8.setText(""); 	    
	    	}
	    	button8.setGraphic(ViewHenry);
	    });
	    
	    // before el loop 3ayzeenx`` enena el current hero nesheel men el array el esmo tempHeroes
	    // nesheelo momken ne7oto fe 7eta tany 3shan ne display him 3al yemeen
	    
	    
	    for (int i = 0; i<tempHeroes.size(); i++) {
	    	if (tempHeroes.get(i).getName().equals("Joel Miller")){
	    		if (currenthero == tempHeroes.get(i)){
	    			currentherobutton = button1;
	    		}else
	    			heroesVBox.getChildren().add(button1);
	    	}
	    	if (tempHeroes.get(i).getName().equals("Ellie Williams")){
	    		if (currenthero == tempHeroes.get(i)){
	    			currentherobutton = button2;
	    		}else
	    			heroesVBox.getChildren().add(button2);
	    	}
	    	if (tempHeroes.get(i).getName().equals("Tess")){
	    		if (currenthero == tempHeroes.get(i)){
	    			currentherobutton = button3;
	    		}else
	    			heroesVBox.getChildren().add(button3);
	    	}
	    	if (tempHeroes.get(i).getName().equals("Riley Abel")){
	    		if (currenthero == tempHeroes.get(i)){
	    			currentherobutton = button4;
	    		}else
	    			heroesVBox.getChildren().add(button4);
	    	}
	    	if (tempHeroes.get(i).getName().equals("Tommy Miller")){
	    		if (currenthero == tempHeroes.get(i)){
	    			currentherobutton = button5;
	    		}else
	    			heroesVBox.getChildren().add(button5);
	    	}
	    	if (tempHeroes.get(i).getName().equals("Bill")){
	    		if (currenthero == tempHeroes.get(i)){
	    			currentherobutton = button6;
	    		}else
	    			heroesVBox.getChildren().add(button6);
	    	}
	    	if (tempHeroes.get(i).getName().equals("David")){
	    		if (currenthero == tempHeroes.get(i)){
	    			currentherobutton = button7;
	    		}else
	    			heroesVBox.getChildren().add(button7);
	    	}
	    	if (tempHeroes.get(i).getName().equals("Henry Burell")){
	    		if (currenthero == tempHeroes.get(i)){
	    			currentherobutton = button8;
	    		}else
	    			heroesVBox.getChildren().add(button8);
	    	}

	    }
	    
	    heroesVBox.setAlignment(Pos.TOP_CENTER);
		heroesVBox.setMinWidth(350);
		heroesVBox.setMaxHeight(1000);
	    leftvbox = heroesVBox;
	    leftvbox.setSpacing(0);
	    
	    if (currentherobutton != null) {
	    	currentherobox.getChildren().clear();
	    	currentherobox.getChildren().add(currentherobutton);
	    	currentherodetailsbox.getChildren().clear();
	    	currentherodetailsbox.getChildren().add(heroinfo(currenthero));
	    }
	    
	}


}

