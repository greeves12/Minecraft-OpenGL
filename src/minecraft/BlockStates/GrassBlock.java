package minecraft.BlockStates;

import Engine.Entities.Entity;
import Models.TexturedModel;
import org.lwjgl.util.vector.Vector3f;

public class GrassBlock extends Entity {


    public GrassBlock(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale, boolean hasGravity, Material material) {
        super(model, position, rotX, rotY, rotZ, scale, hasGravity, material);
    }
}
