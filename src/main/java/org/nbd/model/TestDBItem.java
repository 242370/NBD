package org.nbd.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
public class TestDBItem implements Serializable {

    @BsonProperty("name")
    private String name;

    @BsonProperty("age")
    private Integer age;

    @BsonCreator
    public TestDBItem(@BsonProperty("name") String name,
                      @BsonProperty("age") Integer age) {
        this.name = name;
        this.age = age;
    }


    @Override
    public String toString() {
        return "TestDBItem{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
