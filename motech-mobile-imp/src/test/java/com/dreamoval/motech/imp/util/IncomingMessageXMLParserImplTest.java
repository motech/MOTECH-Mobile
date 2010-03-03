/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.imp.util;

import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.imp.manager.IMPManager;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;

/**
 *
 * @author administrator
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/imp-test-config.xml"})
public class IncomingMessageXMLParserImplTest {
    CoreManager mockCore;
    IMPManager mockIMP;
    IncomingMessageXMLParser xmlParser;

    @Autowired
    IncomingMessageParser imParser;

    public IncomingMessageXMLParserImplTest() {
    }

    @Before
    public void setUp() throws Exception {
        mockIMP = createMock(IMPManager.class);

        expect(
                mockIMP.createIncomingMessageXMLParser()
                ).andReturn(new IncomingMessageXMLParserImpl());
        replay(mockIMP);
        xmlParser = mockIMP.createIncomingMessageXMLParser();
        xmlParser.setDelimiter("\n");
        xmlParser.setSeparator("=");
        xmlParser.setFormTypeTagName("formType");
        xmlParser.setXmlUtil(new XMLUtil());
        Map<String, String> lookup = new HashMap<String, String>();
        lookup.put("data", "Type");
        xmlParser.setFormTypeLookup(lookup);
        mockCore = createMock(CoreManager.class);
        xmlParser.setCoreManager(mockCore);
        verify(mockIMP);
    }

    
    @Test
    public void testToSMSMessage() throws Exception{
        System.out.println("toSMSMessage");

        String xml = "<?xml version='1.0' encoding='UTF-8' ?><patientreg description-template=\"${/patientreg/lastname}$ in ${/patientreg/continent}$\" id=\"1\" name=\"Patient Registration\" xmlns:xf=\"http://www.w3.org/2002/xforms\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><patientid>123</patientid><title>mrs</title><firstname>Test</firstname><lastname>Test</lastname><sex>female</sex><birthdate>1990-06-03</birthdate><weight>40</weight><height>20</height><pregnant>false</pregnant><continent>africa</continent><country>uganda</country><district>mbale</district><formType>data</formType></patientreg>";


        String expResult = "Type=patientreg\npatientid=123\ntitle=mrs\nfirstname=Test\nlastname=Test\nsex=female\nbirthdate=1990-06-03\nweight=40\nheight=20\npregnant=false\ncontinent=africa\ncountry=uganda\ndistrict=mbale";
        String result = ((IncomingMessageXMLParserImpl)xmlParser).toSMSMessage(xml);
        
        assertEquals(result, expResult);

    }

}