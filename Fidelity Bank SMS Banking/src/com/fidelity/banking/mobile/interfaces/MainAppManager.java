/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fidelity.banking.mobile.interfaces;

import com.fidelity.banking.mobile.model.Account;
import com.fidelity.banking.mobile.model.Settings;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;

/**
 *
 * @author administrator
 */
public class MainAppManager {
    RecordStore recStore;
    Settings settings;

    public MainAppManager(Settings settings){
        this.settings = settings;
    }

    public void initializeMainAppStore(){
        try {
            recStore = RecordStore.openRecordStore(Settings.RS_SETTINGS, true);
            if (recStore.getNumRecords() > 0) {
                DataInputStream in = new DataInputStream(new ByteArrayInputStream(recStore.getRecord(1)));
                String settings = in.readUTF();
                in.close();
            }else{
                settings = new Settings();
            }

        } catch (RecordStoreException e) {
            e.getMessage();
        } catch (IOException e) {
            e.getMessage();
        }
    }

    private Settings getSettingsFromString(String s){
        Settings result = null;

        if(s != null){
            
        }

        return result;
    }

    private Account[] getAccountsFromString(String s){
        Account [] result = null;

        if(s != null){
            char []chars = s.toCharArray();

            for (int i = 0; i < chars.length; i++){

            }
        }

        return result;
    }

}
