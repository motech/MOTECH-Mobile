package org.motechproject.mobile.domain.message;

public class SupportCase {

    private String raisedBy;
    private String description;

    public static final SupportCase EMPTY = new SupportCase();

    private SupportCase(){}

    public SupportCase(String raisedBy, String description) {
        this.raisedBy = raisedBy;
        this.description = description;
    }

    public String raisedBy() {
        return raisedBy;
    }

    public String description() {
        return description;
    }
}
