package final_isc517_20100553_20090885

import edu.pucmm.pwa.proyectofinal.domains.CarroCompra
import edu.pucmm.pwa.proyectofinal.domains.CompraJRDataSource
import grails.transaction.Transactional
import net.sf.jasperreports.engine.JasperCompileManager
import net.sf.jasperreports.engine.JasperExportManager
import net.sf.jasperreports.engine.JasperFillManager
import net.sf.jasperreports.engine.JasperPrint
import net.sf.jasperreports.engine.JasperReport
import net.sf.jasperreports.engine.JRDataSource
import javax.servlet.http.HttpServletRequest

@Transactional
class ReporteService {
    final String valorFiscal_ = "A0411122201"
    final String consumidorFinal_ = "A0411122202"

    public JasperPrint getReporteCompra(long compraId, boolean valorFiscal, boolean consumidorFinal, HttpServletRequest request){
        //Obteniendo le objeto que implementa el JRDataSource.
        CarroCompra compra = CarroCompra.lock(compraId)
        CompraJRDataSource datos = new CompraJRDataSource(compra)

        //Parametros.
        HashMap<String, Object> parametros=new HashMap<>();
        def numero = ''
        if(valorFiscal){
            numero = valorFiscal_ + String.format("%08d", compra.id).toString()
        }
        else if(consumidorFinal){
            numero = consumidorFinal_ + String.format("%08d", compra.id).toString()
        }
        else{
            numero = String.format("%08d", compra.id).toString()
        }
        parametros.put("ncf", numero) //Nombre del parametro del reporte para el key y el value el tipo que corresponde.
        parametros.put("rnc", compra.buyer.rnc?:"no tiene")
        parametros.put("nombre_completo", compra.buyer.nombre+compra.buyer.apellido)
        parametros.put("direccion", compra.direccion?:"No Tiene "+compra.ciudad?:"No Tiene "+compra.estado?:"No Tiene "+compra.zip?:"No Tiene")
        parametros.put("fechacompra", compra.fechaCompra)
        parametros.put("total", compra.montoCompra)
        //Cargando el reporte desde el Jar del proyecto. Reporte compilado.
        String basePath = request.getSession().getServletContext().getRealPath("/")
        JasperReport reporte = JasperCompileManager.compileReport(basePath + "/reports/reporteCompra.jrxml");

        //Mandando a ejecutar el proyecto.
        JasperPrint print = JasperFillManager.fillReport(reporte, parametros , datos);
        //JasperExportManager.exportReportToPdfFile(print, File.createTempFile("Reporte Exportado Compra:${compra.transaccion}", ".pdf").getPath());
        return print;
    }

    public JasperPrint getReporteDespacho(long compraId, boolean valorFiscal, boolean consumidorFinal, HttpServletRequest request){

        //Obteniendo le objeto que implementa el JRDataSource.
        CarroCompra compra = CarroCompra.lock(compraId)
        CompraJRDataSource datos = new CompraJRDataSource(compra)

        //Parametros.
        HashMap<String, Object> parametros=new HashMap<>();
        def numero
        if(valorFiscal){
            numero = valorFiscal + System.out.format("%08d", compra.id).toString()
        }
        else if(consumidorFinal){
            numero = consumidorFinal + System.out.format("%08d", compra.id).toString()
        }
        else{
            numero = System.out.format("%08d", compra.id).toString()
        }
        parametros.put("ncf", numero) //Nombre del parametro del reporte para el key y el value el tipo que corresponde.
        parametros.put("rnc", compra.buyer.rnc?:"no tiene")
        parametros.put("nombre_completo", compra.buyer.nombre+compra.buyer.apellido)
        parametros.put("direccion", compra.direccion?:"No Tiene "+compra.ciudad?:"No Tiene "+compra.estado?:"No Tiene "+compra.zip?:"No Tiene")
        parametros.put("fechacompra", compra.fechaCompra)
        parametros.put("total", compra.montoCompra)

        //Cargando el reporte desde el Jar del proyecto. Reporte compilado.
        String basePath = request.getSession().getServletContext().getRealPath("/")
        JasperReport reporte = JasperCompileManager.compileReport(basePath + "/reports/reporteDespacho.jrxml");

        //Mandando a ejecutar el proyecto.
        JasperPrint print = JasperFillManager.fillReport(reporte, parametros , datos);
        //JasperExportManager.exportReportToPdfFile(print, File.createTempFile("Reporte Exportado Despacho:${compra.transaccion}", ".pdf").getPath());
        return print;
    }
}
