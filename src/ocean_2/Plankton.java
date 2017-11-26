/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ocean_2;

import java.awt.Color;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Group 5
 */
public class Plankton extends Creature {

    private final Random rand = new Random();
        protected SimulatorView views;
    //private final ModelConstants model = new ModelConstants();

    public Plankton(boolean randomAge) {
        super();
        if (randomAge) {
            startAge = rand.nextInt(ModelConstants.MAXIMUM_AGE_SARDINE);
            age = startAge;
            foodLevel = rand.nextInt(ModelConstants.NUTRITIONAL_VALUE_PLANKTON);
        } else {
            foodLevel = ModelConstants.NUTRITIONAL_VALUE_PLANKTON;
        }

    }

    public void act(Field field1, Field field2, List<Creature> newPlanktons) {
        increaseAge();

        if (alive) {
            int births = reproduce();
            for (int amount = 0; amount < births; amount++) {
               Plankton newPlankton = new Plankton(false);
               newPlanktons.add(newPlankton);
               Location loc = field1.freeAdjacentLocation(location);
               if(loc != null){
                   newPlankton.setPlaceOnGrid(loc);
                   field1.place(newPlankton, loc);
               }
               else
                   newPlankton.setDead();
            }
           Location newlocation = field1.freeAdjacentLocation(location);
           //it will only located to the updated field if there is a free location
           setPlaceOnGrid(newlocation);
           field1.place(this, newlocation);
        }
        else {
            alive = false; // since there not space to move
        }

    }

    public int reproduce() {
        int births = 0;
        if (canBreed() && rand.nextDouble() <= ModelConstants.BREEDING_AGE_PLANKTON) {
            births = rand.nextInt() + 1;
        }
        return births;
    }
    
     public int getBreedingAge(){
         return ModelConstants.BREEDING_AGE_PLANKTON;
     }
    public int getMaxAge(){
        return ModelConstants.MAXIMUM_AGE_PLANKTON;
    }
    
       public void setColor(Creature creature, Color color)
    {
        views.setColor(Creature.class, color);
    }

// Plankton(){
//     super();
//   
//    startAge = rand.nextInt(ModelConstants.MAXIMUM_AGE_PLANKTON)+1;
//   // we have assigned startage to the actual age here, above we randmly generated an integer betweem 0-100, as it starts at 0 we decided to add 1.
//    age = startAge;
//    // we began with this "maxAge = model.MAXIMUM_AGE_PLANKTON;" but then netbeans suggested that "maxAge = ModelConstants.BREEDING_AGE_PLANKTON"
//    maxAge = ModelConstants.MAXIMUM_AGE_PLANKTON;
//    breedAge = ModelConstants.BREEDING_AGE_PLANKTON;
//    breedProb = ModelConstants.BREEDING_PROBABILITY_PLANKTON;
//    creationProb = ModelConstants.BREEDING_CREATION_PLANKTON;
//    alive = true;
//    nutriStat = ModelConstants.NUTRITIONAL_VALUE_PLANKTON;
//    hunger = 100; 
}
