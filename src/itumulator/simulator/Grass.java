package itumulator.simulator;

import itumulator.world.Location;
import itumulator.world.World;
import java.util.*;


public class Grass extends WorldObject {
    public Grass(){

    }
    public static void spawn(int amount, World world) {
        Random rand = new Random();
        for (int i = 0; i < amount; i++) {
            Grass grass = new Grass();
            int size = world.getSize();
            int x = rand.nextInt(size);
            int y = rand.nextInt(size);
            Location loc = new Location(x, y);

            if (world.isTileEmpty(loc)) {
                world.setTile(loc, grass);
            }
        }
    }
    public void act(World world) {

    }
}
