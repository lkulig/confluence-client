package com.lkulig.confluence.client.attachment;

import com.lkulig.confluence.client.util.builder.AbstractBuildableBuilder;

public class ConfluenceAttachmentBuilder extends AbstractBuildableBuilder<ConfluenceAttachment, ConfluenceAttachmentBuilder> {

    public static ConfluenceAttachmentBuilder confluenceAttachment() {
        return new ConfluenceAttachmentBuilder();
    }

    public ConfluenceAttachmentBuilder fileName(String fileName) {
        buildable.setFileName(fileName);
        return this;
    }

    public ConfluenceAttachmentBuilder pageId(String pageId) {
        buildable.setPageId(pageId);
        return this;
    }

    public ConfluenceAttachmentBuilder contentType(String contentType) {
        buildable.setContentType(contentType);
        return this;
    }

    @Override
    public ConfluenceAttachment build() {
        return buildable;
    }

    @Override
    protected ConfluenceAttachment createBuildable() {
        return new ConfluenceAttachment();
    }

    @Override
    protected ConfluenceAttachmentBuilder getBuilder() {
        return this;
    }
}
