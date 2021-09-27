package Engine;

import Engine.Entities.Entity;
import Engine.Math.Math;
import Engine.Utils.OpenGlUtils;
import Models.RawModel;
import Models.TexturedModel;
import Shaders.GrassShader;
import Shaders.ShaderProgram;
import Shaders.StaticShader;
import Textures.ModelTexture;
import minecraft.BlockStates.GrassBlock;
import org.lwjgl.opengl.*;
import org.lwjgl.util.vector.Matrix4f;

import java.util.List;
import java.util.Map;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_NEAREST;

public class Render {

	private Matrix4f projectionMatrix;
	private static final float FOV = 70;
	private static final float NEAR_PLANE = 1f;
	private static final float FAR_PLANE = 200;
	private StaticShader shader;

	public Render(StaticShader shader){
		createProjectionMatrix();
		shader.start();
		shader.loadProjectionMatrix(projectionMatrix);
		shader.stop();
		this.shader = shader;
	}

	public void prepare() {
		OpenGlUtils.enableDepthTesting(true);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(0, 0, 0, 1);
	}

	public void render(Map<List<TexturedModel>, List<Entity>> entities){
		for(List<TexturedModel> model : entities.keySet()){
			for(TexturedModel model1 : model) {
				prepareTexturedModel(model1);

				List<Entity> batch = entities.get(model);

				for (Entity entity : batch) {
					preparedInstance(entity);
					GL11.glDrawElements(GL_TRIANGLES, model1.getRawModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);

				}
				unBindTexturedModel();
			}
		}
	}

	private void prepareTexturedModel(TexturedModel model){
		RawModel rawModel = model.getRawModel();
		GL30.glBindVertexArray(rawModel.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		//ModelTexture texture = model.getTexture();
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL_TEXTURE_2D, model.getTexture().getID());
	}

	private void unBindTexturedModel(){
		GL11.glTexParameteri(GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		GL11.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL30.glBindVertexArray(0);
	}

	private void preparedInstance(Entity entity){
		Matrix4f matrix = Math.createTransformationMatrix(entity.getPosition(), entity.getRotX(), entity.getRotY(), entity.getRotZ(), entity.getScale());
		shader.loadTransformationMatrix(matrix);
	}

	private void createProjectionMatrix(){
		float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
		float y_scale = (float) ((1f / java.lang.Math.tan(java.lang.Math.toRadians(FOV / 2f))) * aspectRatio);
		float x_scale = y_scale / aspectRatio;
		float frustum_length = FAR_PLANE - NEAR_PLANE;

		projectionMatrix = new Matrix4f();
		projectionMatrix.m00 = x_scale;
		projectionMatrix.m11 = y_scale;
		projectionMatrix.m22 = -((FAR_PLANE + NEAR_PLANE) / frustum_length);
		projectionMatrix.m23 = -1;
		projectionMatrix.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustum_length);
		projectionMatrix.m33 = 0;
	}
}
