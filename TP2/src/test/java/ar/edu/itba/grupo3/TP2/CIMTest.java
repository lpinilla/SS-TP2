package ar.edu.itba.grupo3.TP2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CIMTest {

    CIM cim;

    @Before
    public void setup(){
        cim = new CIM(13, 7.5f, true, false, "resources/Static100.txt");
        cim.loadDynamicFile("resources/Dynamic100.txt");
    }

    @Test
    public void readFile(){
        Assert.assertEquals(100, cim.getN());
        Assert.assertEquals(100, cim.getL(), 0.001);
        Assert.assertNotNull(cim.getAllParticles().get(0).getProperty());
    }

    @Test
    public void randomTest(){
        double eta = 0.1;
        double limit = eta / 2;
        for(int i = 0; i < 10000; i++){
            double val = Math.random() * (2 * limit ) - limit;
            Assert.assertTrue(val >= -limit && val <= limit);
        }
    }

    @Test
    public void neighborTest(){
        cim.calculateNeighbors();
        cim.saveNeighborsToFile("resources/neighborsTest.txt");
    }

}
