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

## 仓储(Repository)
在 DDD 中，我们的业务都是通过实体来进行建模的。对一个业务实体来说，它包含的是实体所需要的必备属性，比如如下样例:
```java
public class Student extends Entity {

    @Getter
    private StudentId studentId;

    @Getter
    private String name;

    @Getter
    private Address address;

    public Student(StudentId studentId, String name, Address address) {
        this.studentId = studentId;
        this.name = name;
        this.address = address;
    }

    public static Student create(StudentId studentId, String name, Address address) {
        return new Student(studentId, name, address);
    }

}
```
对于以上样例，我们定义了 Student 实体，Student 实体包含了 Address 信息，这对我们业务来说是很自然的。但是站在持久化的角度来看，address 是公共资源，需要存储在单独的表中，这就出现了业务模型和数据库模型不匹配的问题了。那怎么来解决这个问题呢？这时候我们就需要引入仓储的概念了：
> 仓储用于解决业务模型和持久化模型之间的不匹配问题，让业务模型专注于业务，不需要关心底层存储模式，让持久化层只关注要存储的数据，不关系具体的业务逻辑，从而达到解耦的目的

DDD 中的仓储主要有两个核心功能:
- 持久化实体: 将实体持久化到底层的数据库中
- 重建实体: 实体持久化后当我们需要再次使用的时候需要从数据库的数据重建实体

```java
public class StudentRepositoryImpl implements StudentRepository {

    @Setter
    private StudentDAO studentDAO;

    @Setter
    private AddressDAO addressDAO;

    public StudentId save(Student student) {
        studentDAO.insert(new StudentDO(student.getName()));
        addressDAO.insert(student.getAddress());
        return student.getStudentId();
    }

    public Student studentOfId(StudentId studentId) {
        final StudentDO studentDO = studentDAO.queryById(studentId.getId());
        final Address address = addressDAO.queryById(studentDO.getAddressId());
        return Student.create(new StudentId(studentDO.getId()), studentDO.getName(), address);
    }
}
```

在实现仓储的时候，我们要尽量使用面向接口编程的方式。上游业务只依赖于接口而不依赖于实现，这样我们在修改或切换存储逻辑的时候对业务的影响会最小。面向接口编程也是面向对象核心的能力。

### DAO 和 Repository 关系
也许大家会觉得 DAO 是用来处理数据库的，Respository 也是用来处理数据库的，那这两者之间有没有什么关系呢?

- 在一般的业务中，我们通常使用 DAO 来解决数据表和代码模型之间的差异性，比如将某个 POJO 对象直接存取到数据库中，一般 DAO 和数据库表是一对一的关系，属于数据库敏感组件。

- Repository 在 DDD 中用于解决实体模型和持久化逻辑之间的差异性，为业务逻辑屏蔽底层存储的具体细节，这时候业务模型是不感知底层数据表的，只关心自己的业务。Repository 中通常一个实体会对应多张表结构，Repository 是承上启下的组件。

- 正因为仓储是针对实体维度的持久化，所以我们业务中只应该依赖于仓储的接口，当我们修改持久化逻辑的时候只需修改仓储实现或者替换新的实现类注入到业务层即可，不需要业务层做任何改动。

## 仓储和工厂关系
仓储和工厂都是对模型操作的核心组件，当我们需要新建实体的时候就使用工厂来生成新的实体。当我们需要持久化和重建实体的时候就用仓储来操作，两者之间是相互协作的关系。

**只有实体才会有仓储，值对象是没有仓储的**