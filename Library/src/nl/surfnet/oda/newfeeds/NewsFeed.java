package nl.surfnet.oda.newfeeds;

import java.util.Date;
import java.util.List;

import nl.surfnet.oda.newsitems.NewsItem;

/**
 * Represents a news feed
 *
 * @author Daniel Zolnai
 *
 */
public class NewsFeed {

    private String _id;
    private List<NewsItem> _items;
    private Date _updated;
    private String _url;
    private String _title;
    private String _description;

    /**
     * Empty constructor needed for conversion
     */
    public NewsFeed() {
    }

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        _id = id;
    }

    public List<NewsItem> getItems() {
        return _items;
    }

    public void setItems(List<NewsItem> items) {
        _items = items;
    }

    public Date getUpdatedDate() {
        return _updated;
    }

    public void setUpdatedDate(Date updated) {
        _updated = updated;
    }

    public String getResourceUrl() {
        return _url;
    }

    public void setResourceUrl(String url) {
        _url = url;
    }

    public String getTitle() {
        return _title;
    }

    public void setTitle(String title) {
        _title = title;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
    }
}
