/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ocean_2;

import java.awt.Color;
import java.util.List;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author Group 5
 */
public class Shark extends Fish {
// as found in the sardine class, we do not need to initalise the fields in these classes as they all have smilar properties and hence we diceded to put them in the creature class so they are avaliable to all the animal. 
    private Random rand = new Random();

    public Shark(boolean randomAge) {
        super();
        if (randomAge) {
            startAge = rand.nextInt(ModelConstants.MAXIMUM_AGE_SHARK);
            age = startAge;
            foodLevel = rand.nextInt(ModelConstants.NUTRITIONAL_VALUE_SARDINE);
        } else {
            foodLevel = ModelConstants.NUTRITIONAL_VALUE_SARDINE;
        }

    }

    public void act(Field field1, Field field2, List<Creature> newSharks) {
        increaseAge();
        reproduce();
     
        if (isAlive()) {
            //this is how new Sharks are born in adjacent locations
            int births = reproduce();
            for (int amount = 0; amount < births; amount++) {
                Shark newShark = new Shark(false);
                newSharks.add(newShark);
                Location loc =(field2.freeAdjacentLocation(location));
                if (loc != null) {
                    newShark.setPlaceOnGrid(loc);
                    field2.place(newShark, loc);
                    hunt(Simulator.field1,location);
                } else {
                    field2.place(this, location);
                }
            }
            //to move towards the Sardine if found
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
                alive = false; //if all the location is taken, then the shark will die
            }
        }
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
            }  else if (tempCreature instanceof Sardine && this instanceof Shark) {
               location = where;
                hunger = hunger = tempCreature.nutriStat;
                Simulator.field2.place(this, location);
                Sardine sardine = new Sardine(false);
                Simulator.creatures.remove(sardine);
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
        if (canBreed() && rand.nextDouble() <= ModelConstants.BREEDING_PROBABILITY_SHARK) {
            births = rand.nextInt() + 1;
        }
        return births;
    }

    public int getBreedingAge() {
        return ModelConstants.BREEDING_AGE_SHARK;
    }

    public int getMaxAge() {
        return ModelConstants.MAXIMUM_AGE_SHARK;
    }

    private Location searchFood(Field field, Location location) {
        Iterator<Location> adjacentLocations = field.adjacentLocations(location);
        while(adjacentLocations.hasNext()) {
            Location where = (Location) adjacentLocations.next();
            Creature creature = field.getObjectAt(where);
            if(creature instanceof Sardine){
                Sardine sardine = (Sardine) creature;
                if(sardine.isAlive()) {
                    sardine.setDead();
                    foodLevel = ModelConstants.NUTRITIONAL_VALUE_SARDINE;
                    return where;
                }
            }
        }
        return null;
    }
    
    
//    maxAge  = ModelConstants.MAXIMUM_AGE_SHARK;
//    breedAge  = ModelConstants.BREEDING_AGE_SHARK;
//    breedProb  = ModelConstants.BREEDING_PROBABILITY_SHARK;
//    creationProb  = ModelConstants.BREEDING_CREATION_SHARK;
//    hunger  = 10;

}
