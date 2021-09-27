package Engine;

import Engine.Entities.Entity;
import Models.TexturedModel;
import Shaders.StaticShader;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_NEAREST;

public class MasterRender {

    private StaticShader shader = new StaticShader();
    private Render render = new Render(shader);

    private Map<List<TexturedModel>, List<Entity>>  entities = new HashMap<>();

    public void render(Camera camera){
        render.prepare();
        shader.start();
        shader.loadViewMatrix(camera);
        render.render(entities);
        shader.stop();
        entities.clear();
    }

    public void processEntity(Entity entity){
        List<TexturedModel> model = entity.getModel();

        List<Entity> batch = entities.get(model);

        if(batch != null){
            batch.add(entity);
        }else{
            List<Entity> newBatch = new ArrayList<>();
            newBatch.add(entity);
            entities.put(model, newBatch);
        }
    }

    public void cleanUp(){
        shader.cleanUp();
    }
}
