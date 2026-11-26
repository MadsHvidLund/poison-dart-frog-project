package plants;

import itumulator.simulator.WorldObject;
import itumulator.world.Location;
import itumulator.world.NonBlocking;
import itumulator.world.World;
import java.util.*;

public class Grass extends WorldObject implements NonBlocking {
    private static final Random rand = new Random();
    private static final double GROW_CHANCE = 0.1;
    private static final int MIN_GRASS = 2;
    private static final int REGROW_AMOUNT = 2;

    @Override
    public void act(World world) {
        grow(world);
    }

    public static void spawn(int amount, World world) {
        int size = world.getSize();
        int placed = 0;
        while (placed < amount) {
            Location loc = new Location(rand.nextInt(size), rand.nextInt(size));
            if (plant(world, loc)) {
                placed++;
            }
        }
    }

    public void grow(World world) {
        if (rand.nextDouble() >= GROW_CHANCE) {
            return;
        }

        List<Location> freeTiles = new ArrayList<>();
        for (Location loc : world.getSurroundingTiles(world.getLocation(this))) {
            if (!world.containsNonBlocking(loc)) {
                freeTiles.add(loc);
            }
        }

        if (freeTiles.isEmpty()) {
            return;
        }

        Location target = freeTiles.get(rand.nextInt(freeTiles.size()));
        plant(world, target);
    }

    //Statisk hjælpe funktion, så hverken spawn og grow begge skal check fletter og derefter placere græs.
    //Nu sker det hele indeni plant() istedet.
    private static boolean plant(World world, Location loc) {
        if (world.containsNonBlocking(loc)) {
            return false;
        }
        world.setTile(loc, new Grass());
        return true;
    }


    public static int count(World world) {
        int count = 0;
        for (Object o : world.getEntities().keySet()) {
            if (o instanceof Grass) {
                count++;
            }
        }
        return count;
    }

    public void eaten(World world) {
        world.delete(this);
        if (count(world) <= MIN_GRASS) {
            spawn(REGROW_AMOUNT, world);
        }
    }

}