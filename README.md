# DDD

> 引用大师 Martin Fowler的一句话镇楼: 好的软件设计都应该是DDD的

- DDD是目前企业软件设计最常采用的建模方式，也是目前软件大师们比较推崇的复杂软件的最佳解决方案。
- 那么，究竟什么是DDD，DDD包括什么，怎么去使用DDD的方式建模，怎么去按照DDD的方式开发呢？
- 本篇文章旨在通过总结我自己对DDD的理解给出一些使用DDD方面的建议，如果你已经是DDD非常熟练的高手了，那么请直接右上角关闭页面，不用再往下看了~

## 事务脚本

> 事务脚本这个词取自Martin Fowler的《企业应用架构模式》,事务脚本是指这样一个过程: 
    
    - 从表示层获得输入，进行校验和计算处理，将数据存储到数据库中以及调用其他系统的操作等。
    - 然后，该过程将更多的数据返回给表示层，中间可能要进行大量的计算来组织和整理返回值。
    
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

- 持久化逻辑缺乏封装，每一个对象需要重复地机械赋值，增加维护的成本。

- 持久化相关的SQL分散在不同的方法体中，SQL本身的维护成本会增加。

- 缺乏对数据库字段和系统模型字段的映射，如果表结构变更，无法很好地直接映射到对应的模型，因为String在重构时只能人肉操作。

#### 事务脚本+表活动入口

> 神马是表活动入口
	
- 其实表活动入口就是大家平常所说的DAO(data access layer)，就是将数据库的模型转换成对应的编程模型。

> 为什么要有DAO呢?

- 单纯的事务脚本会将持久化相关的逻辑分散到不同的业务方法中，不利于维护，使用DAO，可以将相关表的持久化逻辑封装到单独的对象中，使持久化逻辑和业务分离，便于维护。
- 单纯的事务脚本缺乏数据库关系和业务模型关系的映射，造成持久化结构的变更会影响到多出业务方法的变更，业务方法的变更就很可能带来bug。使用DAO进行数据库模型和业务模型的映射，使业务能和数据库模型解耦(例如User类中有一个枚举值，存储时DAO将其转为String存储，取出时DAO将其转为枚举，这样业务就不用再去强耦合数据库的类型)

看一个简单使用DAO更新用户的场景

```java 

	public void changeAge(Person person) throws SQLException {
        final User user = userDAO.queryByName(person.getName());
        user.setAge(person.getAge());
        userDAO.update(user);
    }

