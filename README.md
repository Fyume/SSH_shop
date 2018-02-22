# SSH_shop
### 只是个SSH的练习项目

---------------------------------------------------------------------------------------------------------

#### 数据库中
暂定：

#####  用户作品的存放路径：user\uid\bname\后十位时间戳+后缀

##### 作品图片的路径：WebRoot\user\workImg\uid+后十位时间戳+后缀

#####  书本的存放路径：manager\type\bname\后十位时间戳+后缀

##### 书本图片的路径：WebRoot\images\bookImg\后十位时间戳+后缀

---------------------------------------------------------------------------------------------------------

#### 备忘：(未实现)

数据库的**触发器**（删除长时间未激活帐号）

制作**评论区**

**管理界面**的美化

**随机**读一本书的功能

**用户信息界面**

---------------------------------------------------------------------------------------------------------

***注意事项：***

①修改邮箱之后帐号便成未激活状态了，需要重新激活

②

#### ContentType对照:

##### .doc       application/msword

##### .docx      application/vnd.openxmlformats-officedocument.wordprocessingml.document

##### .txt       text/plain

##### .jpg/.jpeg image/jpeg



③上传的图片没有立刻显示：由于我图片是存到项目文件夹下面的，需要手动刷新项目才出现上传的文件。根据网上说得，好像是由于tomcat和eclipse的文件部署不太一样，eclipse下创建文件会立刻在tomcat那边也生成，而反之不会，这只要配置一下tomcat的server.xml文件即可：

在host标签内加入

`<Context path="eclipse项目下的路径（例如/SSH_text/images）" docBase="相对于磁盘中的绝对路径" debug="0" reloadable="false" privilege="true"/>`

④