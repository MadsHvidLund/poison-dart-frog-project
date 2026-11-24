import java.awt.Color;
import java.util.*;
import itumulator.executable.DisplayInformation;
import itumulator.executable.Program;
import plants.Grass;
import animals.Rabbit;
import itumulator.world.Location;
import itumulator.world.World;

public class Main {
    public static void main(String[] args) {
        int size = 5;
        Program p = new Program(size, 800, 75);
        World w = p.getWorld();

        //Place grass on random tiles
        Grass.spawn(5, w);
        Rabbit.spawn(5, w);
        DisplayInformation di = new DisplayInformation(Color.green, "grass");
        p.setDisplayInformation(Grass.class, di);
        DisplayInformation dii = new DisplayInformation(Color.white, "rabbit-small");
        p.setDisplayInformation(Rabbit.class, dii);
        p.show();
    }
}