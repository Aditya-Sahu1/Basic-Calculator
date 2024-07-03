package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

private  var s:String=""
private var answer="123"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
           if(answer!="0") ans.text= answer
            else ans.text=disp.text
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
        }

        fun solve(s: String): Int {
            var ans = 0
            var i = 0
            while (s[i] != '+' && s[i] != '-')
                i++
            var k = 0
            for (p in 0 until i)
                k = k * 10 + (s[p] - '0')
            ans = k
            k = 0
            while (i < s.length - 1) {
                var e = i + 1
                k = 0
                while (s[e] != '+' && s[e] != '-')
                    e++
                for (p in i + 1 until e)
                    k = k * 10 + (s[p] - '0')

                if (s[i] == '+')
                    ans += k
                else
                    ans -= k
                i = e
            }
            return ans
        }

        fun calculate(s: String): Int? {
            val st = mutableListOf<Int>()
            var copy = ""
            for (i in s.indices) {
                if (s[i] != ' ')
                    copy += s[i]
            }
            var s = copy
            copy = ""
            for (i in s.indices) {
                val c = s[i]
                if (c == '(')
                    if (i == 0)
                        st.add(1)
                    else if (i >= 1 && s[i - 1] == '-')
                        if (st.size > 0)
                            st.add(-1 * st.last())
                        else
                            st.add(-1)

                    else if (st.size > 0)
                        st.add(st.last())
                    else
                        st.add(1)

                else if (c == ')')
                    st.removeAt(st.size - 1)
                else {
                    var t = 0
                    if (st.size > 0)
                        t = st.last()
                    if (c == '+' && t < 0)
                        copy += '-'
                    else if (c == '-' && t < 0)
                        copy += '+'
                    else
                        copy += c
                }
            }
            copy = "$copy+"
            return solve(copy)
        }

        equal.setOnClickListener(){

            answer =calculate(s).toString()
            ans.text= answer
            answer =""
            s =""

        }





    }
}