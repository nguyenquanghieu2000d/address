package com.facenet.address.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class AddressRawDTO {

    @JsonProperty("Mã")
    String id;

    @JsonProperty("Tên")
    String ten;

    @JsonProperty("Tên Tiếng Anh")
    String tenTiengAnh;

    @JsonProperty("Cấp")
    String cap;

    @JsonProperty("Mã QH")
    String idQuanHuyen;

    @JsonProperty("Quận Huyện")
    String tenQuanHuyen;

    @JsonProperty("Mã TP")
    String idTP;

    @JsonProperty("Tỉnh / Thành Phố")
    String tenTP;
}
