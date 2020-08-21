package ar.edu.itba.grupo3.TP2;

import org.junit.Assert;
import org.junit.Test;

public class ParticleTest {

    @Test
    public void moveAgentTest(){
        Particle p = new Particle(4.0, 4.5, 3.3, 0.0);
        p.moveAgent();
        Assert.assertEquals(4.03, p.getX(), 0.001);
        Assert.assertEquals(4.5, p.getY(), 0.001);
        Assert.assertEquals(3.3, p.getRadius(), 0.001);
        Assert.assertEquals(0.0, p.getProperty(), 0.001);
    }


    @Test
    public void moveAgentTest2(){
        Particle p = new Particle(4.0, 4.5, 3.3, Math.PI / 4);
        p.moveAgent();
        Assert.assertEquals(4.021213, p.getX(), 0.000001);
        Assert.assertEquals(4.521213, p.getY(), 0.000001);
        Assert.assertEquals(3.3, p.getRadius(), 0.001);
        Assert.assertEquals(Math.PI / 4, p.getProperty(), 0.001);
    }

    @Test
    public void moveAgentTest3(){
        Particle p = new Particle(4.0, 4.5, 3.3, Math.PI / 2);
        p.moveAgent();
        Assert.assertEquals(4.0, p.getX(), 0.001);
        Assert.assertEquals(4.53, p.getY(), 0.001);
        Assert.assertEquals(3.3, p.getRadius(), 0.001);
        Assert.assertEquals(0.0, p.getProperty(), 0.001);
    }

    @Test
    public void moveAgentTest4(){
        Particle p = new Particle(4.0, 4.5, 3.3, Math.PI);
        p.moveAgent();
        Assert.assertEquals(3.97, p.getX(), 0.001);
        Assert.assertEquals(4.5, p.getY(), 0.001);
        Assert.assertEquals(3.3, p.getRadius(), 0.001);
        Assert.assertEquals(Math.PI, p.getProperty(), 0.001);
    }

    @Test
    public void moveAgentTest5(){
        Particle p = new Particle(4.0, 4.5, 3.3, 5 * Math.PI / 4);
        p.moveAgent();
        Assert.assertEquals(3.978786, p.getX(), 0.000001);
        Assert.assertEquals(4.478786, p.getY(), 0.000001);
        Assert.assertEquals(3.3, p.getRadius(), 0.001);
        Assert.assertEquals( 5 * Math.PI / 4, p.getProperty(), 0.001);
    }

    @Test
    public void moveAgentTest6(){
        Particle p = new Particle(4.0, 4.5, 3.3, 6 * Math.PI / 4);
        p.moveAgent();
        Assert.assertEquals(4.0, p.getX(), 0.000001);
        Assert.assertEquals(4.47, p.getY(), 0.000001);
        Assert.assertEquals(3.3, p.getRadius(), 0.001);
        Assert.assertEquals( 6 * Math.PI / 4, p.getProperty(), 0.001);
    }

    @Test
    public void moveAgentTest7(){
        Particle p = new Particle(4.0, 4.5, 3.3, 2 * Math.PI);
        p.moveAgent();
        Assert.assertEquals(4.03, p.getX(), 0.001);
        Assert.assertEquals(4.5, p.getY(), 0.001);
        Assert.assertEquals(3.3, p.getRadius(), 0.001);
        Assert.assertEquals( 2 * Math.PI, p.getProperty(), 0.001);
    }

}
