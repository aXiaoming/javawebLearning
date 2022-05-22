对FruitDAOImpl中的获取连接操作以及释放连接操作做了提取，放到了BaseDao抽象类中

addFruit:
1.获取连接
2.编写sql ：insert
3.psmt
4.填充参数 ： 4个参数
5.执行更新
6.释放资源


updateFruit:
1.获取连接
2.编写sql ： update
3.psmt
4.填充参数 ： 2个参数
5.执行更新
6.释放资源

增删改操作，里面的步骤是一摸一样的，只是里面的sql与参数有区别
所以，增删改操作可以抽象成一个更新操作，sql是传进来的，参数是传进来的
在BaseDao中进行了操作
Impl对方法进行了修改

