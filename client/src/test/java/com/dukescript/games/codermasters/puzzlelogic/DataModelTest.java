package com.dukescript.games.codermasters.puzzlelogic;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DataModelTest {

  @Test
  public void testUIModelWithoutUI() {
    Usuario usuario = new Usuario("tontonymous", "9:99:99", "99999", "-1");
    ConfiguracionJuego model =
        new ConfiguracionJuego(
            0, // movimientos
            0, // segundos
            0, // minutos
            false, // corretiempo
            "home", // página actual
            4, // filas tablero
            4, // columnas tablero
            true, // tutorial
            0, // color interfaz
            usuario, // datos de usuario
            false, // silencio
            "", // audio
            "snd/strike3ausencia.mp3", // ruta del audio
            new IGU(), // interfaz gráfica
            new Tablero() // tablero
            );

    assertEquals("probando obtener minutos", 0, model.getContador_minutos());
    assertEquals("probando obtener segundos", 0, model.getContador_segundos());
    model.setContador_minutos(1);
    assertEquals("probando poner minutos", 1, model.getContador_minutos());
    model.setContador_segundos(1);
    assertEquals("probando poner segundos", 1, model.getContador_segundos());
  }
}
