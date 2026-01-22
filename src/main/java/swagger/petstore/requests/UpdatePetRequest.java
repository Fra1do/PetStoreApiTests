package swagger.petstore.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdatePetRequest {

    @JsonProperty("petId")
    private Integer petId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("status")
    private String status;

    public static UpdatePetRequest build() {
        return new UpdatePetRequest();
    }

}
