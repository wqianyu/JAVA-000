package bytecode;

public class Demo {
    public static void main(String[] args) {
        int a = 1;
        int b =2;
        int c = (a+b)*5;
    }
}
/*
Compiled from "Demo.java"
public class bytecode.Demo {
  public bytecode.Demo();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: iconst_1
       1: istore_1
       2: iconst_2
       3: istore_2
       4: iload_1
       5: iload_2
       6: iadd
       7: iconst_5
       8: imul
       9: istore_3
      10: return
}

 */