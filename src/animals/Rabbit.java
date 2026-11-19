package animals;

import itumulator.world.Location;
import itumulator.world.World;

import java.util.*;

public class Rabbit extends Animals {
    private static final Random rand = new Random();

    public Rabbit(int age, boolean canReproduce, String name, int energy) {
        super(age, canReproduce, name, energy);
    }
    public static void spawn(int amount, World world) {
        int size = world.getSize();
        int placed = 0;
        while (placed < amount) {
            Location loc = new Location(rand.nextInt(size), rand.nextInt(size));
            if (world.isTileEmpty(loc)) {
                world.setTile(loc, new Rabbit(0, true, "rabbit", 100));
                placed++;
            }
        }
    }

}
