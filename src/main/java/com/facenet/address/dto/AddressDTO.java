package com.facenet.address.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AddressDTO {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("addressId")
    private String addressId;

    @JsonProperty("level")
    private Integer level;

    @JsonProperty("name")
    private String name;

    @JsonProperty("parentId")
    private Integer parentId;

    @JsonProperty("rootParentId")
    private Integer rootParentId;
}
