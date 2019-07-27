package com.infotech.helloworld

object MapDemo {
  
  val map : Map[Int, String] = Map(801 -> "Max", 802 ->"Tom", 803 ->"Jun", 803 ->"Jun3"); 
  
  def main(args:Array[String]){
  
    println(map)
    
    println(map(803))
    
    // 913101863
    
    map.keys.foreach(key => println(key + ">" + map(key)))
    
    
    val opt : Option[String] = Some("test");
    
    println(map.get(100).getOrElse(opt))
       
  }
}