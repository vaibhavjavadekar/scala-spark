package com.infotech.helloworld

object ArrayDemo {
  
  val arr : Array[Int] = new Array[Int](2);
  
  val arr2  = new Array[Int](2);
  
  val arr3 = Array(5,7)
  
  def main(args:Array[String]){
    arr(0) = 2;
    arr(1) = 3;
    
    println(arr);
    
    
    
    val arr4 = Array.concat(arr, arr3)
    
    for(x <- arr4){
      println(x)
    }
    
  }
}