package com.atguigu.scala.test01

/**
 * 匹配类型
 */
object TestMatchClass {
    def main(args: Array[String]): Unit = {
        //泛型擦除
        println(desc(List(1, 2, 3, 4, 5)))

        //数组例外，可保留泛型
        println(desc(Array(1, 2, 3, 4, 5, 6)))
        println(desc(Array("abc")))
        println(desc())

    }
    //TODO 匹配类型的时候，不会匹配泛型
    def desc(x:Any) = x match {
        case i:Int=>"Int"
        case s:String=>"Int"
        case l:List[String]=>"List"
        case c:Array[Int]=>"Array[Int]"
        case soemthing => "soemthing:"+soemthing
    }
}
