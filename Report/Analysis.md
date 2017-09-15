## <center>  Design Analysis Report </center>
######  <center> Project Group 16 </center>
<center> Shuyuan Dang 840992 </center>
<center> Feng Zhao 838219</center>
<center> Hongyi Chen 822666</center>


- **Introduction**  
 This program mainly provide an simulation of AutoMail bot system. However，the modeling of program, that is, association among classes have some shortcomings, as a result, the coupling among classes is high and cohesion within each class is low. We will focus on GRASP patterns to analyze the whole program starting from the reponsibility of each class, to association between classes.

- **Responsibilities of each class**
    - _ExcessiveDeliveryException_, _MailAlreadyDeliveredException_, _TubeFullException_, _Building_, _Clock_
    These 5 classes will not be analyzed because these are error exceptions, static variable carrier which will not affect the design of program.

    - _Simulation_
    This class works as a controller with a main function, which means it is also a driver of the whole program. It is also a creator with the creation of many objects, such as _Automail_, _MailGenerator_. However, _ReportDelivery_ class implementing IMailDelivery interface is defined in this class, which will increase the coupling by using variables of _Simulation_ class and reduce the cohesion because _ReportDelivery_ describes the behaviour of deliver, which is an individual further extendible function. Furthermore, the function calculateDeliveryScore should be extracted from _Simulation_ because it describes the behaviour of calculation of the score representing the usability of system.

    - _MailGenerator_
      This class works as an information expert with high cohesion and creator to create _MailItem_.

    - _Robot_
      This class works as information expert, major in describing the behaviour of Robot. This class include a state machine, describing different triggers for different actions. It receives several parameters, such as _ReportDelivery_, _IMailPool_ and _IRobotBehaviour_

    - _StorageTube_
      This class describes the storagetube carried by robot, and works as an information expert. However, it has default max capacity which do not correspond to different robot behaviour very well.

    - _Automail_
    This class works as creator, which creates three objects of class: _IMailPool_, _IRobotBehaviour_, and _Robot_. Automail act as a core product of this extendible system. It increase the coupling between classes, however, we plan not to remove this class because it eahance integrity of strategies package which is further modifiable and build indirection with Simulation class, working as an information expert as well. However, _Robot_ should not be created here because it belongs to automail package, which is not further extendible. This will increase the coupling between packages.

    - _MailPool_, _IMailPool_
    MailPool, which is an information expert, implements IMailPool interface which is also further extendible. Although there exists 2 classes in MailPool class,

    - _SimpleRobotBehaviour_, _SmartRobotBehaviour_, IRobotBehaviour
    This 2 classes describes two different behaviours of robot implementing interface IRobotBehaviour which is further modifiable and it is good to create this in _Automail_ class.
