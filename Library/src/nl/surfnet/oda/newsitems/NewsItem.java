package nl.surfnet.oda.newsitems;

import java.util.Date;
import java.util.List;

/**
 * Represents a news item.
 *
 * @author Daniel Zolnai
 *
 */
public class NewsItem {

    private List<String> _newsFeedUrls;
    private String _resourceUrl;
    private Date _pubDate;
    private String _title;
    private String _author;
    private String _imageUrl;
    private String _link;
    private String _content;

    /**
     * Empty constructor needed for conversion
     */
    public NewsItem() {
    }

    public List<String> getNewsFeedUrls() {
        return _newsFeedUrls;
    }

    public void setNewsFeedUrls(List<String> newsFeedUrls) {
        _newsFeedUrls = newsFeedUrls;
    }

    public String getResourceUrl() {
        return _resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        _resourceUrl = resourceUrl;
    }

    public Date getPublicationDate() {
        return _pubDate;
    }

    public void setPublicationDate(Date pubDate) {
        _pubDate = pubDate;
    }

    public String getTitle() {
        return _title;
    }

    public void setTitle(String title) {
        _title = title;
    }

    public String getAuthor() {
        return _author;
    }

    public void setAuthor(String author) {
        _author = author;
    }

    public String getImageUrl() {
        return _imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        _imageUrl = imageUrl;
    }

    public String getLink() {
        return _link;
    }

    public void setLink(String link) {
        _link = link;
    }

    public String getContent() {
        return _content;
    }

    public void setContent(String content) {
        _content = content;
    }
}
