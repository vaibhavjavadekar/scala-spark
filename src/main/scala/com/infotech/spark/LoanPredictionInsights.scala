package com.infotech.spark

import org.apache.spark.sql.SparkSession
import org.apache.spark.SparkContext._

object LoanPredictionInsights {
  
  def main(args: Array[String]){
    
    val session = SparkSession.builder().
    appName("LoanPredictionInsights")
    .master("local[1]")
    .getOrCreate()
    
    val loanData = session.read.option("header", "true").
    format("com.databricks.spark.csv").
    option("delimiter", ",").
    option("inferSchema", "true").
    load("in/train_ctrUa4K.csv")
    
    
    loanData.show(2, truncate=false)
    //loanData.printSchema()
    //loanDataSet.groupBy("gender").avg("applicantincome").show()
    
  }
}