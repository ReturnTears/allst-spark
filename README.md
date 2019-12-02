# Spark的复习

## Spark

```
spark-shell在spark中启动Spark的REPL的模式
在这里可以写Scala的命令行来操作Spark

集群相关基础概念

Application : 建立再Spark上的应用程序, 由一个Driver程序和集群上的Executor组成

Application jar : 一个包含用户Spark应用程序的Jar包, 在某些情况下, 包含应用程序的依赖包(不包含在运行时会被加入的Hadoop或Spark库)

Driver program : 驱动程序, 运行main函数并创建SparkContext的进程

Cluster manager : 管理集群资源的外部服务(独立模式管理器, Mesos, YARN等)

Deploye node : 决定在何处运行Driver进程的部署模式, 分为Cluster和Client两种模式

Worker node : 集群中运行应用程序的节点

Executor : 应用程序在Worker节点上启动的进程, 该进程执行任务并保持数据在内存或磁盘中

Task : 被发送到某个Executor的一个工作单元

Job : 作业, 一个Job包含多个RDD及作用于RDD上的各种Operation

Stage : 阶段, 每个Job都会被分解为多个相互依赖的任务集合 

RDD : 弹性分布式数据集

Operation : 作用于RDD的各种操作,分为Transformation和Action

Partition : 数据分区, 一个RDD中的数据可以分成多个不同的分区

DAG :有向无环图，　反映RDD之间的依赖关系

Narrow dependency : 窄依赖, 子RDD依赖父RDD中固定的数据分区

Wide dependency : 宽依赖,子RDD对父RDD中的所有数据分区都有依赖

Caching management : 缓存管理, 对RDD的中间计算结果进行缓存管理, 以加快整体的处理速度































```
## Code
```
va包及其子包都是存放Java语言开发的程序
ca包及其子包都是存放Scala语言开发的程序
local包用于运行在Windows10本地的Hadoop和Spark环境中
master包用于运行在Centos7下Hadoop和Spark集群环境中
```