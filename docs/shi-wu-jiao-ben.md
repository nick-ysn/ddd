## 事务脚本

> 事务脚本这个词取自Martin Fowler的《企业应用架构模式》,事务脚本是指这样一个过程:

* 从表示层获得输入，进行校验和计算处理，将数据存储到数据库中以及调用其他系统的操作等。
* 然后，该过程将更多的数据返回给表示层，中间可能要进行大量的计算来组织和整理返回值。

简单来说，事务脚本就是采用过程式的编程方式，业务逻辑根据流程一次组装在方法中，每个方法是一个业务的执行体，类似于某个业务的执行脚本，所以叫事务脚本。

下面，我们先来看一个最简单的事务脚本

例如:

```java
public class PersonService {

    public void create(Person person) throws SQLException {
        final Connection connection = DBUtils.getConnection();
        final PreparedStatement preparedStatement = connection
                .prepareStatement("insert into users (name,age) values (?,?)");
        preparedStatement.setString(1, person.getName());
        preparedStatement.setInt(2, person.getAge());
        preparedStatement.execute();
    }
}
```

该事务脚本的方式是最简单的业务实现方式，将持久化逻辑和业务逻辑写在一个方法中。这样会带来如下几个问题:

* 持久化逻辑缺乏封装，每一个对象需要重复地机械赋值，增加维护的成本。

* 持久化相关的SQL分散在不同的方法体中，SQL本身的维护成本会增加。

* 缺乏对数据库字段和系统模型字段的映射，如果表结构变更，无法很好地直接映射到对应的模型，因为String在重构时只能人肉操作。

#### 事务脚本+表活动入口

> 神马是表活动入口

* 其实表活动入口就是大家平常所说的DAO\(data access layer\)，就是将数据库的模型转换成对应的编程模型。

> 为什么要有DAO呢?

* 单纯的事务脚本会将持久化相关的逻辑分散到不同的业务方法中，不利于维护，使用DAO，可以将相关表的持久化逻辑封装到单独的对象中，使持久化逻辑和业务分离，便于维护。
* 单纯的事务脚本缺乏数据库关系和业务模型关系的映射，造成持久化结构的变更会影响到多出业务方法的变更，业务方法的变更就很可能带来bug。使用DAO进行数据库模型和业务模型的映射，使业务能和数据库模型解耦\(例如User类中有一个枚举值，存储时DAO将其转为String存储，取出时DAO将其转为枚举，这样业务就不用再去强耦合数据库的类型\)

看一个简单使用DAO更新用户的场景

不使用DAO的例子

```java
public void changeAgeOfNotDAO(Person person) throws SQLException {
        final Connection connection = DBUtils.getConnection();
        final PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT * FROM users WHERE name = ?");
        preparedStatement.setString(1, person.getName());
        final ResultSet resultSet = preparedStatement.executeQuery();
        int id = 0;
        while (resultSet.next()) {
            id = resultSet.getInt("id");
        }
        final PreparedStatement updateStatement = connection
                .prepareStatement("UPDATE users SET age = ? WHERE id = ?");
        updateStatement.setInt(1,person.getAge());
        updateStatement.setInt(2,id);
        updateStatement.execute();
    }
```

使用DAO的例子

```java
public void changeAge(Person person) throws SQLException {
        final User user = userDAO.queryByName(person.getName());
        user.setAge(person.getAge());
        userDAO.update(user);
    }
```

* 通过以上例子，可以看出单纯使用事务脚本会将业务逻辑和持久化逻辑耦合在一起，不仅代码冗余，难以维护，同时也会由于业务逻辑或持久化逻辑的变更造成对业务方法的修改，有代入bug的风险。

#### 表活动入口优点:

* 表活动入口方式是日常编程使用最多的方式，DAO提供了业务逻辑层和持久层的隔离，将业务逻辑和持久化逻辑进行分离，降低耦合和代码的冗余，提高了程序的健壮性和可维护性。
* 表活动入口方式可以采用接口的方式进行开发，使用Spring等Ioc框架进行反向注入，这样可以使业务逻辑层只依赖于持久化层的接口，我们可以为不同的存储提供针对该接口的不同实现，然后只需要通过Spring注入到业务逻辑层，这样可以是两者之间的耦合进一步减小，进一步提升可维护性。

> 考虑这样一种场景: 一个业务系统开始的时候业务量小，完全采用单库单表的方式持久化数据。一段时间后，业务量急速上升，这时候需要对数据库进行分表处理，持久化逻辑发生了改变。这时候采用DAO接口的方式我们可以提供一种针对分表的持久化操作实现，然后在Spring中替换该接口的实现bean，那我们可以保证我们的业务逻辑不受更改，造成的影响最小。

#### 表活动入口缺点:

* 很多人觉得采用DAO+DO的方式就是采用领域模型的方式了，其实不是。事务脚本+DAO方式和领域驱动方式的本质区别是事务脚本在面向数据库编程，领域模型是在面向业务编程，领域模型能更充分地体现业务。

> 考虑这样一个非常简单的场景: 一个Student有多个Phone，用户的某个Phone需要修改，在事务脚本的模式下我们会采用如下的方式:

![DAO方式](https://os.alipayobjects.com/rmsportal/WHhzKhgwAbgALhy.png)

代码实例:

```java
public void changePhoneNo(String studentName, String phoneNo) throws SQLException {
        final StudentDO studentDO = studentDAO.queryByName(studentName);
        final PhoneDO phoneDO = phoneDAO.queryByStudentIdAndPhoneNo(studentDO.getId(), phoneNo);
        phoneDO.setDesc("这是一个被修改的号码");
        phoneDAO.update(phoneDO);
    }
```

> 领域模型如何考虑呢? 首先，phone只能和student绑定，修改phone必须通过student才能修改，那么phone应该依托于student而存在，它们是一个聚合，student是这个聚合的聚合根。 在DDD中，持久化是针对聚合的，那么采用DDD模式的操作流程如下:

![DDD](https://os.alipayobjects.com/rmsportal/TcYVNnZZZweHGiI.png)

代码实例\(为什么id需要做成StudentId以及仓储如何时候这个后面部门会有详细说明\):

```java
public class Student {

    private StudentId studentId;

    private String name;

    private int age;

    private List<Phone> phones;

    public void changePhone(Phone changePhone) {
        for (Phone phone : phones) {
            if (phone.getNumber().equals(changePhone.getNumber())) {
                phones.remove(phone);
            }
        }
        phones.add(changePhone);
    }
}

public interface StudentRepository {

    void save(Student student);

    Student studentOfId(StudentId studentId);

    Student studentOfName(String name);
}

public void changePhoneNoOfDoaminModel(String studentName,Phone phone){
        final Student student = studentRepository.studentOfName(studentName);
        student.changePhone(phone);
        studentRepository.save(student);
    }
```

* 事务脚本的方式使得Phone脱离了Student而存在，每个Student和对应的Phone之间没有对应的从属关系，是一种面向数据库存储\(表\)的编程方式。而DDD的方式体现了我们的业务规则，phone一定是属于某个Student的，修改phone只能通过对应的Student去修改。

* 事务脚本+DAO的方式提供了持久化层和业务层的抽象隔离，但是由于表活动记录和实体表绑定的受限，造成持久化的相关操作还是会有一些泄露到业务层中。同时，事务脚本没有聚合的概念，无法将我们的业务规则的一致性要求映射到代码实现中，这会造成业务规则的分散，当业务复杂到一定程度的时候，维护性会大大降低。



