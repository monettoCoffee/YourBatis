# YourBatis

仿MyBatis实现简易ORM。仅供学习参考。

更完善的ORM请参考 https://github.com/monettoCoffee/EELinker

1.弱引用队列创建SqlSessionFactoryBuilder。

2.通过SqlSessionFactoryBuilder创建SqlSessioFactory,并读取config.xml文件,记录在BatisConfig。

3.初始化连接池。

4.A.1.创建Dao类继承SqlSesseionDaoSupport。

4.A.2.使用Dao类getSqlSession.selectOne方法指定mapper.xml中的ID,传入参数进行查询。

4.A.3.SqlSession标记使用状态,执行器获取Connection,并执行查询。

第二种方法

4.B.1.创建Mapper接口,预留指定方法名。

4.B.2.使用SqlSessionFactory获取Mapper的代理类。

4.B.3.使用Mapper引用代理类,通过invoke方法进行查询。

4.B.4.Mapeer调用内部SqlSession,同4.A.3。

5.查询完毕后标记SqlSession的使用状态。

6.使用反射通过resultType生成实例。
