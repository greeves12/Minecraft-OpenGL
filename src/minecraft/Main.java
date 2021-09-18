package minecraft;

import Engine.Entities.Entity;
import Models.TexturedModel;
import Shaders.StaticShader;
import Textures.ModelTexture;
import org.lwjgl.opengl.Display;

import Engine.DisplayManager;
import Engine.Loader;
import Models.RawModel;
import Engine.Render;
import org.lwjgl.util.vector.Vector3f;

public class Main {

	public static void main(String[] args) {
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		StaticShader shader = new StaticShader();
		Render render = new Render(shader);

		
		float[] vertices = {
				-0.5f, 0.5f, 0,
				  -0.5f, -0.5f, 0,
				  0.5f, -0.5f, 0,
				  0.5f, 0.5f, 0f
		};
		
		int[] indices = {
			0,1,3,
			3,1,2
		};

		float[] textureCoords = {
				0,0,
				0,1,
				1,1,
				1,0
		};

		RawModel model = loader.loadToVAO(vertices,textureCoords, indices);
		ModelTexture texture = new ModelTexture(loader.loadTexture("emerald_ore"));
		TexturedModel texturedModel = new TexturedModel(model, texture);

		Entity entity = new Entity(texturedModel, new Vector3f(0,0,-1), 0,0,0,1);

		while(!Display.isCloseRequested()) {
			entity.increasePosition(0, 0, -0.1f);
			render.prepare();
			shader.start();
			render.render(entity, shader);
			shader.stop();
			DisplayManager.updateDisplayer();
		}

		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}
}
