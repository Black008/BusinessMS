package cn.com.servletBMS.modle;

import java.util.List;

public class ResultTotal{
    List<Business> result;
    List<Business> resultSix;
    int total;
    public List<Business> getResultSix() {
        return resultSix;
    }

    public void setResultSix(List<Business> resultSix) {
        this.resultSix = resultSix;
    }
    public List<Business> getResult() {
        return result;
    }

    public void setResult(List<Business> result) {
        this.result = result;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
