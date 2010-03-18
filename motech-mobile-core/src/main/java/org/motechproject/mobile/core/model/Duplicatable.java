package org.motechproject.mobile.core.model;

/**
 * This enumeration is used to describe the IncomingMessageFormDefinition's rule of duplication.It
 * tells whether a form is allowed to be duplicated or not or if duplication is allowed whether it's
 * time bound.
 *
 * Date : Feb 11, 2010
 * Last Updated : Feb 11, 2010
 * @author joseph Djomeda (joseph@dreamoval.com)
 */
public enum Duplicatable {
    ALLOWED,
    DISALLOWED,
    TIME_BOUND;
}
