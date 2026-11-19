package animals;

import itumulator.simulator.Actor;
import itumulator.simulator.WorldObject;
import itumulator.world.World;


public abstract class Animals extends WorldObject implements Actor {
    int age;
    boolean canReproduce;
    String name;
    int energy;

    public Animals(int age, boolean canReproduce, String name, int energy) {
        this.age = age;
        this.canReproduce = canReproduce;
        this.name = name;
        this.energy = energy;
    }

    public void die(World world) {
        world.delete(this);
    }

    public void age(World world){
        this.age++;
    }
    public void decreaseEnergy(World world, int amount){
        this.energy -= amount;
    }
}
