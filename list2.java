import java.util.Vector;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class list2 extends Application{
	
	// declaration
	Scene scene;
	MenuBar menuBar;
	Menu menu1, menu2;
	MenuItem menuItem1, menuItem2, menuItem3, menuItem4;
	Vector<Detail> vectorDet;
	
	BorderPane borderpane;
	GridPane gridpane;
	
	TableView<Detail> table;
	Connect conn = Connect.getInstance();
	
	public void init() {
		borderpane = new BorderPane();
		gridpane = new GridPane();
		
		table = new TableView<>();
		vectorDet = new Vector<Detail>();
		
		menuBar = new MenuBar();
		menu1 = new Menu("Menu");
		menu2 = new Menu("Logout");
		
		menuItem1 = new MenuItem("Peminjaman");
		menuItem2 = new MenuItem("Pengembalian");
		menuItem3 = new MenuItem("List Peminjaman");
		menuItem4 = new MenuItem("Logout");
		
		menu1.getItems().add(menuItem1);
		menu1.getItems().add(menuItem2);
		menu1.getItems().add(menuItem3);
		menuBar.getMenus().add(menu1);
		
		menu2.getItems().add(menuItem4);
		menuBar.getMenus().add(menu2);
		
		borderpane.setTop(menuBar);
		borderpane.setCenter(table);
		
		scene = new Scene(borderpane, 400, 600);
	}
	
	public void setTable() {
		TableColumn<Detail, String> IDBukuColumn = new TableColumn<Detail, String>("ID Buku");
		IDBukuColumn.setCellValueFactory(new PropertyValueFactory<Detail, String>("idMember"));
		IDBukuColumn.setMinWidth(borderpane.getWidth() / 4);
		
		TableColumn<Detail, String> IDMemberColumn = new TableColumn<Detail, String>("ID Member");
		IDMemberColumn.setCellValueFactory(new PropertyValueFactory<Detail, String>("idBuku"));
		IDMemberColumn.setMinWidth(borderpane.getWidth() / 4);
		
		TableColumn<Detail, String> PinjamColumn = new TableColumn<Detail, String>("Tgl Pinjam");
		PinjamColumn.setCellValueFactory(new PropertyValueFactory<Detail, String>("tglPinjam"));
		PinjamColumn.setMinWidth(borderpane.getWidth() / 4);
		
		TableColumn<Detail, String> KembaliColumn = new TableColumn<Detail, String>("Tgl Kembali");
		KembaliColumn.setCellValueFactory(new PropertyValueFactory<Detail, String>("tglKembali"));
		KembaliColumn.setMinWidth(borderpane.getWidth() / 4);
		
		table.getColumns().addAll(IDBukuColumn, IDMemberColumn, PinjamColumn, KembaliColumn );
		
		refresh();
	}
	
	private void refresh() {
		getData();
		ObservableList<Detail> cusObs = FXCollections.observableArrayList(vectorDet);
		table.setItems(cusObs);
	}
	
	public void move() {
		// pindah ke halaman peminjaman
		menuItem1.setOnAction(ah -> {Stage baru = (Stage) menuBar.getScene().getWindow();
		baru.close();
		
		Stage login = new Stage();
		
		try {
			new peminjaman2().start(login);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}});
		
		// pindah ke halaman pengembalian
		menuItem2.setOnAction(ah -> {Stage baru = (Stage) menuBar.getScene().getWindow();
		baru.close();
				
		Stage login = new Stage();
				
		try {
				new pengembalian2().start(login);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}});
		
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
	}
	
	// Show Data
		private void getData() {
			vectorDet.removeAllElements();
			
			String query = "SELECT idMember, idBuku, tglPinjam, tglKembali FROM isi";
			conn.res = conn.execQuery(query);
			
			try {
				while(conn.res.next()) {
					String IDMEMBER = conn.res.getString("idMember");
					String IDBUKU = conn.res.getString("idBuku");
					String TGLPINJAM = conn.res.getString("tglPinjam");
					String TGLKEMBALI = conn.res.getString("tglKembali");
					
					vectorDet.add(new Detail(IDMEMBER, IDBUKU, TGLPINJAM, TGLKEMBALI));
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		init();
		setTable();
		move();
		
		arg0.setTitle("List Peminjaman");
		arg0.setScene(scene);
		arg0.show();
		
	}

	
}
