package final_isc517_20100553_20090885

import edu.pucmm.pwa.proyectofinal.domains.CarroCompra

class GraficosController {

    def index() {}

    def getCant(int mes){
        int cant = 0
        CarroCompra.findAll().each {CarroCompra compra ->
            Calendar cal = Calendar.getInstance();
            cal.setTime(compra.fechaCompra);
            int month = cal.get(Calendar.MONTH) + 1;
            if(month == mes){
                cant++
            }
        }
        println("MES INT: "+ mes + "CANT: "+cant)
        return cant
    }

    def totalCompras(){
        //total num compras por mes etc
        //Mandando los datos.
        def datos = [
                ['\"Mes\"', '\"Cantidad Compras\"'],
                ['\"Enero\"', getCant(1)],
                ['\"Febrero\"', getCant(2)],
                ['\"Marzo\"', getCant(3)],
                ['\"Abril\"', getCant(4)],
                ['\"Mayo\"', getCant(5)],
                ['\"Junio\"', getCant(6)],
                ['\"Julio\"', getCant(7)],
                ['\"Agosto\"', getCant(8)],
                ['\"Septiembre\"', getCant(9)],
                ['\"Octubre\"', getCant(10)],
                ['\"Noviembre\"', getCant(11)],
                ['\"Diciembre\"', getCant(12)],
        ]
        //retornando...
        [datos: datos];
    }

    def despachosPendientes(){
        //pendientes vs total num compras
        //Mandando los datos.
        println("PENDIENTES: " + CarroCompra.findAllWhere(delivered: false).asList().size())
        println("COMPLETADOS: "+ CarroCompra.findAllWhere(delivered: true).asList().size())
        def datos = [
                ['\"Despachos\"', '\"Cantidad\"'],
                ['\"Pendientes\"', CarroCompra.findAllWhere(delivered: false).asList().size()],
                ['\"Completados\"', CarroCompra.findAllWhere(delivered: true).asList().size()]
        ]
        //retornando...
        [datos: datos];
    }

    def despachosRealizadosHoy(){
        //realizados vs no realizados
        //Mandando los datos.
        def pendientes = CarroCompra.findAllWhere(delivered: false).findAll {(it.fechaCompra..new Date()).size()==1}.asList().size()
        def completados = CarroCompra.findAllWhere(delivered: true).findAll {(it.fechaCompra..new Date()).size()==1}.asList().size()
        println("PENDIENTES HOY: " + pendientes)
        println("COMPLETADOS HOY: "+ completados)
        def datos = [
                ['\"Despachos del Dia\"', '\"Cantidad\"'],
                ['\"Pendientes\"', pendientes],
                ['\"Completados\"', completados]
        ]
        //retornando...
        [datos: datos];
    }
}
