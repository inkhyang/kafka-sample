package com.inkhyang.document.application;

import com.inkhyang.document.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Component
@Getter
@Setter
public class SimpleIdCardGenerator implements IdCardGenerator {

    public static final String CHARSET_NAME = "ASCII";
    public static final int NAME_LENGTH = 3;

    @SneakyThrows
    public String generate(User user) {
        byte[] nameBytes = user.getName().toUpperCase().substring(0, NAME_LENGTH).getBytes(CHARSET_NAME);

        int age = user.getAge();
        StringBuilder result = new StringBuilder();
        result.append(age).append("-");
        for (byte b : nameBytes) {
            result.append(b);
        }
        return result.toString();
    }

    @SneakyThrows
    public User identity(String idCard) {
        String[] s = idCard.split("-");
        int age = Integer.parseInt(s[0]);

        List<String> strings = new ArrayList<>();
        for (int i = 0; i < s[1].length(); i += 2) {
            strings.add(s[1].substring(i, i + 2));
        }
        byte[] nameBytes = new byte[NAME_LENGTH];
        Object[] s1 = strings.stream().map(Byte::valueOf).toArray();
        IntStream.range(0, NAME_LENGTH).forEach(i -> nameBytes[i] = (byte) s1[i]);
        String name = new String(nameBytes, CHARSET_NAME);
        return User.builder().age(age).name(name).build();
    }
}
