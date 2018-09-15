package telphin_parser;
import com.google.gson.annotations.SerializedName;

public class OauthRes {
    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("token_type")
    private String tokenType;

    @SerializedName("expires_in")
    private String expiresIn;

    @SerializedName("scope")
    private String scope;
    public OauthRes(){
    }

    public String getAccessToken() {
        return accessToken;
    }
}
