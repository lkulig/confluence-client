package com.lkulig.confluence.client.page.details;

import static com.google.common.collect.Maps.newHashMap;
import com.lkulig.confluence.client.util.builder.Buildable;
import java.util.Map;

public class ConfluencePageDetails implements Buildable {

    private static final String SPACE = "space";
    private static final String HOME_PAGE = "homePage";
    private static final String TITLE = "title";
    private static final String CONTENT = "content";
    private static final String PARENT_ID = "parentId";

    ConfluencePageDetails() {
    }

    private Map<String, Object> details = newHashMap();

    public void space(String space) {
        details.put(SPACE, space);
    }

    public void homePage() {
        details.put(HOME_PAGE, true);
    }

    public void normalPage() {
        details.put(HOME_PAGE, false);
    }

    public void title(String title) {
        details.put(TITLE, title);
    }

    public void content(String content) {
        details.put(CONTENT, content);
    }

    public void parent(String parentId) {
        details.put(PARENT_ID, parentId);
    }

    public Map<String, Object> details() {
        return details;
    }
}
