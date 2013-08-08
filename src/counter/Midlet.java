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
public class Midlet 
extends MIDlet 
implements CommandListener {
    
    private Command exitCommand  = new Command("Exit",Command.EXIT,2);
    private Command incCommand   = new Command ("Count",Command.ITEM,1);
    private Command resetCommand = new Command ("Reset",Command.ITEM,2);
    private Form form;
    private Counter counter;
    private CounterRecord data_store;
    //private Display display;
    
    private void initialize() {
        data_store = new CounterRecord();
        int count = data_store.getCount();
        form = new Form("Counter");
        counter = new Counter(count);
        form.append(counter);
        form.addCommand(exitCommand);
        form.addCommand(incCommand);
        form.addCommand(resetCommand);
        form.setCommandListener(this);
        Display.getDisplay(this).setCurrent(form);
    }

    public void commandAction(javax.microedition.lcdui.Command command, javax.microedition.lcdui.Displayable displayable) {
        if (displayable == form) {
            if (command == exitCommand) {
                javax.microedition.lcdui.Display.getDisplay(this).setCurrent(null);
                destroyApp(true);
                notifyDestroyed();
            } else if (command == incCommand) {
                counter.setValue(++counter.current_value);
            } else if (command == resetCommand) {
                counter.setValue(counter.current_value = 0);
            }
        }
    }

    public void startApp() {
        initialize();
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
        data_store.setCount(counter.current_value);
    }
}
