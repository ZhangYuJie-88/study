package com.example.study.jdk11;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.net.Authenticator;
import java.net.CookieHandler;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <h3>study</h3>
 * <p></p>
 *
 * @author : ZhangYuJie
 * @date : 2022-03-20 18:42
 **/

public class Jdk11Test {
    public static void main(String[] args) throws IOException, InterruptedException {
//        String blankStr = "    ";
//        // true
//        boolean trueVal = blankStr.isBlank();
//
//        String newStr = "Hello Java 11 \n 张三李四周吴郑王 \r 2022-18-44";
//
//        Stream<String> lines = newStr.lines();
//        lines.forEach(System.out::println);
//
//        String str = "HELLO\u3000";
//        // str = 6
//        System.out.println("str = " + str.length());
//        // trim = 6
//        System.out.println("trim = " + str.trim().length());
//        // strip = 5
//        System.out.println("strip = " + str.strip().length());


//        String str = "HELLO";
//        // 空字符
//        String empty = str.repeat(0);
//        System.err.println(empty);
//        // HELLO
//        String repeatOne = str.repeat(1);
//        System.err.println(repeatOne);
//        // HELLOHELLO
//        String repeatTwo = str.repeat(2);
//        System.err.println(repeatTwo);
//        List<String> sampleList = Arrays.asList("felord.cn", "java 11");
//        // array = {"felord.cn", "java 11"};
//        String[] array = sampleList.toArray(String[]::new);
//        List<String> sampleList = Arrays.asList("张三", "java 11","jack");
//        // [jack]
//        List<String> result = sampleList.stream()
//                // 过滤以j开头的字符串
//                .filter(s -> s.startsWith("j"))
//                // 同时不包含11的字符串
//                .filter(Predicate.not(s -> s.contains("11")))
//                .collect(Collectors.toList());
//        System.err.println(result);
//        List<String> sampleList = Arrays.asList("张三", "java 11","jack");
//        List<String> result = sampleList.stream()
//                // 过滤以j开头的字符串
//                .filter((@NotNull var s) -> s.startsWith("j"))
//                // 同时不包含11的字符串
//                .filter(Predicate.not((@NotNull var s) -> s.contains("11")))
//                .collect(Collectors.toList());
//        System.err.println(result);
        String dir= "C://yourDir/hello.txt";
// 写入文件
        Path path = Files.writeString(Path.of(dir), "hello java 11");
// 读取文件
            String fileContent = Files.readString(path);
            // hello java 11
        System.err.println(fileContent);

//
//        var request = HttpRequest.newBuilder()
//                .uri(URI.create("https://baidu.com"))
//                .GET()
//                .build();
//        var client = HttpClient.newHttpClient();
//        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//        System.out.println(response.body());
//
//

        var request = HttpRequest.newBuilder()
                .uri(URI.create("https://baidu.com"))
                .build();
        var client = HttpClient.newHttpClient();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println);
        Stream.ofNullable(null)
                .count();   // 0
        Stream.of(1, 2, 3, 2, 1)
                .dropWhile(n -> n < 3)
                .collect(Collectors.toList());  // [3, 2, 1]

        Stream.of(1, 2, 3, 2, 1)
                .takeWhile(n -> n < 3)
                .collect(Collectors.toList());  // [1, 2]
        Optional.of("foo").orElseThrow();     // foo
        Optional.of("foo").stream().count();  // 1
        Optional.ofNullable(null)
                .or(() -> Optional.of("fallback"))
                .get();

    }

    public class Outer {
        private int outerInt;

        class Inner {
            public void printOuterField() {
                System.out.println("Outer field = " + outerInt);
            }
        }
    }

}
