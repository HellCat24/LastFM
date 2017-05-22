package com.fancy.lastfm.mapper;

import com.fancy.lastfm.entity.Artist;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Oleg Mazhukin
 */

public class AlbumMapper implements JsonDeserializer<List<Artist>> {

    private final static String ROOT_ELEMENT = "topartists";
    private final static String LIST_ELEMENT = "artist";

    @Override
    public List<Artist> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        List<Artist> albumList = new ArrayList<>();
        Gson gson = new Gson();

        JsonArray arrestList = json.getAsJsonObject().getAsJsonObject(ROOT_ELEMENT).getAsJsonArray(LIST_ELEMENT);
        for (JsonElement jsonElement : arrestList) {
            Artist artist = gson.fromJson(jsonElement, Artist.class);
            albumList.add(artist);
        }
        return albumList;
    }
}
