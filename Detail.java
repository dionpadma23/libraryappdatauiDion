

public class Detail {
	
	private String idMember;
	private String idBuku;
	private String tglPinjam;
	private String tglKembali;
	
	public Detail(String idMember, String idBuku, String tglPinjam, String tglKembali) {
		
		this.idMember = idMember;
		this.idBuku = idBuku;
		this.tglPinjam = tglPinjam;
		this.tglKembali = tglKembali;
	}
	
	public String getIdMember() {
		return idMember;
	}
	public void setIdMember(String idMember) {
		this.idMember = idMember;
	}
	public String getIdBuku() {
		return idBuku;
	}
	public void setIdBuku(String idBuku) {
		this.idBuku = idBuku;
	}
	public String getTglPinjam() {
		return tglPinjam;
	}
	public void setTglPinjam(String tglPinjam) {
		this.tglPinjam = tglPinjam;
	}
	public String getTglKembali() {
		return tglKembali;
	}
	public void setTglKembali(String tglKembali) {
		this.tglKembali = tglKembali;
	}
}
