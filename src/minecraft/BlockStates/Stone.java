package minecraft.BlockStates;

import Engine.Camera;
import Engine.Entities.Entity;
import Models.TexturedModel;
import Shaders.StaticShader;
import org.lwjgl.util.vector.Vector3f;

import java.util.List;

public class Stone extends Entity {
    public Stone(List<TexturedModel> model, Vector3f position, float rotX, float rotY, float rotZ, float scale, boolean hasGravity, Material material) {
        super(model,position, rotX, rotY, rotZ, scale, hasGravity, material);
    }

    public void render(Entity e, StaticShader shader, Camera camera){
        super.main_render(e, shader, camera);
    }
}
