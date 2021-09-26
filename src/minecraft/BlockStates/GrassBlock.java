package minecraft.BlockStates;

import Engine.Camera;
import Engine.Entities.Entity;
import Engine.Entities.EntityModels;
import Engine.Loader;
import Engine.Math.Math;
import Models.RawModel;
import Models.TexturedModel;
import Shaders.StaticShader;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_NEAREST;

public class GrassBlock extends Entity {

    public GrassBlock( Vector3f position, float rotX, float rotY, float rotZ, float scale, boolean hasGravity, Material material) {
        super( position, rotX, rotY, rotZ, scale, hasGravity, material);

    }

    public void render(Entity e, StaticShader shader, Camera camera){
        render_sides(e, shader, camera);
        render_top(e, camera, shader);
        render_bottom(e, shader, camera);
    }

    public void render_top(Entity entity, Camera camera, StaticShader shader){

        shader.start();
        shader.loadViewMatrix(camera);
        TexturedModel texturedModel = new TexturedModel(EntityModels.topModel, EntityModels.grassTop);
        RawModel model = texturedModel.getRawModel();
        GL30.glBindVertexArray(model.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        Matrix4f matrix = Math.createTransformationMatrix(getPosition(), entity.getRotX(), entity.getRotY(), entity.getRotZ(), entity.getScale());
        shader.loadTransformationMatrix(matrix);

        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texturedModel.getTexture().getID());
        GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);;
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL30.glBindVertexArray(0);
        GL11.glTexParameteri(GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        GL11.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        shader.stop();
    }

    private void render_bottom(Entity entity, StaticShader shader, Camera camera){
        shader.start();
        shader.loadViewMatrix(camera);
        TexturedModel texturedModel = new TexturedModel(EntityModels.bottomModel, EntityModels.dirt);
        RawModel model = texturedModel.getRawModel();
        GL30.glBindVertexArray(model.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        Matrix4f matrix = Math.createTransformationMatrix(getPosition(), entity.getRotX(), entity.getRotY(), entity.getRotZ(), entity.getScale());
        shader.loadTransformationMatrix(matrix);

        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texturedModel.getTexture().getID());
        GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);;
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL30.glBindVertexArray(0);
        GL11.glTexParameteri(GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        GL11.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        shader.stop();
    }

    public void render_sides(Entity entity, StaticShader shader, Camera camera) {

        shader.start();
        shader.loadViewMatrix(camera);
        TexturedModel texturedModel = new TexturedModel(EntityModels.sideModel, EntityModels.grassSide);
        RawModel model = texturedModel.getRawModel();
        GL30.glBindVertexArray(model.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        Matrix4f matrix = Math.createTransformationMatrix(entity.getPosition(), entity.getRotX(), entity.getRotY(), entity.getRotZ(), entity.getScale());
        shader.loadTransformationMatrix(matrix);

        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texturedModel.getTexture().getID());
        GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);;
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL30.glBindVertexArray(0);
        GL11.glTexParameteri(GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        GL11.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        shader.stop();
    }
}
