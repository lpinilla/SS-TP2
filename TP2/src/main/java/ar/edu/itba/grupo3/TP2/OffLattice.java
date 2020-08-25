package ar.edu.itba.grupo3.TP2;

import java.util.concurrent.ThreadLocalRandom;

public class OffLattice {

    private final double speed = 0.03f;
    private final double precision = 0.01f;
    private CIM cim;

    public OffLattice(int m, double rc){
        cim = new CIM(m, rc, true, false, "resources/RandomStaticInput.txt");
        cim.loadDynamicFile("resources/RandomDynamicInput.txt");
    }

    public void runSimulation(String fileOutputPath){
        double randomVal;
        double eta = 0.1;
        double limit = eta / 2;
        double delta = precision;
        int maxHitsToStationary = 3;
        int timesWithinRange = 0;
        double lastValue = -1;
        int i = 0;
        while(timesWithinRange != maxHitsToStationary){
            cim.calculateNeighbors();
            for(Particle p :  cim.getAllParticles()){
                //randomVal = (Math.random() * (2 * limit)) - limit;
                randomVal = ThreadLocalRandom.current().nextDouble(-eta / 2, eta / 2);
                p.calculateNewAngle(randomVal);
                p.moveAgent(cim.getL());
            }
            cim.recalculateHeads();
            cim.saveDynamic(fileOutputPath, i); //FIXME FIJARSE SI NO HAY QUE DESCOMENTAR
            i++;
            double aux = calculateOrder();
            if(lastValue == -1){
                lastValue = aux;
            }else{
               if( aux > 0.99 && (lastValue - delta) < aux && aux < (lastValue + delta)){
                   timesWithinRange++;
               }else{
                   timesWithinRange = 0;
                   lastValue = -1;
               }
            }
        }
        System.out.print("Tiempo hasta estacionario:" + i);
    }

    public double calculateOrder(){
        double xVal = 0.0f;
        double yVal = 0.0f;
        double ret;
        for(Particle p : cim.getAllParticles()){
            xVal += Math.cos(p.getProperty());
            yVal += Math.sin(p.getProperty());
        }
        ret =  Math.sqrt( (xVal * xVal) + (yVal * yVal)) / cim.getN() ;
        System.out.println(ret);
        return ret;
    }

}
