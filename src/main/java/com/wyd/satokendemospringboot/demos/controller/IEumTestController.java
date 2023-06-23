package com.wyd.satokendemospringboot.demos.controller;

import com.wyd.satokendemospringboot.demos.common.result.MyResult;
import com.wyd.satokendemospringboot.demos.common.result.MyResultUtil;
import com.wyd.satokendemospringboot.demos.constants.TestIEum;
import com.wyd.satokendemospringboot.demos.entity.dto.IEumContainerDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("rawtypes")
@Api(tags = "枚举类转换测试模块")
@RestController("/ieum")
public class IEumTestController {

    @PostMapping("/test")
    @ApiOperation(value = "获取SpringMVC处理后的传参，枚举类更改一下，然后返回")
    MyResult testIEum(@RequestBody IEumContainerDTO iEumContainerDTO){
        System.out.println(iEumContainerDTO);
        System.out.println("===================");
        // 更改一下枚举类
        TestIEum iEum = iEumContainerDTO.getTestIEum();
        if (iEum == null) {
            return MyResultUtil.getBussinessErrorResult("没有转换枚举类属性成功...");
        }
        if (TestIEum.YXY.getValue().equals(iEum.getValue())){
            iEumContainerDTO.setTestIEum(TestIEum.WYD);
        } else iEumContainerDTO.setTestIEum(TestIEum.YXY);
        System.out.println(iEumContainerDTO);

        // 返回结果
        MyResult<IEumContainerDTO> result = new MyResult<>();
        result.setData(iEumContainerDTO);
        return MyResultUtil.getTrueResult(result);
    }
}
