package com.fancy.lastfm.mapper;

import com.fancy.lastfm.entity.Album;
import com.fancy.lastfm.response.TopAlbumResponse;
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

public class AlbumMapper implements JsonDeserializer<TopAlbumResponse> {

    private final static String ROOT_ELEMENT = "topalbums";
    private final static String LIST_ELEMENT = "album";
    private final static String IMAGE_TAG = "image";
    private final static int LARGE_IMAGE_POSITION = 2;

    @Override
    public TopAlbumResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        TopAlbumResponse response = new TopAlbumResponse();

        List<Album> albumList = new ArrayList<>();
        Gson gson = new Gson();

        JsonArray jsonArray = json.getAsJsonObject().getAsJsonObject(ROOT_ELEMENT).getAsJsonArray(LIST_ELEMENT);
        for (JsonElement jsonElement : jsonArray) {
            Album album = gson.fromJson(jsonElement, Album.class);
            String url = jsonElement.getAsJsonObject().getAsJsonArray(IMAGE_TAG).get(LARGE_IMAGE_POSITION).getAsJsonObject().get("#text").getAsString();
            album.setUrl(url);
            albumList.add(album);
        }

        response.setAlbumList(albumList);
        return response;
    }
}
