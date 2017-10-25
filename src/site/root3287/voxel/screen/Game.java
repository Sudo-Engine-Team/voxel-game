package site.root3287.voxel.screen;

import java.util.logging.Level;

import org.lwjgl.glfw.GLFW;

import site.root3287.sudo2.display.DisplayManager;
import site.root3287.sudo2.display.Screen;
import site.root3287.sudo2.engine.Loader;
import site.root3287.sudo2.engine.camera.Camera;
import site.root3287.sudo2.engine.camera.FirstPersonCamera;
import site.root3287.sudo2.engine.render.RenderUtils;
import site.root3287.sudo2.utils.Input;
import site.root3287.voxel.render.BlockRender;
import site.root3287.voxel.shader.BlockShader;
import site.root3287.voxel.terrain.World;
import site.root3287.voxel.terrain.block.Block;

public class Game implements Screen{

	Camera camera;
	BlockRender br;
	World world;
	
	Block b;
	
	@Override
	public void init() {
		camera = new FirstPersonCamera();
		br = new BlockRender();
		br.projection = camera.getProjectionMatrix();
		br.shader.start();
		((BlockShader) this.br.shader).proj.loadMatrix(br.projection);
		br.shader.stop();
		world = new World();
	}

	@Override
	public void update(float delta) {
		camera.update(delta);
		world.update(camera, delta);
		
		if(Input.Keyboard.isKeyPressed(GLFW.GLFW_KEY_ESCAPE)){
			if(Input.Mouse.isGrabbed()){
				Input.Mouse.setGrabbed(false);
			}else{
				Input.Mouse.setGrabbed(true);
			}
		}
		
		if(Input.Keyboard.isKeyPressed(GLFW.GLFW_KEY_X)){
			RenderUtils.toggleWireframe();
		}
	}

	@Override
	public void render() {
		
		br.addBlock(world.getAllBlocks());
		
		this.br.shader.start();
		((BlockShader) this.br.shader).view.loadMatrix(camera.getViewMatrix());
		this.br.shader.stop();
		
		RenderUtils.clear();
		
		br.render();
	}

	@Override
	public void destory() {
		DisplayManager.LOGGER.log(Level.INFO, world.getAllBlocks().size()+" Blocks Loaded");
		DisplayManager.LOGGER.log(Level.INFO, world.getAllChunks().size()+" Chunks Loaded");
		
		br.dispose();
		Loader.getInstance().destory();
	}

}
