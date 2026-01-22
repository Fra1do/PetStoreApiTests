package swagger.petstore.enums;

import lombok.Getter;

public enum PetStatus {
    AVAILABLE("available"), PENDING("pending"), SOLD("sold");

    @Getter
    private final String value;

    PetStatus(String value) {
        this.value = value;
    }
}
