package swagger.petstore.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdatePetResponse {

    @JsonProperty("code")
    private Integer code;

    @JsonProperty("type")
    private String type;

    @JsonProperty("message")
    private String message;

    public static UpdatePetResponse build() {
        return new UpdatePetResponse();
    }
}
