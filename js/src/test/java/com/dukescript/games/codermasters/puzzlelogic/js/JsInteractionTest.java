package com.dukescript.games.codermasters.puzzlelogic.js;

import java.io.Closeable;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.java.html.boot.script.Scripts;
import org.netbeans.html.boot.spi.Fn;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Tests for behavior of @JavaScriptBody methods. Set your JavaScript
 * environment up (for example define <code>alert</code> or use some emulation
 * library like <em>env.js</em>), register script presenter and then you can
 * call methods that deal with JavaScript in your tests.
 *
 * @author JavaTlacati, monacotoni
 */
public class JsInteractionTest {

    /**
     * Javascript engine.
     */
    transient private Closeable jsEngine;
    /**
     * Log access.
     */
    private transient final static Logger LOG = Logger.getLogger(JsInteractionTest.class.getName());

    /**
     * Tests initialization of the JS engine.
     */
    @BeforeMethod
    public void initializeJSEngine() {

        jsEngine = Fn.activate(Scripts.createPresenter());

    }

    /**
     * Tests shutdown of the JS engine.
     */
    @AfterMethod
    public void shutdownJSEngine() {
        try {
            jsEngine.close();
        } catch (IOException ex) {

            if (null != LOG) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Test
    public void testCallbackFromJavaScript() {
//        class R implements Runnable {
//            int called;
//
//            @Override
//            public void run() {
//                called++;
//            }
//        }
//       R callback = new R();        
//        Dialogs.confirmByUser("Hello", callback);        
//      assertEquals(callback.called, 1, "One immediate callback");
    }
}
