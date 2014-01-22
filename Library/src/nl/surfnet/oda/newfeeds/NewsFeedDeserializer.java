package nl.surfnet.oda.newfeeds;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import nl.surfnet.oda.EntityDeserializer;
import nl.surfnet.oda.newsitems.NewsItem;
import nl.surfnet.oda.newsitems.NewsItemDeserializer;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

/**
 * Deserialites a JSON to a NewsFeed object
 *
 * @author Daniel Zolnai
 *
 */
public class NewsFeedDeserializer extends EntityDeserializer<NewsFeed> {

    @Override
    public NewsFeed deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json == null || json.isJsonNull()) {
            return null;
        }
        JsonObject jsonNewsFeed = json.getAsJsonObject();
        NewsFeed newsFeed = new NewsFeed();
        newsFeed.setId(getAsStringNoNull(jsonNewsFeed.get("id")));
        newsFeed.setDescription(getAsStringNoNull(jsonNewsFeed.get("description")));
        newsFeed.setResourceUrl(getAsStringNoNull(jsonNewsFeed.get("url")));
        newsFeed.setTitle(getAsStringNoNull(jsonNewsFeed.get("title")));
        newsFeed.setUpdatedDate(getAsDateNoNull(jsonNewsFeed.get("updated")));
        // get the news items
        JsonArray jsonItems = jsonNewsFeed.get("items").getAsJsonArray();
        List<NewsItem> items = new ArrayList<NewsItem>();
        NewsItemDeserializer itemDeserializer = new NewsItemDeserializer();
        for (int i = 0; i < jsonItems.size(); ++i) {
            JsonElement jsonItem = jsonItems.get(i);
            NewsItem item = itemDeserializer.deserialize(jsonItem, typeOfT, context);
            items.add(item);
        }
        newsFeed.setItems(items);
        return newsFeed;
    }

}
