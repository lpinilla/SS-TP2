package ar.edu.itba.grupo3.TP2;

import org.junit.Before;
import org.junit.Test;

public class OffLatticeTest {

    OffLattice offLattice;

    @Before
    public void setup(){
        //try {
        //    GenerateInput.inputGenerator(300, 20);
        //}catch(Exception e){
        //    System.out.println(e.getMessage());
        //}
        offLattice = new OffLattice(13,  1.0);
    }

    @Test
    public void runSimulationTest(){
        offLattice.runSimulation("resources/StationaryTest1.txt");
    }
}
