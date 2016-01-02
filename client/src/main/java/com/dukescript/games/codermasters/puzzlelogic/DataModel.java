package com.dukescript.games.codermasters.puzzlelogic;

//import net.java.html.json.ComputedProperty;
import com.dukescript.games.codermasters.puzzlelogic.js.Dialogs;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.java.html.json.Function;
import net.java.html.json.Model;
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
    @Property(name = "tablero", type = Tablero.class, array = true)
})

final class DataModel {

    @Model(className = "Tablero", targetId = "", properties = {
        @Property(name = "fichas", type = Ficha.class, array = true),
        @Property(name = "posiciones_tablero", type = int.class, array = true),
        @Property(name = "imagenDeFondo", type = String.class, array = true)
    })
    static class ModeloTablero {

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
        t.getFichas().clear();
        Collections.shuffle(arreglofichas);//se ordenan aleatoriamente
        t.getFichas().addAll(arreglofichas);
        
        ArrayList<Integer> posiciones_tablero = (ArrayList<Integer>) Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
        
        //for en donde colocaremos las tablas de una forma aleatoria
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
        //generar();
        //Reiniciamos los movimientos
        model.setMovimientos(0);
        //document.getElementById( "movimientos" ).innerText = movimientos;
    }

    @Function
    public static void contarTiempo(ConfiguracionJuego model) {
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

    private static ConfiguracionJuego ui;

    /**
     * Called when the page is ready.
     */
    static void onPageLoad() throws Exception {
        ui = new ConfiguracionJuego(0, 0, 0, false, "home", 4, 4, null);
        Models.toRaw(ui);
        Dialogs.registerBinding();
        ui.applyBindings();
        Dialogs.screenSize();
    }
}
