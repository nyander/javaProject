/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ocean_2;

import java.util.List;
import java.util.Random;

/**
 *
 * @author Group 5
 */
public abstract class Creature {

    protected Location location;
    protected boolean alive;
    protected int age;
    protected int startAge;
    protected Field field;
    protected Random random = new Random();
    protected int breedAge;
    protected double breedProb;
    protected double creationProb;
    protected int hunger;
    protected int nutriStat;
    protected int maxAge;
    protected int foodLevel;
    protected int width;
    protected int length;
    protected Simulator sim;
    public SimulatorView views;

    public boolean breed() {
        double rand = random.nextDouble();
        if (age >= breedAge && rand <= breedProb) {
            boolean value = true;
            return value;
        } else {
            return false;
        }
    }

    public int setBirthAge(int maxAge) {
        int num = random.nextInt(maxAge);
        return num;
    }

    public void setPlaceOnGrid(int row, int col) {
        location = new Location(row, col);
    }

    public void setPlaceOnGrid(Location location) {
        this.location = location;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    protected boolean canBreed() {
        boolean result = age > getBreedingAge();
        return result;
    }

    public boolean isAlive() {
        boolean state = false;
    if(age< maxAge && hunger >0)
            {state = true;}
        
    return state;
    }

    public void setDead(){
        alive = false;
    }
    
    public void increaseAge()
    {
        age++;
    }
    
  //the following methods will be defined in the subclsses
    public abstract int getBreedingAge();
    public abstract int getMaxAge();
    public abstract void act(Field field1, Field field2, List<Creature> newCreature);
    public abstract int reproduce();
    }

