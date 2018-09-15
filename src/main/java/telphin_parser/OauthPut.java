package telphin_parser;
import com.google.gson.annotations.SerializedName;

public class OauthPut {
    @SerializedName("grant_type")
    private String grantType;

 //   @SerializedName("redirect_uri")
 //   private String redirectUri;

    @SerializedName("client_id")
    private String clientId;

    @SerializedName("client_secret")
    private String clientSecret;
    public OauthPut(String clientId, String clientSecret)
    {
        this.clientId=clientId;
        this.clientSecret=clientSecret;
        this.grantType="client_credentials";
  //      this.redirectUri=redirectUri;
    }
}
