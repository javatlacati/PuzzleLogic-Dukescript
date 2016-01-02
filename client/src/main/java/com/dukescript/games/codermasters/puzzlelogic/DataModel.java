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
    static void reiniciaTiempo(CntrlDashboard model) {
        model.setContador_minutos(0);
        model.setContador_segundos(0);
    }

    private static CntrlDashboard ui;

    /**
     * Called when the page is ready.
     */
    static void onPageLoad() throws Exception {
        ui = new CntrlDashboard(0, 0, 0, false, "home", null);
        Models.toRaw(ui);
        Dialogs.registerBinding();
        ui.applyBindings();
        //Dialogs.screenSize();
    }
}
