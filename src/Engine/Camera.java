package Engine;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import java.security.Key;
import java.util.logging.Logger;

public class Camera {

    private float distanceFromPlayer = 0;
    private float angleAroundPlayer = 0;
    private final float WALK_SPEED = 1;
    private final float TURN_SPEED = 160;
    private final float GRAVITY = -50;
    private final float JUMP = 30;

    private float currentSpeed = 0;
    private float currentTurnSpeed = 0;
    private float upwardsSpeed = 0;

    private Vector3f position;

    private float pitch;
    private float yaw;
    private float rotX;
    private float rotY;
    private float rotZ;
    private int x = 500;
    private int y = 500;

    public Vector3f getPosition() {
        return position;
    }

    public Camera(float x, float y, float z, float rotX, float rotY, float rotZ){
        this.position = new Vector3f(x,y,z);
        this.rotX = rotX;
        this.rotY = rotY;
        this.rotZ = rotZ;
    }

    public void move(){

        Vector3f change = checkInputs();


        calculatePitch();
        calculateYaw();


        float distance = currentSpeed * DisplayManager.getFrameTime();

        increasePosition(change.x, change.y, change.z);

        Mouse.setCursorPosition(500,500);


    }

    public void increasePosition(float dx, float dy, float dz){
        this.position.x += dx;
        this.position.y += dy;
        this.position.z += dz;
    }

    private Vector3f checkInputs(){
      Vector3f vec = new Vector3f();

      float yaw2 = (float) Math.toRadians(yaw);
      float yaw90 = (float) Math.toRadians(yaw+90);

      if(Keyboard.isKeyDown(Keyboard.KEY_W)){
          vec.x -= Math.cos(yaw90) * 0.2;
          vec.z -= Math.sin(yaw90) * 0.2;
      }

      if(Keyboard.isKeyDown(Keyboard.KEY_S)){
          vec.x += Math.cos(yaw90) * 0.2;
          vec.z += Math.sin(yaw90) * 0.2;
      }

      if(Keyboard.isKeyDown(Keyboard.KEY_A)){
          vec.x -= Math.cos(yaw2) * 0.2;
          vec.z -= Math.sin(yaw2) * 0.2;
      }

      if(Keyboard.isKeyDown(Keyboard.KEY_D)){
          vec.x += Math.cos(yaw2) * 0.2;
          vec.z += Math.sin(yaw2) * 0.2;
      }

      if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
          vec.y += 0.2;
      }

      if(Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)){
          vec.y -= 0.2;
      }
      return vec;
    }

    private void calculateCamera(float horizontal, float vertical){
        float theta = yaw + angleAroundPlayer;
        float offsetX = (float) (horizontal * Math.sin(Math.toRadians(theta)));
        float offsetZ = (float) (horizontal * Math.cos(Math.toRadians(theta)));
        position.y = position.y + vertical;
        position.x = position.x - offsetX;
        position.z = position.z - offsetZ;

    }

    private float calculateHorizontalDistance(){
        return (float) (distanceFromPlayer * Math.cos(Math.toRadians(pitch)));
    }

    private float calculateVerticalDistance(){
        return (float) (distanceFromPlayer * Math.sin(Math.toRadians(pitch)));
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public float getYaw() {

        return yaw;
    }

    public float getYawPositive() {
        if(yaw < 0){
            return yaw * -1;
        }
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    private void calculatePitch(){

        float pitchChange = (y - Mouse.getY()) * 0.07f;


        pitch += pitchChange;

        //System.out.println("Pitch: " + pitch);
    }

    private void calculateYaw(){

        float angleChange = (x - Mouse.getX()) * 0.07f;


        yaw -= angleChange;

       // System.out.println("Yaw: " + getYaw());
    }

    public void increaseRotation(float dx, float dy, float dz){
        this.rotX += dx;
        this.rotY += dy;
        this.rotZ += dz;
    }


}
