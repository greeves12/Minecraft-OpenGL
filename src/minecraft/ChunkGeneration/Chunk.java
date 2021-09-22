package minecraft.ChunkGeneration;

import minecraft.BlockStates.Block;

import java.util.ArrayList;

public class Chunk {

    public ArrayList<Block> blocks = new ArrayList<>();

    public void loadChunk(int x, int y, int z){
        int newX, newY, newZ;

        if(x % 15 == 0){
                newX = x - 15;
        }else{
            newX = x;
            while((newX % 15) != 0){
                newX++;
            }
        }
    }
}
