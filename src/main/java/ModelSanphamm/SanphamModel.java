package ModelSanphamm;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SanphamModel {
	private Connection con;
	public SanphamModel() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/ql_ban_smatphon", "root", "chinh2003");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insertSanpham(String tenSanPham, double giaBan, int soLuongTrongKho, String moTaSanPham,
			byte[] anhSanPham, String kichThuoc, String trongLuongSanPham, String dienTichManHinh, String ram,
			String dungLuongPin, String thoiGianBaoHanh, int maNhaSanXuat) throws SQLException {
		String jdbcURL = "jdbc:mysql://localhost:3306/ql_ban_smatphon";
		String dbUsername = "root";
		String dbPassword = "chinh2003";

		try (Connection con = DriverManager.getConnection(jdbcURL, dbUsername, dbPassword)) {
			String sql = "INSERT INTO SanPham (TenSanPham, GiaBan, SoLuongTrongKho, MoTaSanPham, AnhSanPham, Kichthuoc, Trongluongsanpham, Dientichmanhinh, Ram, Dungluongpin, Thoigianbaohang, MaNhaSanXuat) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			try (PreparedStatement pst = con.prepareStatement(sql)) {
				pst.setString(1, tenSanPham);
				pst.setDouble(2, giaBan);
				pst.setInt(3, soLuongTrongKho);
				pst.setString(4, moTaSanPham);
				pst.setBytes(5, anhSanPham); // Store the image as a BLOB (Binary Large Object) in the database
				pst.setString(6, kichThuoc);
				pst.setString(7, trongLuongSanPham);
				pst.setString(8, dienTichManHinh);
				pst.setString(9, ram);
				pst.setString(10, dungLuongPin);
				pst.setString(11, thoiGianBaoHanh);
				pst.setInt(12, maNhaSanXuat);

				pst.executeUpdate();
			}
		} catch (SQLException ex) {
// Handle any exceptions that occurred during database operations
			ex.printStackTrace();
		}

	}

	public void updateSanpham(int maSanPham, String tenSanPham, double giaBan, int soLuongTrongKho, String moTaSanPham,
			byte[] anhSanPham, String kichThuoc, String trongLuongSanPham, String dienTichManHinh, String ram,
			String dungLuongPin, String thoiGianBaoHanh, int maNhaSanXuat) throws SQLException {
		String jdbcURL = "jdbc:mysql://localhost:3306/ql_ban_smatphon";
		String dbUsername = "root";
		String dbPassword = "chinh2003";

		try (Connection con = DriverManager.getConnection(jdbcURL, dbUsername, dbPassword)) {
			String sql = "UPDATE SanPham SET TenSanPham = ?, GiaBan = ?, SoLuongTrongKho = ?, MoTaSanPham = ?, AnhSanPham = ?, "
					+ "Kichthuoc = ?, Trongluongsanpham = ?, Dientichmanhinh = ?, Ram = ?, Dungluongpin = ?, Thoigianbaohang = ?, "
					+ "MaNhaSanXuat = ? WHERE MaSanPham = ?";

			try (PreparedStatement pst = con.prepareStatement(sql)) {
				pst.setString(1, tenSanPham);
				pst.setDouble(2, giaBan);
				pst.setInt(3, soLuongTrongKho);
				pst.setString(4, moTaSanPham);
				pst.setBytes(5, anhSanPham); // Store the image as a BLOB (Binary Large Object) in the database
				pst.setString(6, kichThuoc);
				pst.setString(7, trongLuongSanPham);
				pst.setString(8, dienTichManHinh);
				pst.setString(9, ram);
				pst.setString(10, dungLuongPin);
				pst.setString(11, thoiGianBaoHanh);
				pst.setInt(12, maNhaSanXuat);
				pst.setInt(13, maSanPham); // The product ID to update

				pst.executeUpdate();
			}
		} catch (SQLException ex) {
// Handle any exceptions that occurred during database operations
			ex.printStackTrace();
		}
	}

	public void deleteSanpham(int maSanPham) throws SQLException {
		String jdbcURL = "jdbc:mysql://localhost:3306/ql_ban_smatphon";
		String dbUsername = "root";
		String dbPassword = "chinh2003";

		try (Connection con = DriverManager.getConnection(jdbcURL, dbUsername, dbPassword)) {
			String sql = "DELETE FROM SanPham WHERE MaSanPham = ?";

			try (PreparedStatement pst = con.prepareStatement(sql)) {
				pst.setInt(1, maSanPham); // The product ID to delete

				pst.executeUpdate();
			}
		} catch (SQLException ex) {
			// Handle any exceptions that occurred during database operations
			ex.printStackTrace();
		}
	}

	public List<Sanpham> getAllSanpham() throws SQLException {
		List<Sanpham> sanphamList = new ArrayList<>();
		PreparedStatement pst = con.prepareStatement("select * from SanPham");
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			Sanpham sanpham = new Sanpham();
			sanpham.setMaSanPham(rs.getInt("MaSanPham"));
			sanpham.setTenSanPham(rs.getString("TenSanPham"));
			sanpham.setGiaBan(rs.getDouble("GiaBan"));
			sanpham.setSoLuongTrongKho(rs.getInt("SoLuongTrongKho"));
			sanpham.setMoTaSanPham(rs.getString("MoTaSanPham"));
			sanpham.setAnhSanPham(rs.getBytes("AnhSanPham"));
			sanpham.setKichThuoc(rs.getString("Kichthuoc"));
			sanpham.setTrongLuongSanPham(rs.getString("Trongluongsanpham"));
			sanpham.setDienTichManHinh(rs.getString("Dientichmanhinh"));
			sanpham.setRam(rs.getString("Ram"));
			sanpham.setDungLuongPin(rs.getString("Dungluongpin"));
			sanpham.setThoiGianBaoHanh(rs.getString("Thoigianbaohang"));
			sanpham.setMaNhaSanXuat(rs.getInt("MaNhaSanXuat"));
			sanphamList.add(sanpham);
		}
		rs.close();
		pst.close();
		return sanphamList;
	}
}
