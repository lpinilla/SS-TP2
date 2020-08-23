package ar.edu.itba.grupo3.TP2;

import org.junit.Before;
import org.junit.Test;

public class OffLatticeTest {

    OffLattice offLattice;

    @Before
    public void setup(){
        offLattice = new OffLattice(10, 3);
    }

    @Test
    public void runSimulationTest(){
        offLattice.runSimulation("resources/StationaryTest1.txt");
    }
}
