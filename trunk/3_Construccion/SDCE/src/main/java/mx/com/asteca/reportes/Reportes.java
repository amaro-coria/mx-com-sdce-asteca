package mx.com.asteca.reportes;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Servlet implementation class Reportes
 */
public class Reportes extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = null;
		name = request.getParameter("name");
		viewReportPDF(name, response);
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	public void viewReportPDF(String name, HttpServletResponse response) {
		InputStream ins = UtilReporte.class.getResourceAsStream(name + ".jrxml");
		try {
			JasperReport report = JasperCompileManager.compileReport(ins);
			HashMap<String, InputStream> param = new HashMap<String, InputStream>();
			param.put("Sct",
					UtilReporte.class.getResourceAsStream("img_sct.jpg"));
			param.put("Aero",
					UtilReporte.class.getResourceAsStream("img_aero.jpg"));
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(null);
			JasperPrint print = JasperFillManager.fillReport(report, param, ds);
			byte[] file = JasperExportManager.exportReportToPdf(print);
			downloadFile("pdf", file,response);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	public void downloadFile(String ext, byte[] file, HttpServletResponse response) {

		String physicallName = "Cedula 5." + ext;
		String mimeType = "application/vnd.ms-excel";
		if (mimeType != null) {
			response.setContentLength(file.length);
			response.setHeader("Cache-Control",
					"must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Pragma", "public");
			response.setHeader("Content-disposition", "attachment; filename=\""
					+ physicallName + "\"");
			try {
				ServletOutputStream out;
				out = response.getOutputStream();
				out.write(file);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
