package minecraft.BlockStates;

import Engine.Camera;
import Engine.Entities.Entity;
import Engine.Entities.EntityModels;
import Engine.Loader;
import Engine.Math.Math;
import Engine.Query.Query;
import Engine.Utils.OpenGlUtils;
import Models.RawModel;
import Models.TexturedModel;
import Shaders.StaticShader;
import minecraft.Main;
import org.lwjgl.Sys;
import org.lwjgl.opengl.*;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_NEAREST;

public class GrassBlock extends Entity {
    private Query queryTop;
    private Query queryBottom;
    private Query querySides;
    private int displayTop = 1;
    private int displaySides = 1;
    private int displayBottom = 1;
    private List<TexturedModel> model;
    private int count = 0;
    private static TexturedModel topTexture = new TexturedModel(EntityModels.topModel, EntityModels.grassTop);
    private static TexturedModel bottomTexture = new TexturedModel(EntityModels.bottomModel, EntityModels.dirt);
    private static TexturedModel sideTexture = new TexturedModel(EntityModels.sideModel, EntityModels.grassSide);

    public GrassBlock(List<TexturedModel> model, Vector3f position, float rotX, float rotY, float rotZ, float scale, boolean hasGravity, Material material) {
        super(model, position, rotX, rotY, rotZ, scale, hasGravity, material);
        this.queryTop = new Query(GL15.GL_SAMPLES_PASSED);
        this.queryBottom = new Query(GL15.GL_SAMPLES_PASSED);
        this.querySides = new Query(GL15.GL_SAMPLES_PASSED);
        this.model = model;
    }

    public void render(Entity e, StaticShader shader, Camera camera){
        occlusionTest(e, shader, camera);
        //OpenGlUtils.enableAlphaBlending();
        //OpenGlUtils.enableDepthTesting(false);
        if(displayTop == 1) {

            render_top(e, camera, shader);
            Main.counts++;

        }
        ocTestBot(e, shader, camera);

        if(displayBottom == 1){
           render_bottom(e, shader, camera);

        }

        ocTestSide(e, shader, camera);
        if(displaySides == 1){
            render_sides(e, shader, camera);
        }

    }

    private void occlusionTest(Entity entity, StaticShader shader, Camera camera){
        if(queryTop.isResultReady()){
            int samples = queryTop.getResult();
            if(samples > 0){
                displayTop = 1;
            }else{
                displayTop = 0;
            }
        }

        if(!queryTop.isInUse()) {

            GL11.glColorMask(false,false,false,false);
            GL11.glDepthMask(false);
            queryTop.start();
            OpenGlUtils.enableDepthTesting(true);

            shader.start();
            shader.loadViewMatrix(camera);
            TexturedModel texturedModel = topTexture;
            RawModel model = texturedModel.getRawModel();
            GL30.glBindVertexArray(model.getVaoID());
            GL20.glEnableVertexAttribArray(0);
            GL20.glEnableVertexAttribArray(1);
            Matrix4f matrix = Math.createTransformationMatrix(getPosition(), entity.getRotX(), entity.getRotY(), entity.getRotZ(), entity.getScale());
            shader.loadTransformationMatrix(matrix);

            GL11.glDrawElements(GL_TRIANGLES, model.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);;
            GL20.glDisableVertexAttribArray(0);
            GL20.glDisableVertexAttribArray(1);
            GL30.glBindVertexArray(0);

            shader.stop();

            queryTop.end();
            GL11.glColorMask(true,true,true,true);
            GL11.glDepthMask(true);
        }
    }

    private void ocTestBot(Entity entity, StaticShader shader, Camera camera){
        if(queryBottom.isResultReady()){
            int samples = queryBottom.getResult();
            if(samples > 0){
                displayBottom = 1;
            }else{
                displayBottom = 0;
            }
        }

        if(!queryBottom.isInUse()) {
            GL11.glColorMask(false,false,false,false);
            GL11.glDepthMask(false);
            queryBottom.start();
            OpenGlUtils.enableDepthTesting(true);
            shader.start();
            shader.loadViewMatrix(camera);
            TexturedModel texturedModel = bottomTexture;
            RawModel model = texturedModel.getRawModel();
            GL30.glBindVertexArray(model.getVaoID());
            GL20.glEnableVertexAttribArray(0);
            GL20.glEnableVertexAttribArray(1);
            Matrix4f matrix = Math.createTransformationMatrix(getPosition(), entity.getRotX(), entity.getRotY(), entity.getRotZ(), entity.getScale());
            shader.loadTransformationMatrix(matrix);

            GL11.glDrawElements(GL_TRIANGLES, model.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);;
            GL20.glDisableVertexAttribArray(0);
            GL20.glDisableVertexAttribArray(1);
            GL30.glBindVertexArray(0);

            shader.stop();

            queryBottom.end();
            GL11.glColorMask(true,true,true,true);
            GL11.glDepthMask(true);

        }
    }

    private void ocTestSide(Entity entity, StaticShader shader, Camera camera){
        if(querySides.isResultReady()){
            int samples = querySides.getResult();
            if(samples > 0){
                displaySides = 1;
            }else{
                displaySides = 0;
            }
            System.out.println(samples);
        }

        if(!querySides.isInUse()) {
            GL11.glColorMask(false,false,false,false);
            GL11.glDepthMask(false);
            querySides.start();
            OpenGlUtils.enableDepthTesting(true);
            shader.start();
            shader.loadViewMatrix(camera);
            TexturedModel texturedModel = sideTexture;
            RawModel model = texturedModel.getRawModel();
            GL30.glBindVertexArray(model.getVaoID());
            GL20.glEnableVertexAttribArray(0);
            GL20.glEnableVertexAttribArray(1);
            Matrix4f matrix = Math.createTransformationMatrix(getPosition(), entity.getRotX(), entity.getRotY(), entity.getRotZ(), entity.getScale());
            shader.loadTransformationMatrix(matrix);

            GL11.glDrawElements(GL_TRIANGLES, model.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);;
            GL20.glDisableVertexAttribArray(0);
            GL20.glDisableVertexAttribArray(1);
            GL30.glBindVertexArray(0);

            shader.stop();

            querySides.end();
            GL11.glColorMask(true,true,true,true);
            GL11.glDepthMask(true);
        }
    }

    public void render_top(Entity entity, Camera camera, StaticShader shader){

        shader.start();
        shader.loadViewMatrix(camera);
        TexturedModel texturedModel = topTexture;
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
        TexturedModel texturedModel = bottomTexture;
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
