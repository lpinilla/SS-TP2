package ar.edu.itba.grupo3.TP2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

public class OffLattice {

    private final double speed = 0.03f;
    private final double precision = 0.03f;
    private CIM cim;

    public OffLattice(int m, double rc) {
        cim = new CIM(m, rc, true, false, "resources/RandomStaticInput.txt");
        cim.loadDynamicFile("resources/RandomDynamicInput.txt");
    }

    public void runSimulation(String fileOutputPath, boolean plotTime,double eta,boolean plotEta) {
        double randomVal;
        double limit = eta / 2;
        double delta = precision;
        int maxHitsToStationary = 3;
        int timesWithinRange = 0;
        double lastValue = -1;
        int i = 0;

//        if (plotTime) {
//            saveVavsTime(0);
//            Timer t = new Timer();
//            t.schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    double aux = calculateOrder();
//                    System.out.println(aux);
//                    saveVavsTime(aux);
//                }
//            }, 0, 10);
//        }

        cim.saveDynamic(fileOutputPath, i++);
        while (timesWithinRange!=maxHitsToStationary) {
            cim.calculateNeighbors();
            cim.saveNeighborsToFile("resources/neighborsTest.txt");
            for (Particle p : cim.getAllParticles()) {
                //randomVal = (Math.random() * (2 * limit)) - limit;
                randomVal = ThreadLocalRandom.current().nextDouble(-eta / 2, eta / 2);
                p.calculateNewAngle(randomVal);
                p.moveAgent(cim.getL());
            }
            cim.recalculateHeads();
            cim.saveDynamic(fileOutputPath, i); //FIXME FIJARSE SI NO HAY QUE DESCOMENTAR
            i++;

            double aux = calculateOrder();
            if(plotTime){
                saveVavsTime(aux);
            }
            System.out.println(aux+" "+eta);
            if (lastValue == -1) {
                lastValue = aux;
            } else {
                if (aux>1 && (lastValue - delta) < aux && aux < (lastValue + delta)) {
                    timesWithinRange++;
                } else {
                    timesWithinRange = 0;
                    lastValue = -1;
                }
            }

        }
        if(plotEta){
            //System.out.println("Entro plot eta");
            saveEtavsTime(calculateOrder());
        }
        //System.out.println("Tiempo hasta estacionario:"+i);
    }



    public double calculateOrder() {
        double xVal = 0.0f;
        double yVal = 0.0f;
        double ret;
        for (Particle p : cim.getAllParticles()) {
            xVal += Math.cos(p.getProperty()) * p.getSpeed();
            yVal += Math.sin(p.getProperty()) * p.getSpeed();
        }
        ret = (Math.sqrt((xVal * xVal) + (yVal * yVal))) / (cim.getN() * speed);
        //System.out.println(ret);
        return ret;
    }

    public void saveVavsTime(double order) {
        String timePath = "OutFiles/vaVsTime.txt";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(timePath), true));
            if (order == 0) {
                writer.newLine();
            }
            writer.write(String.format(Locale.US, "%6.7e", order) + " ");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveEtavsTime(double order) {
        String timePath = "OutFiles/vaVsEta" + cim.getN() + ".txt";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(timePath), true));
            if (order == 0) {
                writer.newLine();
            }
            writer.write(String.format(Locale.US, "%6.7e", order) + " ");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}
