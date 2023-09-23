import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class peminjaman2 extends Application implements EventHandler<ActionEvent>{
	
	Scene scene;
	MenuBar menuBar;
	Menu menu1, menu2;
	MenuItem menuItem1, menuItem2, menuItem3, menuItem4;
	
	BorderPane borderpane;
	GridPane gridpane;
	Label idMemberLbl, idBukuLbl, idStaffLbl, namaBukuLbl, passLbl, tglLbl, pinjamTitle;
	TextField idMember, idBuku, idStaff, namaBuku, tglField;
	PasswordField pass;
	Button tombol;
	private Connect con = Connect.getInstance();
	
	public void init() {
		borderpane = new BorderPane();
		gridpane = new GridPane();
		
		tombol = new Button("Pinjam");
		tombol.setOnAction(this);
		
		pinjamTitle = new Label("Peminjaman");
		pinjamTitle.setFont(Font.font ("Cambria", 20));
		
		idMemberLbl = new Label("ID Member : ");
		idBukuLbl = new Label("ID Buku : ");
		idStaffLbl = new Label("ID Staff : ");
		namaBukuLbl = new Label("Nama Buku : ");
		passLbl = new Label("Password : ");
		tglLbl = new Label("Tanggal : ");
		
		tglField =  new TextField();
		
		idMember = new TextField();
		idBuku = new TextField();
		idStaff = new TextField();
		namaBuku = new TextField();
		
		pass = new PasswordField();
		
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
		
		scene = new Scene(borderpane, 500, 600);
		
		borderpane.setTop(menuBar);
		borderpane.setCenter(gridpane);
		borderpane.setBottom(tombol);
		
		gridpane.setPadding(new Insets(40,0,0,90));
		gridpane.setHgap(30);
		gridpane.setVgap(30);
		borderpane.setMargin(tombol, new Insets(50,100,50,200));
		
		tombol.setTextFill(Color.WHITE);
		tombol.setStyle("-fx-background-color: #ADB0AD;");
		borderpane.setStyle("-fx-background-color: #50C14C;");
	}
	
	public void layout() {
		gridpane.add(pinjamTitle, 0, 0);
		
		gridpane.add(idMemberLbl, 0, 1);
		gridpane.add(idMember, 1, 1);
		
		gridpane.add(idBukuLbl, 0, 2);
		gridpane.add(idBuku, 1, 2);
		
		gridpane.add(idStaffLbl, 0, 3);
		gridpane.add(idStaff, 1, 3);
		
		gridpane.add(namaBukuLbl, 0, 4);
		gridpane.add(namaBuku, 1, 4);
		
		gridpane.add(passLbl, 0, 5);
		gridpane.add(pass, 1, 5);
		
		gridpane.add(tglLbl, 0, 6);
		gridpane.add(tglField, 1, 6);
	}
	
	public void move(){
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
	
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage arg0) throws Exception {
		init();
		layout();
		move();
		
		arg0.setTitle("Peminjaman");
		arg0.setScene(scene);
		arg0.show();
		
	}

	@Override
	public void handle(ActionEvent arg0) {
		String inputMember = idMember.getText();
		String inputBuku = idBuku.getText();
		String inputPinjam = tglField.getText();
		String inputKembali = tglField.getText();
		
		if(arg0.getSource() == tombol) {
			if(idMember.getText().length() == 0 || tglField.getText().length() == 0 ) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Harap mengisi informasi pengembalian dengan lengkap!");
				alert.showAndWait();
			}
			else {
				// masukkin data pinjam buku
		
			addData(inputMember, inputBuku, inputPinjam, inputKembali);
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Informasi Peminjaman berhasil disimpan");
			alert.showAndWait();
		}			
		}
	}
	
	// add data
		private void addData(String inputMember, String inputBuku, String inputPinjam, String inputKembali) {
			String query = String.format("INSERT INTO isi VALUES ('%s', '%s', '%s', 'belum kembali');", 
													inputMember, inputBuku, inputPinjam, inputKembali);
			con.execUpdate(query);
			
		}

}
