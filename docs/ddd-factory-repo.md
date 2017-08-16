## 工厂
DDD 中的工厂和我们在设计模式中的工厂概念类似，在设计模式中我们通过工厂实现多态对象、单例对象或某种需要特殊构造的对象的实例化。在 DDD 中，当我们需要一个新的实体的时候，我们就需要通过工厂来生成实体。

> 那么，我们如何实现工厂呢？

在 ddd 的工厂实现中，我们可以有两种实现方式，一种是领域工厂，一种是工厂方法。

#### 领域工厂

```java
public class SprintFactory {

    public static SprintEntity create(String name) {
        final SprintId sprintId = new SprintId(RandomStringUtils.random(10, false, true));
        return new SprintEntity(sprintId,name);
    }
}
```

#### 工厂方法
```java
public class SprintEntity {

    @Getter
    private SprintId sprintId;

    @Getter
    private String name;

    public SprintEntity(SprintId sprintId, String name) {
        this.setSprintId(sprintId);
        this.setName(name);
    }
    ...
    public static SprintEntity create(String name){
        final SprintId sprintId = new SprintId(RandomStringUtils.random(10, false, true));
        return new SprintEntity(sprintId,name);
    }
}
```

如上例子所示，领域工厂和工厂方法实现逻辑基本一致，只是一个用单独的类承载，一个用实体的静态方法承载。在大多数业务场景中，用户可根据自己的喜好选择。当构造工厂需要调用外围接口的时候，这时候我们需要使用领域工厂来实现。

- 考虑如下场景: 在一个 spring 应用中，创建实体的时候你需要调用一些外围接口，根据这些接口的调用数据来生成实体。如果使用工厂方法，由于工厂方法是静态的，外围接口对象的注入处理不够优雅。这时候如果我们直接将 factory class 注入为 spring bean，然后外围依赖接口作为这个 bean 的属性装配好，调用的时候就会自然很多。