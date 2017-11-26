/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ocean_2;

import java.awt.Color;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Group 5 
 */
public class Sardine extends Fish{   
//    public Sardine (int depth,int width, int age)
//    private int age;
//    private boolean alive;
//    private int maxAge;
//    private int startAge;
//    private int breedingAge;
//    private double breedProb;
//    private double creationProb;
//    private int nutriStat;
//    private int hunger;
// all the fields above were declared at the start of the project however we decided to remove them and add it in the crature class as all the animals belong to that class and these fields can inhreited from that class. 
   private Random rand = new Random();
   
   public Sardine(boolean randomAge) {
        super();
        if (randomAge) {
            startAge = rand.nextInt(ModelConstants.MAXIMUM_AGE_SARDINE);
            age = startAge;
            foodLevel = rand.nextInt(ModelConstants.NUTRITIONAL_VALUE_PLANKTON);
        } else {
            foodLevel = ModelConstants.NUTRITIONAL_VALUE_PLANKTON;
        }

    }

    public void act(Field field1, Field field2, List<Creature> newSardines) {
        increaseAge();
        starve();
        if (isAlive()) {
            //this is how new Sardine are born in adjacent locations
            int births = reproduce();
            for (int amount = 0; amount < births; amount++) {
                Sardine newSardine = new Sardine(false);
                newSardines.add(newSardine);
                Location loc =(field2.freeAdjacentLocation(location));
                if (loc != null) {
                    newSardine.setPlaceOnGrid(loc);
                    field2.place(newSardine, loc);
                } else {
                    newSardine.setDead();
                }
            }
            //to move towards the Plankton if found
            Location newlocation = searchFood(field1,location);
            if(newlocation == null)
            {
                newlocation = field2.freeAdjacentLocation(location);
            }
            if(newlocation != null) {
                setPlaceOnGrid(newlocation);
                field2.place(this, newlocation);
            }
            else {
                alive = false; //if all the location is taken, then the sardine will die
            }
        }

    }

    private void starve() {
        foodLevel--;
        if (foodLevel <= 0) {
            alive = false;
        }
    }

    public int reproduce() {
        int births = 0;
        if (canBreed() && rand.nextDouble() <= ModelConstants.BREEDING_PROBABILITY_SARDINE) {
            births = rand.nextInt() + 1;
        }
        return births;
    }

    public int getBreedingAge() {
        return ModelConstants.BREEDING_AGE_SARINE;
    }

    public int getMaxAge() {
        return ModelConstants.MAXIMUM_AGE_SARDINE;
    }

    public Location searchFood(Field field, Location location) {
        Iterator<Location> adjacentLocations = field.adjacentLocations(location);
        while(adjacentLocations.hasNext()) {
            Location where = (Location) adjacentLocations.next();
            Creature creature = field.getObjectAt(where);
            if(creature instanceof Plankton){
                Plankton plankton = (Plankton) creature;
                if(plankton.isAlive()) {
                    plankton.setDead();
                    foodLevel = ModelConstants.NUTRITIONAL_VALUE_PLANKTON;
                    return where;
                }
            }
        }
        return null;
    }
   @Override
    public void hunt(Field fld, Location lct) {
     Iterator<Location> adjacentLocations = fld.adjacentLocations(lct);
        while (adjacentLocations.hasNext()) {
            Location where = (Location) adjacentLocations.next();
            Creature tempCreature = fld.getObjectAt(where);
            if (tempCreature == null) {
                location = where;
               Simulator.field2.place(this, location);
            }   else if (tempCreature instanceof Plankton && this instanceof Sardine) {
                location = where;
                hunger = hunger + tempCreature.nutriStat;
                Simulator.field2.place(this, location);
                Plankton plankton = new Plankton(false);
                Simulator.creatures.remove(plankton);
            }
        }
    }
////public Sardine(){    
////    super();
////    startAge = rand.nextInt(ModelConstants.MAXIMUM_AGE_SARDINE)+1;
////    age = startAge;
////    maxAge = ModelConstants.MAXIMUM_AGE_SARDINE;
////    breedAge = ModelConstants.BREEDING_AGE_SARINE;
////    breedProb = ModelConstants.BREEDING_PROBABILITY_SARDINE;
////    creationProb = ModelConstants.BREEDING_CREATION_SARDINE;
////    alive = true;
////    nutriStat = ModelConstants.NUTRITIONAL_VALUE_SARDINE;
////    hunger = 10;
// }
//    
    
}
