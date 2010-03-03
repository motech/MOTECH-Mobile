/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamoval.motech.imp.util;

import java.util.List;
import org.jdom.Document;
import org.jdom.Element;

/**
 *
 * @author Henry Sampson (henry@dreamoval.com)
 * Date Created: Feb 24, 2010
 */
public class XMLUtil {

    /**
     * Searches the doc for the first occurence of tagName
     *
     * @param doc The tage doc to search
     * @param tagName The tag name to search
     * @return
     */
    public Element getElement(Document doc, String tagName) {
        Element result = null;

        if (tagName != null) {
            Element root = doc.getRootElement();
            if (tagName.equals(root.getName())) {
                result = root;
            } else {
                List children = root.getChildren();
                for(Object o : children) {
                    Element e = (Element) o;
                    if (e.getName() != null && e.getName().equals(tagName)) {
                        result = e;
                        break;
                    }
                }
            }
        }

        return result;
    }
}
