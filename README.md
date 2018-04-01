# SSH_test
### 只是个SSH的练习项目

----------

（在阿里云上面租了个服务器47.106.104.150）测试帐号：aaa 密码 iii

没做页面样式兼容

---------------------------------------------------------------------------------------------------------

#### 数据库中
暂定：

#####  用户作品的存放路径：user\（字段）uid\（字段）wname\后十位时间戳+后缀

##### 作品图片的路径：WebRoot\user\（字段）workImg\（字段）uid+后十位时间戳+后缀

#####  书本的存放路径：manager\type（字段）\（字段）bname\后十位时间戳+后缀

##### 书本图片的路径：WebRoot\images\bookImg\后十位时间戳+后缀

---------------------------------------------------------------------------------------------------------

#### 备忘：(未实现)

如果放到阿里云的话 记得修改3个工具类（jedisUtils,bookUtils,workUtils）因为磁盘路径的问题（其实还有一些js的路径我就懒得改了）

ok:

~~制作**评论区**~~

~~**用户信息界面**~~

~~管理员**操作记录**~~

~~批量上传~~

~~cookie解决登录问题~~

~~更新通知~~(要做成网上那种的话需要重构存储方式以及读取方式（分成多个文件连续读取） 方便显示更新的部分)



**细节方面：**

主页：

> 书本分类更加详细
>
> 显示的分类字体添加a标签跳转到查询分类书本

评论区

> 评论的分页

其他：

> 访问其他用户的页面
>
> 读取文件的时候乱码
>
> 评论通知



#### **待定：**

> 由于代码重心全偏向与action层以及dao层，service层几乎就是用来传数据的一个（多余的存在）所以有时间的话，重构一下项目
>
> 数据库的**触发器**（删除长时间未激活帐号）
>
> **随机**读一本书的功能
>
> 删减不必要代码（如hibernate持久态的数据不必要再进行update操作）
>
> 代码方法注释
>
> pagebean分页
>
> 前端。。算了不用框架了，不然就一新项目
>
> ~~**管理界面**的美化~~
>
> 
>

---------------------------------------------------------------------------------------------------------

***注意事项：*** **（指南？）**

**①**修改邮箱之后帐号便成未激活状态了，需要重新激活



**②**

#### ContentType对照:

##### .doc       application/msword

##### .docx      application/vnd.openxmlformats-officedocument.wordprocessingml.document

##### .txt       text/plain

##### .jpg/.jpeg image/jpeg



**③**上传的图片没有立刻显示：由于我图片是存到项目文件夹下面的，需要手动刷新项目才出现上传的文件。根据网上说得，好像是由于tomcat和eclipse的文件部署不太一样，eclipse下创建文件会立刻在tomcat那边也生成，而反之不会，这只要配置一下tomcat的server.xml文件即可：

在host标签内加入(privilege属性好像是核心？)

`<Context path="eclipse项目下的路径（例如/SSH_text/images）" docBase="相对于磁盘中的绝对路径" debug="0" reloadable="false" privilege="true"/>`

**④**hibernate配置文件多对一的时候通常在**一**的一方"**<set>**"标签里面配置**inverse="true"**，即控制反转，让“多”的一方来进行维护，并且 最重要的是，假如外键是**uid** 那么设置的时候**不是setUid()而是setUser()（User带uid）**。**以及通过实体类数据的时候是先getUser()再getUid()**。哇后面做操作记录的时候才发现 好坑啊这个hibernate.配置又麻烦，用也麻烦，还不如我直接敲sql语句呢啧啧啧。

**⑤**用ajax传json数据的时候不返回json数据  ajax的success函数不会执行

**⑥**ajax传数据并不会封装到struts2的属性里面

**⑦**aop增强里面转json字符串的tojson（Object object）方法要求实体类的属性中，将int,long,String,boolean类型的属性值放到最前面，因为只转这几类而且一旦判断不是就结束了，用于跳过hibernate配置的实体类类型的属性

**⑧**自定义标签（真强大 不过可惜现在趋向前后端分离了）

> **工具类：**
>
> *1.新建工具类（timeTag）继承TagSupport*
>
> *2.设置参数value并生成set方法*
>
> *3.实现doStartTag()方法，其中，最种结果通过pageContext.getOut().write()方法显示到页面上 （最后return super.doStartTag()）*
>
> 配置文件（tld文件和web.xml）：
>
> **tld文件（datetag.tld）：**
>
> `<taglib>`
> 	 <tlib-version>1.0</tlib-version>
> 	 <jsp-version>1.2</jsp-version>
> 	 <short-name>date</short-name>
> 	<tag>
> 	     <name>date</name>
> 	     <tag-class>zhku.jsj141.utils.user.timeTag</tag-class>
> 	     <body-content>JSP</body-content>
> 	     <attribute>
> 	         <name>value</name>
> 	         <required>true</required>
> 	         <rtexprvalue>true</rtexprvalue>
> 	     </attribute>
> 	</tag>
> `</taglib>`
>
> **web.xml:**
>
> 	<jsp-config>
> 		<taglib>
> 			<taglib-uri>/mytags</taglib-uri>
> 			<taglib-location>/WEB-INF/tld/datetag.tld</taglib-location>
> 		</taglib>
> 	</jsp-config>
>

**⑨**在子div加上 onclick="event.cancelBubble = true"不触发父div的onclick

**⑩**web.xml配置spring自带过滤器解决hibernate延迟加载出现的访问外键所在实体其他属性时报错的问题。。

**web.xml**:

> 	<filter>
> 		<filter-name>OpenSessionInViewFilter</filter-name>
> 		<filter-class>org.springframework.orm.hibernate5.support.OpenSessionInViewFilter</filter-class>
> 		<init-param>
> 			<param-name>sessionFactoryBeanName</param-name>
> 			<param-value>sessionFactory</param-value>
> 		</init-param>
> 		<init-param>
> 			<param-name>singleSession</param-name>
> 			<param-value>true</param-value>
> 		</init-param>
> 		<init-param>
> 			<param-name>flushMode</param-name>
> 			<param-value>AUTO</param-value>
> 		</init-param>
> 	</filter> 
> 	<filter-mapping>  
> 		<filter-name>OpenSessionInViewFilter</filter-name>  
> 		<url-pattern>/*</url-pattern>  
> 	</filter-mapping>

**⑪**hql语句是根据实体类的，和sql语句不一样 特别是外键查询的时候直接.出来就好

例如要查order(实体类)表中user(实体类)的age为18的order信息：今天看到报错才悟了。。之前被坑的好惨。

`hibernateTemplate.find("from order where order.user.age=18");`

***hibernate配好表和表之间的关系之后可以直接从实体点出来***（还查个鬼。。。）

> user.favour.work.user.uid(用户收藏的作品的作者ID)

涨姿势：

mybatis和hibernate

hibernate相对与mybatis:（对象型数据库和关系型数据库）

1.前者相当于在hibernate创建一个数据库（POJO之间好像），而mybatis则直接编写sql语句（当然 hibernate也可以直接编写sql语句（nativeSQL），不通过hibernate的缓存 这样也可以节省资源，不过这就破坏了hibernate本身的orm思想（不用管数据库的详细细节，直接操作POJO（由模版查询，输入简单的查询语句，直接获取关联对象）））

2.hibernate关联查询的时候为了保证POJO的完整性，会将外键实体（关联的对象）的全部属性都读出来，而mybatis则可以自己控制没有额外的数据，减轻了存储

3.hibernate对数据对象实现较为严密的封装，而mybatis则是半封闭的封装

4.一个重量 一个轻量+解耦合（可以让更懂sql的人去bi编写数据库层的操作代码，然后提供一个接口让service调用即刻 好像还可以动态代理（只编写dao接口和mybatis的xml，在spring配置开启扫描就可以动态生成impl类来调用））

5.hibernate的延迟加载好像不比mybatis差？

6.等等

**⑫**在自定义Struts2拦截器里，获取request：（方法中invocation这个参数已经有提供）

> `HttpServletRequest request = (HttpServletRequest)invocation.getInvocationContext().get(StrutsStatics.HTTP_REQUEST);`

-------------------

**随录**

~~**①**想不到时间戳的问题搞了我这么久，最终决定，书本的时间戳只存年月日以及时（以免丢失精度）部分。前后端的时间戳转换简直有毒，特别是前端怎么在c:foreach标签里处理后台传来的时间戳，一开始还打算交给后台处理，但是如果要改bean，那数据库的表岂不是也要多出来年月日？浪费资源，假如是action处理，那岂不是要遍历list？而且怎么修改时间戳？多了几个字段？还是再存到map里面，想想都浪费时间。所以还是交给前端搞好了。然后发现jsp是服务器这边的，js脚本的调用是客户端的，怎么在c:foreach标签里面处理时间戳？woc瞬间懵了。onload是不可能的了，就只能将就着用onmouseover了，然后根据几个参数遍历显示出来的书本的时间戳，然后js转化并显示在前端的input框。折中的办法...暂时找不到更好的。~~

**②**我....突然间myeclipse的git插件跪了？报cannot open git-upload-pack按照网上说得搞了还是不行，最终还是用一早下载的git插件push成功了，什么鬼。

**③**我发现好像将utils类放到service层比较好，配置得简便点，调用service层就可以操作读写了，不过都已经配置到action层了就。。。

**④**交互的东西越想越多细节...要死

**⑤**突然发现记住bootstrap的样式名就不用敲CSS敲个半死。。特别是下拉列表这种要写jq的。。还有struts2的模型驱动等等的数据封装我没有搞。。直接通过json和request取了 好像很不规范 明早 哦不 睡醒就改回去吧。。

**⑥**呸 忘了属性驱动和模型驱动了 加上配置了baseAction 瞬间少了很多重复代码 ognl就算了 el已经写了一堆 就不改了

**⑦**打算用spring的aop来做操作记录

**⑧**简直有毒。。。打算随随便便做个操作记录的增强就算了的，又想做得不那么机械。。然后就遇到了一堆问题。除了spring注入的配置，事务的配置之外，最恶心的还是fastjson将实体类转jsonString的时候会把所有属性都转，没错，我是用hibernate的，也就是说连配置表和表之间关系而插入的实体类对象都转了，这就出问题了。。。我写的反射机制获取参数值就不能通用了，然后我就根据设计的实体类自己写了个绕过实体类对象属性生成json字符串的方法。是的，又被坑了时间。

**⑨**才发现原来jstl有标签可以将String类型的时间戳转成Date类型的字符串形式展示，不过long类型的还是无可奈何。于是根据网上的例子，自己也写了个自定义标签，实现了long类型转时间字符串的问题，解决了**①**的问题！！！！

**⑩**早知道套模版。。前端耗了好长时间，傻傻地敲样式。不过也好，知道和熟悉了很多的css和jquery实现功能的写法。加了个redis进来（jedis操作）打算弄个在线判断相关的功能（防止一个帐号多次登录以及删除用户时用于判定）嘛 又是一个坑就是了。

**⑪**~~判断在线很难搞啊~~ 监听器中session的attributeReplaced方法又不提供替换前的object只能暂定：

> 1.session创建时，(执行批处理文件+配置jedis)连接redis
>
> 2.session添加Attribute的时候如果是user，则添加“uid-登录时间“这样的键-值对进入redis的Login-time的hashMap集合
>
> 3.session替换Attribute的时候，假如是user，那么若Login_time表中有uid一样的则更新，无则添加
>
> 4.**当session创建、销毁、sessionAttribute取出、更新、添加的时候**更新redis的Login_time表，删除超过30分钟的键值对。（无奈之举）

所以在线判定会不准确，毕竟假如是替换用户的话，原先的用户又没有超过30分钟，那么也还是存留在redis中。。。诶 发现好像不用 只要登录的时候先将session中的user清空，再存，不就执行了delete和add了？关replace什么事 美滋滋(可行！！)

**⑫**呸 应该早点配置hibernate的session过滤器的（一早好像配过，配错了还是顺序问题。。），延迟加载的问题一开始就坑了我，还害我写多了多余的代码，现在加上去了可以直接访问外键所在实体的其他属性了、。。。（不想再改啊 知道就算了 下次先配好）

**⑬**好多不规范啊
1.注释
2.分页
myeclipse原来没有自动在实现方法上添加接口的注释或者反转，那我在接口上写就算了，毕竟调用的时候显示的是接口的。

ajax传数据的好处不仅前后端分离（访问的时候可以先看到静态数据（html的） 出错也不会显示），还可以像过滤器一样先处理数据再显示到页面。。jsp处理数据很不友好（js客户端访问不到session域（服务器的值）只能用jstl写好在jsp上js来获取，页面就有一些相对来说“多余”的数据，而如果改成后端改变传的数据 耦合高的不行）

**⑭**我傻了 直接用aop环绕增强来做权限管理不就不用写这么多判断session user的代码咯。。。（好吧 最后还是用struts2的拦截器实现了）