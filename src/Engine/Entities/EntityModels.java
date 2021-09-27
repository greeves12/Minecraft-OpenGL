package Engine.Entities;

import Engine.Loader;
import Models.RawModel;
import Models.TexturedModel;
import Textures.ModelTexture;

public class EntityModels {

    private static float[] vertice = {
            -0.5f,0.5f,-0.5f,
            -0.5f,-0.5f,-0.5f,
            0.5f,-0.5f,-0.5f,
            0.5f,0.5f,-0.5f,

            -0.5f,0.5f,0.5f,
            -0.5f,-0.5f,0.5f,
            0.5f,-0.5f,0.5f,
            0.5f,0.5f,0.5f,

            0.5f,0.5f,-0.5f,
            0.5f,-0.5f,-0.5f,
            0.5f,-0.5f,0.5f,
            0.5f,0.5f,0.5f,

            -0.5f,0.5f,-0.5f,
            -0.5f,-0.5f,-0.5f,
            -0.5f,-0.5f,0.5f,
            -0.5f,0.5f,0.5f,

            -0.5f,0.5f,0.5f,
            -0.5f,0.5f,-0.5f,
            0.5f,0.5f,-0.5f,
            0.5f,0.5f,0.5f,

            -0.5f,-0.5f,0.5f,
            -0.5f,-0.5f,-0.5f,
            0.5f,-0.5f,-0.5f,
            0.5f,-0.5f,0.5f

    };

    public static Loader loader = new Loader();
    public static ModelTexture grassTop = new ModelTexture(loader.loadTexture("grass_top"));
    public static ModelTexture dirt = new ModelTexture(loader.loadTexture("dirt"));
    public static ModelTexture grassSide = new ModelTexture(loader.loadTexture("grass_block_side"));
    public static ModelTexture stone = new ModelTexture(loader.loadTexture("stone"));

    public static RawModel topModel = loader.loadToVAO(Entity.top_face, Entity.textureCoords, Entity.indices);
    public static RawModel bottomModel = loader.loadToVAO(Entity.bottom_face, Entity.textureCoords, Entity.indices);
    public static RawModel sideModel = loader.loadToVAO(Entity.vertices, Entity.textureCoords, Entity.indices);
    public static RawModel fullModel = loader.loadToVAO(vertice, Entity.textureCoords, Entity.indices);

    public static TexturedModel grassBottom = new TexturedModel(bottomModel, dirt);
    public static TexturedModel grassTopTexture = new TexturedModel(topModel, grassTop);
    public static TexturedModel stoneTexture = new TexturedModel(fullModel, stone);
    public static TexturedModel dirtTexture = new TexturedModel(fullModel, dirt);

}
