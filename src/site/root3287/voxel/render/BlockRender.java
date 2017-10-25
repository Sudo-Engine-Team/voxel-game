package site.root3287.voxel.render;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;

import site.root3287.sudo2.engine.model.Model;
import site.root3287.sudo2.engine.render.RenderUtils;
import site.root3287.sudo2.engine.render.Renderable;
import site.root3287.sudo2.utils.SudoMaths;
import site.root3287.voxel.shader.BlockShader;
import site.root3287.voxel.terrain.block.Block;

public class BlockRender extends Renderable{
	
	private HashMap<Model, List<Block>> blocks = new HashMap<>();
	
	public BlockRender() {
		this.shader = new BlockShader();
	}
	
	public void render(Block b){
		this.shader.start();
		RenderUtils.bindVAO(b.getModel().getVaoID());
		Matrix4f trans = SudoMaths.createTransformationMatrix(b.getPosition(),b.getScale());
		((BlockShader) shader).trans.loadMatrix(trans);
		RenderUtils.enableVertexAttribsArray(0);
		RenderUtils.enableVertexAttribsArray(1);
		RenderUtils.enableVertexAttribsArray(2);
		RenderUtils.renderElements(GL11.GL_TRIANGLES, b.getModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
		RenderUtils.disableVertexAttribsArray(0);
		RenderUtils.disableVertexAttribsArray(1);
		RenderUtils.disableVertexAttribsArray(2);
		RenderUtils.unbindVAO();
		this.shader.stop();
	}
	
	public void render(){
		this.shader.start();
		for(Model bid : blocks.keySet()){
			RenderUtils.bindVAO(bid.getVaoID());
			RenderUtils.enableVertexAttribsArray(0);
			RenderUtils.enableVertexAttribsArray(1);
			RenderUtils.enableVertexAttribsArray(2);
			List<Block> batch = blocks.get(bid);
			for(Block b : batch){
				Matrix4f trans = SudoMaths.createTransformationMatrix(b.getPosition(),b.getScale());
				((BlockShader) shader).trans.loadMatrix(trans);
				RenderUtils.renderElements(GL11.GL_TRIANGLES, bid.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
			}
			RenderUtils.disableVertexAttribsArray(0);
			RenderUtils.disableVertexAttribsArray(1);
			RenderUtils.disableVertexAttribsArray(2);
			RenderUtils.unbindVAO();
		}
		blocks.clear();
		this.shader.stop();
	}
	
	public void addBlock(Block b){
		if(!blocks.containsKey(b.getModel())){
			blocks.put(b.getModel(), new ArrayList<Block>());
		}
		blocks.get(b.getModel()).add(b);
	}
	
	public void addBlock(List<Block> b){
		for(Block bl:b){
			addBlock(bl);
		}
	}
	
	@Override
	public void dispose() {
		shader.dispose();
	}
	
}
