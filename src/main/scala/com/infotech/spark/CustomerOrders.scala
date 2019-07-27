package com.infotech.spark

import org.apache.spark.SparkContext

object CustomerOrders {
  
  def main(args: Array[String]){
  
    val context = new SparkContext("local[1]", "CustomerOrders");
    
    val orderData = context.textFile("in/customer-orders.csv");
    
    val customerSpent = orderData.map(text => (text.split(",")(0).toInt, text.split(",")(2).toFloat)).reduceByKey((x,y) => x+y)
    
    val spentCustomer = customerSpent.map(text => (text._2, text._1)).sortByKey(true)
    
    spentCustomer.foreach(println)
  }
  
  
}