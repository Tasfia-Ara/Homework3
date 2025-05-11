public class Multiples {
    public static int main(int n, int a, int b) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (i % a == 0 || i % b == 0) {
                count++;
            }
        }
        return count;
//        System.out.println(count);
    }
//    public static int main(){
//        return Multiples.main(nulll, 1000, 3, 5)
//    }
//    Multiples obj = new Multiples(100);
}

