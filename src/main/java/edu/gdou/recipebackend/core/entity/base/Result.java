package edu.gdou.recipebackend.core.entity.base;


import lombok.Data;

@Data
public class Result<T> {
    private int code;
    private String message;
    private T data;

    public static <T> Result<T> ok(T data){
        Result<T> result = new Result<>();
        result.data = data;
        result.code = 200;
        return result;
    }

    public static <T> Result<T> ok(){
        Result<T> result = new Result<>();
        result.code = 200;
        return result;
    }


    public static <T> Result<T> error(String msg){
        Result<T> result = new Result<>();
        result.code = -1;
        result.message = msg;
        return result;
    }

}
