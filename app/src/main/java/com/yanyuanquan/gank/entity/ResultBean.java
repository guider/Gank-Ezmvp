package com.yanyuanquan.gank.entity;

public class ResultBean<T>  {


    private boolean error;
    private String msg;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private T results;

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("title=" + error);
        if (null != results) {
            sb.append(" results:" + results.toString());
        }
        return sb.toString();
    }
}
