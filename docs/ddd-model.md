## DDD\(领域驱动设计\)

> 我们以一个流程系统作为案例

### 充血模型和贫血模型

* 什么是贫血模型

> 在传统Java EE项目中，我们会配置很多的Bean，这些Bean只是作为数据的载体，只带有Getter、Setter方法，不具备具体的业务功能方法，所有的业务操作都放到Service中，这种模型称之为贫血模型。

* 什么是充血模型

> 充血模型是相对贫血模型来说的，充血模型认为一个模型不仅应该有其必备的属性、也应该有其所具备的行为。例如一个Person模型，除了具备Person必备的name,age等属性外，也应该有changeAddress\(Address address\) 等自己的行为方法，这样Person才是一个完整的个体。

#### 关于贫血模型和充血模型的争论

> 对于选择贫血模型还是充血模型这个现在存在很多争论，一些人认为贫血模型简单，容易理解，将业务逻辑封装在Servcie中有利于清晰易懂，充血模型会造成代码复杂，难以维护。 另一些人认为，充血模型将模型独立的业务封装在自己内部，有利于业务逻辑的聚合，每个模型都是一个独立的个体，便于代码的扩展和维护。那么，我们应该如何选择呢？

* 我的建议是: 应该毫不犹豫的选择充血模型，包括Martin folwer等大师的观点也是DDD种的模型就应该是充血的。因为贫血模型会带来很多问题，典型的就是 贫血模型引起的失忆症。

> 考虑这样一个场景，我们有一个Customer 类，其中包括了一堆的属性，这时我们更新用户的地址，如果在贫血模型下我们可能会采用如下的代码:

```java
public void update(String name, Address address, String phoneNo) {
        final CustomerDO customerDO = customerDAO.customerOfName(name);
        if (StringUtils.isNotBlank(address.getCity())){
            customerDO.setCity(address.getCity());
        }
        if (StringUtils.isNotBlank(address.getCountry())){
            customerDO.setCountry(address.getCountry());
        }
        if (StringUtils.isNotBlank(phoneNo)){
            customerDO.setPhoneNo(phoneNo);
        }
        customerDAO.update(customerDO);
    }
```

这段代码更新的目的不明确，上层业务不能明确地知道什么东西可以更新，什么东西不能更新，属性之间的约束关系已经不受控制。

