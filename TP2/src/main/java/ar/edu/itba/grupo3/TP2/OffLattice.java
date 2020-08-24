package ar.edu.itba.grupo3.TP2;

public class OffLattice {

    private final double speed = 0.3f;
    private final double precision = 0.001f;
    private CIM cim;

    public OffLattice(int m, float rc){
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
                randomVal = (Math.random() * (2 * limit)) - limit;
                p.moveAgent(cim.getL());
                p.calculateNewAngle(randomVal);
            }
            cim.saveDynamic(fileOutputPath, i); //FIXME FIJARSE SI NO HAY QUE DESCOMENTAR
            i++;
            double aux = calculateOrder();
            if(lastValue == -1){
                lastValue = aux;
            }else{
               if( aux > 0.9 && (lastValue - delta) < aux && aux < (lastValue + delta)){
                   timesWithinRange++;
               }else{
                   timesWithinRange = 0;
               }
            }
        }
    }

    public double calculateOrder(){
        double xVal = 0.0f;
        double yVal = 0.0f;
        double ret;
        for(Particle p : cim.getAllParticles()){
            xVal += Math.cos(p.getProperty());
            yVal += Math.sin(p.getProperty());
        }
        xVal *= speed;
        yVal *= speed;
        ret =  Math.sqrt( (xVal * xVal) + (yVal * yVal)) / (cim.getN() * speed);
        System.out.println((int) (ret * 100) + "%");
        return ret;
    }

}
