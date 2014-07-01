package com.lkulig.confluence.client.page.details;

import com.lkulig.confluence.client.util.builder.AbstractBuildableBuilder;

public class ConfluencePageDetailsBuilder extends AbstractBuildableBuilder<ConfluencePageDetails, ConfluencePageDetailsBuilder> {

    public static ConfluencePageDetailsBuilder pageDetails() {
        return new ConfluencePageDetailsBuilder();
    }

    public ConfluencePageDetailsBuilder space(String space) {
        buildable.space(space);
        return this;
    }

    public ConfluencePageDetailsBuilder homePage() {
        buildable.homePage();
        return this;
    }

    public ConfluencePageDetailsBuilder normalPage() {
        buildable.normalPage();
        return this;
    }

    public ConfluencePageDetailsBuilder title(String title) {
        buildable.title(title);
        return this;
    }

    public ConfluencePageDetailsBuilder content(String content) {
        buildable.content(content);
        return this;
    }

    public ConfluencePageDetailsBuilder parent(String parentId) {
        buildable.parent(parentId);
        return this;
    }

    @Override
    public ConfluencePageDetails build() {
        return buildable;
    }

    @Override
    protected ConfluencePageDetails createBuildable() {
        return new ConfluencePageDetails();
    }

    @Override
    protected ConfluencePageDetailsBuilder getBuilder() {
        return this;
    }
}
