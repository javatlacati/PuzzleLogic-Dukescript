package com.dukescript.games.codermasters.puzzlelogic;

//import net.java.html.json.ComputedProperty;
import com.dukescript.games.codermasters.puzzlelogic.js.Dialogs;
import net.java.html.json.Function;
import net.java.html.json.Model;
import net.java.html.json.Models;
import net.java.html.json.Property;
//import com.dukescript.games.codermasters.puzzlelogic.js.Dialogs;

/**
 * Model annotation generates class Data with one message property, boolean
 * property and read only words property
 */
@Model(className = "CntrlDashboard", targetId = "", properties = {
    @Property(name = "movimientos", type = int.class),
    @Property(name = "contador_segundos", type = int.class),
    @Property(name = "contador_minutos", type = int.class),
    @Property(name = "esta_corriendo_el_tiempo", type = boolean.class),
    @Property(name = "page", type = String.class),
    @Property(name = "arreglo_numeros", type = int.class, array = true)
})
final class DataModel {

    /*@ComputedProperty
    static boolean verificarGanarJuego() {
        return false;
    }*/
    /**
     * Devuelve los contadores a cero.
     */
    @Function
    public static void reiniciaTiempo(CntrlDashboard model) {
        model.setContador_minutos(0);
        model.setContador_segundos(0);
    }
    
    @Function
    public static void nuevo(CntrlDashboard model) {
        //generar();

        //Reiniciamos los movimientos         
        model.setMovimientos(0);
        //document.getElementById( "movimientos" ).innerText = movimientos;
    }

    @Function
    public static void contarTiempo(CntrlDashboard model) {
        if (model.isEsta_corriendo_el_tiempo()) {
            int segundos = model.getContador_segundos();
            if (segundos == 59) {
                int minutos = model.getContador_minutos();
                model.setContador_minutos(++minutos);
                model.setContador_segundos(0);
            } else {
                model.setContador_segundos(++segundos);
            }
        } else {

        }
    } //TODO Ver si conviene más un runnable para no correrlo a mano

    private static CntrlDashboard ui;

    /**
     * Called when the page is ready.
     */
    static void onPageLoad() throws Exception {
        ui = new CntrlDashboard(0, 0, 0, false, "home", null);
        Models.toRaw(ui);
        Dialogs.registerBinding();
        ui.applyBindings();
        Dialogs.screenSize();
    }
}
