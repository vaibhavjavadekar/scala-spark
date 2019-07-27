package com.infotech.helloworld

object SetDemo {
  
  val set : Set[Int] = Set(1,2,3,3,4)
  val setStr : Set[String] = Set("a", "b", "b", "d", "f")
  var setm : Set[Int] = Set(1,2,3,3,4)
  
  def main(args:Array[String]){
  
    println(set)
    println(setStr)
    
    println(setm(3))
    println(setStr("a"))
    
  }
}