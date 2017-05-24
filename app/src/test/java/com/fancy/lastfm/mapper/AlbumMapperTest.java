package com.fancy.lastfm.mapper;

import com.fancy.lastfm.entity.Album;
import com.fancy.lastfm.response.TopAlbumResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONObject;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * @author Oleg Mazhukin
 */
public class AlbumMapperTest {

    private String TEST_JSON = "{  \n" +
            "   \"topalbums\":{  \n" +
            "      \"album\":[  \n" +
            "         {  \n" +
            "            \"name\":\"21\",\n" +
            "            \"mbid\":\"5441c29d-3602-4898-b1a1-b77fa23b8e50\",\n" +
            "            \"playcount\":52489476,\n" +
            "            \"url\":\"https://www.last.fm/music/Adele/21\",\n" +
            "            \"artist\":{  \n" +
            "               \"name\":\"Adele\"\n" +
            "            },\n" +
            "            \"image\":[  \n" +
            "               {  \n" +
            "                  \"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/c894af1e6a735b9bbb2a0312c7719f40.png\",\n" +
            "                  \"size\":\"small\"\n" +
            "               },\n" +
            "               {  \n" +
            "                  \"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/c894af1e6a735b9bbb2a0312c7719f40.png\",\n" +
            "                  \"size\":\"medium\"\n" +
            "               },\n" +
            "               {  \n" +
            "                  \"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/c894af1e6a735b9bbb2a0312c7719f40.png\",\n" +
            "                  \"size\":\"large\"\n" +
            "               },\n" +
            "            ]\n" +
            "         } ] }}";


    @Test
    public void saveTopArtist() {
        AlbumMapper albumMapper = new AlbumMapper();

        TopAlbumResponse topAlbumResponse = albumMapper.deserialize(new JsonParser().parse(TEST_JSON), null, null);

        assertEquals(1, topAlbumResponse.getAlbumList().size());
        Album savedAlbum = topAlbumResponse.getAlbumList().get(0);
        assertEquals(savedAlbum.getName(), "21");
        assertEquals(savedAlbum.getPlayCount(), Integer.valueOf(52489476));
        assertEquals(savedAlbum.getUrl(), "https://lastfm-img2.akamaized.net/i/u/174s/c894af1e6a735b9bbb2a0312c7719f40.png");
        assertEquals(savedAlbum.getArtist(), "Adele");
    }
}
