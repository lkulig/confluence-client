package com.lkulig.confluence.client.space;

import static com.google.common.collect.FluentIterable.from;
import com.google.common.base.Function;
import org.codehaus.swizzle.confluence.SpaceSummary;
import java.util.List;

public class ConfluenceSpaceSummaryFunctions {

    private ConfluenceSpaceSummaryFunctions() {
    }

    public static final Function<SpaceSummary, ConfluenceSpaceSummary> TO_CONFLUENCE_SPACE_SUMMARY =
            new Function<SpaceSummary, ConfluenceSpaceSummary>() {

                @Override
                public ConfluenceSpaceSummary apply(SpaceSummary spaceSummary) {
                    return new ConfluenceSpaceSummary(spaceSummary);
                }
            };

    public static final Function<List<SpaceSummary>, List<ConfluenceSpaceSummary>> TO_CONFLUENCE_SPACE_SUMMARIES =
            new Function<List<SpaceSummary>, List<ConfluenceSpaceSummary>>() {

                @Override
                public List<ConfluenceSpaceSummary> apply(List<SpaceSummary> spaceSummaries) {
                    return from(spaceSummaries).transform(TO_CONFLUENCE_SPACE_SUMMARY).toList();
                }
            };
}
