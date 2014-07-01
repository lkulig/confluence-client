package com.lkulig.confluence.client.attachment;

import com.lkulig.confluence.client.util.builder.Buildable;
import org.codehaus.swizzle.confluence.Attachment;

public class ConfluenceAttachment implements Buildable {

    private Attachment attachment;

    public ConfluenceAttachment() {
        this.attachment = new Attachment();
    }

    public ConfluenceAttachment(Attachment attachment) {
        this.attachment = attachment;
    }

    public String fileName() {
        return attachment.getFileName();
    }

    public String pageId() {
        return attachment.getPageId();
    }

    public void setPageId(String pageId) {
        attachment.setPageId(pageId);
    }

    public void setContentType(String contentType) {
        attachment.setContentType(contentType);
    }

    public String getContentType() {
        return attachment.getContentType();
    }

    public void setFileName(String fileName) {
        attachment.setFileName(fileName);
    }

    public String getFileName() {
        return attachment.getFileName();
    }

    public Attachment representation() {
        return attachment;
    }
}
