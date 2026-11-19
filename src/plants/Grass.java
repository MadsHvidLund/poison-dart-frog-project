package plants;

import itumulator.world.Location;
import itumulator.world.World;
import java.util.*;


public class Grass extends WorldObject {
    public static Set<Location> grassesLoc = new HashSet<Location>();
    public static Set<Grass> grasses= new HashSet<Grass>();
    public double growSpeed = 0.1;
    public int maxGrass = 20;
    public Grass(){
    }

    public static void spawn(int amount, World world) {
        Random rand = new Random();
        int placed = 0;
        while (placed < amount) {
            Grass grass = new Grass();
            int size = world.getSize();
            int x = rand.nextInt(size);
            int y = rand.nextInt(size);
            Location loc = new Location(x, y);

            if (world.isTileEmpty(loc)) {
                world.setTile(loc, grass);
                grassesLoc.add(loc);
                grasses.add(grass);
                placed++;
            }
        }
    }

    public Set<Location> getGrassesLoc() {
        return grassesLoc;
    }
    public Set<Grass> getGrasses() {
        return grasses;
    }

    public void grow(World world) {
        Random rand = new Random();

        if (grasses.size() < maxGrass) {
               if(rand.nextDouble() < growSpeed) {
                    Location current = world.getLocation(this);
                    Set<Location> neighbours = world.getEmptySurroundingTiles(current);
                    if (!neighbours.isEmpty()) {
                        List<Location> list = new ArrayList<>(neighbours);
                        Location target = list.get(rand.nextInt(list.size()));
                        Grass grass = new Grass();
                        world.setTile(target, grass);
                        grasses.add(grass);
                        grassesLoc.add(target);
                    }
                }
            }

    }
    
    public void act(World world) {
        grow(world);
    }
}
