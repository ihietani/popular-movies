package com.ihietani.popularmovies;

import com.ihietani.popularmovies.model.Movie;
import com.ihietani.popularmovies.model.Results;
import com.ihietani.popularmovies.utils.JsonUtils;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class JsonUtilsTest {

    @Test
    public void parseJson(){
        String json = "{\"page\":1,\"total_results\":7280,\"total_pages\":364,\"results\":[{\"vote_count\":1389,\"id\":19404,\"video\":false,\"vote_average\":9.2,\"title\":\"Dilwale Dulhania Le Jayenge\",\"popularity\":15.54307,\"poster_path\":\"/uC6TTUhPpQCmgldGyYveKRAu8JN.jpg\",\"original_language\":\"hi\",\"original_title\":\"aaas\",\"genre_ids\":[35,18,10749],\"backdrop_path\":\"/nl79FQ8xWZkhL3rDr1v2RFFR6J0.jpg\",\"adult\":false,\"overview\":\"Raj is a rich, carefree, happy-go-lucky second generation NRI. Simran is the daughter of Chaudhary Baldev Singh, who in spite of being an NRI is very strict about adherence to Indian values. Simran has left for India to be married to her childhood fiancé. Raj leaves for India with a mission at his hands, to claim his lady love under the noses of her whole family. Thus begins a saga.\",\"release_date\":\"1995-10-20\"}]}";
        Results movie = JsonUtils.parseMovieJson(json);
        String page = movie.getPage();
        assertEquals("1", page);
    }
}
