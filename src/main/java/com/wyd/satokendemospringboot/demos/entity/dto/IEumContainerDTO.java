package com.wyd.satokendemospringboot.demos.entity.dto;

import com.wyd.satokendemospringboot.demos.constants.TestIEum;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class IEumContainerDTO implements Serializable {

    @ApiModelProperty(notes = "测试参数，随便加的", name = "testName")
    private String testName;

    @ApiModelProperty(notes = "枚举类", name = "testIEum")
    private TestIEum testIEum;

}
