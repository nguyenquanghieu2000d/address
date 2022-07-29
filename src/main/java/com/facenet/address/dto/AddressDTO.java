package com.facenet.address.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AddressDTO {

    @JsonProperty("addressId")
    String addressId;

    @JsonProperty("addressName")
    String addressName;

    @JsonProperty("level")
    String level;

    @JsonProperty("listChild")
    List<AddressDTO> listChild;

    public void setEachListChild(AddressDTO addressDTO2) {
        listChild.add(addressDTO2);
    }

}
