/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fidelity.banking.mobile.interfaces;

import com.fidelity.banking.mobile.functions.SendSMS;
import java.io.IOException;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import org.netbeans.microedition.lcdui.SplashScreen;
import org.netbeans.microedition.lcdui.WaitScreen;
import org.netbeans.microedition.util.SimpleCancellableTask;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.Vector;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;

/**
 * @author administrator
 */
public class MainMenu extends MIDlet implements CommandListener {

    protected boolean midletPaused = false;
    protected SendSMS smsSender;
    protected Vector accounts;

    private MainMenuBL mmbl;

    protected static final int REQUEST_CHEQUE_BOOK = 0;
    protected static final int ADD_FUNDS = 1;
    protected static final int CHECK_BALANCE = 2;
    protected static final int CHEQUE_STATUS = 3;
    protected static final int CASHBACK = 4;

    protected static final String RS_SETTINGS = "settings";
    protected int ref = 0;
    protected String shortcode;
    protected RecordStore recStore;

    //<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private SplashScreen splashScreen;
    private List lMainMenu;
    private WaitScreen wsSending;
    private Form frmMStatement;
    private TextField txtAFPin;
    private TextField txtMSAccNumber;
    private Form frmCBalance;
    private TextField txtCBPin;
    private TextField txtCBAccNumber;
    private Form frmCqStatus;
    private TextField txtCSAccNumber;
    private TextField txtTFPin;
    private TextField txtCSCqNumber;
    private Form frmRCBook;
    private TextField txtRPin;
    private TextField txtRCBAccNumber;
    private Alert altFailure;
    private Alert altSuccess;
    private Alert altEmptyField;
    private Form frmSCode;
    private TextField ShortCode;
    private Alert altIllegalAmount;
    private WaitScreen wsSaving;
    private Alert altSFail;
    private Alert altSSuccess;
    private Form frmSCheque;
    private Command okCommand1;
    private Command exitCommand1;
    private Command backCommand1;
    private Command okCommand3;
    private Command backCommand2;
    private Command backCommand3;
    private Command okCommand4;
    private Command backCommand4;
    private Command backCommand6;
    private Command backCommand5;
    private Command okCommand5;
    private Command backCommand;
    private Command okCommand6;
    private Command backCommand7;
    private Command okCommand;
    private Command backCommand8;
    private Command btnCashbackBack;
    private Command btnCashbackOK;
    private Command backCommand9;
    private Command backCommand10;
    private Command okCommand2;
    private Command backCommand11;
    private Command okCommand7;
    private Font splashFont;
    private SimpleCancellableTask sendingTask;
    private Image sendingImage;
    private SimpleCancellableTask task;
    private SimpleCancellableTask savingTask;
    private Image image1;
    //</editor-fold>//GEN-END:|fields|0|
    /**
     * The MainMenu constructor.
     */
    public MainMenu() {
        mmbl = new MainMenuBL(this);
    }

    //<editor-fold defaultstate="collapsed" desc=" Generated Methods ">//GEN-BEGIN:|methods|0|
    //</editor-fold>//GEN-END:|methods|0|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: initialize ">//GEN-BEGIN:|0-initialize|0|0-preInitialize
    /**
     * Initilizes the application.
     * It is called only once when the MIDlet is started. The method is called before the <code>startMIDlet</code> method.
     */
    private void initialize() {//GEN-END:|0-initialize|0|0-preInitialize
        // write pre-initialize user code here
        splashScreen = new SplashScreen(getDisplay());//GEN-BEGIN:|0-initialize|1|0-postInitialize
        splashScreen.setTitle("");
        splashScreen.setCommandListener(this);
        splashScreen.setFullScreenMode(true);
        splashScreen.setImage(getImage1());
        splashScreen.setText("Powered by DreamOval");
        splashScreen.setTextFont(getSplashFont());
        splashScreen.setTimeout(3000);//GEN-END:|0-initialize|1|0-postInitialize
        // write post-initialize user code here
        try {
            recStore = RecordStore.openRecordStore(RS_SETTINGS, true);
            if (recStore.getNumRecords() > 0) {
                DataInputStream in = new DataInputStream(new ByteArrayInputStream(recStore.getRecord(1)));
                shortcode = in.readUTF();
                in.close();
            }else{
                shortcode = "1406";
            }

        } catch (RecordStoreException e) {
            e.getMessage();
        } catch (IOException e) {
            e.getMessage();
        }
    }//GEN-BEGIN:|0-initialize|2|
    //</editor-fold>//GEN-END:|0-initialize|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: startMIDlet ">//GEN-BEGIN:|3-startMIDlet|0|3-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Started point.
     */
    public void startMIDlet() {//GEN-END:|3-startMIDlet|0|3-preAction
        // write pre-action user code here
        switchDisplayable(null, splashScreen);//GEN-LINE:|3-startMIDlet|1|3-postAction
    // write post-action user code here
    }//GEN-BEGIN:|3-startMIDlet|2|
    //</editor-fold>//GEN-END:|3-startMIDlet|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: resumeMIDlet ">//GEN-BEGIN:|4-resumeMIDlet|0|4-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Resumed point.
     */
    public void resumeMIDlet() {//GEN-END:|4-resumeMIDlet|0|4-preAction
    // write pre-action user code here
//GEN-LINE:|4-resumeMIDlet|1|4-postAction
    // write post-action user code here
    }//GEN-BEGIN:|4-resumeMIDlet|2|
    //</editor-fold>//GEN-END:|4-resumeMIDlet|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: switchDisplayable ">//GEN-BEGIN:|5-switchDisplayable|0|5-preSwitch
    /**
     * Switches a current displayable in a display. The <code>display</code> instance is taken from <code>getDisplay</code> method. This method is used by all actions in the design for switching displayable.
     * @param alert the Alert which is temporarily set to the display; if <code>null</code>, then <code>nextDisplayable</code> is set immediately
     * @param nextDisplayable the Displayable to be set
     */
    public void switchDisplayable(Alert alert, Displayable nextDisplayable) {//GEN-END:|5-switchDisplayable|0|5-preSwitch
        // write pre-switch user code here
        Display display = getDisplay();//GEN-BEGIN:|5-switchDisplayable|1|5-postSwitch
        if (alert == null) {
            display.setCurrent(nextDisplayable);
        } else {
            display.setCurrent(alert, nextDisplayable);
        }//GEN-END:|5-switchDisplayable|1|5-postSwitch
    // write post-switch user code here
    }//GEN-BEGIN:|5-switchDisplayable|2|
    //</editor-fold>//GEN-END:|5-switchDisplayable|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: commandAction for Displayables ">//GEN-BEGIN:|7-commandAction|0|7-preCommandAction
    /**
     * Called by a system to indicated that a command has been invoked on a particular displayable.
     * @param command the Command that was invoked
     * @param displayable the Displayable where the command was invoked
     */
    public void commandAction(Command command, Displayable displayable) {//GEN-END:|7-commandAction|0|7-preCommandAction
        // write pre-action user code here
        if (displayable == altEmptyField) {//GEN-BEGIN:|7-commandAction|1|138-preAction
            if (command == backCommand) {//GEN-END:|7-commandAction|1|138-preAction
                mmbl.switchDisplay(ref);
//GEN-LINE:|7-commandAction|2|138-postAction
            // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|3|110-preAction
        } else if (displayable == altFailure) {
            if (command == backCommand6) {//GEN-END:|7-commandAction|3|110-preAction
                // write pre-action user code here
                switchDisplayable(null, getLMainMenu());//GEN-LINE:|7-commandAction|4|110-postAction
            // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|5|132-preAction
        } else if (displayable == altIllegalAmount) {
            if (command == backCommand7) {//GEN-END:|7-commandAction|5|132-preAction
                // write pre-action user code here
                switchDisplayable(null, getFrmCqStatus());//GEN-LINE:|7-commandAction|6|132-postAction
            // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|7|163-preAction
        } else if (displayable == altSFail) {
            if (command == backCommand9) {//GEN-END:|7-commandAction|7|163-preAction
                // write pre-action user code here
                switchDisplayable(null, getFrmSCode());//GEN-LINE:|7-commandAction|8|163-postAction
            // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|9|167-preAction
        } else if (displayable == altSSuccess) {
            if (command == backCommand10) {//GEN-END:|7-commandAction|9|167-preAction
                // write pre-action user code here
                switchDisplayable(null, getLMainMenu());//GEN-LINE:|7-commandAction|10|167-postAction
            // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|11|108-preAction
        } else if (displayable == altSuccess) {
            if (command == backCommand5) {//GEN-END:|7-commandAction|11|108-preAction
                // write pre-action user code here
                switchDisplayable(null, getLMainMenu());//GEN-LINE:|7-commandAction|12|108-postAction
            // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|13|93-preAction
        } else if (displayable == frmCBalance) {
            if (command == backCommand3) {//GEN-END:|7-commandAction|13|93-preAction
                // write pre-action user code here
                switchDisplayable(null, getLMainMenu());//GEN-LINE:|7-commandAction|14|93-postAction
            // write post-action user code here
            } else if (command == okCommand4) {//GEN-LINE:|7-commandAction|15|91-preAction
                mmbl.checkBalanceBL();
                switchDisplayable(null, getWsSending());//GEN-LINE:|7-commandAction|16|91-postAction
            // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|17|75-preAction
        } else if (displayable == frmCqStatus) {
            if (command == backCommand1) {//GEN-END:|7-commandAction|17|75-preAction
                // write pre-action user code here
                switchDisplayable(null, getLMainMenu());//GEN-LINE:|7-commandAction|18|75-postAction
            // write post-action user code here
            } else if (command == okCommand6) {//GEN-LINE:|7-commandAction|19|129-preAction
                mmbl.chequeStatusBL();
                switchDisplayable(null, getWsSending());//GEN-LINE:|7-commandAction|20|129-postAction
            // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|21|87-preAction
        } else if (displayable == frmMStatement) {
            if (command == backCommand2) {//GEN-END:|7-commandAction|21|87-preAction
                // write pre-action user code here
                switchDisplayable(null, getLMainMenu());//GEN-LINE:|7-commandAction|22|87-postAction
            // write post-action user code here
            } else if (command == okCommand3) {//GEN-LINE:|7-commandAction|23|85-preAction
                mmbl.mStatementBL();
                switchDisplayable(null, getWsSending());//GEN-LINE:|7-commandAction|24|85-postAction
            // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|25|97-preAction
        } else if (displayable == frmRCBook) {
            if (command == backCommand4) {//GEN-END:|7-commandAction|25|97-preAction

                switchDisplayable(null, getLMainMenu());//GEN-LINE:|7-commandAction|26|97-postAction
            // write post-action user code here
            } else if (command == okCommand5) {//GEN-LINE:|7-commandAction|27|124-preAction
                mmbl.rCBookBL();
                switchDisplayable(null, getWsSending());//GEN-LINE:|7-commandAction|28|124-postAction
            // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|29|150-preAction
        } else if (displayable == frmSCode) {
            if (command == backCommand8) {//GEN-END:|7-commandAction|29|150-preAction
                // write pre-action user code here
                switchDisplayable(null, getLMainMenu());//GEN-LINE:|7-commandAction|30|150-postAction
            // write post-action user code here
            } else if (command == okCommand) {//GEN-LINE:|7-commandAction|31|148-preAction
                mmbl.settingsBL();
                switchDisplayable(null, getWsSaving());//GEN-LINE:|7-commandAction|32|148-postAction
            // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|33|55-preAction
        } else if (displayable == lMainMenu) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|33|55-preAction
                // write pre-action user code here
                lMainMenuAction();//GEN-LINE:|7-commandAction|34|55-postAction
            // write post-action user code here
            } else if (command == exitCommand1) {//GEN-LINE:|7-commandAction|35|79-preAction
                // write pre-action user code here
                exitMIDlet();//GEN-LINE:|7-commandAction|36|79-postAction
            // write post-action user code here
            } else if (command == okCommand1) {//GEN-LINE:|7-commandAction|37|67-preAction
                lMainMenuAction();
//GEN-LINE:|7-commandAction|38|67-postAction
            // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|39|28-preAction
        } else if (displayable == splashScreen) {
            if (command == SplashScreen.DISMISS_COMMAND) {//GEN-END:|7-commandAction|39|28-preAction
                // write pre-action user code here
                switchDisplayable(null, getLMainMenu());//GEN-LINE:|7-commandAction|40|28-postAction
            // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|41|155-preAction
        } else if (displayable == wsSaving) {
            if (command == WaitScreen.FAILURE_COMMAND) {//GEN-END:|7-commandAction|41|155-preAction
                // write pre-action user code here
                switchDisplayable(getAltSFail(), getWsSaving());//GEN-LINE:|7-commandAction|42|155-postAction
            // write post-action user code here
            } else if (command == WaitScreen.SUCCESS_COMMAND) {//GEN-LINE:|7-commandAction|43|154-preAction
                // write pre-action user code here
                switchDisplayable(getAltSSuccess(), getLMainMenu());//GEN-LINE:|7-commandAction|44|154-postAction
            // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|45|65-preAction
        } else if (displayable == wsSending) {
            if (command == WaitScreen.FAILURE_COMMAND) {//GEN-END:|7-commandAction|45|65-preAction
                // write pre-action user code here
                switchDisplayable(getAltFailure(), getWsSending());//GEN-LINE:|7-commandAction|46|65-postAction
            // write post-action user code here
            } else if (command == WaitScreen.SUCCESS_COMMAND) {//GEN-LINE:|7-commandAction|47|64-preAction
                // write pre-action user code here
                switchDisplayable(getAltSuccess(), getWsSending());//GEN-LINE:|7-commandAction|48|64-postAction
            // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|49|7-postCommandAction
        }//GEN-END:|7-commandAction|49|7-postCommandAction
    // write post-action user code here
    }//GEN-BEGIN:|7-commandAction|50|
    //</editor-fold>//GEN-END:|7-commandAction|50|
    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand1 ">//GEN-BEGIN:|29-getter|0|29-preInit
    /**
     * Returns an initiliazed instance of okCommand1 component.
     * @return the initialized component instance
     */
    public Command getOkCommand1() {
        if (okCommand1 == null) {//GEN-END:|29-getter|0|29-preInit
            // write pre-init user code here
            okCommand1 = new Command("OK", Command.OK, 0);//GEN-LINE:|29-getter|1|29-postInit
        // write post-init user code here
        }//GEN-BEGIN:|29-getter|2|
        return okCommand1;
    }
    //</editor-fold>//GEN-END:|29-getter|2|
    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: splashFont ">//GEN-BEGIN:|39-getter|0|39-preInit
    /**
     * Returns an initiliazed instance of splashFont component.
     * @return the initialized component instance
     */
    public Font getSplashFont() {
        if (splashFont == null) {//GEN-END:|39-getter|0|39-preInit
            // write pre-init user code here
            splashFont = Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN, Font.SIZE_SMALL);//GEN-LINE:|39-getter|1|39-postInit
        // write post-init user code here
        }//GEN-BEGIN:|39-getter|2|
        return splashFont;
    }
    //</editor-fold>//GEN-END:|39-getter|2|
    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand1 ">//GEN-BEGIN:|74-getter|0|74-preInit
    /**
     * Returns an initiliazed instance of backCommand1 component.
     * @return the initialized component instance
     */
    public Command getBackCommand1() {
        if (backCommand1 == null) {//GEN-END:|74-getter|0|74-preInit
            // write pre-init user code here
            backCommand1 = new Command("Back", Command.BACK, 0);//GEN-LINE:|74-getter|1|74-postInit
        // write post-init user code here
        }//GEN-BEGIN:|74-getter|2|
        return backCommand1;
    }
    //</editor-fold>//GEN-END:|74-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand1 ">//GEN-BEGIN:|78-getter|0|78-preInit
    /**
     * Returns an initiliazed instance of exitCommand1 component.
     * @return the initialized component instance
     */
    public Command getExitCommand1() {
        if (exitCommand1 == null) {//GEN-END:|78-getter|0|78-preInit
            // write pre-init user code here
            exitCommand1 = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|78-getter|1|78-postInit
        // write post-init user code here
        }//GEN-BEGIN:|78-getter|2|
        return exitCommand1;
    }
    //</editor-fold>//GEN-END:|78-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand2 ">//GEN-BEGIN:|86-getter|0|86-preInit
    /**
     * Returns an initiliazed instance of backCommand2 component.
     * @return the initialized component instance
     */
    public Command getBackCommand2() {
        if (backCommand2 == null) {//GEN-END:|86-getter|0|86-preInit
            // write pre-init user code here
            backCommand2 = new Command("Back", Command.BACK, 0);//GEN-LINE:|86-getter|1|86-postInit
        // write post-init user code here
        }//GEN-BEGIN:|86-getter|2|
        return backCommand2;
    }
    //</editor-fold>//GEN-END:|86-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand4 ">//GEN-BEGIN:|90-getter|0|90-preInit
    /**
     * Returns an initiliazed instance of okCommand4 component.
     * @return the initialized component instance
     */
    public Command getOkCommand4() {
        if (okCommand4 == null) {//GEN-END:|90-getter|0|90-preInit
            // write pre-init user code here
            okCommand4 = new Command("OK", Command.OK, 0);//GEN-LINE:|90-getter|1|90-postInit
        // write post-init user code here
        }//GEN-BEGIN:|90-getter|2|
        return okCommand4;
    }
    //</editor-fold>//GEN-END:|90-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand3 ">//GEN-BEGIN:|92-getter|0|92-preInit
    /**
     * Returns an initiliazed instance of backCommand3 component.
     * @return the initialized component instance
     */
    public Command getBackCommand3() {
        if (backCommand3 == null) {//GEN-END:|92-getter|0|92-preInit
            // write pre-init user code here
            backCommand3 = new Command("Back", Command.BACK, 0);//GEN-LINE:|92-getter|1|92-postInit
        // write post-init user code here
        }//GEN-BEGIN:|92-getter|2|
        return backCommand3;
    }
    //</editor-fold>//GEN-END:|92-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand4 ">//GEN-BEGIN:|96-getter|0|96-preInit
    /**
     * Returns an initiliazed instance of backCommand4 component.
     * @return the initialized component instance
     */
    public Command getBackCommand4() {
        if (backCommand4 == null) {//GEN-END:|96-getter|0|96-preInit
            // write pre-init user code here
            backCommand4 = new Command("Back", Command.BACK, 0);//GEN-LINE:|96-getter|1|96-postInit
        // write post-init user code here
        }//GEN-BEGIN:|96-getter|2|
        return backCommand4;
    }
    //</editor-fold>//GEN-END:|96-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: sendingTask ">//GEN-BEGIN:|66-getter|0|66-preInit
    /**
     * Returns an initiliazed instance of sendingTask component.
     * @return the initialized component instance
     */
    public SimpleCancellableTask getSendingTask() {
        if (sendingTask == null) {//GEN-END:|66-getter|0|66-preInit
            // write pre-init user code here
            sendingTask = new SimpleCancellableTask();//GEN-BEGIN:|66-getter|1|66-execute
            sendingTask.setExecutable(new org.netbeans.microedition.util.Executable() {
                public void execute() throws Exception {//GEN-END:|66-getter|1|66-execute
                    smsSender.sendMsg();
                }//GEN-BEGIN:|66-getter|2|66-postInit
            });//GEN-END:|66-getter|2|66-postInit
        // write post-init user code here
        }//GEN-BEGIN:|66-getter|3|
        return sendingTask;
    }
    //</editor-fold>//GEN-END:|66-getter|3|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: lMainMenu ">//GEN-BEGIN:|54-getter|0|54-preInit
    /**
     * Returns an initiliazed instance of lMainMenu component.
     * @return the initialized component instance
     */
    public List getLMainMenu() {
        if (lMainMenu == null) {//GEN-END:|54-getter|0|54-preInit
            // write pre-init user code here
            lMainMenu = new List("Main Menu", Choice.IMPLICIT);//GEN-BEGIN:|54-getter|1|54-postInit
            lMainMenu.append("Balance", null);
            lMainMenu.append("Cheque Book", null);
            lMainMenu.append("Mini Statement", null);
            lMainMenu.append("Stop Cheque", null);
            lMainMenu.append("Cheque Status", null);
            lMainMenu.append("Exchange Rate", null);
            lMainMenu.append("Settings", null);
            lMainMenu.addCommand(getOkCommand1());
            lMainMenu.addCommand(getExitCommand1());
            lMainMenu.setCommandListener(this);
            lMainMenu.setSelectCommand(getOkCommand1());
            lMainMenu.setSelectedFlags(new boolean[] { false, false, false, false, false, false, false });//GEN-END:|54-getter|1|54-postInit
        // write post-init user code here
        }//GEN-BEGIN:|54-getter|2|
        return lMainMenu;
    }
    //</editor-fold>//GEN-END:|54-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: lMainMenuAction ">//GEN-BEGIN:|54-action|0|54-preAction
    /**
     * Performs an action assigned to the selected list element in the lMainMenu component.
     */
    public void lMainMenuAction() {//GEN-END:|54-action|0|54-preAction
        // enter pre-action user code here
        String __selectedString = getLMainMenu().getString(getLMainMenu().getSelectedIndex());//GEN-BEGIN:|54-action|1|59-preAction
        if (__selectedString != null) {
            if (__selectedString.equals("Balance")) {//GEN-END:|54-action|1|59-preAction
                // write pre-action user code here
                switchDisplayable(null, getFrmCBalance());//GEN-LINE:|54-action|2|59-postAction
            // write post-action user code here
            } else if (__selectedString.equals("Cheque Book")) {//GEN-LINE:|54-action|3|57-preAction
                // write pre-action user code here
                switchDisplayable(null, getFrmRCBook());//GEN-LINE:|54-action|4|57-postAction
            // write post-action user code here
            } else if (__selectedString.equals("Mini Statement")) {//GEN-LINE:|54-action|5|58-preAction
                // write pre-action user code here
                switchDisplayable(null, getFrmMStatement());//GEN-LINE:|54-action|6|58-postAction
            // write post-action user code here
            } else if (__selectedString.equals("Stop Cheque")) {//GEN-LINE:|54-action|7|175-preAction
                // write pre-action user code here
//GEN-LINE:|54-action|8|175-postAction
                // write post-action user code here
            } else if (__selectedString.equals("Cheque Status")) {//GEN-LINE:|54-action|9|60-preAction
                // write pre-action user code here
                switchDisplayable(null, getFrmCqStatus());//GEN-LINE:|54-action|10|60-postAction
            // write post-action user code here
            } else if (__selectedString.equals("Exchange Rate")) {//GEN-LINE:|54-action|11|176-preAction
                // write pre-action user code here
//GEN-LINE:|54-action|12|176-postAction
                // write post-action user code here
            } else if (__selectedString.equals("Settings")) {//GEN-LINE:|54-action|13|139-preAction
                // write pre-action user code here
//GEN-LINE:|54-action|14|139-postAction
            // write post-action user code here
            }//GEN-BEGIN:|54-action|15|54-postAction
        }//GEN-END:|54-action|15|54-postAction
    // enter post-action user code here
    }//GEN-BEGIN:|54-action|16|
    //</editor-fold>//GEN-END:|54-action|16|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: wsSending ">//GEN-BEGIN:|61-getter|0|61-preInit
    /**
     * Returns an initiliazed instance of wsSending component.
     * @return the initialized component instance
     */
    public WaitScreen getWsSending() {
        if (wsSending == null) {//GEN-END:|61-getter|0|61-preInit
            // write pre-init user code here
            wsSending = new WaitScreen(getDisplay());//GEN-BEGIN:|61-getter|1|61-postInit
            wsSending.setTitle("Sending Request");
            wsSending.setCommandListener(this);
            wsSending.setFullScreenMode(true);
            wsSending.setImage(getSendingImage());
            wsSending.setText("");//GEN-END:|61-getter|1|61-postInit
        // write post-init user code here
        }//GEN-BEGIN:|61-getter|2|
        return wsSending;
    }
    //</editor-fold>//GEN-END:|61-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: frmMStatement ">//GEN-BEGIN:|69-getter|0|69-preInit
    /**
     * Returns an initiliazed instance of frmMStatement component.
     * @return the initialized component instance
     */
    public Form getFrmMStatement() {
        if (frmMStatement == null) {//GEN-END:|69-getter|0|69-preInit
            // write pre-init user code here
            frmMStatement = new Form("Mini Statement", new Item[] { getTxtAFPin(), getTxtMSAccNumber() });//GEN-BEGIN:|69-getter|1|69-postInit
            frmMStatement.addCommand(getOkCommand3());
            frmMStatement.addCommand(getBackCommand2());
            frmMStatement.setCommandListener(this);//GEN-END:|69-getter|1|69-postInit
        // write post-init user code here
        }//GEN-BEGIN:|69-getter|2|
        return frmMStatement;
    }
    //</editor-fold>//GEN-END:|69-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: frmCBalance ">//GEN-BEGIN:|70-getter|0|70-preInit
    /**
     * Returns an initiliazed instance of frmCBalance component.
     * @return the initialized component instance
     */
    public Form getFrmCBalance() {
        if (frmCBalance == null) {//GEN-END:|70-getter|0|70-preInit
            // write pre-init user code here
            frmCBalance = new Form("Balance", new Item[] { getTxtCBPin(), getTxtCBAccNumber() });//GEN-BEGIN:|70-getter|1|70-postInit
            frmCBalance.addCommand(getOkCommand4());
            frmCBalance.addCommand(getBackCommand3());
            frmCBalance.setCommandListener(this);//GEN-END:|70-getter|1|70-postInit
        // write post-init user code here
        }//GEN-BEGIN:|70-getter|2|
        return frmCBalance;
    }
    //</editor-fold>//GEN-END:|70-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: frmCqStatus ">//GEN-BEGIN:|71-getter|0|71-preInit
    /**
     * Returns an initiliazed instance of frmCqStatus component.
     * @return the initialized component instance
     */
    public Form getFrmCqStatus() {
        if (frmCqStatus == null) {//GEN-END:|71-getter|0|71-preInit
            // write pre-init user code here
            frmCqStatus = new Form("Transfer Funds", new Item[] { getTxtTFPin(), getTxtCSAccNumber(), getTxtCSCqNumber() });//GEN-BEGIN:|71-getter|1|71-postInit
            frmCqStatus.addCommand(getBackCommand1());
            frmCqStatus.addCommand(getOkCommand6());
            frmCqStatus.setCommandListener(this);//GEN-END:|71-getter|1|71-postInit
        // write post-init user code here
        }//GEN-BEGIN:|71-getter|2|
        return frmCqStatus;
    }
    //</editor-fold>//GEN-END:|71-getter|2|
    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: frmRCBook ">//GEN-BEGIN:|81-getter|0|81-preInit
    /**
     * Returns an initiliazed instance of frmRCBook component.
     * @return the initialized component instance
     */
    public Form getFrmRCBook() {
        if (frmRCBook == null) {//GEN-END:|81-getter|0|81-preInit
            // write pre-init user code here
            frmRCBook = new Form("Request Check Book", new Item[] { getTxtRPin(), getTxtRCBAccNumber() });//GEN-BEGIN:|81-getter|1|81-postInit
            frmRCBook.addCommand(getBackCommand4());
            frmRCBook.addCommand(getOkCommand5());
            frmRCBook.setCommandListener(this);//GEN-END:|81-getter|1|81-postInit
        // write post-init user code here
        }//GEN-BEGIN:|81-getter|2|
        return frmRCBook;
    }
    //</editor-fold>//GEN-END:|81-getter|2|
    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: txtCBPin ">//GEN-BEGIN:|102-getter|0|102-preInit
    /**
     * Returns an initiliazed instance of txtCBPin component.
     * @return the initialized component instance
     */
    public TextField getTxtCBPin() {
        if (txtCBPin == null) {//GEN-END:|102-getter|0|102-preInit
            // write pre-init user code here
            txtCBPin = new TextField("Enter PIN", "", 4, TextField.NUMERIC | TextField.PASSWORD);//GEN-LINE:|102-getter|1|102-postInit
        // write post-init user code here
        }//GEN-BEGIN:|102-getter|2|
        return txtCBPin;
    }
    //</editor-fold>//GEN-END:|102-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: txtAFPin ">//GEN-BEGIN:|103-getter|0|103-preInit
    /**
     * Returns an initiliazed instance of txtAFPin component.
     * @return the initialized component instance
     */
    public TextField getTxtAFPin() {
        if (txtAFPin == null) {//GEN-END:|103-getter|0|103-preInit
            // write pre-init user code here
            txtAFPin = new TextField("Enter PIN", "", 4, TextField.NUMERIC | TextField.PASSWORD);//GEN-LINE:|103-getter|1|103-postInit
        // write post-init user code here
        }//GEN-BEGIN:|103-getter|2|
        return txtAFPin;
    }
    //</editor-fold>//GEN-END:|103-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: txtMSAccNumber ">//GEN-BEGIN:|104-getter|0|104-preInit
    /**
     * Returns an initiliazed instance of txtMSAccNumber component.
     * @return the initialized component instance
     */
    public TextField getTxtMSAccNumber() {
        if (txtMSAccNumber == null) {//GEN-END:|104-getter|0|104-preInit
            // write pre-init user code here
            txtMSAccNumber = new TextField("Enter Account Number", "", 15, TextField.NUMERIC);//GEN-LINE:|104-getter|1|104-postInit
        // write post-init user code here
        }//GEN-BEGIN:|104-getter|2|
        return txtMSAccNumber;
    }
    //</editor-fold>//GEN-END:|104-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand5 ">//GEN-BEGIN:|107-getter|0|107-preInit
    /**
     * Returns an initiliazed instance of backCommand5 component.
     * @return the initialized component instance
     */
    public Command getBackCommand5() {
        if (backCommand5 == null) {//GEN-END:|107-getter|0|107-preInit
            // write pre-init user code here
            backCommand5 = new Command("Back", Command.BACK, 0);//GEN-LINE:|107-getter|1|107-postInit
        // write post-init user code here
        }//GEN-BEGIN:|107-getter|2|
        return backCommand5;
    }
    //</editor-fold>//GEN-END:|107-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand6 ">//GEN-BEGIN:|109-getter|0|109-preInit
    /**
     * Returns an initiliazed instance of backCommand6 component.
     * @return the initialized component instance
     */
    public Command getBackCommand6() {
        if (backCommand6 == null) {//GEN-END:|109-getter|0|109-preInit
            // write pre-init user code here
            backCommand6 = new Command("Back", Command.BACK, 0);//GEN-LINE:|109-getter|1|109-postInit
        // write post-init user code here
        }//GEN-BEGIN:|109-getter|2|
        return backCommand6;
    }
    //</editor-fold>//GEN-END:|109-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: altSuccess ">//GEN-BEGIN:|105-getter|0|105-preInit
    /**
     * Returns an initiliazed instance of altSuccess component.
     * @return the initialized component instance
     */
    public Alert getAltSuccess() {
        if (altSuccess == null) {//GEN-END:|105-getter|0|105-preInit
            // write pre-init user code here
            altSuccess = new Alert("Successful", "Request Sent", null, AlertType.CONFIRMATION);//GEN-BEGIN:|105-getter|1|105-postInit
            altSuccess.addCommand(getBackCommand5());
            altSuccess.setCommandListener(this);
            altSuccess.setTimeout(Alert.FOREVER);//GEN-END:|105-getter|1|105-postInit
        // write post-init user code here
        }//GEN-BEGIN:|105-getter|2|
        return altSuccess;
    }
    //</editor-fold>//GEN-END:|105-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: altFailure ">//GEN-BEGIN:|106-getter|0|106-preInit
    /**
     * Returns an initiliazed instance of altFailure component.
     * @return the initialized component instance
     */
    public Alert getAltFailure() {
        if (altFailure == null) {//GEN-END:|106-getter|0|106-preInit
            // write pre-init user code here
            altFailure = new Alert("Failed", "Request Failed", null, AlertType.ERROR);//GEN-BEGIN:|106-getter|1|106-postInit
            altFailure.addCommand(getBackCommand6());
            altFailure.setCommandListener(this);
            altFailure.setTimeout(Alert.FOREVER);//GEN-END:|106-getter|1|106-postInit
        // write post-init user code here
        }//GEN-BEGIN:|106-getter|2|
        return altFailure;
    }
    //</editor-fold>//GEN-END:|106-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: sendingImage ">//GEN-BEGIN:|118-getter|0|118-preInit
    /**
     * Returns an initiliazed instance of sendingImage component.
     * @return the initialized component instance
     */
    public Image getSendingImage() {
        if (sendingImage == null) {//GEN-END:|118-getter|0|118-preInit
            // write pre-init user code here
            try {//GEN-BEGIN:|118-getter|1|118-@java.io.IOException
                sendingImage = Image.createImage("/im_loading.gif");
            } catch (java.io.IOException e) {//GEN-END:|118-getter|1|118-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|118-getter|2|118-postInit
        // write post-init user code here
        }//GEN-BEGIN:|118-getter|3|
        return sendingImage;
    }
    //</editor-fold>//GEN-END:|118-getter|3|


    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand5 ">//GEN-BEGIN:|123-getter|0|123-preInit
    /**
     * Returns an initiliazed instance of okCommand5 component.
     * @return the initialized component instance
     */
    public Command getOkCommand5() {
        if (okCommand5 == null) {//GEN-END:|123-getter|0|123-preInit
            // write pre-init user code here
            okCommand5 = new Command("OK", Command.OK, 0);//GEN-LINE:|123-getter|1|123-postInit
        // write post-init user code here
        }//GEN-BEGIN:|123-getter|2|
        return okCommand5;
    }
    //</editor-fold>//GEN-END:|123-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: txtRPin ">//GEN-BEGIN:|122-getter|0|122-preInit
    /**
     * Returns an initiliazed instance of txtRPin component.
     * @return the initialized component instance
     */
    public TextField getTxtRPin() {
        if (txtRPin == null) {//GEN-END:|122-getter|0|122-preInit
            // write pre-init user code here
            txtRPin = new TextField("Enter PIN", "", 4, TextField.NUMERIC | TextField.PASSWORD);//GEN-LINE:|122-getter|1|122-postInit
        // write post-init user code here
        }//GEN-BEGIN:|122-getter|2|
        return txtRPin;
    }
    //</editor-fold>//GEN-END:|122-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: txtTFPin ">//GEN-BEGIN:|126-getter|0|126-preInit
    /**
     * Returns an initiliazed instance of txtTFPin component.
     * @return the initialized component instance
     */
    public TextField getTxtTFPin() {
        if (txtTFPin == null) {//GEN-END:|126-getter|0|126-preInit
            // write pre-init user code here
            txtTFPin = new TextField("Enter PIN", "", 4, TextField.NUMERIC | TextField.PASSWORD);//GEN-LINE:|126-getter|1|126-postInit
        // write post-init user code here
        }//GEN-BEGIN:|126-getter|2|
        return txtTFPin;
    }
    //</editor-fold>//GEN-END:|126-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: txtCSAccNumber ">//GEN-BEGIN:|127-getter|0|127-preInit
    /**
     * Returns an initiliazed instance of txtCSAccNumber component.
     * @return the initialized component instance
     */
    public TextField getTxtCSAccNumber() {
        if (txtCSAccNumber == null) {//GEN-END:|127-getter|0|127-preInit
            // write pre-init user code here
            txtCSAccNumber = new TextField("Enter Account Number", "", 15, TextField.NUMERIC);//GEN-LINE:|127-getter|1|127-postInit
        // write post-init user code here
        }//GEN-BEGIN:|127-getter|2|
        return txtCSAccNumber;
    }
    //</editor-fold>//GEN-END:|127-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand6 ">//GEN-BEGIN:|128-getter|0|128-preInit
    /**
     * Returns an initiliazed instance of okCommand6 component.
     * @return the initialized component instance
     */
    public Command getOkCommand6() {
        if (okCommand6 == null) {//GEN-END:|128-getter|0|128-preInit
            // write pre-init user code here
            okCommand6 = new Command("OK", Command.OK, 0);//GEN-LINE:|128-getter|1|128-postInit
        // write post-init user code here
        }//GEN-BEGIN:|128-getter|2|
        return okCommand6;
    }
    //</editor-fold>//GEN-END:|128-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand7 ">//GEN-BEGIN:|131-getter|0|131-preInit
    /**
     * Returns an initiliazed instance of backCommand7 component.
     * @return the initialized component instance
     */
    public Command getBackCommand7() {
        if (backCommand7 == null) {//GEN-END:|131-getter|0|131-preInit
            // write pre-init user code here
            backCommand7 = new Command("Back", Command.BACK, 0);//GEN-LINE:|131-getter|1|131-postInit
        // write post-init user code here
        }//GEN-BEGIN:|131-getter|2|
        return backCommand7;
    }
    //</editor-fold>//GEN-END:|131-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: altIllegalAmount ">//GEN-BEGIN:|130-getter|0|130-preInit
    /**
     * Returns an initiliazed instance of altIllegalAmount component.
     * @return the initialized component instance
     */
    public Alert getAltIllegalAmount() {
        if (altIllegalAmount == null) {//GEN-END:|130-getter|0|130-preInit
            // write pre-init user code here
            altIllegalAmount = new Alert("Illegal Amount", "Illegal Amount. Please enter GHC 100 or less", null, AlertType.ERROR);//GEN-BEGIN:|130-getter|1|130-postInit
            altIllegalAmount.addCommand(getBackCommand7());
            altIllegalAmount.setCommandListener(this);
            altIllegalAmount.setTimeout(Alert.FOREVER);//GEN-END:|130-getter|1|130-postInit
        // write post-init user code here
        }//GEN-BEGIN:|130-getter|2|
        return altIllegalAmount;
    }
    //</editor-fold>//GEN-END:|130-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: txtCSCqNumber ">//GEN-BEGIN:|135-getter|0|135-preInit
    /**
     * Returns an initiliazed instance of txtCSCqNumber component.
     * @return the initialized component instance
     */
    public TextField getTxtCSCqNumber() {
        if (txtCSCqNumber == null) {//GEN-END:|135-getter|0|135-preInit
            // write pre-init user code here
            txtCSCqNumber = new TextField("Enter Cheque Number", "", 20, TextField.NUMERIC);//GEN-LINE:|135-getter|1|135-postInit
        // write post-init user code here
        }//GEN-BEGIN:|135-getter|2|
        return txtCSCqNumber;
    }
    //</editor-fold>//GEN-END:|135-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand ">//GEN-BEGIN:|137-getter|0|137-preInit
    /**
     * Returns an initiliazed instance of backCommand component.
     * @return the initialized component instance
     */
    public Command getBackCommand() {
        if (backCommand == null) {//GEN-END:|137-getter|0|137-preInit
            // write pre-init user code here
            backCommand = new Command("Back", Command.BACK, 0);//GEN-LINE:|137-getter|1|137-postInit
        // write post-init user code here
        }//GEN-BEGIN:|137-getter|2|
        return backCommand;
    }
    //</editor-fold>//GEN-END:|137-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: altEmptyField ">//GEN-BEGIN:|136-getter|0|136-preInit
    /**
     * Returns an initiliazed instance of altEmptyField component.
     * @return the initialized component instance
     */
    public Alert getAltEmptyField() {
        if (altEmptyField == null) {//GEN-END:|136-getter|0|136-preInit
            // write pre-init user code here
            altEmptyField = new Alert("Empty Field", "All fields are required. Please try again", null, AlertType.ERROR);//GEN-BEGIN:|136-getter|1|136-postInit
            altEmptyField.addCommand(getBackCommand());
            altEmptyField.setCommandListener(this);
            altEmptyField.setTimeout(Alert.FOREVER);//GEN-END:|136-getter|1|136-postInit
        // write post-init user code here
        }//GEN-BEGIN:|136-getter|2|
        return altEmptyField;
    }
    //</editor-fold>//GEN-END:|136-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand ">//GEN-BEGIN:|147-getter|0|147-preInit
    /**
     * Returns an initiliazed instance of okCommand component.
     * @return the initialized component instance
     */
    public Command getOkCommand() {
        if (okCommand == null) {//GEN-END:|147-getter|0|147-preInit
            // write pre-init user code here
            okCommand = new Command("OK", Command.OK, 0);//GEN-LINE:|147-getter|1|147-postInit
        // write post-init user code here
        }//GEN-BEGIN:|147-getter|2|
        return okCommand;
    }
    //</editor-fold>//GEN-END:|147-getter|2|
    
    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand8 ">//GEN-BEGIN:|149-getter|0|149-preInit
    /**
     * Returns an initiliazed instance of backCommand8 component.
     * @return the initialized component instance
     */
    public Command getBackCommand8() {
        if (backCommand8 == null) {//GEN-END:|149-getter|0|149-preInit
            // write pre-init user code here
            backCommand8 = new Command("Back", Command.BACK, 0);//GEN-LINE:|149-getter|1|149-postInit
        // write post-init user code here
        }//GEN-BEGIN:|149-getter|2|
        return backCommand8;
    }
    //</editor-fold>//GEN-END:|149-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: frmSCode ">//GEN-BEGIN:|140-getter|0|140-preInit
    /**
     * Returns an initiliazed instance of frmSCode component.
     * @return the initialized component instance
     */
    public Form getFrmSCode() {
        if (frmSCode == null) {//GEN-END:|140-getter|0|140-preInit
            // write pre-init user code here
            frmSCode = new Form("Short Code", new Item[] { getShortCode() });//GEN-BEGIN:|140-getter|1|140-postInit
            frmSCode.addCommand(getOkCommand());
            frmSCode.addCommand(getBackCommand8());
            frmSCode.setCommandListener(this);//GEN-END:|140-getter|1|140-postInit

            ((TextField)frmSCode.get(0)).setString(shortcode);
        }//GEN-BEGIN:|140-getter|2|
        return frmSCode;
    }
    //</editor-fold>//GEN-END:|140-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: ShortCode ">//GEN-BEGIN:|151-getter|0|151-preInit
    /**
     * Returns an initiliazed instance of ShortCode component.
     * @return the initialized component instance
     */
    public TextField getShortCode() {
        if (ShortCode == null) {//GEN-END:|151-getter|0|151-preInit
            // write pre-init user code here
            ShortCode = new TextField("Short Code", "1406", 16, TextField.NUMERIC);//GEN-LINE:|151-getter|1|151-postInit
        // write post-init user code here
        }//GEN-BEGIN:|151-getter|2|
        return ShortCode;
    }
    //</editor-fold>//GEN-END:|151-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: task ">//GEN-BEGIN:|156-getter|0|156-preInit
    /**
     * Returns an initiliazed instance of task component.
     * @return the initialized component instance
     */
    public SimpleCancellableTask getTask() {
        if (task == null) {//GEN-END:|156-getter|0|156-preInit
            // write pre-init user code here
            task = new SimpleCancellableTask();//GEN-BEGIN:|156-getter|1|156-execute
            task.setExecutable(new org.netbeans.microedition.util.Executable() {
                public void execute() throws Exception {//GEN-END:|156-getter|1|156-execute

                }//GEN-BEGIN:|156-getter|2|156-postInit
            });//GEN-END:|156-getter|2|156-postInit
        // write post-init user code here
        }//GEN-BEGIN:|156-getter|3|
        return task;
    }
    //</editor-fold>//GEN-END:|156-getter|3|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: savingTask ">//GEN-BEGIN:|157-getter|0|157-preInit
    /**
     * Returns an initiliazed instance of savingTask component.
     * @return the initialized component instance
     */
    public SimpleCancellableTask getSavingTask() {
        if (savingTask == null) {//GEN-END:|157-getter|0|157-preInit
            // write pre-init user code here
            savingTask = new SimpleCancellableTask();//GEN-BEGIN:|157-getter|1|157-execute
            savingTask.setExecutable(new org.netbeans.microedition.util.Executable() {
                public void execute() throws Exception {//GEN-END:|157-getter|1|157-execute
                    mmbl.saveSettings();
                }//GEN-BEGIN:|157-getter|2|157-postInit
            });//GEN-END:|157-getter|2|157-postInit
        // write post-init user code here
        }//GEN-BEGIN:|157-getter|3|
        return savingTask;
    }
    //</editor-fold>//GEN-END:|157-getter|3|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: wsSaving ">//GEN-BEGIN:|153-getter|0|153-preInit
    /**
     * Returns an initiliazed instance of wsSaving component.
     * @return the initialized component instance
     */
    public WaitScreen getWsSaving() {
        if (wsSaving == null) {//GEN-END:|153-getter|0|153-preInit
            // write pre-init user code here
            wsSaving = new WaitScreen(getDisplay());//GEN-BEGIN:|153-getter|1|153-postInit
            wsSaving.setTitle("");
            wsSaving.setCommandListener(this);
            wsSaving.setFullScreenMode(true);
            wsSaving.setText("Saving ");
            wsSaving.setTask(getSavingTask());//GEN-END:|153-getter|1|153-postInit
        // write post-init user code here
        }//GEN-BEGIN:|153-getter|2|
        return wsSaving;
    }
    //</editor-fold>//GEN-END:|153-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand9 ">//GEN-BEGIN:|162-getter|0|162-preInit
    /**
     * Returns an initiliazed instance of backCommand9 component.
     * @return the initialized component instance
     */
    public Command getBackCommand9() {
        if (backCommand9 == null) {//GEN-END:|162-getter|0|162-preInit
            // write pre-init user code here
            backCommand9 = new Command("Back", Command.BACK, 0);//GEN-LINE:|162-getter|1|162-postInit
        // write post-init user code here
        }//GEN-BEGIN:|162-getter|2|
        return backCommand9;
    }
    //</editor-fold>//GEN-END:|162-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand10 ">//GEN-BEGIN:|166-getter|0|166-preInit
    /**
     * Returns an initiliazed instance of backCommand10 component.
     * @return the initialized component instance
     */
    public Command getBackCommand10() {
        if (backCommand10 == null) {//GEN-END:|166-getter|0|166-preInit
            // write pre-init user code here
            backCommand10 = new Command("Back", Command.BACK, 0);//GEN-LINE:|166-getter|1|166-postInit
        // write post-init user code here
        }//GEN-BEGIN:|166-getter|2|
        return backCommand10;
    }
    //</editor-fold>//GEN-END:|166-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: altSFail ">//GEN-BEGIN:|161-getter|0|161-preInit
    /**
     * Returns an initiliazed instance of altSFail component.
     * @return the initialized component instance
     */
    public Alert getAltSFail() {
        if (altSFail == null) {//GEN-END:|161-getter|0|161-preInit
            // write pre-init user code here
            altSFail = new Alert("", "Save Failed", null, null);//GEN-BEGIN:|161-getter|1|161-postInit
            altSFail.addCommand(getBackCommand9());
            altSFail.setCommandListener(this);
            altSFail.setTimeout(Alert.FOREVER);//GEN-END:|161-getter|1|161-postInit
        // write post-init user code here
        }//GEN-BEGIN:|161-getter|2|
        return altSFail;
    }
    //</editor-fold>//GEN-END:|161-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: altSSuccess ">//GEN-BEGIN:|165-getter|0|165-preInit
    /**
     * Returns an initiliazed instance of altSSuccess component.
     * @return the initialized component instance
     */
    public Alert getAltSSuccess() {
        if (altSSuccess == null) {//GEN-END:|165-getter|0|165-preInit
            // write pre-init user code here
            altSSuccess = new Alert("Saved", "Save Succesful", null, null);//GEN-BEGIN:|165-getter|1|165-postInit
            altSSuccess.addCommand(getBackCommand10());
            altSSuccess.setCommandListener(this);
            altSSuccess.setTimeout(Alert.FOREVER);//GEN-END:|165-getter|1|165-postInit
        // write post-init user code here
        }//GEN-BEGIN:|165-getter|2|
        return altSSuccess;
    }
    //</editor-fold>//GEN-END:|165-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand3 ">//GEN-BEGIN:|84-getter|0|84-preInit
    /**
     * Returns an initiliazed instance of okCommand3 component.
     * @return the initialized component instance
     */
    public Command getOkCommand3() {
        if (okCommand3 == null) {//GEN-END:|84-getter|0|84-preInit
            // write pre-init user code here
            okCommand3 = new Command("OK", Command.OK, 0);//GEN-LINE:|84-getter|1|84-postInit
        // write post-init user code here
        }//GEN-BEGIN:|84-getter|2|
        return okCommand3;
    }
    //</editor-fold>//GEN-END:|84-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: btnCashbackOK ">//GEN-BEGIN:|172-getter|0|172-preInit
    /**
     * Returns an initiliazed instance of btnCashbackOK component.
     * @return the initialized component instance
     */
    public Command getBtnCashbackOK() {
        if (btnCashbackOK == null) {//GEN-END:|172-getter|0|172-preInit
            // write pre-init user code here
            btnCashbackOK = new Command("OK", Command.OK, 0);//GEN-LINE:|172-getter|1|172-postInit
            // write post-init user code here
        }//GEN-BEGIN:|172-getter|2|
        return btnCashbackOK;
    }
    //</editor-fold>//GEN-END:|172-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: btnCashbackBack ">//GEN-BEGIN:|174-getter|0|174-preInit
    /**
     * Returns an initiliazed instance of btnCashbackBack component.
     * @return the initialized component instance
     */
    public Command getBtnCashbackBack() {
        if (btnCashbackBack == null) {//GEN-END:|174-getter|0|174-preInit
            // write pre-init user code here
            btnCashbackBack = new Command("Back", Command.BACK, 0);//GEN-LINE:|174-getter|1|174-postInit
            // write post-init user code here
        }//GEN-BEGIN:|174-getter|2|
        return btnCashbackBack;
    }
    //</editor-fold>//GEN-END:|174-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: txtCBAccNumber ">//GEN-BEGIN:|195-getter|0|195-preInit
    /**
     * Returns an initiliazed instance of txtCBAccNumber component.
     * @return the initialized component instance
     */
    public TextField getTxtCBAccNumber() {
        if (txtCBAccNumber == null) {//GEN-END:|195-getter|0|195-preInit
            // write pre-init user code here
            txtCBAccNumber = new TextField("Enter Account Number", "", 15, TextField.NUMERIC);//GEN-BEGIN:|195-getter|1|195-postInit
            txtCBAccNumber.setLayout(ImageItem.LAYOUT_DEFAULT);//GEN-END:|195-getter|1|195-postInit
            // write post-init user code here
        }//GEN-BEGIN:|195-getter|2|
        return txtCBAccNumber;
    }
    //</editor-fold>//GEN-END:|195-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: txtRCBAccNumber ">//GEN-BEGIN:|196-getter|0|196-preInit
    /**
     * Returns an initiliazed instance of txtRCBAccNumber component.
     * @return the initialized component instance
     */
    public TextField getTxtRCBAccNumber() {
        if (txtRCBAccNumber == null) {//GEN-END:|196-getter|0|196-preInit
            // write pre-init user code here
            txtRCBAccNumber = new TextField("Enter Account Number", "", 15, TextField.NUMERIC);//GEN-LINE:|196-getter|1|196-postInit
            // write post-init user code here
        }//GEN-BEGIN:|196-getter|2|
        return txtRCBAccNumber;
    }
    //</editor-fold>//GEN-END:|196-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand2 ">//GEN-BEGIN:|199-getter|0|199-preInit
    /**
     * Returns an initiliazed instance of okCommand2 component.
     * @return the initialized component instance
     */
    public Command getOkCommand2() {
        if (okCommand2 == null) {//GEN-END:|199-getter|0|199-preInit
            // write pre-init user code here
            okCommand2 = new Command("OK", Command.OK, 0);//GEN-LINE:|199-getter|1|199-postInit
            // write post-init user code here
        }//GEN-BEGIN:|199-getter|2|
        return okCommand2;
    }
    //</editor-fold>//GEN-END:|199-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand11 ">//GEN-BEGIN:|201-getter|0|201-preInit
    /**
     * Returns an initiliazed instance of backCommand11 component.
     * @return the initialized component instance
     */
    public Command getBackCommand11() {
        if (backCommand11 == null) {//GEN-END:|201-getter|0|201-preInit
            // write pre-init user code here
            backCommand11 = new Command("Back", Command.BACK, 0);//GEN-LINE:|201-getter|1|201-postInit
            // write post-init user code here
        }//GEN-BEGIN:|201-getter|2|
        return backCommand11;
    }
    //</editor-fold>//GEN-END:|201-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: frmSCheque ">//GEN-BEGIN:|209-getter|0|209-preInit
    /**
     * Returns an initiliazed instance of frmSCheque component.
     * @return the initialized component instance
     */
    public Form getFrmSCheque() {
        if (frmSCheque == null) {//GEN-END:|209-getter|0|209-preInit
            // write pre-init user code here
            frmSCheque = new Form("form");//GEN-LINE:|209-getter|1|209-postInit
            // write post-init user code here
        }//GEN-BEGIN:|209-getter|2|
        return frmSCheque;
    }
    //</editor-fold>//GEN-END:|209-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand7 ">//GEN-BEGIN:|210-getter|0|210-preInit
    /**
     * Returns an initiliazed instance of okCommand7 component.
     * @return the initialized component instance
     */
    public Command getOkCommand7() {
        if (okCommand7 == null) {//GEN-END:|210-getter|0|210-preInit
            // write pre-init user code here
            okCommand7 = new Command("Ok", Command.OK, 0);//GEN-LINE:|210-getter|1|210-postInit
            // write post-init user code here
        }//GEN-BEGIN:|210-getter|2|
        return okCommand7;
    }
    //</editor-fold>//GEN-END:|210-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: image1 ">//GEN-BEGIN:|212-getter|0|212-preInit
    /**
     * Returns an initiliazed instance of image1 component.
     * @return the initialized component instance
     */
    public Image getImage1() {
        if (image1 == null) {//GEN-END:|212-getter|0|212-preInit
            // write pre-init user code here
            try {//GEN-BEGIN:|212-getter|1|212-@java.io.IOException
                image1 = Image.createImage("/FidelityMobile5.png");
            } catch (java.io.IOException e) {//GEN-END:|212-getter|1|212-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|212-getter|2|212-postInit
            // write post-init user code here
        }//GEN-BEGIN:|212-getter|3|
        return image1;
    }
    //</editor-fold>//GEN-END:|212-getter|3|











    /**
     * Returns a display instance.
     * @return the display instance.
     */
    public Display getDisplay() {
        return Display.getDisplay(this);
    }

    /**
     * Exits MIDlet.
     */
    public void exitMIDlet() {
        switchDisplayable(null, null);
        destroyApp(true);
        notifyDestroyed();
    }

    /**
     * Called when MIDlet is started.
     * Checks whether the MIDlet have been already started and initialize/starts or resumes the MIDlet.
     */
    public void startApp() {
        if (midletPaused) {
            resumeMIDlet();
        } else {
            initialize();
            startMIDlet();
        }
        midletPaused = false;
    }

    /**
     * Called when MIDlet is paused.
     */
    public void pauseApp() {
        midletPaused = true;
    }

    /**
     * Called to signal the MIDlet to terminate.
     * @param unconditional if true, then the MIDlet has to be unconditionally terminated and all resources has to be released.
     */
    public void destroyApp(boolean unconditional) {
    }

    /**
     * @return the splashScreen
     */
    public SplashScreen getSplashScreen() {
        return splashScreen;
    }

    /**
     * @param splashScreen the splashScreen to set
     */
    public void setSplashScreen(SplashScreen splashScreen) {
        this.splashScreen = splashScreen;
    }

    /**
     * @param lMainMenu the lMainMenu to set
     */
    public void setLMainMenu(List lMainMenu) {
        this.lMainMenu = lMainMenu;
    }

    /**
     * @param wsSending the wsSending to set
     */
    public void setWsSending(WaitScreen wsSending) {
        this.wsSending = wsSending;
    }

    /**
     * @param frmAFunds the frmAFunds to set
     */
    public void setFrmMStatement(Form frmAFunds) {
        this.frmMStatement = frmAFunds;
    }

    /**
     * @param txtAFPin the txtAFPin to set
     */
    public void setTxtAFPin(TextField txtAFPin) {
        this.txtAFPin = txtAFPin;
    }

    /**
     * @param txtAFVoucher the txtAFVoucher to set
     */
    public void setTxtMSAccNumber(TextField txtAFVoucher) {
        this.txtMSAccNumber = txtAFVoucher;
    }

    /**
     * @param frmCBalance the frmCBalance to set
     */
    public void setFrmCBalance(Form frmCBalance) {
        this.frmCBalance = frmCBalance;
    }

    /**
     * @param txtCBPin the txtCBPin to set
     */
    public void setTxtCBPin(TextField txtCBPin) {
        this.txtCBPin = txtCBPin;
    }

    /**
     * @param frmTFunds the frmTFunds to set
     */
    public void setFrmCqStatus(Form frmTFunds) {
        this.frmCqStatus = frmTFunds;
    }

    /**
     * @param txtTFAmount the txtTFAmount to set
     */
    public void setTxtCSCqNumber(TextField txtTFAmount) {
        this.txtCSCqNumber = txtTFAmount;
    }

    /**
     * @param txtTFPin the txtTFPin to set
     */
    public void setTxtTFPin(TextField txtTFPin) {
        this.txtTFPin = txtTFPin;
    }

    /**
     * @param txtTFRNumber the txtTFRNumber to set
     */
    public void setTxtCSAccNumber(TextField txtTFRNumber) {
        this.txtCSAccNumber = txtTFRNumber;
    }

    /**
     * @param frmRegister the frmRegister to set
     */
    public void setFrmRCBook(Form frmRegister) {
        this.frmRCBook = frmRegister;
    }

    /**
     * @param txtRPin the txtRPin to set
     */
    public void setTxtRPin(TextField txtRPin) {
        this.txtRPin = txtRPin;
    }

    /**
     * @param altFailure the altFailure to set
     */
    public void setAltFailure(Alert altFailure) {
        this.altFailure = altFailure;
    }

    /**
     * @param altSuccess the altSuccess to set
     */
    public void setAltSuccess(Alert altSuccess) {
        this.altSuccess = altSuccess;
    }

    /**
     * @param altEmptyField the altEmptyField to set
     */
    public void setAltEmptyField(Alert altEmptyField) {
        this.altEmptyField = altEmptyField;
    }

    /**
     * @param frmSettings the frmSettings to set
     */
    public void setFrmSCode(Form frmSettings) {
        this.frmSCode = frmSettings;
    }

    /**
     * @param ShortCode the ShortCode to set
     */
    public void setShortCode(TextField ShortCode) {
        this.ShortCode = ShortCode;
    }

    /**
     * @param altIllegalAmount the altIllegalAmount to set
     */
    public void setAltIllegalAmount(Alert altIllegalAmount) {
        this.altIllegalAmount = altIllegalAmount;
    }

    /**
     * @param wsSaving the wsSaving to set
     */
    public void setWsSaving(WaitScreen wsSaving) {
        this.wsSaving = wsSaving;
    }

    /**
     * @param altSFail the altSFail to set
     */
    public void setAltSFail(Alert altSFail) {
        this.altSFail = altSFail;
    }

    /**
     * @param altSSuccess the altSSuccess to set
     */
    public void setAltSSuccess(Alert altSSuccess) {
        this.altSSuccess = altSSuccess;
    }

    /**
     * @param okCommand1 the okCommand1 to set
     */
    public void setOkCommand1(Command okCommand1) {
        this.okCommand1 = okCommand1;
    }

    /**
     * @param exitCommand1 the exitCommand1 to set
     */
    public void setExitCommand1(Command exitCommand1) {
        this.exitCommand1 = exitCommand1;
    }

    /**
     * @param backCommand1 the backCommand1 to set
     */
    public void setBackCommand1(Command backCommand1) {
        this.backCommand1 = backCommand1;
    }

    /**
     * @param okCommand3 the okCommand3 to set
     */
    public void setOkCommand3(Command okCommand3) {
        this.okCommand3 = okCommand3;
    }

    /**
     * @param backCommand2 the backCommand2 to set
     */
    public void setBackCommand2(Command backCommand2) {
        this.backCommand2 = backCommand2;
    }

    /**
     * @param backCommand3 the backCommand3 to set
     */
    public void setBackCommand3(Command backCommand3) {
        this.backCommand3 = backCommand3;
    }

    /**
     * @param okCommand4 the okCommand4 to set
     */
    public void setOkCommand4(Command okCommand4) {
        this.okCommand4 = okCommand4;
    }

    /**
     * @param backCommand4 the backCommand4 to set
     */
    public void setBackCommand4(Command backCommand4) {
        this.backCommand4 = backCommand4;
    }

    /**
     * @param backCommand6 the backCommand6 to set
     */
    public void setBackCommand6(Command backCommand6) {
        this.backCommand6 = backCommand6;
    }

    /**
     * @param backCommand5 the backCommand5 to set
     */
    public void setBackCommand5(Command backCommand5) {
        this.backCommand5 = backCommand5;
    }

    /**
     * @param okCommand5 the okCommand5 to set
     */
    public void setOkCommand5(Command okCommand5) {
        this.okCommand5 = okCommand5;
    }

    /**
     * @param backCommand the backCommand to set
     */
    public void setBackCommand(Command backCommand) {
        this.backCommand = backCommand;
    }

    /**
     * @param okCommand6 the okCommand6 to set
     */
    public void setOkCommand6(Command okCommand6) {
        this.okCommand6 = okCommand6;
    }

    /**
     * @param backCommand7 the backCommand7 to set
     */
    public void setBackCommand7(Command backCommand7) {
        this.backCommand7 = backCommand7;
    }

    /**
     * @param okCommand the okCommand to set
     */
    public void setOkCommand(Command okCommand) {
        this.okCommand = okCommand;
    }

    /**
     * @param backCommand8 the backCommand8 to set
     */
    public void setBackCommand8(Command backCommand8) {
        this.backCommand8 = backCommand8;
    }

    /**
     * @param backCommand9 the backCommand9 to set
     */
    public void setBackCommand9(Command backCommand9) {
        this.backCommand9 = backCommand9;
    }

    /**
     * @param backCommand10 the backCommand10 to set
     */
    public void setBackCommand10(Command backCommand10) {
        this.backCommand10 = backCommand10;
    }

    /**
     * @param splashFont the splashFont to set
     */
    public void setSplashFont(Font splashFont) {
        this.splashFont = splashFont;
    }

    /**
     * @param sendingTask the sendingTask to set
     */
    public void setSendingTask(SimpleCancellableTask sendingTask) {
        this.sendingTask = sendingTask;
    }

    /**
     * @param sendingImage the sendingImage to set
     */
    public void setSendingImage(Image sendingImage) {
        this.sendingImage = sendingImage;
    }

    /**
     * @param image1 the image1 to set
     */
    public void setImage1(Image image1) {
        this.image1 = image1;
    }

    /**
     * @param task the task to set
     */
    public void setTask(SimpleCancellableTask task) {
        this.task = task;
    }

    /**
     * @param savingTask the savingTask to set
     */
    public void setSavingTask(SimpleCancellableTask savingTask) {
        this.savingTask = savingTask;
    }
}
