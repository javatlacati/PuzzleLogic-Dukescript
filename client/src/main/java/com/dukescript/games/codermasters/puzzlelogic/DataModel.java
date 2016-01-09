package com.dukescript.games.codermasters.puzzlelogic;


import com.dukescript.games.codermasters.puzzlelogic.js.Dialogs;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
    /**Segundos transcurridos en la partida actual.*/
    @Property(name = "contador_segundos", type = int.class),
    /**Minutos transcurridos en la partida actual.*/
    @Property(name = "contador_minutos", type = int.class),
    /**Bandera que nos indica si el tiempo transcurre o no.*/
    @Property(name = "esta_corriendo_el_tiempo", type = boolean.class),
    /**Página actual que se está mostrando.*/
    @Property(name = "page", type = String.class),
    /**Tamaño en filas del tablero.*/
    @Property(name = "filas", type = int.class),
    /**Tamaño en columnas del tablero.*/
    @Property(name = "columnas", type = int.class),
    /**Bandera que nos indica si se debe ejecutar el tutorial*/
    @Property(name = "tutorial", type = boolean.class),
    /**Combinación de colores seleccionada para la interfaz gráfica.*/
    @Property(name = "colorGUI", type = int.class),
    /**Información del jugador.*/
    @Property(name = "jugador", type = Usuario.class),
    /**Bandera que nos indica si se debe o no reproducir la música defondo. */
    @Property(name = "silencio", type = boolean.class),
    @Property(name="audio",type = String.class),
    /**ruta donde se encuentra la música de fondo.*/
    @Property(name="rutaaudio",type = String.class),
    /**Configuración de la interfaz gráfica.*/
    @Property(name = "miGUI", type = IGU.class),
    /**Tablero de juego actual*/
    @Property(name = "tablero", type = Tablero.class)
})

final class DataModel {
    /**Tablero de juego*/
    @Model(className = "Tablero", targetId = "", properties = {
        /**Arreglo con las filas del tablero.*/
        @Property(name = "filas", type = Fila.class, array = true),
        @Property(name = "posiciones_tablero", type = int.class, array = true),
        /**Imágen de fondo que debe aparecer en el tablero.*/
        @Property(name = "imagenDeFondo", type = String.class, array = true)
    })
    static class ModeloTablero {

    }

 @Model(className = "Fila", targetId = "", properties = {
     /**Columnas que contiene.*/
        @Property(name = "columnas", type = Columna.class, array = true)
    })
    static class ModeloFila {
    }
    
 @Model(className = "Columna", targetId = "", properties = {
     /**Fichas que contiene.*/
        @Property(name = "fichas", type = Ficha.class, array = true)
    })
    static class ModeloColumna {
    }
     

    @Model(className = "Ficha", targetId = "", properties = {
        @Property(name = "clase",type = String.class),
        /**Símbolo que debe de mostrarse encima de la ficha.*/
        @Property(name = "NumeroLetraSimbolo", type = String.class, array = true)
    })
    static class ModeloFicha {

    }

    @Model(className = "Usuario", targetId = "", properties = {
        /**id de usuario equivalente al id de facebook*/
        @Property(name = "facebookid", type = String.class),
        /**Mejor tiempo del usuario registrado*/
        @Property(name = "mejorTiempo", type = String.class),
        /**Menos movimientos del usuario registrado*/
        @Property(name = "menosMovimientos", type = String.class),
        /**Mejor puntuación del usuario registrado.*/
        @Property(name = "mejorPuntuacion", type = String.class)
    })
    static class ModeloUsuario {

    }
    
    @Model(className = "IGU", targetId = "", properties = {
        @Property(name = "claseMisMovimientos", type = String.class),
        @Property(name = "rutaFondoApp", type = String.class)
    })
    static class ModeloIGU {

    }
    
    @Function
    public static void poncssMisMovimientos(ConfiguracionJuego model) {
        int esquemaDeColores=model.getColorGUI();
        String micolor;
        switch(esquemaDeColores){
        case 1: 
                micolor="mismovimientosazul";
                break;
                default:
                    micolor="mismovimientoscafe";
                    break;
        }
        
        
        model.getMiGUI().setClaseMisMovimientos(micolor);
        //model.getMiGUI().setColorFondo(obtenColorMarfil());//background-color: 
        System.out.println("model.getMiGUI():"+model.getMiGUI());
    }

    @Function
    public static void generar(ConfiguracionJuego model) {
        reiniciaTiempo(model);
        
        
        
        //agregar contenido de el tablero
      int numeroFichas=0;
      int numeroDeFilas=model.getFilas();
      int numeroDeColumnas=model.getColumnas();
      int totalfichas=numeroDeFilas*numeroDeColumnas-1;
      List<Ficha> arreglofichas = new ArrayList<>();
      for(int index=0;index<totalfichas;index++){
          arreglofichas.add(new Ficha("col ",(index+1)+""));
      }
      arreglofichas.add(new Ficha("col vacio", ""));
      Collections.shuffle(arreglofichas);//se ordenan aleatoriamente
      
      List<Fila> arreglofilas = new ArrayList<Fila>();
      //List<Columna> arreglocolumnasdelafila = new ArrayList<Columna>();
      int fichaActual=0;
      for(int i=0 ; i<numeroDeFilas;i++){
      Fila filaColumnas = new Fila();
          for (int j = 0; j < numeroDeColumnas; j++) {
              Columna columnaDeFichas = new Columna();
              //solo una ficha por columna
              Ficha miFicha = arreglofichas.get(fichaActual);
              fichaActual++;
              columnaDeFichas.getFichas().add(miFicha);
              
                  filaColumnas.getColumnas().add(columnaDeFichas);
              
          }
          
                  arreglofilas.add(filaColumnas);
              
      }
        Tablero tablero = new Tablero();
        tablero.getFilas().addAll(arreglofilas);
//        t.getFichas().clear();
        
//        t.getFichas().addAll(arreglofichas);
        ArrayList<Integer> posiciones_tablero = new ArrayList<>();
        for (int idx = 0; idx < (numeroFichas-1); idx++) {
            posiciones_tablero.add(idx);
        }
        
        //for en donde colocaremos las tablas de una forma aleatoria
        tablero.getPosiciones_tablero().addAll(posiciones_tablero);
        //for (Integer entero : arreglofichas) {

            //if (document.getElementById("p" + j).getAttribute("class").indexOf("vacio") < 0) {
            //document.getElementById("p" + j).innerText = num;
            //}
        //}
        
        model.setTablero(tablero);
        System.out.println(tablero);
       // JQuery.displayResultsAsTable("board",tablero.toString());
       System.out.println("Modelo completo:"+model);
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

    @Function
    public static void pararSonido(final ConfiguracionJuego model){
        if(model.isSilencio()){
            //si ya está en silecio
            Dialogs.configuraAudio(model.getRutaaudio());
        }else{
            //si no está en silencio
            Dialogs.pararAudio();
        }
    }
    
    
    private static ConfiguracionJuego ui;

    /**
     * Called when the page is ready.
     */
    static void onPageLoad() throws Exception {
        //TODO cuando se administre la música desde eñ backen usar nativo RemoveListener para quitar el loop.
        Usuario usuario = new Usuario("tontonymous", "9:99:99", "99999", "-1");
        IGU configuracionDeInterfazGrafica = new IGU();
        configuracionDeInterfazGrafica.setRutaFondoApp("../img/fondo.png");
        ui= new ConfiguracionJuego(
                0, //movimientos
                0, //segundos
                0, //minutos
                false, //corretiempo
                "home", //página actual
                4, //filas tablero
                4, //columnas tablero
                true, //tutorial
                1, //color interfaz
                usuario, //datos de usuario
                false, // silencio
                "", // audio
                "snd/strike3ausencia.mp3", //ruta del audio
                configuracionDeInterfazGrafica,//interfaz gráfica
                new Tablero() //tablero
        );
        poncssMisMovimientos(ui);
        Models.toRaw(ui);
        Dialogs.registerBinding();
        ui.applyBindings().contarTiempo();
        //Dialogs.screenSize();
        ui.setAudio(Dialogs.configuraAudio(ui.getRutaaudio()));
        Dialogs.reproducirAudio();
        
        //TODO agregar una bandera para saber si se reproduce o no
        //o checar desde js si el evento ya está escuchándose
    }
}
