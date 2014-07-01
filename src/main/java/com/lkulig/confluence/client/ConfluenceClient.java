package com.lkulig.confluence.client;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.of;
import static com.google.common.collect.Lists.newArrayList;
import static com.lkulig.confluence.client.attachment.ConfluenceAttachmentFunctions.TO_CONFLUENCE_ATTACHMENT;
import static com.lkulig.confluence.client.attachment.ConfluenceAttachmentFunctions.TO_CONFLUENCE_ATTACHMENTS;
import static com.lkulig.confluence.client.page.summary.ConfluencePageSummaryFunctions.TO_CONFLUENCE_PAGE_SUMMARY_LIST;
import static com.lkulig.confluence.client.space.ConfluenceSpaceSummaryFunctions.TO_CONFLUENCE_SPACE_SUMMARIES;
import static java.lang.Long.parseLong;
import static org.slf4j.LoggerFactory.getLogger;
import com.google.common.base.Optional;
import com.lkulig.confluence.client.attachment.ConfluenceAttachment;
import com.lkulig.confluence.client.page.ConfluencePage;
import com.lkulig.confluence.client.page.summary.ConfluencePageSummary;
import com.lkulig.confluence.client.space.ConfluenceSpaceSummary;
import org.codehaus.swizzle.confluence.Confluence;
import org.codehaus.swizzle.confluence.SwizzleException;
import org.slf4j.Logger;
import java.util.List;

public class ConfluenceClient {

    private static final Logger LOG = getLogger(ConfluenceClient.class);
    private ConfluenceClientConfig config;
    private Confluence confluence;

    public ConfluenceClient(ConfluenceClientConfig config, Confluence confluence) {
        this.config = config;
        this.confluence = confluence;
    }

    public void login() {
        try {
            confluence.login(config.getUsername(), config.getPassword());
        } catch (SwizzleException e) {
            LOG.error("Failed to login to Confluence", e);
        }
    }

    public Optional<ConfluencePage> addOrUpdatePage(ConfluencePage page) {
        try {
            return of(new ConfluencePage(confluence.storePage(page.representation())));
        } catch (SwizzleException e) {
            LOG.error("Failed to add page: {}", page.title(), e);
            return absent();
        }
    }

    public boolean pageExists(String spaceName, String pageName) {
        try {
            return confluence.getPage(spaceName, pageName) != null;
        } catch (SwizzleException e) {
            LOG.error("Failed to find page: {} in space: {}", pageName, spaceName, e);
        }
        return false;
    }

    public Optional<ConfluencePage> getPage(String spaceName, String pageName) {
        try {
            return of(new ConfluencePage(confluence.getPage(spaceName, pageName)));
        } catch (SwizzleException e) {
            LOG.error("Failed to retrieve page: {}, from space: {}", pageName, spaceName, e);
            return absent();
        }
    }

    public Optional<ConfluencePage> getPage(String pageId) {
        try {
            return of(new ConfluencePage(confluence.getPage(pageId)));
        } catch (SwizzleException e) {
            LOG.error("Failed to retrieve page with id: {}", pageId, e);
            return absent();
        }
    }

    public Optional<ConfluencePage> getPage(ConfluencePageSummary confluencePageSummary) {
        try {
            return of(new ConfluencePage(confluence.getPage(confluencePageSummary.representation())));
        } catch (SwizzleException e) {
            LOG.error("Failed to retrieve page: {}", confluencePageSummary.title(), e);
            return absent();
        }
    }

    @SuppressWarnings("unchecked")
    public List<ConfluencePageSummary> getPages(String spaceKey) {
        try {
            return confluence.getPages(spaceKey);
        } catch (SwizzleException e) {
            LOG.error("Failed to retrieve all pages from space: {}", spaceKey, e);
            return newArrayList();
        }
    }

    public void exportSpace(String spaceName, ExportType exportType) {
        try {
            confluence.exportSpace(spaceName, exportType.asString());
        } catch (SwizzleException e) {
            LOG.error("Failed to export space: {}", spaceName, e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<ConfluencePageSummary> getDescendentsOf(ConfluencePage confluencePage) {
        try {
            return TO_CONFLUENCE_PAGE_SUMMARY_LIST.apply(confluence.getDescendents(confluencePage.id()));
        } catch (SwizzleException e) {
            LOG.error("Failed to get descendants pages of page with id: {}", confluencePage.id(), e);
            return newArrayList();
        }
    }

    @SuppressWarnings("unchecked")
    public List<ConfluencePageSummary> getChildrenOf(ConfluencePage confluencePage) {
        try {
            return TO_CONFLUENCE_PAGE_SUMMARY_LIST.apply(confluence.getChildren(confluencePage.id()));
        } catch (SwizzleException e) {
            LOG.error("Failed to retrieve children pages of page with id: {}", confluencePage.id(), e);
            return newArrayList();
        }
    }

    @SuppressWarnings("unchecked")
    public List<ConfluencePageSummary> getAllPagesOf(String spaceName) {
        try {
            return TO_CONFLUENCE_PAGE_SUMMARY_LIST.apply(confluence.getPages(spaceName));
        } catch (SwizzleException e) {
            LOG.error("Failed to retrieve all pages of space: {}", spaceName, e);
            return newArrayList();
        }
    }

    public void removePage(ConfluencePageSummary confluencePageSummary) {
        try {
            confluence.removePage(confluencePageSummary.id());
        } catch (SwizzleException e) {
            LOG.error("Failed to remove page with id: {}", confluencePageSummary.id(), e);
        }
    }

    @SuppressWarnings("ConstantConditions")
    public Optional<ConfluenceAttachment> addAttachment(ConfluenceAttachment attachment, byte[] data) {
        long pageId = parseLong(attachment.pageId());
        try {
            return of(TO_CONFLUENCE_ATTACHMENT.apply(confluence.addAttachment(pageId, attachment.representation(), data)));
        } catch (SwizzleException e) {
            LOG.error("Failed to add attachment: {}", attachment.fileName(), e);
            return absent();
        }
    }

    public void logout() {
        try {
            confluence.logout();
        } catch (SwizzleException e) {
            LOG.error("Failed to logout from Confluence");
        }
    }

    @SuppressWarnings("unchecked")
    public List<ConfluenceAttachment> getAttachments(String pageId) {
        try {
            return TO_CONFLUENCE_ATTACHMENTS.apply(confluence.getAttachments(pageId));
        } catch (SwizzleException e) {
            LOG.error("Failed to retrieve attachments of page with id: {}", pageId);
            return newArrayList();
        }
    }

    public Optional<byte[]> getAttachmentData(String pageId, String fileName, String version) {
        try {
            return of(confluence.getAttachmentData(pageId, fileName, version));
        } catch (SwizzleException e) {
            LOG.error("Failed to get attachment with name: {}, version: {}, for page with id: {}", fileName, version,
                    pageId, e);
            return absent();
        }
    }

    @SuppressWarnings("unchecked")
    public List<ConfluenceSpaceSummary> getSpaces() {
        try {
            return TO_CONFLUENCE_SPACE_SUMMARIES.apply(confluence.getSpaces());
        } catch (Exception e) {
            LOG.error("Failed to retrieve spaces from Confluence", e);
            return newArrayList();
        }
    }
}
