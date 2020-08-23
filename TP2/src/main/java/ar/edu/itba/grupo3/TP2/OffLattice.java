package ar.edu.itba.grupo3.TP2;

public class OffLattice {

    private final float speed = 0.03f;
    private CIM cim;

    public OffLattice(int m, float rc){
        cim = new CIM(m, rc, true, false, "resources/Static2.txt");
        cim.loadDynamicFile("resources/Dynamic100.txt");
    }

    public void runSimulation(String fileOutputPath){
        double randomVal;
        double eta = 0.1;
        double limit = eta / 2;
        float delta = 0.00001f;
        int maxHitsToStationary = 3;
        int timesWithinRange = 0;
        float lastValue = -1;
        int i = 0;
        while(timesWithinRange != maxHitsToStationary){
            cim.calculateNeighbors();
            for(Particle p :  cim.getAllParticles()){
                randomVal = (Math.random() * (2 * limit)) - limit;
                p.moveAgent();
                p.calculateNewAngle(randomVal);
            }
            cim.saveDynamic(fileOutputPath, i); //FIXME FIJARSE SI NO HAY QUE DESOCMENTAR
            i++;
            float aux = calculateOrder();
            if(lastValue == -1){
                lastValue = aux;
            }else{
               if( (lastValue - delta) < aux && aux < (lastValue + delta)){
                   timesWithinRange++;
               }else{
                   timesWithinRange = 0;
               }
            }
        }
    }

    public float calculateOrder(){
        float xVal = 0.0f;
        float yVal = 0.0f;
        float ret;
        for(Particle p : cim.getAllParticles()){
            xVal += Math.cos(p.getProperty());
            yVal += Math.sin(p.getProperty());
        }
        xVal *= speed;
        yVal *= speed;
        ret = (float) Math.sqrt( (xVal * xVal) + (yVal * yVal)) / (cim.getN() * speed);
        System.out.println(ret);
        return ret;
    }

}
