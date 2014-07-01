package com.lkulig.confluence.client.page;

import com.lkulig.confluence.client.page.details.ConfluencePageDetails;
import org.codehaus.swizzle.confluence.Page;

public class ConfluencePage {

    private Page page;

    public ConfluencePage(ConfluencePageDetails confluencePageDetails) {
        this.page = new Page(confluencePageDetails.details());
    }

    public ConfluencePage(Page page) {
        this.page = page;
    }

    public Page representation() {
        return page;
    }

    public String title() {
        return page.getTitle();
    }

    public String id() {
        return page.getId();
    }

    public String content() {
       return page.getContent();
    }

    public void removeContent() {
        page.setContent("");
    }
}
