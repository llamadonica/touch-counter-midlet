/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package counter;
import javax.microedition.rms.*;

import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;

/**
 *
 * @author Adam
 */
public class CounterRecord 
implements RecordFilter, RecordComparator {
    private RecordStore recordStore = null;
    
    public boolean matches(byte[] candidate)
	throws IllegalArgumentException
    {
	ByteArrayInputStream bais = new ByteArrayInputStream(candidate);
	DataInputStream inputStream = new DataInputStream(bais);
	
	try {
	    int score = inputStream.readInt();
	}
	catch (EOFException eofe) {
	    System.out.println(eofe);
	    eofe.printStackTrace();
	}
	catch (IOException eofe) {
	    System.out.println(eofe);
	    eofe.printStackTrace();
	}
	return (true);
    }
    
    /*
     * Part of the RecordComparator interface.
     */
    public int compare(byte[] rec1, byte[] rec2)
    {
	// Construct DataInputStreams for extracting the scores from
	// the records.
	ByteArrayInputStream bais1 = new ByteArrayInputStream(rec1);
	DataInputStream inputStream1 = new DataInputStream(bais1);
	ByteArrayInputStream bais2 = new ByteArrayInputStream(rec2);
	DataInputStream inputStream2 = new DataInputStream(bais2);
	int score1 = 0;
	int score2 = 0;
	try {
	    // Extract the scores.
	    score1 = inputStream1.readInt();
	    score2 = inputStream2.readInt();
	}
	catch (EOFException eofe) {
	    System.out.println(eofe);
	    eofe.printStackTrace();
	}
	catch (IOException eofe) {
	    System.out.println(eofe);
	    eofe.printStackTrace();
	}

	// Sort by score
	if (score1 < score2) {
	    return RecordComparator.PRECEDES;
	}
	else if (score1 > score2) {
	    return RecordComparator.FOLLOWS;
	}
	else {
	    return RecordComparator.EQUIVALENT;
	}
    }
    
    public CounterRecord()
    {
	//
	// Create a new record store for this example
	//
	try {
	    recordStore = RecordStore.openRecordStore("count", true);
	}
	catch (RecordStoreException rse) {
	    System.out.println(rse);
	    rse.printStackTrace();
	}
    }
    
    public void setCount(int score)
    {
	//
	// Each score is stored in a separate record, formatted with
	// the score, followed by the player name.
	//
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	DataOutputStream outputStream = new DataOutputStream(baos);
	try {
	    // Push the score into a byte array.
	    outputStream.writeInt(score);
	}
	catch (IOException ioe) {
	    System.out.println(ioe);
	    ioe.printStackTrace();
	}

	// Extract the byte array
	byte[] b = baos.toByteArray();
	// Add it to the record store
	try {
            if (recordStore.getNumRecords() == 0) {
       	       recordStore.addRecord(b, 0, b.length);
            } else {
                recordStore.setRecord(1, b, 0, b.length);
            }
	}
	catch (RecordStoreException rse) {
	    System.out.println(rse);
	    rse.printStackTrace();
	}
    }
    
    public int getCount ()
    {
        byte[] b;
        
        try {
            if (recordStore.getNumRecords() == 0) {
                return 0;
            }
             b = recordStore.getRecord(1);
        }
	catch (RecordStoreException rse) {
	    System.out.println(rse);
	    rse.printStackTrace();
            return 0;
	}
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        DataInputStream dais = new DataInputStream(bais);
	try {
	    return(dais.readInt());
	}
	catch (EOFException eofe) {
	    System.out.println(eofe);
	    eofe.printStackTrace();
            return 0;
	}
	catch (IOException eofe) {
	    System.out.println(eofe);
	    eofe.printStackTrace();
            return 0;
	}
        
    }

}
