package com.dukescript.games.codermasters.puzzlelogic.js;

import net.java.html.js.JavaScriptBody;
import net.java.html.js.JavaScriptResource;

/**
 * Use {@link JavaScriptBody} annotation on methods to directly interact with
 * JavaScript. See
 * http://bits.netbeans.org/html+java/1.2/net/java/html/js/package-summary.html
 * to understand how.
 *
 * @author javatlacati, monacotoni
 */
@JavaScriptResource(value = "registerRouter.js")
public final class Dialogs {

    private Dialogs() {
    }

    /**
     * Shows confirmation dialog to the user.
     *
     * @param msg the message
     * @param callback called back when the use accepts (can be null)
     */
    @JavaScriptBody(
            args = {"msg", "callback"},
            javacall = true,
            body = "if (confirm(msg)) {\n"
            + "  callback.@java.lang.Runnable::run()();\n"
            + "}\n"
    )
    public static native void confirmByUser(String msg, Runnable callback);

    /**
     * Adjusts document to screen size.
     *
     * @return Size of the screen
     */
    @JavaScriptBody(
            args = {}, body
            = "var w = window,\n"
            + "    d = document,\n"
            + "    e = d.documentElement,\n"
            + "    g = d.getElementsByTagName('body')[0],\n"
            + "    x = w.innerWidth || e.clientWidth || g.clientWidth,\n"
            + "    y = w.innerHeight|| e.clientHeight|| g.clientHeight;\n"
            + "\n"
            + "return 'Screen size is ' + x + ' times ' + y;\n"
    )
    public static native String screenSize();

    @JavaScriptBody(
            args = {"rutaMusica"}, body
            = "var audioElement = document.getElementById('musicadefondo');" //" var audioElement = document.createElement('audio');\n"
            + "            audioElement.setAttribute('src',rutaMusica);\n"
    )
    public static native String configuraAudio(String rutaMusica);

    @JavaScriptBody(
            args = {},
            body = "var audioElement = document.getElementById('musicadefondo');"
            + "var playtime=function playTime() {"
            + "            audioElement.currentTime = 0;"
            + "            audioElement .play();\n"
            + "};"
            + "if (audioElement !== null){ " //TODO tal vez salga sobrando este if
            + "            audioElement.addEventListener('ended', playtime"
            + "            , false);\n"
            + "            audioElement.play();"
            + "}"
    )
    public static native String reproducirAudio();

    @JavaScriptBody(
            args = {},
            body = "var audioElement = document.getElementById('musicadefondo');"
            + "var playtime=function playTime() {"
            + "            audioElement.currentTime = 0;"
            + "            audioElement .play();\n"
            + "};"
            + "if (audioElement !== null){ " //TODO tal vez salga sobrando este if
            + " if (audioElement.removeEventListener) {   "
            + "            audioElement.removeEventListener('ended',playtime);"
            + " }else if(audioElement.detachEvent) {"
            + "            audioElement.detachEvent('ended',playtime);"
            + " }"
            + "            audioElement.pause();\n"
            + "            audioElement.currentTime = 0;"
            + "}"
    )
    public static native String pararAudio();

    /**
     * This method registers the bindings.
     *
     * @return ??
     */
    @JavaScriptBody(
            args = {}, body
            = ""
    )
    public static native String registerBinding();
}
