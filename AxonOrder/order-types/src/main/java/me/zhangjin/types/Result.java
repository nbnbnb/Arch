package me.zhangjin.types;

public class Result<T> {

    private T res;

    public Result(T res) {
        this.res = res;
    }

    public T getResult() {
        return res;
    }

    public static Result<Boolean> success() {
        return new Result<>(true);
    }

    public static Result<Boolean> fail() {
        return new Result<>(false);
    }

    public static Result<Boolean> fail(String msg) {
        return new Result<>(false);
    }
}
