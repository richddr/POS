package edu.pucmm.pwa.proyectofinal.domains
/**
 * Created by Richard on 12/5/2014.
 */

import edu.pucmm.pwa.proyectofinal.domains.CarroCompra
import edu.pucmm.pwa.proyectofinal.domains.ItemCompra
import net.sf.jasperreports.engine.JRDataSource
import net.sf.jasperreports.engine.JRException
import net.sf.jasperreports.engine.JRField

public class CompraJRDataSource implements JRDataSource{
    private List<ItemCompra> listado;
    private int indice=-1;

    private CarroCompra compra

    public CompraJRDataSource(CarroCompra c){
        compra = c
        listado = c.getItemsCompra().asList()
    }

    @Override
    boolean next() throws JRException {
        indice++;
        if(indice<listado.size()){
            return true;
        }
        return false;
    }

    @Override
    Object getFieldValue(JRField fila) throws JRException {
        Object tmp = null;
        if(fila.name=="nombre"){
            tmp = listado.get(indice).producto.nombre
        }else if(fila.name=="cantidad"){
            tmp = listado.get(indice).quantity
        }else if(fila.name=="precio_unidad"){
            tmp = listado.get(indice).producto.precioVenta
        }
        else if(fila.name=="subtotal"){
            tmp = (listado.get(indice).producto.precioVenta*listado.get(indice).quantity)
        }

        return tmp;
    }
}

