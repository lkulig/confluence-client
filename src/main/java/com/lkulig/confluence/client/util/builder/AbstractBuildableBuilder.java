package com.lkulig.confluence.client.util.builder;

public abstract class AbstractBuildableBuilder<TYPE extends Buildable, BUILDER extends AbstractBuildableBuilder<TYPE, BUILDER>> {

    protected TYPE buildable;
    protected BUILDER builder;

    protected AbstractBuildableBuilder() {
        buildable = createBuildable();
        builder = getBuilder();
    }

    public abstract TYPE build();

    protected abstract TYPE createBuildable();

    protected abstract BUILDER getBuilder();

}
