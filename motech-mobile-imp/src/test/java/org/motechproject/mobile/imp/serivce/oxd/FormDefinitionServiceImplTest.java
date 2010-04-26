/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.motechproject.mobile.imp.serivce.oxd;

import java.util.HashSet;
import java.util.Set;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author user
 */
public class FormDefinitionServiceImplTest {

    public FormDefinitionServiceImplTest() {
    }

    @Test
    public void testInit() throws Exception {
        String resource = "/MoTeCHDataEntry.xml";
        FormDefinitionServiceImpl fds = new FormDefinitionServiceImpl();
        Set resources = new HashSet();
        resources.add(resource);
        fds.setOxdFormDefResources(resources);
        fds.init();
        Assert.assertEquals(1, fds.getStudies().size());
        Assert.assertEquals(9, fds.getStudyForms(0).size());
    }

//    @Test
//    public void testGetXForms() throws Exception {
//    }
//
//    @Test
//    public void testGetOxdFormDefResources() {
//    }
//
//    @Test
//    public void testSetOxdFormDefResources() {
//    }
//
//    @Test
//    public void testAddOxdFormDefResources() {
//    }
//
//    @Test
//    public void testGetStudies() {
//    }
//
//    @Test
//    public void testGetUsers() {
//    }
//
//    @Test
//    public void testGetStudyName() {
//    }
//
//    @Test
//    public void testGetStudyForms() {
//    }

}