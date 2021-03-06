package minecraft;

import Engine.*;
import Engine.Entities.Entity;
import Models.TexturedModel;
import Shaders.GrassShader;
import Shaders.StaticShader;
import Textures.ModelTexture;
import minecraft.BlockStates.GrassBlock;
import minecraft.BlockStates.Material;
import minecraft.ChunkGeneration.Chunk;
import minecraft.ChunkGeneration.ChunkGeneration;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.*;

import Models.RawModel;

import java.util.ArrayList;

public class Main {

	public static boolean running;
	public static int counts = 0;

	public static TexturedModel grassModel;

	public static void main(String[] args) {
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		StaticShader shader = new StaticShader();
		GrassShader grassShader = new GrassShader();

		GrassRender grassRender = new GrassRender(grassShader);

		running = true;

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
		 grassModel = new TexturedModel(model, grass);
		TexturedModel dirtModel = new TexturedModel(m, dirt);

		Camera camera = new Camera(0, 80, 0, 0, 0, 0);

		ChunkGeneration generateWorld = new ChunkGeneration();
		generateWorld.generateAroundPlayer((int)camera.getPosition().x, (int)camera.getPosition().y, (int)camera.getPosition().z);

		ArrayList<Chunk> loadedChunks = generateWorld.getLoadedChunks();
		//ArrayList<Entity> grassBlocks = generateWorld.loadedChunks.get(0).blocks;



		try {
			Mouse.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}

		Mouse.setGrabbed(true);

		float fps = 60;
		double ns = 1000000000 / fps;
		long last = System.nanoTime();
		double delta = 0;

		MasterRender render = new MasterRender();

		while(!Display.isCloseRequested()) {

			camera.move();

			for(Chunk chunk : loadedChunks) {
				for (Entity entity : chunk.getBlocks()) {
					render.processEntity(entity);
				}
			}

			render.render(camera);
			DisplayManager.updateDisplayer();

		}

		loader.cleanUp();
		DisplayManager.closeDisplay();
		Mouse.destroy();

		running = false;
	}
}
