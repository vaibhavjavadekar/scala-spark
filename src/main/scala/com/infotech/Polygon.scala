package com.infotech

abstract class Polygon {
  def area: Double;
}


trait Shape{
  def color: String;
}
object Polygon{
 
  def main(args: Array[String]){
    //var poly = new Polygon;
    //printArea("Polygon ", poly);
    
    val rect = new Rectangle(10.1, 20.2);
    printArea("Rectangle ",  rect)
    println(rect.color)
    
    val tri = new Triangle(10.1, 20.2);
    printArea("Triangle ",  tri);
    println(tri.color)
  }
  
  def printArea(s: String, p: Polygon){
    println(s + p.area);
    
  }
}