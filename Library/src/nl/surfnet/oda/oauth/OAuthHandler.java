package nl.surfnet.oda.oauth;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Handles the OAuth registration and session handling.
 *
 * @author Daniel Zolnai
 *
 */
public class OAuthHandler {

    private final static String _clientId = "example_id"; // use your own at your version
    private final static String _clientSecret = "example_secret"; // use your own at your version

    private interface OAuthInterface {
        @FormUrlEncoded
        @POST("/oauth2/access_token")
        public void getAccessToken(@Field("username") String username, @Field("password") String password, @Field("client_id") String clientId, @Field("client_secret") String clientSecret, @Field("grant_type") String grantType,
                Callback<TokenData> cb);

        @FormUrlEncoded
        @POST("/oauth2/access_token")
        public void refreshAccessToken(@Field("grant_type") String grantType, @Field("client_id") String clientId, @Field("client_secret") String clientSecret, @Field("refresh_token") String refreshToken, Callback<TokenData> cb);
    }

    public interface LoginHandler {
        public void success();

        public void failure(String message);
    }

    private OAuthInterface _oauth;
    private TokenData _tokenData = null;

    public OAuthHandler(String baseUrl) {
        Gson gson = new GsonBuilder().registerTypeAdapter(TokenData.class, new TokenDataDeserializer()).create();
        GsonConverter converter = new GsonConverter(gson);
        RestAdapter restAdapter = new RestAdapter.Builder().setServer(baseUrl).setConverter(converter).build();
        _oauth = restAdapter.create(OAuthInterface.class);
    }

    public OAuthHandler(String baseUrl, TokenData tokenData) {
        this(baseUrl);
        _tokenData = tokenData;
    }

    /**
     * Asks for a new access token using the username and the password
     *
     * @param username The username of the user
     * @param password The password of the user
     * @param handler Callback for success or failure
     */
    public void login(String username, String password, final LoginHandler handler) {
        _oauth.getAccessToken(username, password, _clientId, _clientSecret, "password", new Callback<TokenData>() {

            @Override
            public void success(TokenData tokenData, Response response) {
                _tokenData = tokenData;
                handler.success();
            }

            @Override
            public void failure(RetrofitError error) {
                handler.failure(error.getMessage());
            }
        });
    }

    /**
     * Refreshes the access token using the refresh token.
     *
     * @param handler Callback handler
     */
    public void refreshToken(final LoginHandler handler){
        if (_tokenData == null || _tokenData.getRefreshToken() == null) {
            handler.failure("No refresh token available");
            return;
        }
        _oauth.refreshAccessToken("refresh_token", _clientId, _clientSecret, _tokenData.getRefreshToken(), new Callback<TokenData>() {

            @Override
            public void success(TokenData tokenData, Response response) {
                _tokenData = tokenData;
                handler.success();
            }

            @Override
            public void failure(RetrofitError error) {
                handler.failure(error.getMessage());
            }
        });

    }

    /**
     * The implementing app is responsible for saving token datas.
     *
     * @return The java object holding all the data of the tokens.
     */
    public TokenData getTokenData() {
        return _tokenData;
    }

    /**
     * Returns whether there is an access token available
     * 
     * @return True if there is an access token available, false if not.
     */
    public boolean isLoggedIn() {
        if (_tokenData != null && _tokenData.getAccessToken() != null) {
            return true;
        }
        return false;
    }
}
