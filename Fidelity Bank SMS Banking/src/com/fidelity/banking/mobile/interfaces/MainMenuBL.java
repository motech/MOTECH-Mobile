/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fidelity.banking.mobile.interfaces;

import com.fidelity.banking.mobile.functions.MathUtil;
import com.fidelity.banking.mobile.functions.SendSMS;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import javax.microedition.lcdui.TextField;
import javax.microedition.rms.InvalidRecordIDException;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreNotOpenException;

/**
 *
 * @author administrator
 */
public class MainMenuBL {

    MainMenu mainMenu;

    public MainMenuBL(MainMenu m) {
        this.mainMenu = m;
    }

    public void saveSettings() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(baos);

            out.writeUTF(mainMenu.shortcode);
            out.close();

            if (mainMenu.recStore.getNumRecords() > 0) {
                mainMenu.recStore.setRecord(1, baos.toByteArray(), 0, baos.size());
            } else {
                mainMenu.recStore.addRecord(baos.toByteArray(), 0, baos.size());
            }
        } catch (RecordStoreNotOpenException ex) {
            ex.printStackTrace();
        } catch (InvalidRecordIDException ex) {
            ex.printStackTrace();
        } catch (RecordStoreException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void switchDisplay(int ref) {
        switch (ref) {
            case 0: {
                mainMenu.switchDisplayable(null, mainMenu.getFrmRCBook());
                break;
            }
            case 1: {
                mainMenu.switchDisplayable(null, mainMenu.getFrmMStatement());
                break;
            }
            case 2: {
                mainMenu.switchDisplayable(null, mainMenu.getFrmCBalance());
                break;
            }
            case 3: {
                mainMenu.switchDisplayable(null, mainMenu.getFrmCqStatus());
                break;
            }
        }
    }

    public void mStatementBL() {
        String pin = mainMenu.getTxtAFPin().getString().trim();
        String accNumber = mainMenu.getTxtMSAccNumber().getString().trim();
        if (pin.equals("") || accNumber.equals("")) {
            mainMenu.switchDisplayable(null, mainMenu.getAltEmptyField());
            mainMenu.ref = MainMenu.ADD_FUNDS;
            return;
        }
        mainMenu.smsSender = new SendSMS();
        mainMenu.smsSender.setMessage("MSR " + pin + " " + accNumber);
        mainMenu.smsSender.setPhoneNumber(mainMenu.shortcode);
        mainMenu.getTxtAFPin().setString("");
        mainMenu.getTxtMSAccNumber().setString("");
    }

    public void checkBalanceBL() {
        String pin = mainMenu.getTxtCBPin().getString().trim();
        String accNumber = mainMenu.getTxtCBAccNumber().getString().trim();
        if (pin.equals("") || accNumber.equals("")) {
            mainMenu.switchDisplayable(null, mainMenu.getAltEmptyField());
            mainMenu.ref = MainMenu.CHECK_BALANCE;
            return;
        }
        mainMenu.smsSender = new SendSMS();
        mainMenu.smsSender.setMessage("BAL " + pin + " " + accNumber);
        mainMenu.smsSender.setPhoneNumber(mainMenu.shortcode);
        mainMenu.getTxtCBPin().setString("");
        mainMenu.getTxtCBAccNumber().setString("");
    }

    public void rCBookBL() {
        String pin = mainMenu.getTxtRPin().getString().trim();
        String accNumber = mainMenu.getTxtRCBAccNumber().getString().trim();
        if (pin.equals("") || accNumber.equals("")) {
            mainMenu.switchDisplayable(null, mainMenu.getAltEmptyField());
            mainMenu.ref = MainMenu.REQUEST_CHEQUE_BOOK;
            return;
        }
        mainMenu.smsSender = new SendSMS();
        mainMenu.smsSender.setMessage("CBR " + pin + " " + accNumber);
        mainMenu.smsSender.setPhoneNumber(mainMenu.shortcode);
        mainMenu.getTxtRPin().setString("");
        mainMenu.getTxtRCBAccNumber().setString("");
    }

    public void settingsBL() {
        mainMenu.shortcode = ((TextField) mainMenu.getFrmSCode().get(0)).getString();
    }

    public void chequeStatusBL() {
        String pin = mainMenu.getTxtTFPin().getString().trim();
        String accNumber = mainMenu.getTxtCSAccNumber().getString().trim();
        String cqNumber = mainMenu.getTxtCSCqNumber().getString().trim();
        if (pin.equals("") || accNumber.equals("") || cqNumber.equals("")) {
            mainMenu.switchDisplayable(null, mainMenu.getAltEmptyField());
            mainMenu.ref = MainMenu.CHEQUE_STATUS;
            return;
        }
        
        mainMenu.smsSender = new SendSMS();
        mainMenu.smsSender.setMessage("CSR " + pin + " " + accNumber + " " + cqNumber);
        mainMenu.smsSender.setPhoneNumber(mainMenu.shortcode);
        mainMenu.getTxtTFPin().setString("");
        mainMenu.getTxtCSAccNumber().setString("");
        mainMenu.getTxtCSCqNumber().setString("");
    }
}
