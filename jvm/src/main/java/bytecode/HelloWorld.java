package bytecode;

/**
 * javap -c -v HelloWorld.class
 * -c 反编译
 * -v  -verbose             输出附加信息
 * new 对象会看到dup指令：构造函数没有返回值。那么怎么获取构造完毕的对象呢？只能在调用构造函数前把对象句柄复制一份
 */
public class HelloWorld {
    public static void main(String[] args) {
        HelloWorld obj = new HelloWorld();
    }
}
/*
Compiled from "HelloWorld.java"
public class bytecode.HelloWorld {
  public bytecode.HelloWorld();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: new           #2                  // class bytecode/HelloWorld
       3: dup                               new之后固定的指令，用于调用构造函数之后能够返回this对象，把对象引用复制了一次，这样运算之后还剩下对象的引用
       4: invokespecial #3                  // Method "<init>":()V
       7: astore_1
       8: return
}
 */