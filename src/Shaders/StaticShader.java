package Shaders;

import org.lwjgl.util.vector.Matrix4f;

public class StaticShader extends ShaderProgram{

    private static final String VERTEX_FILE = "src/vertexShader.txt";
    private static final String Fragment_FILE = "src/fragementShader.txt";

    private int location_matrix;
    private int location__projectionMatrix;

    public StaticShader() {
        super(VERTEX_FILE, Fragment_FILE);
    }

    @Override
    protected void getAllUniformLocations() {
       location_matrix =  super.getUniformLocation("transformationMatrix");
       location__projectionMatrix = super.getUniformLocation("projectionMatrix");
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoords");
    }

    public void loadTransformationMatrix(Matrix4f matrix){
        super.loadMatrix(location_matrix, matrix);
    }

    public void loadProjectionMatrix(Matrix4f projection){
        super.loadMatrix(location__projectionMatrix, projection);
    }
}
