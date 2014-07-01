package com.lkulig.confluence.client;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.of;
import static com.google.common.collect.Lists.newArrayList;
import static java.lang.Long.parseLong;
import static java.util.Collections.emptyList;
import static org.slf4j.LoggerFactory.getLogger;
import com.google.common.base.Optional;
import org.codehaus.swizzle.confluence.*;
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

    public Optional<Page> addOrUpdatePage(Page page) {
        try {
            return of(confluence.storePage(page));
        } catch (SwizzleException e) {
            LOG.error("Failed to add page: {}", page.getTitle(), e);
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

    public Optional<Page> getPage(String spaceName, String pageName) {
        try {
            return of(confluence.getPage(spaceName, pageName));
        } catch (SwizzleException e) {
            LOG.error("Failed to retrieve page: {}, from space: {}", pageName, spaceName, e);
            return absent();
        }
    }

    public Optional<Page> getPage(String pageId) {
        try {
            return of(confluence.getPage(pageId));
        } catch (SwizzleException e) {
            LOG.error("Failed to retrieve page with id: {}", pageId, e);
            return absent();
        }
    }

    public Optional<Page> getPage(PageSummary pageSummary) {
        try {
            return of(confluence.getPage(pageSummary));
        } catch (SwizzleException e) {
            LOG.error("Failed to retrieve page: {}", pageSummary.getTitle(), e);
            return absent();
        }
    }

    @SuppressWarnings("unchecked")
    public List<PageSummary> getPages(String spaceKey) {
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
    public List<PageSummary> getDescendentsOf(PageSummary pageSummary) {
        try {
            return (List<PageSummary>) confluence.getDescendents(pageSummary.getId());
        } catch (SwizzleException e) {
            LOG.error("Failed to get descendants pages of: {}", pageSummary.getTitle(), e);
            return newArrayList();
        }
    }

    @SuppressWarnings("unchecked")
    public List<PageSummary> getChildrenOf(PageSummary pageSummary) {
        try {
            return (List<PageSummary>) confluence.getChildren(pageSummary.getId());
        } catch (SwizzleException e) {
            LOG.error("Failed to retrieve children pages of: {}", pageSummary.getTitle(), e);
            return newArrayList();
        }
    }

    @SuppressWarnings("unchecked")
    public List<PageSummary> getAllPagesOf(String spaceName) {
        try {
            return (List<PageSummary>) confluence.getPages(spaceName);
        } catch (SwizzleException e) {
            LOG.error("Failed to retrieve all pages of space: {}", spaceName, e);
            return newArrayList();
        }
    }

    public void removePage(PageSummary page) {
        try {
            confluence.removePage(page.getId());
        } catch (SwizzleException e) {
            LOG.error("Failed to remove page: {}", page.getTitle(), e);
        }
    }

    public Optional<Attachment> addAttachment(Attachment attachment, byte[] data) {
        long pageId = parseLong(attachment.getPageId());
        try {
            return of(confluence.addAttachment(pageId, attachment, data));
        } catch (SwizzleException e) {
            LOG.error("Failed to add attachment: {}", attachment.getFileName(), e);
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
    public List<Attachment> getAttachments(String pageId) {
        try {
            return confluence.getAttachments(pageId);
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
    public List<SpaceSummary> getSpaces() {
        try {
            return confluence.getSpaces();
        } catch (Exception e) {
            LOG.error("Failed to retrieve spaces from Confluence", e);
            return emptyList();
        }
    }
}
