package Engine;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;


public class DisplayManager {
	
	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;
	private static long frameTime;
	private static float delta;
	
	public static void createDisplay() {
		
		ContextAttribs attribs = new ContextAttribs(3, 2);
		attribs.withForwardCompatible(true);
		attribs.withProfileCore(true);
		
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.create(new PixelFormat(), attribs);
			Display.setTitle("Minecraft");
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frameTime = currentTime();
		GL11.glViewport(0, 0, WIDTH, HEIGHT);
	}
	
	public static void updateDisplayer() {
		
		Display.sync(60);
		Display.update();

		long current = currentTime();
		delta = (current - frameTime) / 1000f;
		frameTime = current;
	}

	private static long currentTime(){
		return Sys.getTime() * 1000 / Sys.getTimerResolution();
	}

	public static float getFrameTime(){
		return delta;
	}

	public static void closeDisplay() {
		Display.destroy();
	}
}
