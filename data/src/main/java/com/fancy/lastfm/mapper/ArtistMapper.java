package com.fancy.lastfm.mapper;

import com.fancy.lastfm.entity.Album;
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

public class ArtistMapper implements JsonDeserializer<List<Album>> {

    private final static String ROOT_ELEMENT = "topalbums";
    private final static String LIST_ELEMENT = "album";

    @Override
    public List<Album> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        List<Album> albumList = new ArrayList<>();
        Gson gson = new Gson();

        JsonArray arrestList = json.getAsJsonObject().getAsJsonObject(ROOT_ELEMENT).getAsJsonArray(LIST_ELEMENT);
        for (JsonElement jsonElement : arrestList) {
            Album album = gson.fromJson(jsonElement, Album.class);
            albumList.add(album);
        }
        return albumList;
    }
}
