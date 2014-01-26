package nl.surfnet.oda.newsitems;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import nl.surfnet.oda.EntityDeserializer;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

/**
 * Deserializes a JSON to a NewsItem
 *
 * @author Daniel Zolnai
 *
 */
public class NewsItemDeserializer extends EntityDeserializer<NewsItem> {

    @Override
    public NewsItem deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json == null || json.isJsonNull()) {
            return null;
        }
        JsonObject newsItemJson = json.getAsJsonObject();
        NewsItem newsItem = new NewsItem();
        newsItem.setId(getAsStringNoNull(newsItemJson.get("id")));
        newsItem.setAuthor(getAsStringNoNull(newsItemJson.get("author")));
        newsItem.setContent(getAsStringNoNull(newsItemJson.get("content")));
        newsItem.setImageUrl(getAsStringNoNull(newsItemJson.get("imageUrl")));
        newsItem.setLink(getAsStringNoNull(newsItemJson.get("link")));
        newsItem.setLastModified(getAsDateNoNull(newsItemJson.get("lastModified")));
        newsItem.setResourceUrl(getAsStringNoNull(newsItemJson.get("url")));
        newsItem.setTitle(getAsStringNoNull(newsItemJson.get("title")));
        newsItem.setPublicationDate(getAsDateNoNull(newsItemJson.get("pubDate")));
        // get the news feed urls
        JsonArray jsonFeedUrls = newsItemJson.get("feeds").getAsJsonArray();
        List<String> feedUrls = new ArrayList<String>();
        for (int i = 0; i < jsonFeedUrls.size(); ++i) {
            feedUrls.add(jsonFeedUrls.get(i).getAsString());
        }
        newsItem.setNewsFeedUrls(feedUrls);
        return newsItem;
    }

}
