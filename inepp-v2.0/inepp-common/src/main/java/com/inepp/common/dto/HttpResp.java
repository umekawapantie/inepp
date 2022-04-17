package com.inepp.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
@ApiModel(description = "http响应")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HttpResp {

    @ApiModelProperty(value = "响应代码")
    private int code;
    @ApiModelProperty(value  = "响应信息")
    private String msg;
    @ApiModelProperty(value ="响应内容")
    private Object results;
    @ApiModelProperty(value = "响应日期")
    private LocalDate date;
}
