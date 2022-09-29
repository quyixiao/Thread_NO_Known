//: net/mindview/util/FourTuple.java
package com.example.thread_no_known.utils.t;


/***
 *
 *
 * 355页
 *
 *
 * @param <A>
 * @param <B>
 * @param <C>
 * @param <D>
 */
public class FourTuple<A, B, C, D> extends ThreeTuple<A, B, C> {
    private  D fourth;

    public FourTuple(A a, B b, C c, D d) {
        super(a, b, c);
        fourth = d;
    }


    public D getFourth() {
        return fourth;
    }

    public void setFourth(D fourth) {
        this.fourth = fourth;
    }
}
