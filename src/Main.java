import java.awt.Color;

import itumulator.executable.DisplayInformation;
import itumulator.executable.Program;
import itumulator.world.Location;
import itumulator.world.World;

public class Main {
    //August er her
    public static void main(String[] args) {
        int size = 5;
        Program p = new Program(size, 800, 70);

        World w = p.getWorld();

        p.show();
    }
}