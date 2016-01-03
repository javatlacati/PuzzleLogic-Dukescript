package com.dukescript.games.codermasters.puzzlelogic;


import com.dukescript.games.codermasters.puzzlelogic.js.Dialogs;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.java.html.json.ComputedProperty;
import net.java.html.json.Function;
import net.java.html.json.Model;
import net.java.html.json.ModelOperation;
import net.java.html.json.Models;
import net.java.html.json.Property;

/**
 * Model annotation generates class Data with one message property, boolean
 * property and read only words property
 */
@Model(className = "ConfiguracionJuego", targetId = "", properties = {
    /** Número de movimientos realizados por el jugador. */
    @Property(name = "movimientos", type = int.class),
    /***/
    @Property(name = "contador_segundos", type = int.class),
    /***/
    @Property(name = "contador_minutos", type = int.class),
    @Property(name = "esta_corriendo_el_tiempo", type = boolean.class),
    @Property(name = "page", type = String.class),
    @Property(name = "filas", type = int.class),
    @Property(name = "columnas", type = int.class),
    @Property(name = "tutorial", type = boolean.class),
    @Property(name = "colorGUI", type = int.class),
    @Property(name = "jugador", type = Usuario.class),
    @Property(name = "silencio", type = boolean.class),
    @Property(name="audio",type = String.class),
    @Property(name="rutaaudio",type = String.class),
    @Property(name = "tablero", type = Tablero.class, array = true)
})

final class DataModel {

    @Model(className = "Tablero", targetId = "", properties = {
        @Property(name = "filas", type = Fila.class, array = true),
        @Property(name = "posiciones_tablero", type = int.class, array = true),
        @Property(name = "imagenDeFondo", type = String.class, array = true)
    })
    static class ModeloTablero {

    }

 @Model(className = "Fila", targetId = "", properties = {
        @Property(name = "columnas", type = Columna.class, array = true)
    })
    static class ModeloFila {

    }
    
 @Model(className = "Columna", targetId = "", properties = {
        @Property(name = "fichas", type = Ficha.class, array = true)
    })
    static class ModeloColumna {

    }
     

    @Model(className = "Ficha", targetId = "", properties = {
        @Property(name = "NumeroLetraSimbolo", type = String.class, array = true)
    })
    static class ModeloFicha {

    }

    @Model(className = "Usuario", targetId = "", properties = {
        @Property(name = "facebookid", type = String.class),
        @Property(name = "mejorTiempo", type = String.class),
        @Property(name = "menosMovimientos", type = String.class),
        @Property(name = "mejorPuntuacion", type = String.class)
    })
    static class ModeloUsuario {

    }
    
    @Model(className = "IGU", targetId = "", properties = {
        @Property(name = "color1", type = String.class),
        @Property(name = "color2", type = String.class),
        @Property(name = "color3", type = String.class),
        @Property(name = "color4", type = String.class)
    })
    static class ModeloIGU {

    }

    @Function
    public static void generar(ConfiguracionJuego model) {
        reiniciaTiempo(model);
        
        List<Ficha> arreglofichas = new ArrayList<Ficha>();
        //agregar contenido de el tablero
      int numeroFichas=0;
        for(int i=0 ; i<model.getFilas();i++){
            for (int j = 0; j < model.getColumnas(); j++) {
                arreglofichas.add(new Ficha(++numeroFichas+""));
            }
        }
        
        Tablero t = new Tablero();
//        t.getFichas().clear();
        Collections.shuffle(arreglofichas);//se ordenan aleatoriamente
//        t.getFichas().addAll(arreglofichas);
        ArrayList<Integer> posiciones_tablero = new ArrayList<>();
        for (int idx = 0; idx < (numeroFichas-1); idx++) {
            posiciones_tablero.add(idx);
        }
        
        //for en donde colocaremos las tablas de una forma aleatoria
        t.getPosiciones_tablero().addAll(posiciones_tablero);
        //for (Integer entero : arreglofichas) {

            //if (document.getElementById("p" + j).getAttribute("class").indexOf("vacio") < 0) {
            //document.getElementById("p" + j).innerText = num;
            //}
        //}
        model.getTablero().clear();
        model.getTablero().add(t);
    }

    /*@ComputedProperty
    static boolean verificarGanarJuego() {
        return false;
    }*/
    /**
     * Devuelve los contadores a cero.
     */
    @Function
    public static void reiniciaTiempo(ConfiguracionJuego model) {
        model.setContador_minutos(0);
        model.setContador_segundos(0);
    }

    @Function
    public static void nuevo(ConfiguracionJuego model) {
        generar(model);
        model.setEsta_corriendo_el_tiempo(true);
        //Reiniciamos los movimientos
        model.setMovimientos(0);
        //document.getElementById( "movimientos" ).innerText = movimientos;
    }
    
    @Function
    public static void pausa(ConfiguracionJuego model) {
        model.setEsta_corriendo_el_tiempo(!model.isEsta_corriendo_el_tiempo());
    }

    @ModelOperation
    public static void contarTiempo(final ConfiguracionJuego model) {
        if (model.isEsta_corriendo_el_tiempo()) {
            int segundos = model.getContador_segundos();
            if (segundos == 59) {
                int minutos = model.getContador_minutos();
                model.setContador_minutos(++minutos);
                model.setContador_segundos(0);
            } else {
                model.setContador_segundos(++segundos);
            }
        }
        java.util.Timer timer = new java.util.Timer("Soy el mapa soy el mapa soy el mapa soy el mapa");
            timer.schedule(new java.util.TimerTask() {
                @Override
                public void run() {
                    contarTiempo(model);
                }
            }, 1000);
    } //TODO Ver si conviene más un runnable para no correrlo a mano

    
    
    private static ConfiguracionJuego ui;

    /**
     * Called when the page is ready.
     */
    static void onPageLoad() throws Exception {
        //TODO cuando se administre la música desde eñ backen usar nativo RemoveListener para quitar el loop.
        Usuario usuario = new Usuario("tontonymous", "9:99:99", "99999", "-1");
        ui = new ConfiguracionJuego(0, 0, 0, false, "home", 4, 4, true,0,
                usuario,false,"","snd/strike3ausencia.mp3");
        
        Models.toRaw(ui);
        Dialogs.registerBinding();
        ui.applyBindings().contarTiempo();
        //Dialogs.screenSize();
        ui.setAudio(Dialogs.configuraAudio(ui.getRutaaudio()));
    }
}
