package minecraft.ChunkGeneration;

import java.util.ArrayList;

public class ChunkGeneration {

    public ArrayList<Chunk> loadedChunks = new ArrayList<>();

    public void generateAroundPlayer(int x, int y, int z){
        loadedChunks.add(new Chunk().loadChunk(x,50,z));
    }

    public void unloadChunk(Chunk chunk){

    }
}
