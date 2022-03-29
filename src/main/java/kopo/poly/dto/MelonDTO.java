package kopo.poly.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
//변수에 저장된 값이 기본값이 아닌 경우 제외
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
public class MelonDTO {
    String collectTime;
    String song;
    String singer;
    int singerCnt; //차트에 등록된 가수별 노래 수
}
