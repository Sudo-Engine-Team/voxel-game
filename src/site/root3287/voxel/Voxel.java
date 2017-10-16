package site.root3287.voxel;

import site.root3287.sudo2.display.Application;
import site.root3287.voxel.screen.Game;

public class Voxel {
	public static void main(String[] args) {
		Application app = new Application("voxel");
		app.setScreen(new Game());
		app.run();
	}
}
