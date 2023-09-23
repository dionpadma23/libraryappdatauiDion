import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
public class Main extends Application {
	
	Scene scene;
	BorderPane borderContainer;
	FlowPane colorContainer;
	GridPane nimContainer;
	Label enterKeyLbl;
	Button color1Btn,color2Btn,color3Btn,color4Btn,color5Btn,no1Btn,no2Btn,no0Btn,no3Btn,no4Btn,no5Btn,no6Btn,no7Btn,no8Btn,no9Btn;
	
	public static void main(String[] args) {
		launch(args);

	}

	private void initiate() {
		borderContainer = new BorderPane();
		scene = new Scene (borderContainer,450,200);
		nimContainer = new GridPane();
		colorContainer = new FlowPane();
		
		color1Btn = new Button ("ColorKey#1");
		color2Btn = new Button ("ColorKey#2");
		color3Btn = new Button ("ColorKey#3");
		color4Btn = new Button ("ColorKey#4");
		color5Btn = new Button ("ColorKey#5");
		
		no0Btn = new Button("0");
		no1Btn = new Button("1");
		no2Btn = new Button("2");
		no3Btn = new Button("3");
		no4Btn = new Button("4");
		no5Btn = new Button("5");
		no6Btn = new Button("6");
		no7Btn = new Button("7");
		no8Btn = new Button("8");
		no9Btn = new Button("9");
		
		enterKeyLbl = new Label("Enter last 5 digit key:");
	}
	
	private void layout() {
		
		colorContainer.getChildren().add(color1Btn);
		colorContainer.getChildren().add(color2Btn);
		colorContainer.getChildren().add(color3Btn);
		colorContainer.getChildren().add(color4Btn);
		colorContainer.getChildren().add(color5Btn);
		
		colorContainer.setPadding(new Insets(10));
		colorContainer.setHgap(10);
		
		nimContainer.add(enterKeyLbl, 0, 0);
		nimContainer.add(no0Btn, 0, 1);
		nimContainer.add(no1Btn, 1, 1);
		nimContainer.add(no2Btn, 2, 1);
		nimContainer.add(no3Btn, 3, 1);
		nimContainer.add(no4Btn, 4, 1);
		nimContainer.add(no5Btn, 5, 1);
		nimContainer.add(no6Btn, 6, 1);
		nimContainer.add(no7Btn, 7, 1);
		nimContainer.add(no8Btn, 8, 1);
		nimContainer.add(no9Btn, 9, 1);
		nimContainer.setPadding(new Insets(10));
		nimContainer.setVgap(10);
		nimContainer.setHgap(10);
		
		
		borderContainer.setTop(colorContainer);
		borderContainer.setCenter(nimContainer);
		
	}
	
	@Override
	public void start(Stage arg0) throws Exception {
		initiate();
		layout();
		arg0.setTitle("Color Key - NIM: 2501965222");
		arg0.setScene(scene);
		arg0.show();
	}

	

}