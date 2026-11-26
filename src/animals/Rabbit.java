package animals;

import itumulator.world.Location;
import itumulator.world.World;
import plants.Grass;

import java.util.*;


public class Rabbit extends Animals {
    private static final Random rand = new Random();
    private static final double REPRODUCE_CHANCE = 0.05;
    private static final int MIN_DIE_AGE = 30;
    private static final int MAX_DIE_AGE = 35;

    public Rabbit(int age, boolean canReproduce, String name, int energy) {
        super(age, canReproduce, name, energy);
    }

    @Override
    public void act(World world) {
        age(world);
        move(this, world);
        eat(this, world);
        reproduce(world);
        die(world);
    }

    public static void spawn(int amount, World world) {
        int size = world.getSize();
        int placed = 0;
        while (placed < amount) {
            Location loc = new Location(rand.nextInt(size), rand.nextInt(size));
            if (world.isTileEmpty(loc)) {

                world.setTile(loc, new Rabbit(5, false, "rabbit", 100));
                placed++;
            }
        }
    }

    public void move(Rabbit rabbit, World world) {
        if (this.energy <= 0) {
            return;
        }

        Location loc = world.getLocation(this);
        List<Location> movementTiles = new ArrayList<>(world.getEmptySurroundingTiles(loc));
        if (movementTiles.isEmpty()) {
            return;
        }

        Location target = movementTiles.get(rand.nextInt(movementTiles.size()));
        world.move(rabbit, target);
        this.energy--;
    }

    public void eat(Rabbit rabbit, World world) {
        Location loc = world.getLocation(rabbit);
        if (!world.containsNonBlocking(loc)) {
            return;
        }

        Object o = world.getNonBlocking(loc);
        if (o instanceof Grass) {
            Grass grass = (Grass) o;
            grass.eaten(world);
            this.energy += 10;
        }
    }

    public void reproduce(World world) {
        if (this.age > 10) {
            this.canReproduce = true;
        }
        if (!this.canReproduce || this.energy <= 30) return;

        Location loc = world.getLocation(this);
        Set<Location> tiles = world.getSurroundingTiles(loc);
        Set<Rabbit> nearbyRabbits = world.getAll(Rabbit.class, tiles);

        if (!nearbyRabbits.isEmpty() && rand.nextDouble() < REPRODUCE_CHANCE) {
            List<Location> free = new ArrayList<>(world.getEmptySurroundingTiles(loc));
            if (free.isEmpty()) return;

            Location babyLoc = free.get(rand.nextInt(free.size()));
            world.setTile(babyLoc, new BabyRabbit(0, false, "BabyRabbit", 100));
            this.energy -= 30;
        }
    }

    @Override
    public void die(World world) {
        if (this.energy <= 0 || this.age > rand.nextInt(MIN_DIE_AGE, MAX_DIE_AGE)) {
            world.delete(this);
        }
    }

    public void burrow(World world) {
    }
}
