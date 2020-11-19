package bytecode;

public class LocalVariableTest {
    public static void main(String[] args) {
        MovingAverage ma = new MovingAverage();
        int num1 = 1;
        int num2 = 2;
        ma.submit(num1);
        ma.submit(num2);
        double avg = ma.getAvg();
    }
}
/*
Compiled from "LocalVariableTest.java"
public class bytecode.LocalVariableTest {
  public bytecode.LocalVariableTest();
    Code:                                   固定的调用Object类的init方法
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: new           #2                  // class bytecode/MovingAverage
       3: dup                               复制对象，用于new之后保留对象在栈中
       4: invokespecial #3                  // Method bytecode/MovingAverage."<init>":()V   调用MovingAverage类signature为<init>()V的构造器
       7: astore_1                          存储：ma
       8: iconst_1                          常量1
       9: istore_2                          存储num1
      10: iconst_2                          常量2
      11: istore_3                          存储num2
      12: aload_1                           加载ma
      13: iload_2                           读取num1
      14: i2d                               int to double，int类型转化为double
      15: invokevirtual #4                  // Method bytecode/MovingAverage.submit:(D)V 调用submit方法
      18: aload_1                           加载ma
      19: iload_3                           加载num2
      20: i2d                               int to double
      21: invokevirtual #4                  // Method bytecode/MovingAverage.submit:(D)V
      24: aload_1                           加载ma
      25: invokevirtual #5                  // Method bytecode/MovingAverage.getAvg:()D
      28: dstore        4                   存储double 4
      30: return
}

 */