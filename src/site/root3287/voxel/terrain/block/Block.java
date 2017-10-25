package site.root3287.voxel.terrain.block;

import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import site.root3287.sudo2.engine.Loader;
import site.root3287.sudo2.engine.model.Model;
import site.root3287.sudo2.engine.objConverter.ModelData;
import site.root3287.sudo2.engine.objConverter.OBJFileLoader;
import site.root3287.sudo2.engine.texture.Texture;

public abstract class Block {
	public static Model BASE_MODEL;
	protected Model model;
	protected Texture texture;
	protected boolean forceColour = true;
	protected Vector4f colour = new Vector4f(0, 1, 0, 0);
	
	
	protected Vector3f position = new Vector3f();
	protected Vector3f scale = new Vector3f(1, 1, 1);
	
	public Block(){
		if(BASE_MODEL == null){
			ModelData loader = OBJFileLoader.loadOBJ("res/models/cube.obj");
			BASE_MODEL = Loader.getInstance().loadToVAO(loader.getVertices(), loader.getTextureCoords(), loader.getNormals(), loader.getIndices());
		}
		model = BASE_MODEL;
	}
	
	
	public abstract void update(float delta);
	
	public Model getModel() {
		return model;
	}
	public void setModel(Model model) {
		this.model = model;
	}
	public Texture getTexture() {
		return texture;
	}
	public void setTexture(Texture texture) {
		this.texture = texture;
	}


	public Vector3f getPosition() {
		return position;
	}


	public void setPosition(Vector3f position) {
		this.position = position;
	}


	public Vector3f getScale() {
		return scale;
	}


	public void setScale(Vector3f scale) {
		this.scale = scale;
	}


	public boolean isForceColour() {
		return forceColour;
	}


	public void setForceColour(boolean forceColour) {
		this.forceColour = forceColour;
	}


	public Vector4f getColour() {
		return colour;
	}


	public void setColour(Vector4f colour) {
		this.colour = colour;
	}
}
