package br.com.ibarra.clima.api.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.ibarra.clima.api.services.interfaces.YahooWeatherService;
import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by joaoibarra on 19/01/16.
 */
public class YahooWeatherServiceImpl {
    public static YahooWeatherService yahooWeatherService;

    public static YahooWeatherService getInstance(){
        if(yahooWeatherService ==null)
            return new YahooWeatherServiceImpl().create();

        return yahooWeatherService;
    }

    private YahooWeatherServiceImpl(){}

    public YahooWeatherService create() {
        final OkHttpClient okHttpClient = new OkHttpClient();
        Gson gson = new GsonBuilder()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://query.yahooapis.com")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        yahooWeatherService = retrofit.create(YahooWeatherService.class);
        return yahooWeatherService;
    }
}
