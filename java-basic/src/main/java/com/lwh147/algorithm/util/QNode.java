package com.lwh147.algorithm.util;

public class QNode implements Comparable<QNode> {
    private double cw;
    private double cv;
    private boolean isLeft;
    private int level;
    private QNode parent;

    public QNode(double cw, double cv, boolean isLeft, int level, QNode parent) {
        this.cw = cw;
        this.cv = cv;
        this.isLeft = isLeft;
        this.level = level;
        this.parent = parent;
    }

    public QNode() {

    }

    @Override
    public int compareTo(QNode o) {
        return (this.getCv() > o.getCv()) ? -1 : 1;
    }

    public double getCw() {
        return cw;
    }

    public void setCw(double cw) {
        this.cw = cw;
    }

    public double getCv() {
        return cv;
    }

    public void setCv(double cv) {
        this.cv = cv;
    }

    public boolean isLeft() {
        return isLeft;
    }

    public void setLeft(boolean isLeft) {
        this.isLeft = isLeft;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public QNode getParent() {
        return parent;
    }

    public void setParent(QNode parent) {
        this.parent = parent;
    }

}
