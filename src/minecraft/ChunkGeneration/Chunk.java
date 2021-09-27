package minecraft.ChunkGeneration;

import Engine.Entities.Entity;
import minecraft.BlockStates.Block;
import minecraft.BlockStates.GrassBlock;
import minecraft.BlockStates.Material;
import minecraft.Main;
import org.lwjgl.util.vector.Vector3f;

import java.util.ArrayList;

public class Chunk {

    public ArrayList<Entity> blocks = new ArrayList<>();
    private int startX;
    private int startZ;
    private int endZ;
    private int endX;

    public Entity[][][] block = new Entity[16][256][16];



    public Chunk loadChunk(int x, int y, int z){

        int newX, newY, newZ;

        if(x % 16 == 0){
                newX = x - 16;
                startX = x;
        }else{
            newX = x;
            while((newX % 16) != 0){
                newX++;
            }

            newX = newX - 16;

            endX = newX;
        }

        if(z % 16 == 0){
            newZ = z - 16;
            startZ = z;
        }else{
            newZ = z;
            while((newZ % 16) != 0){
                newZ++;
            }
            newZ = newZ - 16;
            endZ = newZ;
        }


        for(int y1 = 0; y1 < 80; y1++) {
            for (int z1 = newZ; z1 < endZ; z1++) {
                for (int x1 = newX; x1 < endX; x1++) {


                    blocks.add(new GrassBlock(Main.grassModel, new Vector3f((float) x1, (float) y1, (float) z1), 0, 0, 0, 1, false, Material.GRASS));
                    block[x1+16][y1][z1+16] = new GrassBlock(Main.grassModel,new Vector3f((float) x1, (float) y1, (float) z1), 0, 0, 0, 1, false, Material.GRASS);
                }
            }
        }


        return this;
    }
}
