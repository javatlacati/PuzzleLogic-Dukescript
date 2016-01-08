package com.dukescript.games.codermasters.puzzlelogic;

import static org.testng.Assert.*;
import org.testng.annotations.Test;

public class DataModelTest {

    @Test
    public void testUIModelWithoutUI() {
        Usuario usuario = new Usuario("tontonymous", "9:99:99", "99999", "-1");
        ConfiguracionJuego model = new ConfiguracionJuego(
                0, //movimientos
                0, //segundos
                0, //minutos
                false, //corretiempo
                "home", //página actual
                4, //filas tablero
                4, //columnas tablero
                true, //tutorial
                0, //color interfaz
                usuario, //datos de usuario
                false, // silencio
                "", // audio
                "snd/strike3ausencia.mp3", //ruta del audio
                new IGU(),//interfaz gráfica
                new Tablero() //tablero
        );

        assertEquals(0, model.getContador_minutos(), "probando obtener minutos");
        assertEquals(0, model.getContador_segundos(), "probando obtener segundos");
        model.setContador_minutos(1);
        assertEquals(1, model.getContador_minutos(), "probando poner minutos");
        model.setContador_segundos(1);
        assertEquals(1, model.getContador_segundos(), "probando poner segundos");
    }
}
