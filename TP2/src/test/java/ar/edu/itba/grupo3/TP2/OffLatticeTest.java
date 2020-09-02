package ar.edu.itba.grupo3.TP2;

import org.junit.Before;
import org.junit.Test;

public class OffLatticeTest {

    OffLattice offLattice;

    @Before
    public void setup() {
        try {
            GenerateInput.inputGenerator(250, 5);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        offLattice = new OffLattice(5, 0.999);
    }

    @Test
    public void runSimulationTest() {
        offLattice.runSimulation("resources/StationaryTest.txt", true, 4, false);
        //offLattice.saveEtavsTime(0);
    }

    @Test
    public void plotVaVsEta() {
        double maxEta = 5;
        double etaJump = 0.5;
        double eta = 0.001;
        while (eta < maxEta) {
            offLattice.runSimulation("resources/StationaryTest.txt", false, eta, true);
            //System.out.print("eta ");
            eta += etaJump;
        }
        offLattice.saveEtavsTime(0);


    }


}
