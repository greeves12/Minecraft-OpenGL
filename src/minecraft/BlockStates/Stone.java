package minecraft.BlockStates;

import Engine.Camera;
import Engine.Entities.Entity;
import Shaders.StaticShader;
import org.lwjgl.util.vector.Vector3f;

public class Stone extends Entity {
    public Stone(Vector3f position, float rotX, float rotY, float rotZ, float scale, boolean hasGravity, Material material) {
        super(position, rotX, rotY, rotZ, scale, hasGravity, material);
    }

    public void render(Entity e, StaticShader shader, Camera camera){
        super.main_render(e, shader, camera);
    }
}
