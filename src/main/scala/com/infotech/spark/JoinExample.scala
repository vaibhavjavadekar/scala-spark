package com.infotech.spark

import org.apache.spark.SparkContext

object JoinExample {

  def main(args: Array[String]) {

    val sc = new SparkContext("local[*]", "JoinExample");
    //val emp = sc.parallelize(Seq((1,"jordan",10), (2,"ricky",20), (3,"matt",30), (4,"mince",35), (5,"rhonda",30)))
    val emp = sc.parallelize(Seq(("jordan", 10), ("ricky", 20), ("matt", 30), ("mince", 35), ("rhonda", 30)))

    val dept = sc.parallelize(Seq(("hadoop", 10), ("spark", 20), ("hive", 30), ("sqoop", 40)))

    val empM = emp.keyBy(t => t._2)
    val deptM = dept.keyBy(t => t._2)
    
    
    //empM.join(deptM).collect().foreach(System.out.println)
    empM.rightOuterJoin(deptM).collect().foreach(System.out.println)

    //val shifted_fields_emp = emp.map(t => (t._2, t._1))

    //val shifted_fields_dept = dept.map(t => (t._2, t._1))
    
    //shifted_fields_emp.join(shifted_fields_dept).collect().foreach(System.out.println)

  }
}