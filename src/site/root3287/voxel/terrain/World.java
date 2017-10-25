package site.root3287.voxel.terrain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import site.root3287.sudo2.engine.camera.Camera;
import site.root3287.voxel.terrain.block.Block;
import site.root3287.voxel.terrain.chunk.Chunk;

public class World {
	private List<Block> allBlocks = new ArrayList<>();
	private List<Chunk> allChunks = new ArrayList<>();
	/**
	 * A HashMap that holds loadedChunks and have a integer pointing to the place in the List
	 * X, Y, Z, POS
	 */
	private HashMap<Integer, HashMap<Integer, HashMap<Integer, Integer>>> loadedChunks = new HashMap<>();
	
	public World(){
		for(int x = 0; x < 1; x++){
			for(int y = 0; y < 1; y++){
				for(int z = 0; z < 1; z++){
					addChunk(new Chunk(x, y, z));
				}
			}
		}
	}
	
	public void update(Camera c, float delta){
		int xChunk = (int)(c.getPosition().x)/Chunk.CHUNK_SIZE/2;
		int zChunk = (int)(c.getPosition().z)/Chunk.CHUNK_SIZE/2;
		
		if(!isLoaded(xChunk, 0, zChunk)){
			addChunk(new Chunk(xChunk, 0, zChunk));
		}
	}
	
	public void addChunk(Chunk c){
		allChunks.add(c);
		allBlocks.addAll(c.getBlocks());
		int loc = allChunks.size();
		tryToAddToLoadedList((int)c.getX(), (int)c.getY(), (int)c.getZ(), loc);
	}
	
	public List<Block> getAllBlocks(){
		return allBlocks;
	}
	public List<Chunk> getAllChunks(){
		return allChunks;
	}
	
	private void tryToAddToLoadedList(int x, int y, int z, int loc){
		if(loadedChunks.containsKey(x)){
			if(loadedChunks.get(x).containsKey(y)){
				loadedChunks.get(x).get(y).put(z, loc);
			}else{
				HashMap<Integer, Integer> batch = new HashMap<>();
				loadedChunks.get(x).put(y, batch);
				tryToAddToLoadedList(x, y, z, loc);
			}
		}else{
			HashMap<Integer, HashMap<Integer, Integer>> batch = new HashMap<>();
			loadedChunks.put(x, batch);
			tryToAddToLoadedList(x, y, z, loc);
		}
	}
	
	public boolean isLoaded(int x, int y, int z){
		boolean loaded = false;
		if(loadedChunks.containsKey(x)){
			if(loadedChunks.get(x).containsKey(y)){
				if(loadedChunks.get(x).get(y).get(z) != null){
					loaded = true;
				}
			}
		}
		return loaded;
	}
	
	public HashMap<Integer, HashMap<Integer, HashMap<Integer, Integer>>> getLoadedChunks(){
		return loadedChunks;
	}
}
