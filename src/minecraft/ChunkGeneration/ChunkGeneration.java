package minecraft.ChunkGeneration;

import java.util.ArrayList;

public class ChunkGeneration {

    private ArrayList<Chunk> loadedChunks = new ArrayList<>();

    public void generateAroundPlayer(int x, int y, int z){

        loadedChunks.add(new Chunk().loadChunk(0,80,0));
        loadedChunks.add(new Chunk().loadChunk(x+17,80, z+16));
        loadedChunks.add(new Chunk().loadChunk(x,80, z+16));
        loadedChunks.add(new Chunk().loadChunk(x+17,80, z));

    }

    public void unloadChunk(Chunk chunk){

    }

    public ArrayList<Chunk> getLoadedChunks() {
        return loadedChunks;
    }
}
