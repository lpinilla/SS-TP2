package ar.edu.itba.grupo3.TP2;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Getter
@Setter
public class Particle implements Comparable<Particle> {
    private Double x; //x position of particle
    private Double y; //y position of particle
    private Integer id; //id of particle
    private Double radius; //radius of particle
    private Double property; //value of property
    private Set<Particle> neighbours; //list of neighbours
    private final double speed = 0.03;

    //Vamos a tener una lista de particulas general, la primer particula de la lista hace referencia a la particula "padre" ubicada en el casillero cero
    //la segunda particula del array hace referencia a la particula "padre" ubicada en el segundo casillero del tablero....
    //"padre" llamamos a la primer particula que esta ubicada en ese casillero. el next de esa particula, hace refernecia
    //a otra particula ubicada en el mismo casillero
    private List<Particle> particlesSameCellList;

    public Particle(Double x, Double y, Double radius, Double property) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.property = property;
        this.neighbours = new TreeSet<>();
        this.particlesSameCellList = new LinkedList<>();
        this.particlesSameCellList.add(this);
    }

    public Particle(Double radius, Double property, Integer id){
        this.radius = radius;
        this.property = property;
        this.id = id;
        this.x = -1.0; //placeholder
        this.y = -1.0; //placeholder
        this.neighbours = new TreeSet<>();
        this.particlesSameCellList = new LinkedList<>();
        this.particlesSameCellList.add(this);
    }

    public String toString(){
        return this.getId().toString();
    }

    public boolean equals(Object o){
        if(o == null || o.getClass() != this.getClass()) return false;
        if(o == this) return true;
        Particle p = (Particle) o;
        return this.id.equals(p.id);
    }

    @Override
    public int compareTo(Particle particle) {
        return this.getId().compareTo(particle.getId());
    }

    public List<Particle> getParticlesFromCell(){
        return particlesSameCellList;
    }

    public void moveAgent(float l){
        double xPos = (Math.cos(this.getProperty()) * speed) + this.getX();
        double yPos = (Math.sin(this.getProperty()) * speed) + this.getY();
        if(xPos < 0) xPos += l;
        if(yPos < 0) yPos += l;
        if(yPos > l) yPos %= l;
        if(xPos > l) xPos %= l;
        this.setX(xPos);
        this.setY(yPos);
    }


    public void calculateNewAngle(double randomVal){
        double sinAux = Math.sin(this.getProperty());
        double cosAux = Math.cos(this.getProperty());
        Set<Particle> neighbors = this.getNeighbours();
        for(Particle p : neighbors){
            sinAux += Math.sin(p.getProperty());
            cosAux += Math.cos(p.getProperty());
        }
        if(neighbors.size() > 0) {
            sinAux /= neighbors.size() + 1;
            cosAux /= neighbors.size() + 1;
        }
        double newProperty = Math.atan2(sinAux, cosAux);
        if(newProperty < 0) newProperty += Math.PI * 2;
        double finalProperty = newProperty + randomVal;
        if(finalProperty > Math.PI * 2) finalProperty -= 2 * Math.PI;
        if(finalProperty < 0) finalProperty += Math.PI * 2;
        this.setProperty(finalProperty);
    }
}
