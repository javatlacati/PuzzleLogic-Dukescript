package com.dukescript.games.codermasters.puzzlelogic;

import static org.testng.Assert.*;
import org.testng.annotations.Test;

public class DataModelTest {

    @Test
    public void testUIModelWithoutUI() {
        Usuario usuario = new Usuario("tontonymous", "9:99:99", "99999", "-1");
        ConfiguracionJuego model = new ConfiguracionJuego(0, 0, 0, false, "home", 4, 4, true, 0,
                usuario, false, "", "snd/strike3ausencia.mp3");

        assertEquals(0, model.getContador_minutos(), "probando obtener minutos");
        assertEquals(0, model.getContador_segundos(), "probando obtener segundos");
        model.setContador_minutos(1);
        assertEquals(1, model.getContador_minutos(), "probando poner minutos");
        model.setContador_segundos(1);
        assertEquals(1, model.getContador_segundos(), "probando poner segundos");
    }
}
