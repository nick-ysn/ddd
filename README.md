# DDD

> 引用大师 Martin Fowler的一句话镇楼: 好的软件设计都应该是DDD的

* DDD是目前企业软件设计最常采用的建模方式，也是目前软件大师们比较推崇的复杂软件的最佳解决方案。
* 那么，究竟什么是DDD，DDD包括什么，怎么去使用DDD的方式建模，怎么去按照DDD的方式开发呢？
* 本篇文章旨在通过总结我自己对DDD的理解给出一些使用DDD方面的建议，如果你已经是DDD非常熟练的高手了，那么请直接右上角关闭页面，不用再往下看了~

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

### DDD的关键概念

#### 实体

> 官方解释:  实体是具有唯一标记的领域模型对象。 这句话很难理解，这样来看:
>
> 实体是一种具有一个唯一ID标记的对象，即使其他的属性或行为完全一样，只要ID不一样就不是一个实体。 例如: 每个人都有名字，两个人即使身高、体重完全一样，只要他们的名字不一样他们就不是一个人。同时，实体是具有生命周期的，实体创建完后会被持久化，然后在某个时间重建出来再执行具体的业务，然后再被持久化。

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

    public void createSprint(){

    }

    public void setSprintId(SprintId sprintId) {
        if (sprintId == null) {
            throw new IllegalArgumentException("sprintId isn't allowed null");
        }
        this.sprintId = sprintId;
    }

    public void setName(String name) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("name isn't allowed blank");
        }
        this.name = name;
    }
}
```

##### 实体的特点

```
实体依赖原则: 实体之间不能相互持有引用，只能持有其他实体的唯一ID
```

* 具有唯一的标记

> 实体一定是具有唯一的标记的，那么这个标记应该做成什么类型呢? 按数据库int? 随机生成Stirng?  按我的经验和《实现领域驱动设计》 的理论，实体的ID应该是一个特殊的值对象，例如上诉的Sprint实体的ID为 SprintId这样一个值对象。

* 为什么实体的ID应该做成值对象

> 由于实体与实体之间只能相互持有对方的ID，那么把ID做成值对象可以明确的标识出实体间的依赖关系，防止由于传入不正确的ID造成的业务异常。

* 为什么构造方法里赋值调用了set方法?

> 使用set方法是一种构造自检的编码风格，这样可以保证在构建对象的时候就能发现参数是否正确，防止执行业务的时候才出现异常。 这也是软件大师们的推荐做法。

#### 值对象

> 值对象也属于领域对象，值对象和实体最本质的区别是值对象没有唯一的ID，两个对象只要属性一样，那么就认为这两个对象是一样的东西，可以互换。

```java
public class Address {

    @Getter
    private String country;

    @Getter
    private String province;

    @Getter
    private String city;

    public Address(String country,String province,String city){
        this.setCountry(country);
        this.setProvince(province);
        this.setCity(city);
    }

    public String generateSimpleAddress(){
        return country + "-" + province + "-" + city;
    }

    private void setCountry(String country) {
        if (StringUtils.isBlank(country)) {
            throw new IllegalArgumentException("country isn't allowed blank");
        }
        this.country = country;
    }

    private void setProvince(String province) {
        if (StringUtils.isBlank(province)){
            throw new IllegalArgumentException("province isn't allowed blank");
        }
        this.province = province;
    }

    private void setCity(String city) {
        if (StringUtils.isBlank(city)){
            throw new IllegalArgumentException("city isn't allowed blank");
        }
        this.city = city;
    }
}
```

##### 值对象特点

* 值对象是不可变的，值对象创建完后不允许再修改，所以上诉代码中set方法都是private的。。为什么值对象要设计成不可变的呢?

> 在DDD中实体/聚合之间是不能保持对方的引用的，也就是说实体不能持有另一个实体的引用，只能持有另一个实体的ID。但是值对象只是一个数据的载体，所以在DDD中值对象是可以在多个实体间共享的，这时候如果值对象允许更改那么可能会造成 实体1修改了某个值对象意外影响到了 实体2的业务的情况，所以值对象应该设计为不可变的。

* 值对象的方法都应该是无副作用方法。。 什么是无副作用方法?

> 无副作用方法的意思就是方法执行前和方法执行后对象的状态都是不会改变的。简单来说就是方法执行完后不会调用对象的任何set方法修改方法的属性和状态，例如上诉代码中的 generateSimpleAddress\(\) 方法。

* 为什么值对象的方法都应该是无副作用方法?

> 由于值对象是不可变的，所以如果值对象的方法不是无副作用方法那么执行值对象的方法后会破坏值对象的不可变性，不符合我们的约束。

* 当需要修改值对象的部分属性的时候怎么办?

```java
    public class VersionManager {

        @Getter
        private Version dev;

        @Getter
        private Version test;

        @Getter
        private Version prod;

        public VersionManager() {

        }

        public VersionManager(Version dev, Version test, Version prod) {
            this.setDev(dev);
            this.setTest(test);
            this.setProd(prod);
        }

        public VersionManager(Version dev) {
            this.setDev(dev);
        }

        public VersionManager(Version dev, Version test) {
            this.setDev(dev);
            this.setTest(test);
        }

        public VersionManager addDevVersion(Version version) {
            return new VersionManager(version);
        }

        public VersionManager addTestVersion(Version version) {
            return new VersionManager(dev, version);
        }

        public VersionManager addProdVersion(Version version) {
            return new VersionManager(dev, test, version);
        }
   }
```

> 如上诉代码，当需要修改值对象的某个属性的时候在方法中重新构造一个新的对象返回，这样可以保证值对象的不变性。。

### 值对象和实体对比

| 类型 | 是否需要唯一标记 | 是否可变 | 是否能持有实体引用\(不同聚合根\) | 是否可替换 |
| --- | --- | --- | --- | --- |
| 实体 | 是 | 是 | 否 | 否 |
| 值对象 | 否 | 否 | 否 | 是 |

* 业务建模的时候什么东西建成实体，什么东西建成值对象?

> 如何一个模型是需要有生命周期\(有持久化和后续重建修改\) 的需求那么应该建模成实体，否则应该建模成值对象



