package minecraft;

import Engine.*;
import Engine.Entities.Entity;
import Models.TexturedModel;
import Shaders.GrassShader;
import Shaders.StaticShader;
import Textures.ModelTexture;
import minecraft.BlockStates.GrassBlock;
import minecraft.BlockStates.Material;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import Models.RawModel;
import org.lwjgl.util.vector.Vector3f;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		StaticShader shader = new StaticShader();
		GrassShader grassShader = new GrassShader();
		Render render = new Render(shader);
		GrassRender grassRender = new GrassRender(grassShader);


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
				-0.5f,0.5f,0.5f

		};

		float [] v = {
				-0.5f,0.5f,0.5f,
				-0.5f,0.5f,-0.5f,
				0.5f,0.5f,-0.5f,
				0.5f,0.5f,0.5f,

		};

		float [] v2 = {
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
		RawModel m2 = loader.loadToVAO(v2,textureCoords, indices);

		ModelTexture dirt = new ModelTexture(loader.loadTexture("dirt"));
		ModelTexture grass = new ModelTexture(loader.loadTexture("grass_block_side"));
		ModelTexture emerald = new ModelTexture(loader.loadTexture("emerald_ore"));
		TexturedModel emeraldModel = new TexturedModel(m2, emerald);
		TexturedModel grassModel = new TexturedModel(model, grass);
		TexturedModel dirtModel = new TexturedModel(m, dirt);

		ArrayList<Entity> grassBlocks = new ArrayList<>();
		GrassBlock grassBlock = new GrassBlock(grassModel, new Vector3f(0,0,0),0,0,0,1, false, Material.GRASS);
		grassBlocks.add(grassBlock);
		grassBlocks.add(new GrassBlock(grassModel, new Vector3f(0,0,-1), 0,0,0,1, false, Material.GRASS));

		Camera camera = new Camera(0, 0, 0, 0, 0, 0);

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

			for(Entity entity : grassBlocks) {
				render.render(entity, shader);
			}

			shader.stop();


			//glDisable(GL_TEXTURE_2D);

			//grassShader.start();
			//grassShader.loadViewMatrix(camera);
			//grassRender.render(f2, grassShader);

			//grassShader.stop();

			DisplayManager.updateDisplayer();
		}

		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
		Mouse.destroy();
	}
}
