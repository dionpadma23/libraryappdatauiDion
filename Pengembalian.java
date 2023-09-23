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
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Pengembalian extends BorderPane{
	
	Scene scene;
	Stage stage = new Stage();
	MenuBar menuBar = new MenuBar();
	Menu menu1 = new Menu("Menu"),  menu2 = new Menu("Logout");
	MenuItem menuItem1, menuItem2, menuItem3, menuItem4;
	
	BorderPane borderpane = new BorderPane();
	GridPane gridpane = new GridPane();
	Label idMemberLbl, idBukuLbl, idStaffLbl, namaBukuLbl, passLbl, tglLbl, kembaliTitle;
	DatePicker tgl;
	TextField idMember, idBuku, idStaff, namaBuku;
	PasswordField pass;
	Button tombol = new Button("Kembalikan");
	
	
	
	public Pengembalian() {
		kembaliTitle = new Label("Pengembalian");
		kembaliTitle.setFont(Font.font ("Cambria", 20));
		
		idMemberLbl = new Label("ID Member : ");
		idBukuLbl = new Label("ID Buku : ");
		idStaffLbl = new Label("ID Staff : ");
		namaBukuLbl = new Label("Nama Buku : ");
		passLbl = new Label("Password : ");
		tglLbl = new Label("Tanggal : ");
		
		tgl =  new DatePicker();
		
		idMember = new TextField();
		idBuku = new TextField();
		idStaff = new TextField();
		namaBuku = new TextField();
		
		pass = new PasswordField();
		
		scene = new Scene(borderpane, 500, 600);
		menuItem1 = new MenuItem("Peminjaman");
		menuItem2 = new MenuItem("Pengembalian");
		menuItem3 = new MenuItem("List Peminjaman");
		menuItem4 = new MenuItem("Logout");
		
		gridpane.add(kembaliTitle, 0, 0);
		
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
		gridpane.add(tgl, 1, 6);
		
		menu1.getItems().add(menuItem1);
		menu1.getItems().add(menuItem2);
		menu1.getItems().add(menuItem3);
		menuBar.getMenus().add(menu1);
		
		menu2.getItems().add(menuItem4);
		menuBar.getMenus().add(menu2);
		
		// validasi penyimpanan
		tombol.setOnAction(e -> {
			if(idMember.getText().length() == 0 || idBuku.getText().length() == 0 || idStaff.getText().length() == 0 || namaBuku.getText().length() == 0 || pass.getText().length() == 0) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Harap mengisi informasi pengembalian dengan lengkap!");
				alert.showAndWait();
			}
			else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText("Informasi Pengembalian berhasil disimpan");
				alert.showAndWait();
			}
		});
		
		
		// pindah ke halaman pengembalian
		menuItem1.setOnAction(e -> {
			new Peminjaman();
			stage.close();
			
		});
		
		
		// pindah ke halaman list
		menuItem3.setOnAction(e -> {
			new List();
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
		borderpane.setCenter(gridpane);
		borderpane.setBottom(tombol);
		
		gridpane.setPadding(new Insets(40,0,0,90));
		gridpane.setHgap(30);
		gridpane.setVgap(30);
		borderpane.setMargin(tombol, new Insets(50,100,50,200));
		
		stage.setTitle("Pengembalian");
		stage.setScene(scene);
		stage.show();
		
		
	}
	
	
}
