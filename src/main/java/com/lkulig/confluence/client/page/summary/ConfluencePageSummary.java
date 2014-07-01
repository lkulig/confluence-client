package com.lkulig.confluence.client.page.summary;

import org.codehaus.swizzle.confluence.PageSummary;

public class ConfluencePageSummary {

    private PageSummary pageSummary;

    ConfluencePageSummary(){}

    ConfluencePageSummary(PageSummary pageSummary) {
        this.pageSummary = pageSummary;
    }

    public PageSummary representation() {
        return pageSummary;
    }

    public String title() {
        return pageSummary.getTitle();
    }

    public String id() {
        return pageSummary.getId();
    }

}
