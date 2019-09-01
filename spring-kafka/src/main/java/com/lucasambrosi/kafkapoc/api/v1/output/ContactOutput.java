package com.lucasambrosi.kafkapoc.api.v1.output;

public class ContactOutput {

    private Long contactId;

    public ContactOutput(Long contactId) {
        this.contactId = contactId;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }
}
