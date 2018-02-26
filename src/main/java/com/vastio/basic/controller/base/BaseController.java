package com.vastio.basic.controller.base;

import com.vastio.basic.entity.response.ResponseResult;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class BaseController {
    protected <T> ResponseResult<T> success(List<T> result, Integer total) {
        ResponseResult<T> responseResult = new ResponseResult<>();
        responseResult.setCode(HttpServletResponse.SC_OK);
        responseResult.setTotal(total);
        responseResult.setResults(result);
        return responseResult;
    }

    protected <T> ResponseResult<T> success(T result) {
        ResponseResult<T> responseResult = new ResponseResult<>();
        responseResult.setCode(HttpServletResponse.SC_OK);
        List<T> list = new ArrayList<>();
        list.add(result);
        responseResult.setResults(list);
        return responseResult;
    }

    protected <T> ResponseResult<T> success(String message) {
        ResponseResult<T> responseResult = new ResponseResult<>();
        responseResult.setCode(HttpServletResponse.SC_OK);
        responseResult.setMessage(message);
        return responseResult;
    }

    protected <T> ResponseResult<T> error(String message, Integer code) {
        ResponseResult<T> responseResult = new ResponseResult<>();
        responseResult.setMessage(message);
        responseResult.setCode(code);
        return responseResult;
    }
}
