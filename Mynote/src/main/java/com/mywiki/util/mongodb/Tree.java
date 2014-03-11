package com.mywiki.util.mongodb;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 * 
MongoMappingConverter这个类实现的。它可以通过注释把java类转换为mongodb的文档。它有以下几种注释：
@Id - 文档的唯一标识，在mongodb中为ObjectId，它是唯一的，通过时间戳+机器标识+进程ID+自增计数器（确保同一秒内产生的Id不会冲突）构成。
@Document - 把一个java类声明为mongodb的文档，可以通过collection参数指定这个类对应的文档。
@DBRef - 声明类似于关系数据库的关联关系。ps：暂不支持级联的保存功能，当你在本实例中修改了DERef对象里面的值时，单独保存本实例并不能保存DERef引用的对象，它要另外保存，如下面例子的Person和Account。
@Indexed - 声明该字段需要索引，建索引可以大大的提高查询效率。
@CompoundIndex - 复合索引的声明，建复合索引可以有效地提高多字段的查询效率。
@GeoSpatialIndexed - 声明该字段为地理信息的索引。
@Transient - 映射忽略的字段，该字段不会保存到mongodb。
@PersistenceConstructor - 声明构造函数，作用是把从数据库取出的数据实例化为对象。该构造函数传入的值为从DBObject中取出的数据。 
 *
 */
@Document
public class Tree {
    @Id
    private String id;
    private String name;
    private String category;
    private int age;
    public Tree(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    @Override
    public String toString() {
        return "Person [id=" + id + ", name=" + name + ", age=" + age
                + ", category=" + category + "]";
    }
}


