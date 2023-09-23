import java.util.Vector;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class pengembalian2 extends Application implements EventHandler<ActionEvent>{
	
	Scene scene;
	BorderPane borderpane;
	GridPane gridpane;
	MenuBar menuBar;
	Menu menu1,menu2;
	MenuItem menuItem1, menuItem2, menuItem3, menuItem4;
	
	Vector<Detail> vectorDet;
	TableView<Detail> table;
	Connect conn = Connect.getInstance();
	
	Label tglLbl,idMemberLbl, kembaliTitle;
	TextField tglField, idMember;
	Button tombol;
	
	public void init(){
		kembaliTitle = new Label("Pengembalian");
		kembaliTitle.setFont(Font.font ("Cambria", 20));
		
		menuBar = new MenuBar();
		menu1 = new Menu("Menu");
		menu2 = new Menu("Logout");
		borderpane = new BorderPane();
		gridpane = new GridPane();
		
		scene = new Scene(borderpane, 500, 600);
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
		
		idMemberLbl = new Label("ID Member : ");
		idMember = new TextField();
		
		tglLbl = new Label("Tanggal Kembali: ");
		tglField = new TextField();
		
		tombol = new Button("Kembalikan");
		tombol.setOnAction(this);
		
		
		vectorDet = new Vector<Detail>();
		table = new TableView<>();
		
		// buat kalau dipencet di table bisa keluar di texfieldnya
		table.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
			if(newValue != null) {
				idMember.setText(newValue.getIdMember());
				
			}
		});
	}
	
	public void layout() {
		borderpane.setTop(menuBar);
		borderpane.setCenter(gridpane);
		borderpane.setBottom(table);
		
		gridpane.add(kembaliTitle, 0, 0);
		gridpane.add(idMemberLbl, 0, 1);
		gridpane.add(idMember, 1, 1);
		gridpane.add(tglLbl, 0, 2);
		gridpane.add(tglField, 1, 2);
		gridpane.add(tombol, 0, 3);
		
		gridpane.setVgap(10);
		gridpane.setHgap(30);
		
		tombol.setTextFill(Color.WHITE);
		tombol.setStyle("-fx-background-color: #ADB0AD;");
		borderpane.setStyle("-fx-background-color: #50C14C;");
	}
	
	public void setTable() {
		TableColumn<Detail, String> IDBukuColumn = new TableColumn<Detail, String>("ID Buku");
		IDBukuColumn.setCellValueFactory(new PropertyValueFactory<Detail, String>("idBuku"));
		IDBukuColumn.setMinWidth(borderpane.getWidth() / 3);
		
		TableColumn<Detail, String> IDMemberColumn = new TableColumn<Detail, String>("ID Member");
		IDMemberColumn.setCellValueFactory(new PropertyValueFactory<Detail, String>("idMember"));
		IDMemberColumn.setMinWidth(borderpane.getWidth() / 3);
		
		TableColumn<Detail, String> PinjamColumn = new TableColumn<Detail, String>("Tgl Pinjam");
		PinjamColumn.setCellValueFactory(new PropertyValueFactory<Detail, String>("tglPinjam"));
		PinjamColumn.setMinWidth(borderpane.getWidth() / 3);
		
		table.getColumns().addAll(IDBukuColumn, IDMemberColumn, PinjamColumn);
		
		refresh();
	}
	
	private void refresh() {
		getData();
		ObservableList<Detail> cusObs = FXCollections.observableArrayList(vectorDet);
		table.setItems(cusObs);
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
				
				// pindah ke halaman list
				menuItem3.setOnAction(ah -> {Stage baru = (Stage) menuBar.getScene().getWindow();
				baru.close();
				
				Stage login = new Stage();
				
				try {
					new list2().start(login);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}});
				
//				buat balik ke menu login
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
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		init();
		layout();
		setTable();
		move();
		
		arg0.setTitle("Pengembalian");
		arg0.setScene(scene);
		arg0.show();
		
	}

	@Override
	public void handle(ActionEvent arg0) {
		String inputMember = idMember.getText();
		String inputKembali = tglField.getText();
		
		
		// buat update tanggal kembali
		if (arg0.getSource() == tombol) {
			if(idMember.getText().length() == 0 || tglField.getText().length() == 0 ) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Harap mengisi informasi pengembalian dengan lengkap!");
				alert.showAndWait();
			}
			else {
				// update tanggal pengembalian
				String query = String.format("UPDATE isi SET tglKembali = '%s' WHERE idMember = '%s'", 
						inputKembali, inputMember);
				
				conn.execUpdate(query);
				refresh();
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText("Informasi Pengembalian berhasil disimpan");
				alert.showAndWait();
				
			}
			
		}
		
	}

}
