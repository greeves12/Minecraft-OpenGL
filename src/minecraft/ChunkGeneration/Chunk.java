package minecraft.ChunkGeneration;

import Engine.Entities.Entity;
import Engine.Entities.EntityModels;
import Models.TexturedModel;
import minecraft.BlockStates.Block;
import minecraft.BlockStates.GrassBlock;
import minecraft.BlockStates.Material;
import minecraft.BlockStates.Stone;
import minecraft.Main;
import org.lwjgl.util.vector.Vector3f;

import java.util.ArrayList;
import java.util.Random;

public class Chunk {

    private ArrayList<Entity> blocks = new ArrayList<>();
    private int startX;
    private int startZ;
    private int endZ;
    private int endX;

    public Entity[][][] block = new Entity[16][256][16];

    public ArrayList<Entity> getBlocks() {
        return blocks;
    }

    public Chunk loadChunk(int x, int y, int z){

        int newX, newY, newZ;

        if(x % 16 == 0){
                newX = x - 16;
                startX = x;
                endX = startX + 16;
        }else{
            newX = x;
            while((newX % 16) != 0){
                newX--;
            }

            //newX = newX - 16;
            startX = newX;

            endX = newX+16;
        }

        if(z % 16 == 0){
            newZ = z - 16;
            startZ = z;
            endZ = startZ + 16;
        }else{
            newZ = z;
            while((newZ % 16) != 0){
                newZ--;
            }
            startZ = newZ;
            endZ = newZ+16;
        }

        System.out.println("Start X: " + startX + " End X: " + endX);
        System.out.println("Start Z: " + startZ + " End Z: " + endZ);

        Random random = new Random();
        for(int y1 = 0; y1 < 80; y1++) {
            for (int z1 = startZ; z1 < endZ; z1++) {
                for (int x1 = startX; x1 < endX; x1++) {
                    ArrayList<TexturedModel> models = new ArrayList<>();
                    int randomNumber;
                    if(y1 > 78) {
                        models.add(Main.grassModel);
                        models.add(EntityModels.grassBottom);
                        models.add(EntityModels.grassTopTexture);

                        blocks.add(new GrassBlock(models, new Vector3f((float) x1, (float) y1, (float) z1), 0, 0, 0, 1, false, Material.GRASS));
                    }else if( y1 >= 0 && y1 <= 75){
                        models.add(EntityModels.stoneTexture);
                        blocks.add(new Stone(models, new Vector3f((float) x1, (float) y1, (float) z1), 0, 0, 0, 1, false, Material.STONE));
                    }else if(y1 > 75 && y1 <= 78){
                        randomNumber = random.nextInt(100) + 1;
                        if(randomNumber <= 30){
                            if(y1 == 76){
                                models.add(EntityModels.stoneTexture);
                                blocks.add(new Stone(models, new Vector3f((float) x1, (float) y1, (float) z1), 0, 0, 0, 1, false, Material.STONE));
                            }else{
                                models.add(EntityModels.dirtTexture);
                                blocks.add(new Stone(models, new Vector3f((float) x1, (float) y1, (float) z1), 0, 0, 0, 1, false, Material.STONE));
                            }
                        }else {
                            models.add(EntityModels.dirtTexture);
                            blocks.add(new Stone(models, new Vector3f((float) x1, (float) y1, (float) z1), 0, 0, 0, 1, false, Material.STONE));
                        }
                    }
                }
            }
        }


        return this;
    }
}
