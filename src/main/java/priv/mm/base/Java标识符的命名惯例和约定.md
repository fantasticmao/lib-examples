分类 | 命令惯例和约定 | 示例
--- | --- | ---
包 | - 采用“从大到小”的方式，与网络域名相反。<br>- 每级包名都用小写字母。<br>- 若由多个单词组成，则直接连接在一起。 | org.springframework.context、<br>com.mycompany.mysoft
类 | - 使用“大驼峰”表示法。<br>- 尽量使用名词结尾。 | Account、MobilePhone、VipCustomer
接口 | - 使用“大驼峰”表示法。<br>- 通常以“able”结尾，有时以大写字母I（interface）开头。 | Drawable、IShape、MouseListener
方法 | - 使用“小驼峰”表示法。<br>- 第一个单词一般是动词。<br>- 若是取值型（读）方法，一般以get开头。<br>- 若取的值是boolean型，一般以is开头。<br>- 若是设置型（写）方法，一般以set开头。 | deleteStudent、getUserName、isFemale、setParentNode
字段和局部变量 | - 使用“小驼峰”表示法。<br>- 命名应能体现变量值的意义。<br>- 短名称一般只用于局部变量且是基本类型。<br>- 若变量是数组或容器，则应使用单词的复数形式。<br>- 组件型变量也可以采用“匈牙利”表示法：类型缩写前缀+变量描述，如btnLogin、dlgDeletion、frmLogin等。 | studentWithMaxAge、allMaleEmployees、loginDialog、okButton
常量 | - 全部大写，多个单词以下划线隔开 | PI、WINDOW_HEIGHT