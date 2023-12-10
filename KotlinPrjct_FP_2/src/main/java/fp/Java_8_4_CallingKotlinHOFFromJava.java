package fp;

public class Java_8_4_CallingKotlinHOFFromJava {

    public static void main(String[] args) {

        FList<String> words = FList.Companion.flistOf("Kotlin", "from", "Java");
        FList<Integer> wordLengths = words.filter(word -> word.length() > 0).map(word -> word.length());


        wordLengths =
              words.filter(new kotlin.jvm.functions.Function1<String, Boolean>() {
                        @Override
                        public Boolean invoke(String word) {
                            return word.length() > 0;
                        }
                    })
                    .map(new kotlin.jvm.functions.Function1<String, Integer>() {
                        @Override
                        public Integer invoke(String word) {
                            return word.length();
                    }
        });
        System.out.println(wordLengths);

        words.forEach(
            word -> {
                System.out.println(word);
                return kotlin.Unit.INSTANCE;
            }
        );


    }

}
