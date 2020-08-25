package ar.edu.itba.grupo3.TP2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

public class CIMTest {

    CIM cim;

    @Before
    public void setup(){
        cim = new CIM(13, 1.0, true, false, "resources/RandomStaticInput.txt");
        cim.loadDynamicFile("resources/suspect90.txt");
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

    @Test
    public void suspectTest(){
        cim.calculateNeighbors();
        //System.out.println(cim.getParticleCurrentCell(cim.getAllParticles().get(287))); 159
        Assert.assertEquals(9, cim.getAllParticles().get(15).getNeighbours().size());
    }

    @Test
    public void angleTest(){
        cim.calculateNeighbors();
        Particle p = cim.getAllParticles().get(15);
        System.out.println(p.getProperty());
        p.calculateNewAngle(ThreadLocalRandom.current().nextDouble(-0.1/ 2, 0.1/ 2));
        System.out.println(p.getProperty());
    }

    @Test
    public void suspectCellTest(){
        Assert.assertEquals(60, cim.getParticleCurrentCell(cim.getAllParticles().get(0)));
    }

    @Test
    public void movingParticleCimTest(){
        double xPos =cim.getAllParticles().get(0).getX();
        double yPos =cim.getAllParticles().get(0).getY();
        cim.getAllParticles().get(0).moveAgent(cim.getL());
        Particle p0 = cim.getAllParticles().get(0);
        Assert.assertNotEquals(xPos, p0.getX(), 0.001);
        Assert.assertNotEquals(yPos, p0.getY(), 0.001);
    }

}
