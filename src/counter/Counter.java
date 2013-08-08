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
public class Counter extends CustomItem {
    public int current_value ;
    public Counter(int value) {
        super("" + value);
        current_value = value;
    }
    public void setValue (int value) {
        setLabel("" + value);
    }
    protected void paint (Graphics g, int x, int y) {
        
    }
    protected int getPrefContentWidth(int height) {
        return 0;
    }
    protected int getMinContentWidth() {
        return 0;
    }
    protected int getPrefContentHeight(int width) {
        return 0;
    }
    protected int getMinContentHeight() {
        return 0;
    }
    public void pointerPressed(int x, int y) {
        setValue(++current_value);
    }
}