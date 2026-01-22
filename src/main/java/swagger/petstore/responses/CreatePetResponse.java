package swagger.petstore.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import swagger.petstore.enums.PetStatus;
import swagger.petstore.requests.CreatePetRequest;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatePetResponse {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("category")
    private Category category;
    @JsonProperty("photoUrls")
    private List<String> photoUrls;
    @JsonProperty("tags")
    private List<Tag> tags;
    @JsonProperty("status")
    private String status;

    @Data
    private static class Category {
        @JsonProperty("id")
        private Integer id;
        @JsonProperty("name")
        private String name;
    }

    @Data
    private static class Tag {
        @JsonProperty("id")
        private Integer id;
        @JsonProperty("name")
        private String name;
    }

    public static CreatePetResponse build() {
        String[] photos = new String[]{"One", "Two", "Three"};
        return new CreatePetResponse().setCategory(new Category().setName("friendly").setId(1))
                .setTags(singletonList(new Tag().setName("firstTag").setId(1)))
                .setPhotoUrls(Arrays.asList(photos))
                .setStatus(PetStatus.AVAILABLE.getValue());
    }
}
