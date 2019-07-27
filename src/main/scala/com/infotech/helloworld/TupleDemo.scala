package com.infotech.helloworld

object TupleDemo {
  
  val tuple =  (10,"12121", "fwdfhkw", 'w', true)
  val tuple2 =  new Tuple3(10,"12121", 'w')
  
  def main(args:Array[String]){
  
    println(tuple._4)
    println(tuple2._2)
    
    tuple.productIterator.foreach(println)
    
    
       
  }
}