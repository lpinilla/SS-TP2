package ar.edu.itba.grupo3.TP2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class CIMTest {

    CIM cim;

    @Before
    public void setup(){
        cim = new CIM(10, 3, false, false, "resources/Static100.txt");
        cim.loadDynamicFile("resources/Dynamic100.txt");
    }

    @Test
    public void readFile(){
        Assert.assertEquals(100, cim.getN());
        Assert.assertEquals(100, cim.getL(), 0.001);
        Assert.assertNotNull(cim.getAllParticles().get(0).getProperty());
        System.out.println(String.format("%6.7e", cim.getAllParticles().get(0).getX()));
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
    public void offLatticeTest(){
        double randomVal;
        double eta = 0.1;
        double limit = eta / 2;
        for(int i = 0; i < 1; i++){
            for(Particle p :  cim.getAllParticles()){
                randomVal = Math.random() * (2 * limit) - limit;
                //p.moveAgent();
                //p.calculateNewAngle(randomVal);
            }
            cim.saveDynamic("resources", i);
        }
    }
}
