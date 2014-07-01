package com.lkulig.confluence.client.page.summary;

import static com.google.common.collect.FluentIterable.from;
import com.google.common.base.Function;
import org.codehaus.swizzle.confluence.PageSummary;
import java.util.List;

public class ConfluencePageSummaryFunctions {

    private ConfluencePageSummaryFunctions() {
    }

    public static final Function<PageSummary, ConfluencePageSummary> TO_CONFLUENCE_PAGE_SUMMARY =
            new Function<PageSummary, ConfluencePageSummary>() {

        @Override
        public ConfluencePageSummary apply(PageSummary pageSummary) {
            return new ConfluencePageSummary(pageSummary);
        }
    };

    public static final Function<List<PageSummary>, List<ConfluencePageSummary>> TO_CONFLUENCE_PAGE_SUMMARY_LIST =
            new Function<List<PageSummary>, List<ConfluencePageSummary>>() {

        @Override
        public List<ConfluencePageSummary> apply(List<PageSummary> input) {
            return from(input).transform(TO_CONFLUENCE_PAGE_SUMMARY).toList();
        }
    };
}
