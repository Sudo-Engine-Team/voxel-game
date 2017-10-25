package site.root3287.voxel.terrain.chunk;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import site.root3287.voxel.terrain.block.Block;

public class Chunk {
	public static final int CHUNK_SIZE = 16;
	
	private List<Block> blocks = new ArrayList<>(CHUNK_SIZE*CHUNK_SIZE*CHUNK_SIZE);

	public float x, y, z;
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}

	public Chunk(int xOff, int yOff, int zOff) {
		this.x = xOff; 
		this.y = yOff;
		this.z = zOff;
		for(int x = -CHUNK_SIZE/2; x < CHUNK_SIZE/2;x++){
			for(int y = -CHUNK_SIZE/2; y < CHUNK_SIZE/2; y++){
				for(int z = -CHUNK_SIZE/2; z < CHUNK_SIZE/2; z++){
					if(x < CHUNK_SIZE/2-1 && x > -CHUNK_SIZE/2){
						if(y < CHUNK_SIZE/2-1 && y > -CHUNK_SIZE/2){
							if(z < CHUNK_SIZE/2-1 && z > -CHUNK_SIZE/2){
								continue;
							}	
						}
					}
					Block b = new Block() {
						
						@Override
						public void update(float delta) {
							// TODO Auto-generated method stub
							
						}
					};
					b.setPosition(new Vector3f(x+(CHUNK_SIZE*xOff), y+(CHUNK_SIZE*yOff), z+(CHUNK_SIZE*zOff)));
					blocks.add(b);
				}
			}
		}
	}
	
	public List<Block> getBlocks() {
		return blocks;
	}

	public void setBlocks(List<Block> blocks) {
		this.blocks = blocks;
	}
}
