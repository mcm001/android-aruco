package aruco.min3d;

import android.content.Context;
import min3d.core.MyRenderer;
import min3d.core.TextureManager;

/**
 * Holds static references to TextureManager, Renderer, and the application Context.
 */
public class Shared {
    private static Context _context;
    private static MyRenderer _renderer;
    private static TextureManager _textureManager;


    public static Context context() {
        return _context;
    }

    public static void context(Context $c) {
        _context = $c;
    }

    public static MyRenderer renderer() {
        return _renderer;
    }

    public static void renderer(MyRenderer $r) {
        _renderer = $r;
    }

    /**
     * You must access the TextureManager instance through this accessor
     */
    public static TextureManager textureManager() {
        return _textureManager;
    }

    public static void textureManager(TextureManager $bm) {
        _textureManager = $bm;
    }
}
