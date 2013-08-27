/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package counter;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

/**
 * @author Adam
 */
public class Counter 
extends StringItem
implements ItemCommandListener {
    public int current_value ;
    private Command command_inc;
    public Counter(int value) {
        super("Count", "" + value,Item.BUTTON);
        current_value = value;
    }
    public void initialize () {
        setItemCommandListener(this);
        command_inc = new Command("Count", Command.OK, 4);
        setDefaultCommand(command_inc);
    }
    public void setValue (int value) {
        setText("" + value);
    }
    public void commandAction(Command c, Item item) {
        if (c == command_inc) {
            setValue(++current_value);
        }
    }
}