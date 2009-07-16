/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fidelity.banking.mobile.interfaces;

import com.fidelity.banking.mobile.model.Settings;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

/**
 * @author administrator
 */
public class MainApp extends MIDlet implements CommandListener {

    private boolean midletPaused = false;
    public static Settings settings;
    MainAppManager appManager;

    //<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private java.util.Hashtable __previousDisplayables = new java.util.Hashtable();
    private List lMainMenu;
    private List lCRMenu;
    private List lSettingsMenu;
    private Form frmShortcode;
    private TextField txtFrmShortCode;
    private Form frmBalance;
    private TextField txtFrmBalance;
    private Form frmChooseAccount;
    private List lHelp;
    private Form frmHelpContents;
    private StringItem stringItem;
    private Form frmHelpAbout;
    private StringItem stringItem1;
    private List lAccounts;
    private Form frmEditAccount;
    private TextField textField;
    private TextField textField1;
    private Command okcSettings;
    private Command bkcSettings;
    private Command okcMainMenu;
    private Command bkcMainMenu;
    private Command okcCRMenu;
    private Command bkcCRMenu;
    private Command okcFrmShortCode;
    private Command bkcFrmShortCode;
    private Command okcFrmBalance;
    private Command bkcFrmBalance;
    private Command okcFrmChooseAccount;
    private Command bkcFrmChooseAccount;
    private Command okCommand;
    private Command cancelCommand;
    private Command backCommand1;
    private Command extcMainMenu;
    private Command bkcFrmHelpContents;
    private Command bkcFrmHelpAbout;
    private Command stopCommand;
    private Command bkcLAccountsOptions;
    private Command okcLAccountsOptions;
    private Command screenCommand;
    private Command okcLAccounts;
    private Command bkcLAccounts;
    private Command okcFrmEditAccount;
    private Command ccFrmEditAccount;
    private Ticker tkAdverts;
    //</editor-fold>//GEN-END:|fields|0|

    /**
     * The MainApp constructor.
     */
    public MainApp() {
    }

    //<editor-fold defaultstate="collapsed" desc=" Generated Methods ">//GEN-BEGIN:|methods|0|
    /**
     * Switches a display to previous displayable of the current displayable.
     * The <code>display</code> instance is obtain from the <code>getDisplay</code> method.
     */
    private void switchToPreviousDisplayable() {
        Displayable __currentDisplayable = getDisplay().getCurrent();
        if (__currentDisplayable != null) {
            Displayable __nextDisplayable = (Displayable) __previousDisplayables.get(__currentDisplayable);
            if (__nextDisplayable != null) {
                switchDisplayable(null, __nextDisplayable);
            }
        }
    }
    //</editor-fold>//GEN-END:|methods|0|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: initialize ">//GEN-BEGIN:|0-initialize|0|0-preInitialize
    /**
     * Initilizes the application.
     * It is called only once when the MIDlet is started. The method is called before the <code>startMIDlet</code> method.
     */
    private void initialize() {//GEN-END:|0-initialize|0|0-preInitialize
        // write pre-initialize user code here
//GEN-LINE:|0-initialize|1|0-postInitialize
        // write post-initialize user code here
    }//GEN-BEGIN:|0-initialize|2|
    //</editor-fold>//GEN-END:|0-initialize|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: startMIDlet ">//GEN-BEGIN:|3-startMIDlet|0|3-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Started point.
     */
    public void startMIDlet() {//GEN-END:|3-startMIDlet|0|3-preAction
        // write pre-action user code here
//GEN-LINE:|3-startMIDlet|1|3-postAction
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
        Displayable __currentDisplayable = display.getCurrent();
        if (__currentDisplayable != null  &&  nextDisplayable != null) {
            __previousDisplayables.put(nextDisplayable, __currentDisplayable);
        }
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
        if (displayable == frmBalance) {//GEN-BEGIN:|7-commandAction|1|65-preAction
            if (command == bkcFrmBalance) {//GEN-END:|7-commandAction|1|65-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|2|65-postAction
                // write post-action user code here
            } else if (command == okcFrmBalance) {//GEN-LINE:|7-commandAction|3|63-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|4|63-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|5|71-preAction
        } else if (displayable == frmChooseAccount) {
            if (command == bkcFrmChooseAccount) {//GEN-END:|7-commandAction|5|71-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|6|71-postAction
                // write post-action user code here
            } else if (command == okcFrmChooseAccount) {//GEN-LINE:|7-commandAction|7|69-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|8|69-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|9|135-preAction
        } else if (displayable == frmEditAccount) {
            if (command == ccFrmEditAccount) {//GEN-END:|7-commandAction|9|135-preAction
                // write pre-action user code here
                switchToPreviousDisplayable();//GEN-LINE:|7-commandAction|10|135-postAction
                // write post-action user code here
            } else if (command == okcFrmEditAccount) {//GEN-LINE:|7-commandAction|11|133-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|12|133-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|13|99-preAction
        } else if (displayable == frmHelpAbout) {
            if (command == bkcFrmHelpAbout) {//GEN-END:|7-commandAction|13|99-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|14|99-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|15|94-preAction
        } else if (displayable == frmHelpContents) {
            if (command == bkcFrmHelpContents) {//GEN-END:|7-commandAction|15|94-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|16|94-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|17|58-preAction
        } else if (displayable == frmShortcode) {
            if (command == bkcFrmShortCode) {//GEN-END:|7-commandAction|17|58-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|18|58-postAction
                // write post-action user code here
            } else if (command == okcFrmShortCode) {//GEN-LINE:|7-commandAction|19|56-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|20|56-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|21|103-preAction
        } else if (displayable == lAccounts) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|21|103-preAction
                // write pre-action user code here
                lAccountsAction();//GEN-LINE:|7-commandAction|22|103-postAction
                // write post-action user code here
            } else if (command == bkcLAccounts) {//GEN-LINE:|7-commandAction|23|128-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|24|128-postAction
                // write post-action user code here
            } else if (command == okcLAccounts) {//GEN-LINE:|7-commandAction|25|126-preAction
                // write pre-action user code here
                switchDisplayable(null, getFrmEditAccount());//GEN-LINE:|7-commandAction|26|126-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|27|29-preAction
        } else if (displayable == lCRMenu) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|27|29-preAction
                // write pre-action user code here
                lCRMenuAction();//GEN-LINE:|7-commandAction|28|29-postAction
                // write post-action user code here
            } else if (command == bkcCRMenu) {//GEN-LINE:|7-commandAction|29|53-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|30|53-postAction
                // write post-action user code here
            } else if (command == okcCRMenu) {//GEN-LINE:|7-commandAction|31|51-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|32|51-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|33|87-preAction
        } else if (displayable == lHelp) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|33|87-preAction
                // write pre-action user code here
                lHelpAction();//GEN-LINE:|7-commandAction|34|87-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|35|20-preAction
        } else if (displayable == lMainMenu) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|35|20-preAction
                // write pre-action user code here
                lMainMenuAction();//GEN-LINE:|7-commandAction|36|20-postAction
                // write post-action user code here
            } else if (command == extcMainMenu) {//GEN-LINE:|7-commandAction|37|83-preAction
                // write pre-action user code here
                exitMIDlet();//GEN-LINE:|7-commandAction|38|83-postAction
                // write post-action user code here
            } else if (command == okcMainMenu) {//GEN-LINE:|7-commandAction|39|47-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|40|47-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|41|36-preAction
        } else if (displayable == lSettingsMenu) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|41|36-preAction
                // write pre-action user code here
                lSettingsMenuAction();//GEN-LINE:|7-commandAction|42|36-postAction
                // write post-action user code here
            } else if (command == bkcSettings) {//GEN-LINE:|7-commandAction|43|45-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|44|45-postAction
                // write post-action user code here
            } else if (command == okcSettings) {//GEN-LINE:|7-commandAction|45|43-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|46|43-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|47|7-postCommandAction
        }//GEN-END:|7-commandAction|47|7-postCommandAction
        // write post-action user code here
    }//GEN-BEGIN:|7-commandAction|48|
    //</editor-fold>//GEN-END:|7-commandAction|48|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: lMainMenu ">//GEN-BEGIN:|18-getter|0|18-preInit
    /**
     * Returns an initiliazed instance of lMainMenu component.
     * @return the initialized component instance
     */
    public List getLMainMenu() {
        if (lMainMenu == null) {//GEN-END:|18-getter|0|18-preInit
            // write pre-init user code here
            lMainMenu = new List("Main Menu", Choice.IMPLICIT);//GEN-BEGIN:|18-getter|1|18-postInit
            lMainMenu.append("Balance", null);
            lMainMenu.append("Mini Statement", null);
            lMainMenu.append("Cheque Requests", null);
            lMainMenu.append("Exchange Rate", null);
            lMainMenu.append("Settings", null);
            lMainMenu.append("Help", null);
            lMainMenu.setTicker(getTkAdverts());
            lMainMenu.addCommand(getOkcMainMenu());
            lMainMenu.addCommand(getExtcMainMenu());
            lMainMenu.setCommandListener(this);
            lMainMenu.setSelectedFlags(new boolean[] { false, false, false, false, false, false });//GEN-END:|18-getter|1|18-postInit
            // write post-init user code here
        }//GEN-BEGIN:|18-getter|2|
        return lMainMenu;
    }
    //</editor-fold>//GEN-END:|18-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: lMainMenuAction ">//GEN-BEGIN:|18-action|0|18-preAction
    /**
     * Performs an action assigned to the selected list element in the lMainMenu component.
     */
    public void lMainMenuAction() {//GEN-END:|18-action|0|18-preAction
        // enter pre-action user code here
        String __selectedString = getLMainMenu().getString(getLMainMenu().getSelectedIndex());//GEN-BEGIN:|18-action|1|22-preAction
        if (__selectedString != null) {
            if (__selectedString.equals("Balance")) {//GEN-END:|18-action|1|22-preAction
                // write pre-action user code here
                switchDisplayable(null, getFrmChooseAccount());//GEN-LINE:|18-action|2|22-postAction
                // write post-action user code here
            } else if (__selectedString.equals("Mini Statement")) {//GEN-LINE:|18-action|3|23-preAction
                // write pre-action user code here
                switchDisplayable(null, getFrmChooseAccount());//GEN-LINE:|18-action|4|23-postAction
                // write post-action user code here
            } else if (__selectedString.equals("Cheque Requests")) {//GEN-LINE:|18-action|5|24-preAction
                // write pre-action user code here
                switchDisplayable(null, getLCRMenu());//GEN-LINE:|18-action|6|24-postAction
                // write post-action user code here
            } else if (__selectedString.equals("Exchange Rate")) {//GEN-LINE:|18-action|7|25-preAction
                // write pre-action user code here
                switchDisplayable(null, getFrmChooseAccount());//GEN-LINE:|18-action|8|25-postAction
                // write post-action user code here
            } else if (__selectedString.equals("Settings")) {//GEN-LINE:|18-action|9|26-preAction
                // write pre-action user code here
                switchDisplayable(null, getLSettingsMenu());//GEN-LINE:|18-action|10|26-postAction
                // write post-action user code here
            } else if (__selectedString.equals("Help")) {//GEN-LINE:|18-action|11|27-preAction
                // write pre-action user code here
//GEN-LINE:|18-action|12|27-postAction
                // write post-action user code here
            }//GEN-BEGIN:|18-action|13|18-postAction
        }//GEN-END:|18-action|13|18-postAction
        // enter post-action user code here
    }//GEN-BEGIN:|18-action|14|
    //</editor-fold>//GEN-END:|18-action|14|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: lCRMenu ">//GEN-BEGIN:|28-getter|0|28-preInit
    /**
     * Returns an initiliazed instance of lCRMenu component.
     * @return the initialized component instance
     */
    public List getLCRMenu() {
        if (lCRMenu == null) {//GEN-END:|28-getter|0|28-preInit
            // write pre-init user code here
            lCRMenu = new List("Cheque Requests", Choice.IMPLICIT);//GEN-BEGIN:|28-getter|1|28-postInit
            lCRMenu.append("Cheque Status", null);
            lCRMenu.append("Stop Cheque", null);
            lCRMenu.append("New Cheque Book", null);
            lCRMenu.setTicker(getTkAdverts());
            lCRMenu.addCommand(getOkcCRMenu());
            lCRMenu.addCommand(getBkcCRMenu());
            lCRMenu.setCommandListener(this);
            lCRMenu.setSelectedFlags(new boolean[] { false, false, false });//GEN-END:|28-getter|1|28-postInit
            // write post-init user code here
        }//GEN-BEGIN:|28-getter|2|
        return lCRMenu;
    }
    //</editor-fold>//GEN-END:|28-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: lCRMenuAction ">//GEN-BEGIN:|28-action|0|28-preAction
    /**
     * Performs an action assigned to the selected list element in the lCRMenu component.
     */
    public void lCRMenuAction() {//GEN-END:|28-action|0|28-preAction
        // enter pre-action user code here
        String __selectedString = getLCRMenu().getString(getLCRMenu().getSelectedIndex());//GEN-BEGIN:|28-action|1|33-preAction
        if (__selectedString != null) {
            if (__selectedString.equals("Cheque Status")) {//GEN-END:|28-action|1|33-preAction
                // write pre-action user code here
//GEN-LINE:|28-action|2|33-postAction
                // write post-action user code here
            } else if (__selectedString.equals("Stop Cheque")) {//GEN-LINE:|28-action|3|32-preAction
                // write pre-action user code here
//GEN-LINE:|28-action|4|32-postAction
                // write post-action user code here
            } else if (__selectedString.equals("New Cheque Book")) {//GEN-LINE:|28-action|5|34-preAction
                // write pre-action user code here
//GEN-LINE:|28-action|6|34-postAction
                // write post-action user code here
            }//GEN-BEGIN:|28-action|7|28-postAction
        }//GEN-END:|28-action|7|28-postAction
        // enter post-action user code here
    }//GEN-BEGIN:|28-action|8|
    //</editor-fold>//GEN-END:|28-action|8|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: lSettingsMenu ">//GEN-BEGIN:|35-getter|0|35-preInit
    /**
     * Returns an initiliazed instance of lSettingsMenu component.
     * @return the initialized component instance
     */
    public List getLSettingsMenu() {
        if (lSettingsMenu == null) {//GEN-END:|35-getter|0|35-preInit
            // write pre-init user code here
            lSettingsMenu = new List("Settings", Choice.IMPLICIT);//GEN-BEGIN:|35-getter|1|35-postInit
            lSettingsMenu.append("Accounts", null);
            lSettingsMenu.append("Shortcode", null);
            lSettingsMenu.setTicker(getTkAdverts());
            lSettingsMenu.addCommand(getOkcSettings());
            lSettingsMenu.addCommand(getBkcSettings());
            lSettingsMenu.setCommandListener(this);
            lSettingsMenu.setSelectedFlags(new boolean[] { false, false });//GEN-END:|35-getter|1|35-postInit
            // write post-init user code here
        }//GEN-BEGIN:|35-getter|2|
        return lSettingsMenu;
    }
    //</editor-fold>//GEN-END:|35-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: lSettingsMenuAction ">//GEN-BEGIN:|35-action|0|35-preAction
    /**
     * Performs an action assigned to the selected list element in the lSettingsMenu component.
     */
    public void lSettingsMenuAction() {//GEN-END:|35-action|0|35-preAction
        // enter pre-action user code here
        String __selectedString = getLSettingsMenu().getString(getLSettingsMenu().getSelectedIndex());//GEN-BEGIN:|35-action|1|39-preAction
        if (__selectedString != null) {
            if (__selectedString.equals("Accounts")) {//GEN-END:|35-action|1|39-preAction
                // write pre-action user code here
                switchDisplayable(null, getLAccounts());//GEN-LINE:|35-action|2|39-postAction
                // write post-action user code here
            } else if (__selectedString.equals("Shortcode")) {//GEN-LINE:|35-action|3|40-preAction
                // write pre-action user code here
                switchDisplayable(null, getFrmShortcode());//GEN-LINE:|35-action|4|40-postAction
                // write post-action user code here
            }//GEN-BEGIN:|35-action|5|35-postAction
        }//GEN-END:|35-action|5|35-postAction
        // enter post-action user code here
    }//GEN-BEGIN:|35-action|6|
    //</editor-fold>//GEN-END:|35-action|6|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: frmShortcode ">//GEN-BEGIN:|41-getter|0|41-preInit
    /**
     * Returns an initiliazed instance of frmShortcode component.
     * @return the initialized component instance
     */
    public Form getFrmShortcode() {
        if (frmShortcode == null) {//GEN-END:|41-getter|0|41-preInit
            // write pre-init user code here
            frmShortcode = new Form("Shortcode", new Item[] { getTxtFrmShortCode() });//GEN-BEGIN:|41-getter|1|41-postInit
            frmShortcode.setTicker(getTkAdverts());
            frmShortcode.addCommand(getOkcFrmShortCode());
            frmShortcode.addCommand(getBkcFrmShortCode());
            frmShortcode.setCommandListener(this);//GEN-END:|41-getter|1|41-postInit
            // write post-init user code here
        }//GEN-BEGIN:|41-getter|2|
        return frmShortcode;
    }
    //</editor-fold>//GEN-END:|41-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: txtFrmShortCode ">//GEN-BEGIN:|54-getter|0|54-preInit
    /**
     * Returns an initiliazed instance of txtFrmShortCode component.
     * @return the initialized component instance
     */
    public TextField getTxtFrmShortCode() {
        if (txtFrmShortCode == null) {//GEN-END:|54-getter|0|54-preInit
            // write pre-init user code here
            txtFrmShortCode = new TextField("Enter Shortcode", "", 4, TextField.NUMERIC);//GEN-LINE:|54-getter|1|54-postInit
            // write post-init user code here
        }//GEN-BEGIN:|54-getter|2|
        return txtFrmShortCode;
    }
    //</editor-fold>//GEN-END:|54-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: frmBalance ">//GEN-BEGIN:|60-getter|0|60-preInit
    /**
     * Returns an initiliazed instance of frmBalance component.
     * @return the initialized component instance
     */
    public Form getFrmBalance() {
        if (frmBalance == null) {//GEN-END:|60-getter|0|60-preInit
            // write pre-init user code here
            frmBalance = new Form("Balance", new Item[] { getTxtFrmBalance() });//GEN-BEGIN:|60-getter|1|60-postInit
            frmBalance.setTicker(getTkAdverts());
            frmBalance.addCommand(getOkcFrmBalance());
            frmBalance.addCommand(getBkcFrmBalance());
            frmBalance.setCommandListener(this);//GEN-END:|60-getter|1|60-postInit
            // write post-init user code here
        }//GEN-BEGIN:|60-getter|2|
        return frmBalance;
    }
    //</editor-fold>//GEN-END:|60-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: txtFrmBalance ">//GEN-BEGIN:|61-getter|0|61-preInit
    /**
     * Returns an initiliazed instance of txtFrmBalance component.
     * @return the initialized component instance
     */
    public TextField getTxtFrmBalance() {
        if (txtFrmBalance == null) {//GEN-END:|61-getter|0|61-preInit
            // write pre-init user code here
            txtFrmBalance = new TextField("Enter PIN", "", 4, TextField.ANY);//GEN-LINE:|61-getter|1|61-postInit
            // write post-init user code here
        }//GEN-BEGIN:|61-getter|2|
        return txtFrmBalance;
    }
    //</editor-fold>//GEN-END:|61-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okcSettings ">//GEN-BEGIN:|42-getter|0|42-preInit
    /**
     * Returns an initiliazed instance of okcSettings component.
     * @return the initialized component instance
     */
    public Command getOkcSettings() {
        if (okcSettings == null) {//GEN-END:|42-getter|0|42-preInit
            // write pre-init user code here
            okcSettings = new Command("OK", Command.OK, 0);//GEN-LINE:|42-getter|1|42-postInit
            // write post-init user code here
        }//GEN-BEGIN:|42-getter|2|
        return okcSettings;
    }
    //</editor-fold>//GEN-END:|42-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: bkcSettings ">//GEN-BEGIN:|44-getter|0|44-preInit
    /**
     * Returns an initiliazed instance of bkcSettings component.
     * @return the initialized component instance
     */
    public Command getBkcSettings() {
        if (bkcSettings == null) {//GEN-END:|44-getter|0|44-preInit
            // write pre-init user code here
            bkcSettings = new Command("Back", Command.BACK, 0);//GEN-LINE:|44-getter|1|44-postInit
            // write post-init user code here
        }//GEN-BEGIN:|44-getter|2|
        return bkcSettings;
    }
    //</editor-fold>//GEN-END:|44-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okcMainMenu ">//GEN-BEGIN:|46-getter|0|46-preInit
    /**
     * Returns an initiliazed instance of okcMainMenu component.
     * @return the initialized component instance
     */
    public Command getOkcMainMenu() {
        if (okcMainMenu == null) {//GEN-END:|46-getter|0|46-preInit
            // write pre-init user code here
            okcMainMenu = new Command("Ok", Command.OK, 0);//GEN-LINE:|46-getter|1|46-postInit
            // write post-init user code here
        }//GEN-BEGIN:|46-getter|2|
        return okcMainMenu;
    }
    //</editor-fold>//GEN-END:|46-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: bkcMainMenu ">//GEN-BEGIN:|48-getter|0|48-preInit
    /**
     * Returns an initiliazed instance of bkcMainMenu component.
     * @return the initialized component instance
     */
    public Command getBkcMainMenu() {
        if (bkcMainMenu == null) {//GEN-END:|48-getter|0|48-preInit
            // write pre-init user code here
            bkcMainMenu = new Command("Back", Command.BACK, 0);//GEN-LINE:|48-getter|1|48-postInit
            // write post-init user code here
        }//GEN-BEGIN:|48-getter|2|
        return bkcMainMenu;
    }
    //</editor-fold>//GEN-END:|48-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okcCRMenu ">//GEN-BEGIN:|50-getter|0|50-preInit
    /**
     * Returns an initiliazed instance of okcCRMenu component.
     * @return the initialized component instance
     */
    public Command getOkcCRMenu() {
        if (okcCRMenu == null) {//GEN-END:|50-getter|0|50-preInit
            // write pre-init user code here
            okcCRMenu = new Command("Ok", Command.OK, 0);//GEN-LINE:|50-getter|1|50-postInit
            // write post-init user code here
        }//GEN-BEGIN:|50-getter|2|
        return okcCRMenu;
    }
    //</editor-fold>//GEN-END:|50-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: bkcCRMenu ">//GEN-BEGIN:|52-getter|0|52-preInit
    /**
     * Returns an initiliazed instance of bkcCRMenu component.
     * @return the initialized component instance
     */
    public Command getBkcCRMenu() {
        if (bkcCRMenu == null) {//GEN-END:|52-getter|0|52-preInit
            // write pre-init user code here
            bkcCRMenu = new Command("Back", Command.BACK, 0);//GEN-LINE:|52-getter|1|52-postInit
            // write post-init user code here
        }//GEN-BEGIN:|52-getter|2|
        return bkcCRMenu;
    }
    //</editor-fold>//GEN-END:|52-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okcFrmShortCode ">//GEN-BEGIN:|55-getter|0|55-preInit
    /**
     * Returns an initiliazed instance of okcFrmShortCode component.
     * @return the initialized component instance
     */
    public Command getOkcFrmShortCode() {
        if (okcFrmShortCode == null) {//GEN-END:|55-getter|0|55-preInit
            // write pre-init user code here
            okcFrmShortCode = new Command("Ok", Command.OK, 0);//GEN-LINE:|55-getter|1|55-postInit
            // write post-init user code here
        }//GEN-BEGIN:|55-getter|2|
        return okcFrmShortCode;
    }
    //</editor-fold>//GEN-END:|55-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: bkcFrmShortCode ">//GEN-BEGIN:|57-getter|0|57-preInit
    /**
     * Returns an initiliazed instance of bkcFrmShortCode component.
     * @return the initialized component instance
     */
    public Command getBkcFrmShortCode() {
        if (bkcFrmShortCode == null) {//GEN-END:|57-getter|0|57-preInit
            // write pre-init user code here
            bkcFrmShortCode = new Command("Back", Command.BACK, 0);//GEN-LINE:|57-getter|1|57-postInit
            // write post-init user code here
        }//GEN-BEGIN:|57-getter|2|
        return bkcFrmShortCode;
    }
    //</editor-fold>//GEN-END:|57-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okcFrmBalance ">//GEN-BEGIN:|62-getter|0|62-preInit
    /**
     * Returns an initiliazed instance of okcFrmBalance component.
     * @return the initialized component instance
     */
    public Command getOkcFrmBalance() {
        if (okcFrmBalance == null) {//GEN-END:|62-getter|0|62-preInit
            // write pre-init user code here
            okcFrmBalance = new Command("OK", Command.OK, 0);//GEN-LINE:|62-getter|1|62-postInit
            // write post-init user code here
        }//GEN-BEGIN:|62-getter|2|
        return okcFrmBalance;
    }
    //</editor-fold>//GEN-END:|62-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: bkcFrmBalance ">//GEN-BEGIN:|64-getter|0|64-preInit
    /**
     * Returns an initiliazed instance of bkcFrmBalance component.
     * @return the initialized component instance
     */
    public Command getBkcFrmBalance() {
        if (bkcFrmBalance == null) {//GEN-END:|64-getter|0|64-preInit
            // write pre-init user code here
            bkcFrmBalance = new Command("Back", Command.BACK, 0);//GEN-LINE:|64-getter|1|64-postInit
            // write post-init user code here
        }//GEN-BEGIN:|64-getter|2|
        return bkcFrmBalance;
    }
    //</editor-fold>//GEN-END:|64-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: tkAdverts ">//GEN-BEGIN:|59-getter|0|59-preInit
    /**
     * Returns an initiliazed instance of tkAdverts component.
     * @return the initialized component instance
     */
    public Ticker getTkAdverts() {
        if (tkAdverts == null) {//GEN-END:|59-getter|0|59-preInit
            // write pre-init user code here
            tkAdverts = new Ticker("To enjoy more services visit any of our brances or www.fidelitybank.com.gh");//GEN-LINE:|59-getter|1|59-postInit
            // write post-init user code here
        }//GEN-BEGIN:|59-getter|2|
        return tkAdverts;
    }
    //</editor-fold>//GEN-END:|59-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: frmChooseAccount ">//GEN-BEGIN:|67-getter|0|67-preInit
    /**
     * Returns an initiliazed instance of frmChooseAccount component.
     * @return the initialized component instance
     */
    public Form getFrmChooseAccount() {
        if (frmChooseAccount == null) {//GEN-END:|67-getter|0|67-preInit
            // write pre-init user code here
            frmChooseAccount = new Form("Choose Account");//GEN-BEGIN:|67-getter|1|67-postInit
            frmChooseAccount.setTicker(getTkAdverts());
            frmChooseAccount.addCommand(getOkcFrmChooseAccount());
            frmChooseAccount.addCommand(getBkcFrmChooseAccount());
            frmChooseAccount.setCommandListener(this);//GEN-END:|67-getter|1|67-postInit
            // write post-init user code here
        }//GEN-BEGIN:|67-getter|2|
        return frmChooseAccount;
    }
    //</editor-fold>//GEN-END:|67-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: lHelp ">//GEN-BEGIN:|86-getter|0|86-preInit
    /**
     * Returns an initiliazed instance of lHelp component.
     * @return the initialized component instance
     */
    public List getLHelp() {
        if (lHelp == null) {//GEN-END:|86-getter|0|86-preInit
            // write pre-init user code here
            lHelp = new List("list", Choice.IMPLICIT);//GEN-BEGIN:|86-getter|1|86-postInit
            lHelp.append("Contents", null);
            lHelp.append("About", null);
            lHelp.setCommandListener(this);
            lHelp.setSelectedFlags(new boolean[] { false, false });//GEN-END:|86-getter|1|86-postInit
            // write post-init user code here
        }//GEN-BEGIN:|86-getter|2|
        return lHelp;
    }
    //</editor-fold>//GEN-END:|86-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: lHelpAction ">//GEN-BEGIN:|86-action|0|86-preAction
    /**
     * Performs an action assigned to the selected list element in the lHelp component.
     */
    public void lHelpAction() {//GEN-END:|86-action|0|86-preAction
        // enter pre-action user code here
        String __selectedString = getLHelp().getString(getLHelp().getSelectedIndex());//GEN-BEGIN:|86-action|1|89-preAction
        if (__selectedString != null) {
            if (__selectedString.equals("Contents")) {//GEN-END:|86-action|1|89-preAction
                // write pre-action user code here
                switchDisplayable(null, getFrmHelpContents());//GEN-LINE:|86-action|2|89-postAction
                // write post-action user code here
            } else if (__selectedString.equals("About")) {//GEN-LINE:|86-action|3|90-preAction
                // write pre-action user code here
                switchDisplayable(null, getFrmHelpAbout());//GEN-LINE:|86-action|4|90-postAction
                // write post-action user code here
            }//GEN-BEGIN:|86-action|5|86-postAction
        }//GEN-END:|86-action|5|86-postAction
        // enter post-action user code here
    }//GEN-BEGIN:|86-action|6|
    //</editor-fold>//GEN-END:|86-action|6|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: frmHelpContents ">//GEN-BEGIN:|91-getter|0|91-preInit
    /**
     * Returns an initiliazed instance of frmHelpContents component.
     * @return the initialized component instance
     */
    public Form getFrmHelpContents() {
        if (frmHelpContents == null) {//GEN-END:|91-getter|0|91-preInit
            // write pre-init user code here
            frmHelpContents = new Form("form", new Item[] { getStringItem() });//GEN-BEGIN:|91-getter|1|91-postInit
            frmHelpContents.addCommand(getBkcFrmHelpContents());
            frmHelpContents.setCommandListener(this);//GEN-END:|91-getter|1|91-postInit
            // write post-init user code here
        }//GEN-BEGIN:|91-getter|2|
        return frmHelpContents;
    }
    //</editor-fold>//GEN-END:|91-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItem ">//GEN-BEGIN:|92-getter|0|92-preInit
    /**
     * Returns an initiliazed instance of stringItem component.
     * @return the initialized component instance
     */
    public StringItem getStringItem() {
        if (stringItem == null) {//GEN-END:|92-getter|0|92-preInit
            // write pre-init user code here
            stringItem = new StringItem("stringItem", null);//GEN-LINE:|92-getter|1|92-postInit
            // write post-init user code here
        }//GEN-BEGIN:|92-getter|2|
        return stringItem;
    }
    //</editor-fold>//GEN-END:|92-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: frmHelpAbout ">//GEN-BEGIN:|96-getter|0|96-preInit
    /**
     * Returns an initiliazed instance of frmHelpAbout component.
     * @return the initialized component instance
     */
    public Form getFrmHelpAbout() {
        if (frmHelpAbout == null) {//GEN-END:|96-getter|0|96-preInit
            // write pre-init user code here
            frmHelpAbout = new Form("form", new Item[] { getStringItem1() });//GEN-BEGIN:|96-getter|1|96-postInit
            frmHelpAbout.addCommand(getBkcFrmHelpAbout());
            frmHelpAbout.setCommandListener(this);//GEN-END:|96-getter|1|96-postInit
            // write post-init user code here
        }//GEN-BEGIN:|96-getter|2|
        return frmHelpAbout;
    }
    //</editor-fold>//GEN-END:|96-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItem1 ">//GEN-BEGIN:|100-getter|0|100-preInit
    /**
     * Returns an initiliazed instance of stringItem1 component.
     * @return the initialized component instance
     */
    public StringItem getStringItem1() {
        if (stringItem1 == null) {//GEN-END:|100-getter|0|100-preInit
            // write pre-init user code here
            stringItem1 = new StringItem("stringItem1", null);//GEN-LINE:|100-getter|1|100-postInit
            // write post-init user code here
        }//GEN-BEGIN:|100-getter|2|
        return stringItem1;
    }
    //</editor-fold>//GEN-END:|100-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: lAccounts ">//GEN-BEGIN:|102-getter|0|102-preInit
    /**
     * Returns an initiliazed instance of lAccounts component.
     * @return the initialized component instance
     */
    public List getLAccounts() {
        if (lAccounts == null) {//GEN-END:|102-getter|0|102-preInit
            // write pre-init user code here
            lAccounts = new List("list", Choice.IMPLICIT);//GEN-BEGIN:|102-getter|1|102-postInit
            lAccounts.append("Account 1", null);
            lAccounts.append("Account 2", null);
            lAccounts.append("Account 3", null);
            lAccounts.append("Account 4", null);
            lAccounts.append("Account 5", null);
            lAccounts.addCommand(getOkcLAccounts());
            lAccounts.addCommand(getBkcLAccounts());
            lAccounts.setCommandListener(this);
            lAccounts.setSelectedFlags(new boolean[] { false, false, false, false, false });//GEN-END:|102-getter|1|102-postInit
            // write post-init user code here
        }//GEN-BEGIN:|102-getter|2|
        return lAccounts;
    }
    //</editor-fold>//GEN-END:|102-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: lAccountsAction ">//GEN-BEGIN:|102-action|0|102-preAction
    /**
     * Performs an action assigned to the selected list element in the lAccounts component.
     */
    public void lAccountsAction() {//GEN-END:|102-action|0|102-preAction
        // enter pre-action user code here
        String __selectedString = getLAccounts().getString(getLAccounts().getSelectedIndex());//GEN-BEGIN:|102-action|1|105-preAction
        if (__selectedString != null) {
            if (__selectedString.equals("Account 1")) {//GEN-END:|102-action|1|105-preAction
                // write pre-action user code here
//GEN-LINE:|102-action|2|105-postAction
                // write post-action user code here
            } else if (__selectedString.equals("Account 2")) {//GEN-LINE:|102-action|3|106-preAction
                // write pre-action user code here
//GEN-LINE:|102-action|4|106-postAction
                // write post-action user code here
            } else if (__selectedString.equals("Account 3")) {//GEN-LINE:|102-action|5|107-preAction
                // write pre-action user code here
//GEN-LINE:|102-action|6|107-postAction
                // write post-action user code here
            } else if (__selectedString.equals("Account 4")) {//GEN-LINE:|102-action|7|108-preAction
                // write pre-action user code here
//GEN-LINE:|102-action|8|108-postAction
                // write post-action user code here
            } else if (__selectedString.equals("Account 5")) {//GEN-LINE:|102-action|9|111-preAction
                // write pre-action user code here
//GEN-LINE:|102-action|10|111-postAction
                // write post-action user code here
            }//GEN-BEGIN:|102-action|11|102-postAction
        }//GEN-END:|102-action|11|102-postAction
        // enter post-action user code here
    }//GEN-BEGIN:|102-action|12|
    //</editor-fold>//GEN-END:|102-action|12|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: frmEditAccount ">//GEN-BEGIN:|113-getter|0|113-preInit
    /**
     * Returns an initiliazed instance of frmEditAccount component.
     * @return the initialized component instance
     */
    public Form getFrmEditAccount() {
        if (frmEditAccount == null) {//GEN-END:|113-getter|0|113-preInit
            // write pre-init user code here
            frmEditAccount = new Form("Edit Account", new Item[] { getTextField(), getTextField1() });//GEN-BEGIN:|113-getter|1|113-postInit
            frmEditAccount.addCommand(getOkcFrmEditAccount());
            frmEditAccount.addCommand(getCcFrmEditAccount());
            frmEditAccount.setCommandListener(this);//GEN-END:|113-getter|1|113-postInit
            // write post-init user code here
        }//GEN-BEGIN:|113-getter|2|
        return frmEditAccount;
    }
    //</editor-fold>//GEN-END:|113-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: textField ">//GEN-BEGIN:|130-getter|0|130-preInit
    /**
     * Returns an initiliazed instance of textField component.
     * @return the initialized component instance
     */
    public TextField getTextField() {
        if (textField == null) {//GEN-END:|130-getter|0|130-preInit
            // write pre-init user code here
            textField = new TextField("textField", null, 32, TextField.ANY);//GEN-LINE:|130-getter|1|130-postInit
            // write post-init user code here
        }//GEN-BEGIN:|130-getter|2|
        return textField;
    }
    //</editor-fold>//GEN-END:|130-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: textField1 ">//GEN-BEGIN:|131-getter|0|131-preInit
    /**
     * Returns an initiliazed instance of textField1 component.
     * @return the initialized component instance
     */
    public TextField getTextField1() {
        if (textField1 == null) {//GEN-END:|131-getter|0|131-preInit
            // write pre-init user code here
            textField1 = new TextField("textField1", null, 32, TextField.ANY);//GEN-LINE:|131-getter|1|131-postInit
            // write post-init user code here
        }//GEN-BEGIN:|131-getter|2|
        return textField1;
    }
    //</editor-fold>//GEN-END:|131-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okcFrmChooseAccount ">//GEN-BEGIN:|68-getter|0|68-preInit
    /**
     * Returns an initiliazed instance of okcFrmChooseAccount component.
     * @return the initialized component instance
     */
    public Command getOkcFrmChooseAccount() {
        if (okcFrmChooseAccount == null) {//GEN-END:|68-getter|0|68-preInit
            // write pre-init user code here
            okcFrmChooseAccount = new Command("OK", Command.OK, 0);//GEN-LINE:|68-getter|1|68-postInit
            // write post-init user code here
        }//GEN-BEGIN:|68-getter|2|
        return okcFrmChooseAccount;
    }
    //</editor-fold>//GEN-END:|68-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: bkcFrmChooseAccount ">//GEN-BEGIN:|70-getter|0|70-preInit
    /**
     * Returns an initiliazed instance of bkcFrmChooseAccount component.
     * @return the initialized component instance
     */
    public Command getBkcFrmChooseAccount() {
        if (bkcFrmChooseAccount == null) {//GEN-END:|70-getter|0|70-preInit
            // write pre-init user code here
            bkcFrmChooseAccount = new Command("Back", Command.BACK, 0);//GEN-LINE:|70-getter|1|70-postInit
            // write post-init user code here
        }//GEN-BEGIN:|70-getter|2|
        return bkcFrmChooseAccount;
    }
    //</editor-fold>//GEN-END:|70-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand ">//GEN-BEGIN:|76-getter|0|76-preInit
    /**
     * Returns an initiliazed instance of okCommand component.
     * @return the initialized component instance
     */
    public Command getOkCommand() {
        if (okCommand == null) {//GEN-END:|76-getter|0|76-preInit
            // write pre-init user code here
            okCommand = new Command("Ok", Command.OK, 0);//GEN-LINE:|76-getter|1|76-postInit
            // write post-init user code here
        }//GEN-BEGIN:|76-getter|2|
        return okCommand;
    }
    //</editor-fold>//GEN-END:|76-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: cancelCommand ">//GEN-BEGIN:|78-getter|0|78-preInit
    /**
     * Returns an initiliazed instance of cancelCommand component.
     * @return the initialized component instance
     */
    public Command getCancelCommand() {
        if (cancelCommand == null) {//GEN-END:|78-getter|0|78-preInit
            // write pre-init user code here
            cancelCommand = new Command("Cancel", Command.CANCEL, 0);//GEN-LINE:|78-getter|1|78-postInit
            // write post-init user code here
        }//GEN-BEGIN:|78-getter|2|
        return cancelCommand;
    }
    //</editor-fold>//GEN-END:|78-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand1 ">//GEN-BEGIN:|80-getter|0|80-preInit
    /**
     * Returns an initiliazed instance of backCommand1 component.
     * @return the initialized component instance
     */
    public Command getBackCommand1() {
        if (backCommand1 == null) {//GEN-END:|80-getter|0|80-preInit
            // write pre-init user code here
            backCommand1 = new Command("Back", Command.BACK, 0);//GEN-LINE:|80-getter|1|80-postInit
            // write post-init user code here
        }//GEN-BEGIN:|80-getter|2|
        return backCommand1;
    }
    //</editor-fold>//GEN-END:|80-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: extcMainMenu ">//GEN-BEGIN:|82-getter|0|82-preInit
    /**
     * Returns an initiliazed instance of extcMainMenu component.
     * @return the initialized component instance
     */
    public Command getExtcMainMenu() {
        if (extcMainMenu == null) {//GEN-END:|82-getter|0|82-preInit
            // write pre-init user code here
            extcMainMenu = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|82-getter|1|82-postInit
            // write post-init user code here
        }//GEN-BEGIN:|82-getter|2|
        return extcMainMenu;
    }
    //</editor-fold>//GEN-END:|82-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: bkcFrmHelpContents ">//GEN-BEGIN:|93-getter|0|93-preInit
    /**
     * Returns an initiliazed instance of bkcFrmHelpContents component.
     * @return the initialized component instance
     */
    public Command getBkcFrmHelpContents() {
        if (bkcFrmHelpContents == null) {//GEN-END:|93-getter|0|93-preInit
            // write pre-init user code here
            bkcFrmHelpContents = new Command("Back", Command.BACK, 0);//GEN-LINE:|93-getter|1|93-postInit
            // write post-init user code here
        }//GEN-BEGIN:|93-getter|2|
        return bkcFrmHelpContents;
    }
    //</editor-fold>//GEN-END:|93-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: bkcFrmHelpAbout ">//GEN-BEGIN:|98-getter|0|98-preInit
    /**
     * Returns an initiliazed instance of bkcFrmHelpAbout component.
     * @return the initialized component instance
     */
    public Command getBkcFrmHelpAbout() {
        if (bkcFrmHelpAbout == null) {//GEN-END:|98-getter|0|98-preInit
            // write pre-init user code here
            bkcFrmHelpAbout = new Command("Back", Command.BACK, 0);//GEN-LINE:|98-getter|1|98-postInit
            // write post-init user code here
        }//GEN-BEGIN:|98-getter|2|
        return bkcFrmHelpAbout;
    }
    //</editor-fold>//GEN-END:|98-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: stopCommand ">//GEN-BEGIN:|109-getter|0|109-preInit
    /**
     * Returns an initiliazed instance of stopCommand component.
     * @return the initialized component instance
     */
    public Command getStopCommand() {
        if (stopCommand == null) {//GEN-END:|109-getter|0|109-preInit
            // write pre-init user code here
            stopCommand = new Command("Stop", Command.STOP, 0);//GEN-LINE:|109-getter|1|109-postInit
            // write post-init user code here
        }//GEN-BEGIN:|109-getter|2|
        return stopCommand;
    }
    //</editor-fold>//GEN-END:|109-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: bkcLAccountsOptions ">//GEN-BEGIN:|117-getter|0|117-preInit
    /**
     * Returns an initiliazed instance of bkcLAccountsOptions component.
     * @return the initialized component instance
     */
    public Command getBkcLAccountsOptions() {
        if (bkcLAccountsOptions == null) {//GEN-END:|117-getter|0|117-preInit
            // write pre-init user code here
            bkcLAccountsOptions = new Command("Back", Command.BACK, 0);//GEN-LINE:|117-getter|1|117-postInit
            // write post-init user code here
        }//GEN-BEGIN:|117-getter|2|
        return bkcLAccountsOptions;
    }
    //</editor-fold>//GEN-END:|117-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okcLAccountsOptions ">//GEN-BEGIN:|119-getter|0|119-preInit
    /**
     * Returns an initiliazed instance of okcLAccountsOptions component.
     * @return the initialized component instance
     */
    public Command getOkcLAccountsOptions() {
        if (okcLAccountsOptions == null) {//GEN-END:|119-getter|0|119-preInit
            // write pre-init user code here
            okcLAccountsOptions = new Command("Ok", Command.OK, 0);//GEN-LINE:|119-getter|1|119-postInit
            // write post-init user code here
        }//GEN-BEGIN:|119-getter|2|
        return okcLAccountsOptions;
    }
    //</editor-fold>//GEN-END:|119-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: screenCommand ">//GEN-BEGIN:|123-getter|0|123-preInit
    /**
     * Returns an initiliazed instance of screenCommand component.
     * @return the initialized component instance
     */
    public Command getScreenCommand() {
        if (screenCommand == null) {//GEN-END:|123-getter|0|123-preInit
            // write pre-init user code here
            screenCommand = new Command("Screen", Command.SCREEN, 0);//GEN-LINE:|123-getter|1|123-postInit
            // write post-init user code here
        }//GEN-BEGIN:|123-getter|2|
        return screenCommand;
    }
    //</editor-fold>//GEN-END:|123-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okcLAccounts ">//GEN-BEGIN:|125-getter|0|125-preInit
    /**
     * Returns an initiliazed instance of okcLAccounts component.
     * @return the initialized component instance
     */
    public Command getOkcLAccounts() {
        if (okcLAccounts == null) {//GEN-END:|125-getter|0|125-preInit
            // write pre-init user code here
            okcLAccounts = new Command("Edit", Command.OK, 0);//GEN-LINE:|125-getter|1|125-postInit
            // write post-init user code here
        }//GEN-BEGIN:|125-getter|2|
        return okcLAccounts;
    }
    //</editor-fold>//GEN-END:|125-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: bkcLAccounts ">//GEN-BEGIN:|127-getter|0|127-preInit
    /**
     * Returns an initiliazed instance of bkcLAccounts component.
     * @return the initialized component instance
     */
    public Command getBkcLAccounts() {
        if (bkcLAccounts == null) {//GEN-END:|127-getter|0|127-preInit
            // write pre-init user code here
            bkcLAccounts = new Command("Back", Command.BACK, 0);//GEN-LINE:|127-getter|1|127-postInit
            // write post-init user code here
        }//GEN-BEGIN:|127-getter|2|
        return bkcLAccounts;
    }
    //</editor-fold>//GEN-END:|127-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okcFrmEditAccount ">//GEN-BEGIN:|132-getter|0|132-preInit
    /**
     * Returns an initiliazed instance of okcFrmEditAccount component.
     * @return the initialized component instance
     */
    public Command getOkcFrmEditAccount() {
        if (okcFrmEditAccount == null) {//GEN-END:|132-getter|0|132-preInit
            // write pre-init user code here
            okcFrmEditAccount = new Command("Ok", Command.OK, 0);//GEN-LINE:|132-getter|1|132-postInit
            // write post-init user code here
        }//GEN-BEGIN:|132-getter|2|
        return okcFrmEditAccount;
    }
    //</editor-fold>//GEN-END:|132-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: ccFrmEditAccount ">//GEN-BEGIN:|134-getter|0|134-preInit
    /**
     * Returns an initiliazed instance of ccFrmEditAccount component.
     * @return the initialized component instance
     */
    public Command getCcFrmEditAccount() {
        if (ccFrmEditAccount == null) {//GEN-END:|134-getter|0|134-preInit
            // write pre-init user code here
            ccFrmEditAccount = new Command("Cancel", Command.CANCEL, 0);//GEN-LINE:|134-getter|1|134-postInit
            // write post-init user code here
        }//GEN-BEGIN:|134-getter|2|
        return ccFrmEditAccount;
    }
    //</editor-fold>//GEN-END:|134-getter|2|

    /**
     * Returns a display instance.
     * @return the display instance.
     */
    public Display getDisplay () {
        return Display.getDisplay(this);
    }

    /**
     * Exits MIDlet.
     */
    public void exitMIDlet() {
        switchDisplayable (null, null);
        destroyApp(true);
        notifyDestroyed();
    }

    /**
     * Called when MIDlet is started.
     * Checks whether the MIDlet have been already started and initialize/starts or resumes the MIDlet.
     */
    public void startApp() {
        if (midletPaused) {
            resumeMIDlet ();
        } else {
            initialize ();
            startMIDlet ();
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

}
