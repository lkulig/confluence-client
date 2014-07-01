package com.lkulig.confluence.client.attachment;

import static com.google.common.collect.FluentIterable.from;
import com.google.common.base.Function;
import org.codehaus.swizzle.confluence.Attachment;
import java.util.List;

public class ConfluenceAttachmentFunctions {

    private ConfluenceAttachmentFunctions() {
    }

    public static final Function<Attachment, ConfluenceAttachment> TO_CONFLUENCE_ATTACHMENT =
            new Function<Attachment, ConfluenceAttachment>() {

        @Override
        public ConfluenceAttachment apply(Attachment attachment) {
            return new ConfluenceAttachment(attachment);
        }
    };

    public static final Function<List<Attachment>, List<ConfluenceAttachment>> TO_CONFLUENCE_ATTACHMENTS =
            new Function<List<Attachment>, List<ConfluenceAttachment>>() {

        @Override
        public List<ConfluenceAttachment> apply(List<Attachment> attachments) {
            return from(attachments).transform(TO_CONFLUENCE_ATTACHMENT).toList();
        }
    };
}
