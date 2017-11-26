/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ocean_2;

import static Ocean_2.Simulator.field1;
import java.awt.Color;

/**
 *
 * @author Group 5 
 */

public abstract class Fish extends Creature{
    public abstract void hunt(Field fld, Location lct);
}
