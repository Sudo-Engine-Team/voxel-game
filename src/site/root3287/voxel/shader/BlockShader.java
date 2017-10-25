package site.root3287.voxel.shader;

import site.root3287.sudo2.engine.shader.Shader;
import site.root3287.sudo2.engine.shader.uniforms.UniformMatrix;

public class BlockShader extends Shader{

	public UniformMatrix proj, view, trans;
	
	public BlockShader() {
		super("/shader/block/vertex.glsl", "/shader/block/fragment.glsl");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void getAllUniformLocations() {
		proj = new UniformMatrix(programID, "proj");
		view = new UniformMatrix(programID, "view");
		trans = new UniformMatrix(programID, "trans");
	}

	@Override
	protected void bindAttributes() {
		bindAttribute(0, "pos");
		bindAttribute(1, "tc");
		bindAttribute(2, "normal");
	}

}