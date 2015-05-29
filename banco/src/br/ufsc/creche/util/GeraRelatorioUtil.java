package br.ufsc.creche.util;

import java.awt.Frame;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRPptxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.view.JasperViewer;

/**
 * @author Carlos Eduardo Vilarinho
 * @18/05/2015 16:34:56
 */

public class GeraRelatorioUtil {

	private String caminhoRelatorio="";
	private FacesContext context;
	private final String diretorioEstatico ="/WEB-INF/relatorios/";
	public  final int	JASPER_VIEWER					= 0;
	public  final int	RELATORIO_GERA_PDF				= 1;
	public  final int	RELATORIO_GERA_EXCEL			= 2;
	public  final int	RELATORIO_GERA_WORD				= 3;
	public  final int	RELATORIO_GERA_ODT	            = 4;
	public  final int	RELATORIO_GERA_POWER_POINT      = 5;

	/**
	 * @param lista
	 * @param template
	 * @param tipoRelatorio
	 * @param parametros
	 * @param nomeArquivo
	 * @throws JRException
	 */
	public void montaRelatorio(List<?> lista, String template, int tipoRelatorio, HashMap<String, Object> parametros, String nomeArquivo) throws JRException {
		String caminho = diretorioEstatico+template;
		context = FacesContext.getCurrentInstance();
		ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
		caminhoRelatorio = servletContext.getRealPath(caminho);

		HashMap<String, Object> parametrosCabecalho = new HashMap<String, Object>();
		parametrosCabecalho.putAll(parametros);
		parametrosCabecalho.put("empresa", "");

		gerarRelatorio(lista, nomeArquivo, tipoRelatorio, parametrosCabecalho );
	}

	private void gerarRelatorio(List<?> lista, String nomeArquivo, int tipoRelatorio, HashMap<String, Object> parametros ) {
		try {
			HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
			JasperReport jasperReport = JasperCompileManager.compileReport(caminhoRelatorio);

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, new JRBeanCollectionDataSource(lista));

			switch (tipoRelatorio) {
			case JASPER_VIEWER:
				jasperViewer(jasperPrint);
				break;
			case RELATORIO_GERA_PDF:
				geraPdf(jasperPrint, nomeArquivo, response);
				break;
			case RELATORIO_GERA_EXCEL:
				geraExcel(jasperPrint, nomeArquivo, response);
				break;
			case RELATORIO_GERA_WORD:
				geraWord(jasperPrint, nomeArquivo, response);
				break;
			case RELATORIO_GERA_ODT :
				geraOdt(jasperPrint, nomeArquivo, response);
				break;
			case  RELATORIO_GERA_POWER_POINT:
				geraPowerPoint(jasperPrint, nomeArquivo, response);
				break;
			default:
				jasperViewer(jasperPrint);
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	private void geraPowerPoint(JasperPrint print, String nomeArquivo,HttpServletResponse response) throws JRException, IOException {
		response.setContentType(nomeArquivo +"/pptx");
		response.addHeader("Content-disposition", "attachment; filename=\""+nomeArquivo+".pptx\"");
		ServletOutputStream servletOutputStream=response.getOutputStream();
		JRPptxExporter docxExporter=new JRPptxExporter();
		docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
		docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
		docxExporter.exportReport();
		context.getApplication().getStateManager().saveView(context);
		context.responseComplete();
	}

	@SuppressWarnings("deprecation")
	private void geraOdt(JasperPrint print, String nomeArquivo,HttpServletResponse response) throws JRException, IOException {
		response.setContentType(nomeArquivo +"/odt");
		response.addHeader("Content-disposition", "attachment; filename=\""+nomeArquivo+".odt\"");
		ServletOutputStream servletOutputStream=response.getOutputStream();
		JROdtExporter docxExporter=new JROdtExporter();
		docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
		docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
		docxExporter.exportReport();
		context.getApplication().getStateManager().saveView(context);
		context.responseComplete();
	}

	@SuppressWarnings("deprecation")
	private void geraWord(JasperPrint print, String nomeArquivo,HttpServletResponse response) throws JRException, IOException {
		response.setContentType(nomeArquivo +"/docx");
		response.addHeader("Content-disposition", "attachment; filename=\""+nomeArquivo+".docx\"");
		ServletOutputStream servletOutputStream=response.getOutputStream();
		JRDocxExporter docxExporter=new JRDocxExporter();
		docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
		docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
		docxExporter.setParameter(JRDocxExporterParameter.OUTPUT_STREAM, servletOutputStream);
		docxExporter.exportReport();
		context.getApplication().getStateManager().saveView(context);
		context.responseComplete();
	}

	private void jasperViewer(JasperPrint print) {
		JasperViewer view = new JasperViewer(print, false);
		view.setTitle("SmartWeb Report");
		view.setZoomRatio(1.00F);
		view.setExtendedState(Frame.MAXIMIZED_BOTH);
		view.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/br/com/smartsis/smartweb/imagem/smart32.png")));
		view.setAlwaysOnTop(true);
		view.setAutoRequestFocus(true);
		view.setVisible(true);
		view.toFront();
	}

	@SuppressWarnings("deprecation")
	private void geraPdf(JasperPrint print , String nomeArquivo, HttpServletResponse response) throws JRException, IOException{
		response.setContentType(nomeArquivo +"/pdf");
		response.addHeader("Content-disposition", "attachment; filename=\""+nomeArquivo+".pdf\"");
		ServletOutputStream servletOutputStream=response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(print, servletOutputStream);
		context.getApplication().getStateManager().saveView(context);
		context.responseComplete();
	}

	@SuppressWarnings("deprecation")
	private void geraExcel(JasperPrint print , String nomeArquivo, HttpServletResponse response) throws JRException, IOException{
		response.setContentType(nomeArquivo +"/xlsx");
		response.addHeader("Content-disposition", "attachment; filename=\""+nomeArquivo+".xlsx\"");
		ServletOutputStream servletOutputStream=response.getOutputStream();
		JRXlsxExporter docxExporter=new JRXlsxExporter();
		docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
		docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
		docxExporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.TRUE);
		docxExporter.exportReport();
		context.getApplication().getStateManager().saveView(context);
		context.responseComplete();
	}

}
