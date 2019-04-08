package com.baihoomuch.cloud.utils;

import com.baihoomuch.cloud.vo.ResultVO;

/**
 * Description: sell
 * auther Administrator on 2018/6/27
 *
 * ResultVO 通用方法集成
 */
public class ResultVOUtil {
    /**
     * 含有参数成功传值
     * @param object
     * @return
     */
    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(200);
        resultVO.setMsg("成功");
        resultVO.setData(object);
        return resultVO;
    }

    /**
     * 不含有参数，成功
     * @return
     */
    public static ResultVO success(){
        return success(null);
    }
    public static ResultVO error(Integer code ,String msg){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
