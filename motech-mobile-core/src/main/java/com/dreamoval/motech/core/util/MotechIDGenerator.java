package com.dreamoval.motech.core.util;

import org.apache.log4j.Logger;

/**
 * <p>Class for generating IDs </p>
 *
 * @author Henry Sampson (henry@dreamoval.com)
 * Date Created: Aug 10, 2009
 */
public class MotechIDGenerator {

    public static final int DEFUALT_ID_LENGTH = 15;
    private static Logger logger = Logger.getLogger(MotechIDGenerator.class);
    private static final int[] NUMS = {6, 2, 9, 3, 4, 9, 1, 4, 8, 0, 5, 0, 2, 5, 6, 7, 1, 7, 3, 8};

    /**
     * <p>Generates a Long ID of length <code>length</code></p>
     *
     * @param length the length of the id to be generated
     * @return an ID of type Long with length <code>length</code>
     */
    public static Long generateID(int length) {
        logger.info("Calling generateID with specify length");
        Long result = null;

        if (length > 0) {
            StringBuffer id = new StringBuffer(length);
            for (int i = 0; i < length; i++) {
                id.append(NUMS[(int) Math.floor(Math.random() * 20)]);
            }
            result = Long.parseLong(id.toString());
        }

        return result;
    }

    /**
     * <p>Generates an ID of type Long of length 15</p>
     *
     * @return an ID of type Long
     */
    public static Long generateID() {
        logger.info("Calling Default generateID");
        return generateID(DEFUALT_ID_LENGTH);
    }
}
