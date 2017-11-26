/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ocean_2;
import java.util.Random;
/**
 *
 * @author Group 5
 */
public class ModelConstants {
    public static final Random rand = RandomGenerator.getRandom();
    public static final  int MAXIMUM_AGE_SHARK = 150;
    public static final int MAXIMUM_AGE_SARDINE = 75;
    public static final int MAXIMUM_AGE_PLANKTON = 100;
    
    public static final int BREEDING_AGE_SHARK = 25;
    public static final int BREEDING_AGE_SARINE = 12;
    public static final int BREEDING_AGE_PLANKTON = 0;
    
    public static final int NUTRITIONAL_VALUE_SARDINE = 14;
    public static final int NUTRITIONAL_VALUE_PLANKTON = 4;
    
    public static final double BREEDING_PROBABILITY_SHARK = 0.2;
    public static final double BREEDING_PROBABILITY_SARDINE = 0.5;
    public static final double BREEDING_PROBABILITY_PLANKTON = 0.8;
    
    public static final double BREEDING_CREATION_SHARK = 0.05;
    public static final double BREEDING_CREATION_SARDINE = 0.1;
    public static final double BREEDING_CREATION_PLANKTON = 0.7;

    public static final int SIMULATION_LENGTH = 1000;
    public static  int RANDOM_NUMBER_GENERATOR_SEED = 44;
    public static  int OCEAN_WIDTH = 100;
    public static  int OCEAN_DEPTH = 100;
}
