package site.root3287.voxel.render;

import site.root3287.sudo2.engine.render.Renderable;

public class ChunkRendering extends Renderable{

	@Override
	public void dispose() {
		this.shader.dispose();
	}

}
