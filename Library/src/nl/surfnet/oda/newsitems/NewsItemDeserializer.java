package nl.surfnet.oda.newsitems;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
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
        Integer id = getAsIntegerNoNull(newsItemJson.get("id"));
        if (id == null) {
            newsItem.setId(null);
        } else {
            newsItem.setId(id.toString());
        }
        newsItem.setAuthor(getAsStringNoNull(newsItemJson.get("author")));
        newsItem.setContent(getAsStringNoNull(newsItemJson.get("content")));
        newsItem.setImageUrl(getAsStringNoNull(newsItemJson.get("imageUrl")));
        newsItem.setLink(getAsStringNoNull(newsItemJson.get("link")));
        newsItem.setResourceUrl(getAsStringNoNull(newsItemJson.get("url")));
        newsItem.setTitle(getAsStringNoNull(newsItemJson.get("title")));
        // parse the date
        String date = getAsStringNoNull(newsItemJson.get("pubDate"));
        if (date == null) {
            newsItem.setPublicationDate(null);
        } else {
            // convert the date
            try {
            Date pubDate = ISO8601.toCalendar(date).getTime();
            newsItem.setPublicationDate(pubDate);
            } catch (ParseException e) {
                newsItem.setPublicationDate(null);
            }
        }
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
