package com.grass.bean;

/**
 * Created by grass on 15/12/20.
 */
public class ActivityInfo {
    String name;
    String des;
    String fullClassName;

    public ActivityInfo(String name, String des, String fullClassName) {
        this.name = name;
        this.des = des;
        this.fullClassName = fullClassName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ActivityInfo that = (ActivityInfo) o;

        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        if (des != null ? !des.equals(that.des) : that.des != null) {
            return false;
        }
        return !(fullClassName != null ? !fullClassName.equals(that.fullClassName) : that.fullClassName != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (des != null ? des.hashCode() : 0);
        result = 31 * result + (fullClassName != null ? fullClassName.hashCode() : 0);
        return result;
    }
}
