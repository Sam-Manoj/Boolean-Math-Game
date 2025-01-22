public class LEVEL2 {
    public static int or_op(int a, int b,int c) {
        return a | b | c;
    }
    public static int and_op(int a, int b, int c) {
        return a & b & c;
    }
    public static int r(int a, int b, int c){
        return a & b | c;
    }
    public static int r2(int a,int b,int c){
        return a | b & c;
    }
    public static int exor_op(int a, int b,int c) {
        return a ^ b | c;
    }
    public static int ex_and(int a, int b,int c){
        return a ^ b & c;
    }
    public static int and_ex(int a, int b,int c){
        return a & b ^ c;
    }
    public static int or_ex(int a, int b,int c){
        return a | b ^ c;
    }
}
