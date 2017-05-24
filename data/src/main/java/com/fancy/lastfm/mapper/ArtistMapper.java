package com.fancy.lastfm.mapper;

import com.fancy.lastfm.entity.Artist;
import com.fancy.lastfm.response.TopArtistsResponse;
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

public class ArtistMapper implements JsonDeserializer<TopArtistsResponse> {

    private final static String ROOT_ELEMENT = "topartists";
    private final static String LIST_ELEMENT = "artist";
    private final static String IMAGE_TAG = "image";
    private final static String IMAGE_URL = "#text";
    private final static int LARGE_IMAGE_POSITION = 2;

    @Override
    public TopArtistsResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        TopArtistsResponse response = new TopArtistsResponse();

        List<Artist> artistList = new ArrayList<>();
        Gson gson = new Gson();

        JsonArray jsonArray = json.getAsJsonObject().getAsJsonObject(ROOT_ELEMENT).getAsJsonArray(LIST_ELEMENT);
        for (JsonElement jsonElement : jsonArray) {
            Artist artist = gson.fromJson(jsonElement, Artist.class);
            String url = jsonElement.getAsJsonObject().getAsJsonArray(IMAGE_TAG).get(LARGE_IMAGE_POSITION).getAsJsonObject().get(IMAGE_URL).getAsString();
            artist.setImageUrl(url);
            artistList.add(artist);
        }

        response.setArtist(artistList);
        return response;
    }
}
