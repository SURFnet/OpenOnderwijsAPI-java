package nl.surfnet.oda.oauth;

import java.util.Date;

/**
 * Holds the data about the OAuth2 tokens.
 *
 * @author Daniel Zolnai
 *
 */
public class TokenData {
    private String _accessToken;
    private String _refreshToken;
    private Date _expires;

    public String getAccessToken() {
        return _accessToken;
    }

    public void setAccessToken(String accessToken) {
        _accessToken = accessToken;
    }

    public String getRefreshToken() {
        return _refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        _refreshToken = refreshToken;
    }

    public Date getExpiryDate() {
        return _expires;
    }

    public void setExpiryDate(Date expires) {
        _expires = expires;
    }

    public boolean isTokenAvailable() {
        return _accessToken != null;
    }
}
