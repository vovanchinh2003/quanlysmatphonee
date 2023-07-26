package ControllerSanpham;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import ModelNhasanxuat.NhaSanXuatModel;
import ModelSanphamm.SanphamModel;

/**
 * Servlet implementation class AddsanPhamController
 */
@WebServlet("/addsanPhamController")
public class AddsanPhamController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddsanPhamController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		// Other variables initialization

		String tenSanPham = request.getParameter("ten_sanpham");
		String giaParameter = request.getParameter("gia");
		double giaBan = 0.0; // Default value in case the parameter is missing or invalid
		if (giaParameter != null && !giaParameter.isEmpty()) {
			try {
				giaBan = Double.parseDouble(giaParameter);
			} catch (NumberFormatException e) {
				// Handle the case where the parameter is present but not a valid double
				e.printStackTrace(); // You can log the exception for debugging purposes
			}
		}
		String soLuongParameter = request.getParameter("so_luong");
		int soLuongTrongKho = 0; // Default value in case the parameter is missing or invalid
		if (soLuongParameter != null && !soLuongParameter.isEmpty()) {
			try {
				soLuongTrongKho = Integer.parseInt(soLuongParameter);
			} catch (NumberFormatException e) {
				// Handle the case where the parameter is present but not a valid integer
				e.printStackTrace(); // You can log the exception for debugging purposes
			}
		}
		String moTaSanPham = request.getParameter("mo_ta");
		byte[] anhSanPham = null; // Retrieve the image data and convert it to byte array
		if (ServletFileUpload.isMultipartContent(request)) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);

			try {
				// Parse the request to get all FileItems
				for (FileItem item : upload.parseRequest(request)) {
					if (!item.isFormField() && "anh_sanpham".equals(item.getFieldName())) {
						// Handle file upload and convert the image to a byte array
						try (InputStream inputStream = item.getInputStream()) {
							anhSanPham = inputStream.readAllBytes();
						}
					}
				}

			} catch (FileUploadException e) {
				e.printStackTrace();
				response.sendRedirect(request.getContextPath() + "/error.jsp"); // Redirect to error page
				return;
			}
		}
		String kichThuoc = request.getParameter("kich_thuoc");
		String trongLuongSanPham = request.getParameter("trong_luong_san_pham");
		String dienTichManHinh = request.getParameter("dien_tich_man_hinh");
		String ram = request.getParameter("ram");
		String dungLuongPin = request.getParameter("dung_luong_pin");
		String thoiGianBaoHanh = request.getParameter("thoi_gian_bao_hanh");
		int maNhaSanXuat = Integer.parseInt(request.getParameter("ma_nha_sx_"));

		// Rest of the code remains the same

		try {
			SanphamModel model = new SanphamModel();
			model.insertSanpham(tenSanPham, giaBan, soLuongTrongKho, moTaSanPham, anhSanPham, kichThuoc,
					trongLuongSanPham, dienTichManHinh, ram, dungLuongPin, thoiGianBaoHanh, maNhaSanXuat);

			response.sendRedirect(request.getContextPath() + "/sanPhamController");
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp"); // Redirect to error page
		}
	}

}
