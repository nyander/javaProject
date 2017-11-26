/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ocean_2;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
import java.util.Iterator;
import java.util.logging.Level;

/**
 *
 * @author Group 5
 */
public class Simulator {

    protected static Field field1;
    protected static Field field2;
    private static SimulatorView sim2;
    protected static ArrayList<Creature> creatures;
    // this arraylist will be used to store the new creatures that get created.
    // reason we decided to use an arraylist instead of an array is because array has fixed size hence make it diffuclt to store the creatures and they are generated randomly.
    protected static ArrayList<Creature> newCreatures;
    private static int steps = 0;
    protected static Creature currentCureature;
    protected Creature creat;
    Random randomNumber = new Random();
    
    public Simulator(int height, int width) {

        if (height <= 0 || width <= 0) {
            height = ModelConstants.OCEAN_DEPTH;
            width = ModelConstants.OCEAN_WIDTH;
        }

        field1 = new Field(height, width);
        field2 = new Field(height, width);
        sim2 = new SimulatorView(height, width);
        sim2.setColor(Shark.class, Color.YELLOW);
        sim2.setColor(Sardine.class, Color.BLUE);
        sim2.setColor(Plankton.class, Color.RED);
        populate();
        sim2.showStatus(steps, field1);

//        sim2.showStatus(steps, field1);
//simulate(ModelConstants.SIMULATION_LENGTH);
// we have commented out simulate method there because we were calling it in the main method and the constructor which reulsted in the simulation running for both of those periods
//        creatures = new ArrayList<Creature>();
//        rnd.initialiseWithSeed(44);
//        simulate(100);
        RandomGenerator.initialiseWithSeed(ModelConstants.RANDOM_NUMBER_GENERATOR_SEED);
    }
    
    public static void main(String[] args) {
        int numSteps = 1000;
        if (args.length >= 1) {
            numSteps = Integer.parseInt(args[0]);
        }
        if (numSteps <= 0) {
            numSteps = 1;
        }
        int seed = ModelConstants.RANDOM_NUMBER_GENERATOR_SEED;
        if (args.length >= 2) {
            seed = Integer.parseInt(args[1]);
        }
        Simulator simulator = new Simulator(0, 0);
        simulator.simulate(numSteps);
    }
    
    public void populate() {
        field1.clear();
        creatures = new ArrayList<>();
        newCreatures = new ArrayList<>();
        for (int w = 0; w < field1.getWidth(); w++) {
            for (int h = 0; h < field1.getDepth(); h++) {
                Shark shark = new Shark(true);
                Sardine sardine = new Sardine(true);
                Plankton plankton = new Plankton(true);
                Double dd = randomNumber.nextDouble();
                if (dd <= ModelConstants.BREEDING_CREATION_SHARK) {
                    creatures.add(shark);
                    shark.setPlaceOnGrid(h, w);
                    field1.place(shark, shark.location);

                } else if ( dd <= ModelConstants.BREEDING_CREATION_SARDINE) {
                    creatures.add(sardine);
                    sardine.setPlaceOnGrid(h, w);
                    field1.place(sardine, sardine.location);

                } else if (dd <= ModelConstants.BREEDING_CREATION_PLANKTON) {
                    creatures.add(plankton);
                    plankton.setPlaceOnGrid(h, w);
                    field1.place(plankton, plankton.location);
                }
               Collections.shuffle(creatures, RandomGenerator.getRandom());
            }
        }
    }
    
    

  public void simulate(int numSteps) {
        for (steps = 0; steps < numSteps; steps++) {
            simulateOneStep();
//            if (creatures.isEmpty()) {
//                numSteps = steps;
              sim2.showStatus(steps, field1);
//            }
            

            try { // this is used control the speed the of the animation, lower the number faster it gets so quicker your animations finishes. 
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                java.util.logging.Logger.getLogger(Simulator.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    
 

    public void simulateOneStep(){
        newCreatures.clear();
        field2.clear();
        for (Iterator<Creature> iter = creatures.iterator(); iter.hasNext();) {
            currentCureature = iter.next();
         if(!currentCureature.isAlive()) {
             iter.remove();
             field1.remove(currentCureature.location);
    }else { currentCureature.act(field1, field2, newCreatures);
             }
            
        }
        if (newCreatures.size() >= 1) {
        } else {
            creatures.addAll(newCreatures);
        }
        Field temp = field1;
        field1 = field2;
        field2 = temp;
    }
	}
//        field2.clear();
//        for (Iterator<Creature> iter = creatures.iterator(); iter.hasNext();) {
//            currentCureature = iter.next();
//            if (isAlive(currentCureature) != true) {
//                iter.remove();
//                field1.remove(currentCureature.location);
//            } else {
//                act();
//            }
//
//        }
//        if (newCreatures.size() >= 1) {
//        } else {
//            creatures.addAll(newCreatures);
//        }
//        Field temp = field1;
//        field1 = field2;
//        field2 = temp;


//    private void act() {
//        currentCureature.age++;
//        reproducing();
//        if (!Plankton.class.equals(currentCureature.getClass())) {
//            currentCureature.hunger--;
//            hunt(field1, currentCureature.location);
//        } else {
//            field2.place(currentCureature, currentCureature.location);
//        }
//    }
//    private void reproducing() {
//        boolean breed = currentCureature.breed();
//        if (breed == true) {
//            if (currentCureature instanceof Plankton) {
//                Plankton plankton = new Plankton();
//                newCreatures.add(plankton);
//                Location newLoc = field1.freeAdjacentLocation(currentCureature.location);
//                if (newLoc != null) {
//                    field2.place(plankton, newLoc);
//
//                } else if (currentCureature instanceof Sardine) {
//                    Sardine sardine = new Sardine();
//                    newCreatures.add(sardine);
//                    newLoc = field1.freeAdjacentLocation(currentCureature.location);
//                    if (newLoc != null) {
//                        field2.place(sardine, newLoc);
//
//                    } else if (currentCureature instanceof Shark) {
//                        Shark shark = new Shark();
//                        newCreatures.add(shark);
//                        newLoc = field1.freeAdjacentLocation(currentCureature.location);
//                        if (newLoc != null) {
//                            field2.place(shark, newLoc);
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    private void hunt(Field fld, Location lct) {
//
//        Iterator<Location> adjacentLocations = fld.adjacentLocations(lct);
//        while (adjacentLocations.hasNext()) {
//            Location where = (Location) adjacentLocations.next();
//            Creature tempCreature = fld.getObjectAt(where);
//            if (tempCreature == null) {
//                currentCureature.location = where;
//                field2.place(currentCureature, currentCureature.location);
//            } else if (tempCreature instanceof Plankton && currentCureature instanceof Sardine) {
//                currentCureature.location = where;
//                currentCureature.hunger = currentCureature.hunger + tempCreature.nutriStat;
//                field2.place(currentCureature, currentCureature.location);
//                Plankton plankton = new Plankton();
//                creatures.remove(plankton);
//            } else if (tempCreature instanceof Sardine && currentCureature instanceof Shark) {
//                currentCureature.location = where;
//                currentCureature.hunger = currentCureature.hunger = tempCreature.nutriStat;
//                field2.place(currentCureature, currentCureature.location);
//                Sardine sardine = new Sardine();
//                creatures.remove(sardine);
//            }
//        }
//    }
//
//    private boolean isAlive(Creature currentCreature) {
//        //needs adjusting so plankton doesn't starve.
//        boolean state = false;
//        if (currentCreature.age < currentCreature.maxAge && currentCreature.hunger > 0) {
//            state = true;
//        }
//
//        return state;
//    }
//}
//public static void main(String[] args) { 
//  Plankton plankton1 = new Plankton();
//    Sardine sardine1 = new Sardine();
//    Shark shark1 = new Shark();
////    Field field = new Field(50,50);
//        ModelConstants.BREEDING_PROBABILITY_SHARK && dd <= ModelConstants.BREEDING_PROBABILITY_SARDINE
//private  void simulateOneStep(){
//        ArrayList<Creature> newCreatures = new ArrayList<Creature>();
//        for(Creature c: creatures) {
//            if (c.isAlive())
//            {
//              if (c.equals(Shark.class)){
//                 Shark shark = new Shark();
//                 shark.act(field1);
//                   }
//                 else if(c.equals(Sardine.class)){
//                     Sardine sard = new Sardine();
//                 sard.act(field1);
//                   }
//              else if(c.equals(Plankton.class)){
//                     Plankton plank = new Plankton();
//                 plank.act(field1);
//                   }
//                
//              }
//        }
//            for(Creature c: newCreatures) {
//                creatures.add(c);
//
//            } 
