package cn.fantasticmao.demo.java.algorithm;

import java.io.Serializable;

/**
 * Matrix 矩阵
 *
 * @author fantasticmao
 * @since 2017/7/8
 */
public interface Matrix<E> extends Serializable {

    Integer getRow();

    Integer getCol();

    Matrix<E> plus(Matrix<E> matrix);

    Matrix<E> minus(Matrix<E> matrix);

    Matrix<E> multiply(Matrix<E> matrix);

    Matrix<E> divide(Matrix<E> matrix);

    Matrix<E> invert(Matrix<E> matrix);

    Boolean is(Matrix<E> matrix);
}
