/**
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created Aug 10, 2009
 */

package com.dreamoval.motech.omi.manager;


import java.util.Map;
import static org.easymock.EasyMock.*;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class MessageStoreManagerImplTest {

    Map<String, String> mockStore;
    MessageStoreManager instance;

    public MessageStoreManagerImplTest() {
    }

    @Before
    public void setUp(){
        mockStore = createMock(Map.class);
        instance = new MessageStoreManagerImpl();
        instance.setMessageStore(mockStore);
    }

    /**
     * Test of getMessage method, of class MessageStoreManagerImpl.
     */
    @Test
    public void testGetMessage() {
        System.out.println("getMessage");
        String key = "patient";

        expect(
                mockStore.get((String) anyObject())
                ).andReturn("Test Message");
        replay(mockStore);

        String result = instance.getMessage(key);
        assertNotNull(result);
        verify(mockStore);
    }

}