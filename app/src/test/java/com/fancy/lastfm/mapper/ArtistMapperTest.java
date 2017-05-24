package com.fancy.lastfm.mapper;

import com.fancy.lastfm.entity.Album;
import com.fancy.lastfm.entity.Artist;
import com.fancy.lastfm.response.TopAlbumResponse;
import com.fancy.lastfm.response.TopArtistsResponse;
import com.google.gson.JsonParser;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * @author Oleg Mazhukin
 */
public class ArtistMapperTest {

    private String TEST_JSON = "{  \n" +
            "   \"topartists\":{  \n" +
            "      \"artist\":[  \n" +
            "         {  \n" +
            "            \"name\":\"David Bowie\",\n" +
            "            \"listeners\":\"3177102\",\n" +
            "            \"mbid\":\"5441c29d-3602-4898-b1a1-b77fa23b8e50\",\n" +
            "            \"url\":\"https://www.last.fm/music/David+Bowie\",\n" +
            "            \"streamable\":\"0\",\n" +
            "            \"image\":[  \n" +
            "               {  \n" +
            "                  \"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/31a51f6e3ec647c8997150ec837891c7.png\",\n" +
            "                  \"size\":\"small\"\n" +
            "               },\n" +
            "               {  \n" +
            "                  \"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/31a51f6e3ec647c8997150ec837891c7.png\",\n" +
            "                  \"size\":\"medium\"\n" +
            "               },\n" +
            "               {  \n" +
            "                  \"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/31a51f6e3ec647c8997150ec837891c7.png\",\n" +
            "                  \"size\":\"large\"\n" +
            "               },\n" +
            "               {  \n" +
            "                  \"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/31a51f6e3ec647c8997150ec837891c7.png\",\n" +
            "                  \"size\":\"extralarge\"\n" +
            "               },\n" +
            "               {  \n" +
            "                  \"#text\":\"https://lastfm-img2.akamaized.net/i/u/31a51f6e3ec647c8997150ec837891c7.png\",\n" +
            "                  \"size\":\"mega\"\n" +
            "               }\n" +
            "            ]\n" +
            "         } ] }}";

    @Test
    public void saveTopArtist() {
        ArtistMapper artistMapper = new ArtistMapper();

        TopArtistsResponse topArtistsResponse = artistMapper.deserialize(new JsonParser().parse(TEST_JSON), null, null);

        assertEquals(1, topArtistsResponse.getArtist().size());
        Artist artist = topArtistsResponse.getArtist().get(0);
        assertEquals(artist.getId(), "5441c29d-3602-4898-b1a1-b77fa23b8e50");
        assertEquals(artist.getName(), "David Bowie");
        assertEquals(artist.getListenersCount(), "3177102");
        assertEquals(artist.getImageUrl(), "https://lastfm-img2.akamaized.net/i/u/174s/31a51f6e3ec647c8997150ec837891c7.png");
    }
}

