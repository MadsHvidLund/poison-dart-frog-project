package animals;

import itumulator.world.Location;
import itumulator.world.World;

public class BabyRabbit extends Rabbit {

    public BabyRabbit(int age, boolean canReproduce, String name, int energy) {
        super(age, canReproduce, name, energy);
    }
    public void act(World world) {
        super.act(world);
        if (world.contains(this) && this.age >= 5) {
            growUp(world);
        }
    }

    private void growUp(World world) {
        Location loc = world.getLocation(this);
        world.delete(this);
        world.setTile(loc, new Rabbit(this.age, true, "rabbit", this.energy));
    }
}