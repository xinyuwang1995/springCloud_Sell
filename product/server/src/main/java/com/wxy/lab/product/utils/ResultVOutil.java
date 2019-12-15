package com.wxy.lab.product.utils;

import com.wxy.lab.product.VO.ResultVO;

public class ResultVOutil {
    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("success");
        return resultVO;

    }
}
