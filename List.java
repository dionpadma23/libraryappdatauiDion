import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class List extends BorderPane{
	
	Scene scene;
	Stage stage = new Stage();
	MenuBar menuBar = new MenuBar();
	Menu menu1 = new Menu("Menu"), menu2 = new Menu("Logout");
	MenuItem menuItem1, menuItem2, menuItem3, menuItem4;
	
	BorderPane borderpane = new BorderPane();
	GridPane gridpane = new GridPane();
	
	TableView table = new TableView<>();
	
	
	
	public void list() {
		menuItem1 = new MenuItem("Peminjaman");
		menuItem2 = new MenuItem("Pengembalian");
		menuItem3 = new MenuItem("List Peminjaman");
		menuItem4 = new MenuItem("Logout");
		
		scene = new Scene(borderpane, 400, 600);
		
		TableColumn<Detail, String> IDBukuColumn = new TableColumn<Detail, String>("ID Buku");
		IDBukuColumn.setCellValueFactory(new PropertyValueFactory<Detail, String>("ID"));
		IDBukuColumn.setMinWidth(borderpane.getWidth() / 4);
		
		TableColumn<Detail, String> IDMemberColumn = new TableColumn<Detail, String>("ID Member");
		IDMemberColumn.setCellValueFactory(new PropertyValueFactory<Detail, String>("Name"));
		IDMemberColumn.setMinWidth(borderpane.getWidth() / 4);
		
		TableColumn<Detail, String> PinjamColumn = new TableColumn<Detail, String>("Tgl Pinjam");
		PinjamColumn.setCellValueFactory(new PropertyValueFactory<Detail, String>("Membership"));
		PinjamColumn.setMinWidth(borderpane.getWidth() / 4);
		
		TableColumn<Detail, String> KembaliColumn = new TableColumn<Detail, String>("Tgl Kembali");
		KembaliColumn.setCellValueFactory(new PropertyValueFactory<Detail, String>("Membership"));
		KembaliColumn.setMinWidth(borderpane.getWidth() / 4);
		
		table.getColumns().addAll(IDBukuColumn, IDMemberColumn, PinjamColumn, KembaliColumn );
		table.refresh();
		
		
		
		
		menu1.getItems().add(menuItem1);
		menu1.getItems().add(menuItem2);
		menu1.getItems().add(menuItem3);
		menuBar.getMenus().add(menu1);
		
		menu2.getItems().add(menuItem4);
		menuBar.getMenus().add(menu2);
		
		menuItem1.setOnAction(e -> {
			new Peminjaman();
			stage.close();
			
		});
		
		menuItem2.setOnAction(e -> {
			new Pengembalian();
			stage.close();
			
		});
		
//		buat balik ke menu login
		menuItem4.setOnAction(ah -> {Stage baru = (Stage) menuBar.getScene().getWindow();
		baru.close();
		
		Stage login = new Stage();
		
		try {
			new Main().start(login);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}});
		
		borderpane.setTop(menuBar);
		borderpane.setCenter(table);
	
		
		stage.setTitle("List Peminjaman");
		stage.setScene(scene);
		stage.show();
		
		
	}
	
	
}
