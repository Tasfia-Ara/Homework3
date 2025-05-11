public class Reduce {
    public static void main(String[] args, int n) {
        reduceMethod(n);
    }

    private static int reduceMethod(int n) {
        int count = 0;
        while(n > 0){
            if (!(n % 2 == 0)) {
                n = n -1;
            }
            n = n /2;
            count++;
        }
        return count;
//        System.out.println(count);
    }
}



