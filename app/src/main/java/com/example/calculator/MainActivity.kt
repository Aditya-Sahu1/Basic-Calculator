package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.Stack
import kotlin.math.ceil
import kotlin.math.floor


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
          var s:String=""
         var answer="0"

        var b1=findViewById<Button>(R.id.btn1)
        var b2=findViewById<Button>(R.id.btn2)
        var b3=findViewById<Button>(R.id.btn3)
        var b4=findViewById<Button>(R.id.btn4)
        var b5=findViewById<Button>(R.id.btn5)
        var b6=findViewById<Button>(R.id.btn6)
        var b7=findViewById<Button>(R.id.btn7)
        var b8=findViewById<Button>(R.id.btn8)
        var b9=findViewById<Button>(R.id.btn9)
        var b0=findViewById<Button>(R.id.btn0)
        var add=findViewById<Button>(R.id.add)
        var sub=findViewById<Button>(R.id.sub)
        var equal=findViewById<Button>(R.id.equal)
        var open=findViewById<Button>(R.id.open)
        var close=findViewById<Button>(R.id.close)
        var disp=findViewById<TextView>(R.id.exp)
        var ans=findViewById<TextView>(R.id.ans)
        var cut=findViewById<Button>(R.id.clear)
        var multiply=findViewById<Button>(R.id.mul)
        var divide=findViewById<Button>(R.id.div)
        var dot=findViewById<Button>(R.id.dec)
        var modulos=findViewById<Button>(R.id.mod)

        cut.setOnClickListener(){

            disp.text=""
            ans.text=""
            s =""
        }

        if(disp.text=="")
        {
            ans.text=""
        }
        disp.text=""
        ans.text=""

        equal.setOnClickListener(){
           if(answer!="0") {

               ans.text= answer
           }
            else {
                disp.text="0"
               ans.text = disp.text

           }
        }
        b0.setOnClickListener(){
            s +="0"
            disp.text= s
            ans.text=""

        }
        b1.setOnClickListener(){
            s +="1"
            disp.text= s
            ans.text=""

        }
        b2.setOnClickListener(){
            s +="2"
            disp.text= s
            ans.text=""
        }
        b3.setOnClickListener(){
            s +="3"
            disp.text= s
            ans.text=""
        }
        b4.setOnClickListener(){
            s +="4"
            disp.text= s
            ans.text=""
        }
        b5.setOnClickListener() {
            s += "5"
            disp.text = s
        }
        b6.setOnClickListener(){
            s +="6"
            disp.text= s
            ans.text=""
        }
        b7.setOnClickListener(){
            s +="7"
            disp.text= s
            ans.text=""
        }
        b8.setOnClickListener(){
            s +="8"
            disp.text= s
            ans.text=""
        }
        b9.setOnClickListener(){
            s +="9"
            disp.text= s
            ans.text=""
        }
        add.setOnClickListener(){
            s +="+"
            disp.text= s
            ans.text=""
        }
        sub.setOnClickListener(){
            s +="-"
            disp.text= s
            ans.text=""
        }
        multiply.setOnClickListener(){
            s +="*"
            disp.text= s
            ans.text=""
        }
        divide.setOnClickListener(){
            s +="/"
            disp.text= s
            ans.text=""
        }
        dot.setOnClickListener(){
            s +="."
            disp.text= s
            ans.text=""
        }
        modulos.setOnClickListener(){
            s +="%"
            disp.text= s
            ans.text=""
        }

        open.setOnClickListener(){
            s +="("
            disp.text= s
            ans.text=""
        }
        close.setOnClickListener(){
            s +=")"
            disp.text= s
            ans.text=""
        }
        ans.setOnClickListener(){

            ans.text= s
            disp.text=s
        }


        fun precedence(op: Char): Int {
            return when (op) {
                '+', '-' -> 1
                '*', '/', '%' -> 2
                '^' -> 3
                else -> -1
            }
        }

//        fun applyOperation(op: Char, b: Double, a: Double): Double {
//            return when (op) {
//                '+' -> a + b
//                '-' -> a - b
//                '*' -> a * b
//                '/' -> a / b
//                '%' -> a % b
//                '^' -> Math.pow(a, b)
//                else -> throw IllegalArgumentException("Invalid operator: $op")
//            }
//        }
fun applyOperation(op: Char, b: Double, a: Double): Double {
    return when (op) {
        '+' -> a + b
        '-' -> a - b
        '*' -> a * b
        '/' -> {
            if (b == 0.0) {
                throw ArithmeticException("Division by zero")
            }
            a / b
        }
        '%' -> a % b
        '^' -> Math.pow(a, b)
        else -> throw IllegalArgumentException("Invalid operator: $op")
    }
}


        fun infixToPostfix(expression: String): String {
            val result = StringBuilder()
            val stack = Stack<Char>()
            var i = 0

            while (i < expression.length) {
                val c = expression[i]

                when {
                    c.isWhitespace() -> i++
                    c.isDigit() || c == '.' -> {
                        while (i < expression.length && (expression[i].isDigit() || expression[i] == '.')) {
                            result.append(expression[i++])
                        }
                        result.append(' ')
                    }
                    c == '(' -> stack.push(c)
                    c == ')' -> {
                        while (stack.isNotEmpty() && stack.peek() != '(') {
                            result.append(stack.pop()).append(' ')
                        }
                        stack.pop()
                        i++
                    }
                    else -> {
                        while (stack.isNotEmpty() && precedence(c) <= precedence(stack.peek())) {
                            result.append(stack.pop()).append(' ')
                        }
                        stack.push(c)
                        i++
                    }
                }
            }

            while (stack.isNotEmpty()) {
                result.append(stack.pop()).append(' ')
            }

            return result.toString().trim()
        }

        fun evaluatePostfix(postfix: String): Double {
            val stack = Stack<Double>()
            val tokens = postfix.split("\\s+".toRegex())

            for (token in tokens) {
                if (token.isNotEmpty()) {
                    if (token[0].isDigit() || (token.length > 1 && token[0] == '-')) {
                        stack.push(token.toDouble())
                    } else {
                        val b = stack.pop()
                        val a = stack.pop()
                        stack.push(applyOperation(token[0], b, a))
                    }
                }
            }

            return stack.pop()
        }

//        fun evaluateInfix(expression: String): Double {
//            val postfix = infixToPostfix(expression)
//            return evaluatePostfix(postfix)
//        }
        fun evaluateInfix(expression: String): Double {
            if(expression=="")return 0.0
            return try {
                val postfix = infixToPostfix(expression)
                evaluatePostfix(postfix)
            } catch (e: ArithmeticException) {
                disp.text = "Error"
                0.0
            } catch (e: IllegalArgumentException) {
                disp.text = "Error"
                0.0
            } catch (e: Exception) {
                disp.text = "Error"
                0.0
            }
        }


        equal.setOnClickListener(){

            var  res =evaluateInfix(s)
            if(res == res.toInt().toDouble()) answer= res.toInt().toString()
            else answer=res.toString()

            ans.text= answer
            disp.text=s
//            answer =""
            s =""
//            ans.text= answer
//            disp.text=s

        }





    }
}
