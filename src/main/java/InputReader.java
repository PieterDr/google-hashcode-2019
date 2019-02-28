import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputReader {

    public static List<Photo> read(String file) {
        try (Stream<String> lines = Files.lines(Paths.get(ClassLoader.getSystemResource(file).toURI()))) {
            return lines.skip(1)
                    .map(line -> {
                        String[] parts = line.split(" ");
                        return new Photo(parts[0], Stream.of(parts).skip(3).collect(Collectors.toList()));
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

}
