package ControllerSanpham;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import ModelSanphamm.Sanpham;
import ModelSanphamm.SanphamModel;

/**
 * Servlet implementation class SanPhamController
 */
@MultipartConfig
@WebServlet("/hOME")
public class SanPhamController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SanphamModel mSanphamModel;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SanPhamController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() throws ServletException {
		mSanphamModel = new SanphamModel();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public class ImageUtils {
		public static String byteArrayToBase64(byte[] byteArray) {
			return Base64.getEncoder().encodeToString(byteArray);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			List<Sanpham> sanphamlList = mSanphamModel.getAllSanpham();
			for (Sanpham sanpham : sanphamlList) {
				byte[] imageBytes = sanpham.getAnhSanPham();
				if (imageBytes != null) {
					String base64Image = Base64.getEncoder().encodeToString(imageBytes);
					sanpham.setAnhSanPhamBase64(base64Image);
				}
			}
			request.setAttribute("sanphamlList", sanphamlList);
			request.getRequestDispatcher("view/header.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
