public class Multiples {
    public static int main(String[] args, int n, int a, int b) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (i % a == 0 || i % b == 0) {
                count++;
            }
        }
        return count;
//        System.out.println(count);
    }
//    Multiples obj = new Multiples(100);
}

