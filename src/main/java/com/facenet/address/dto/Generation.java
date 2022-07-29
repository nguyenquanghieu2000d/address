package com.facenet.address.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Data
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Generation {
    Integer childId;
    String childAddressId;
    String childName;
    String childType;
    Integer generationNumber;
    String parentAddressId;
    String parentName;
    String parentType;
}
