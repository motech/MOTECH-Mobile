/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.imp.serivce.oxd;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

/**
 *
 * @author Henry Sampson (henry@dreamoval.com) and Brent Atkinson
 * Date Created: Mar 3, 2010
 */
public class FormDefinitionServiceImpl implements FormDefinitionService{
    private Map<Integer, String> formMap;

	private InputStream exportStream;

    private String oxdFormDefResourceName;

    /**
     * Sets the InputStream for the Exported OXD Form definitions
     *
     * @param exportStream
     */
	private void setExportStream(String xFormsResourcename) {
		exportStream = getClass().getResourceAsStream(xFormsResourcename);
	}

	/**
	 * @return a map of xml xforms definitions, keyed on their definition ids
	 */
	public Map<Integer, String> getXForms() throws Exception {

		if (formMap == null) {

			formMap = new HashMap<Integer, String>();

			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);

			XmlPullParser xpp = factory.newPullParser();
			xpp.setInput(new InputStreamReader(getExportStream()));

			boolean isXform = false;
			boolean isVersionText = false;
			String xformDef = null;
			Integer formId = null;

			for (int eventType = xpp.getEventType(); eventType != XmlPullParser.END_DOCUMENT; eventType = xpp
					.next()) {

				if (eventType == XmlPullParser.START_TAG) {
					if ("xform".equals(xpp.getName()))
						isXform = true;
					else if ("versionText".equals(xpp.getName()))
						isVersionText = true;
				} else if (eventType == XmlPullParser.END_TAG) {
					if ("xform".equals(xpp.getName()))
						isXform = false;
					else if ("versionText".equals(xpp.getName()))
						isVersionText = false;
				} else if (eventType == XmlPullParser.TEXT) {
					if (isXform) {
						if (!isVersionText) {
							xformDef = xpp.getText();
						} else {
							// Parse xform element in versionText to get id
							String versionTextXform = xpp.getText();
							XmlPullParser formXpp = factory.newPullParser();
							formXpp
									.setInput(new StringReader(versionTextXform));
							for (int formEvent = formXpp.getEventType(); formEvent != XmlPullParser.END_DOCUMENT; formEvent = formXpp
									.next()) {
								if (formEvent == XmlPullParser.START_TAG
										&& "xform".equals(formXpp.getName())) {
									String formIdStr = formXpp
											.getAttributeValue(null, "id");
									formId = Integer.valueOf(formIdStr);
								}
							}

							// Store form def keyed on id, don't re-process
							if (formId != null && formMap.get(formId) == null)
								formMap.put(formId, xformDef);
						}
					}
				}
			}
		}
		return formMap;
	}

    /**
     * @return the exportStream
     */
    public InputStream getExportStream() {
        return exportStream;
    }

    /**
     * @return the oxdFormDefResourceName
     */
    public String getOxdFormDefResourceName() {
        return oxdFormDefResourceName;
    }

    /**
     * <p>Sets the resource name for the Exported OXD Form definitions' InputStream
     * and set the inputstream created for the resource as well</p>
     *
     * @param oxdFormDefResourceName the oxdFormDefResourceName to set
     */
    public void setOxdFormDefResourceName(String oxdFormDefResourceName) {
        this.oxdFormDefResourceName = oxdFormDefResourceName;

        setExportStream(this.oxdFormDefResourceName);
    }


}