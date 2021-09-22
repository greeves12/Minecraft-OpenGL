package minecraft;

import Engine.Camera;
import Engine.Entities.Entity;
import Models.TexturedModel;
import Shaders.GrassShader;
import Shaders.StaticShader;
import Textures.ModelTexture;
import minecraft.BlockStates.GrassBlock;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import Engine.DisplayManager;
import Engine.Loader;
import Models.RawModel;
import Engine.Render;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Vector3f;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

public class Main {

	public static void main(String[] args) {
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		StaticShader shader = new StaticShader();
		GrassShader grassShader = new GrassShader();
		Render render = new Render(shader);


		float[] vertices = {
				-0.5f,0.5f,-0.5f,
				-0.5f,-0.5f,-0.5f,
				0.5f,-0.5f,-0.5f,
				0.5f,0.5f,-0.5f,

				-0.5f,0.5f,0.5f,
				-0.5f,-0.5f,0.5f,
				0.5f,-0.5f,0.5f,
				0.5f,0.5f,0.5f,

				0.5f,0.5f,-0.5f,
				0.5f,-0.5f,-0.5f,
				0.5f,-0.5f,0.5f,
				0.5f,0.5f,0.5f,

				-0.5f,0.5f,-0.5f,
				-0.5f,-0.5f,-0.5f,
				-0.5f,-0.5f,0.5f,
				-0.5f,0.5f,0.5f,

				/*-0.5f,0.5f,0.5f,
				-0.5f,0.5f,-0.5f,
				0.5f,0.5f,-0.5f,
				0.5f,0.5f,0.5f,

				-0.5f,-0.5f,0.5f,
				-0.5f,-0.5f,-0.5f,
				0.5f,-0.5f,-0.5f,
				0.5f,-0.5f,0.5f*/

		};

		float [] v = {
				-0.5f,0.5f,0.5f,
				-0.5f,0.5f,-0.5f,
				0.5f,0.5f,-0.5f,
				0.5f,0.5f,0.5f,

				-0.5f,-0.5f,0.5f,
				-0.5f,-0.5f,-0.5f,
				0.5f,-0.5f,-0.5f,
				0.5f,-0.5f,0.5f
		};

		float[] textureCoords = {

				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0


		};

		int[] indices = {
				0,1,3,
				3,1,2,
				4,5,7,
				7,5,6,
				8,9,11,
				11,9,10,
				12,13,15,
				15,13,14,
				16,17,19,
				19,17,18,
				20,21,23,
				23,21,22

		};

		RawModel model = loader.loadToVAO(vertices,textureCoords, indices);
		RawModel m = loader.loadToVAO(v,textureCoords, indices);
		ModelTexture emerald = new ModelTexture(loader.loadTexture("grass_block_top"));
		ModelTexture grass = new ModelTexture(loader.loadTexture("grass_block_side"));
		TexturedModel emeraldModel = new TexturedModel(m, emerald);
		TexturedModel grassModel = new TexturedModel(model, grass);

		ArrayList<GrassBlock> grassBlocks = new ArrayList<>();

		Entity f1 = new Entity(grassModel, new Vector3f(0,0,-1), 0,0,0,1);
		Entity f2 = new Entity(emeraldModel, new Vector3f(0,0,-1), 0,0,0,1);

		Camera camera = new Camera(0, 0, 0, 0, 0, 0);

		GL11.glTexParameteri(GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		GL11.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);



		try {
			Mouse.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}

		Mouse.setGrabbed(true);
		while(!Display.isCloseRequested()) {
			camera.move();
			render.prepare();
			shader.start();

			shader.loadViewMatrix(camera);
			render.render(f1, shader);
			render.render(f2, shader);
			shader.stop();
			//GL20.glDisableVertexAttribArray(GL_COLOR);
			DisplayManager.updateDisplayer();
		}

		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
		Mouse.destroy();
	}
}
