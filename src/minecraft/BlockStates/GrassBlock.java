package minecraft.BlockStates;

public class GrassBlock extends Block {

    public GrassBlock (int x, int y, int z, boolean hasGravity){
        this.setX(x);
        this.setY(y);
        this.setZ(z);
        this.setHasGravity(false);
        this.setMaterial(Material.GRASS);
    }



}
