package com.betacat.go.domain;

public class Data {
    private Point p;
    private Integer result;

    public Point getP() {
        return p;
    }

    public void setP(Point p) {
        this.p = p;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Data{" +
                "p=" + p +
                ", result=" + result +
                '}';
    }
}
